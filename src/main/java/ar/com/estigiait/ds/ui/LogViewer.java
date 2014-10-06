package ar.com.estigiait.ds.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Position.Bias;
import javax.swing.text.View;

import ar.com.estigiait.ds.tool.Constants;
import ar.com.estigiait.ds.tool.Util;

public class LogViewer {

    private static int MAX = 7;

    static public void main( String[] s ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, IOException {

        UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );

        JFrame frame = new JFrame();
        frame.setBounds( 50, 50, 1000, 700 );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        final JTextPane pane = new JTextPane();

        URL fileURL = Util.class.getResource("/digital-signature.log");	
 		System.out.println(fileURL);
 		InputStream input =  fileURL.openStream();
 		
        
        pane.setText(Util.getStringFromInputStream(input));

        JPanel pnl = new JPanel(new BorderLayout());
        pnl.add( pane, BorderLayout.CENTER );

        pane.getDocument().addDocumentListener( new DocumentListener() {
            public void removeUpdate( DocumentEvent e ) {
            }
            public void insertUpdate( DocumentEvent e ) {
                SwingUtilities.invokeLater( new Runnable() {
                    public void run() {
                        try {
                            View baseView = pane.getUI().getRootView( pane );
                            View root = baseView.getView(0);
                            for( int i = 0; i < root.getViewCount()-MAX; i++ ) {
                                int line = root.getViewIndex( i, Bias.Forward );
                                View lineview = root.getView(line);
                                pane.getDocument().remove( lineview.getStartOffset(), lineview.getEndOffset() );
                            }
                        } catch( BadLocationException e1 ) {
                            e1.printStackTrace();
                        }
                    }
                } );
            }
            public void changedUpdate( DocumentEvent e ) {
            }
        });

//        pnl.add(new JButton(new AbstractAction("Delete") {
//            public void actionPerformed( ActionEvent e ) {
//               try {
//                   View baseView = pane.getUI().getRootView( pane );
//                   View root = baseView.getView(0);
//                   int line = root.getViewIndex( 0, Bias.Forward );
//                   View lineview = root.getView(line);
//                   pane.getDocument().remove( lineview.getStartOffset(), lineview.getEndOffset() );
//               } catch( BadLocationException e1 ) {
//                   e1.printStackTrace();
//               }
//            }
//        }), BorderLayout.SOUTH);

//        pnl.add(new JButton(new AbstractAction("Add") {
//            public void actionPerformed( ActionEvent e ) {
//                try {
//                    pane.getDocument().insertString(pane.getDocument().getEndPosition().getOffset(), new SimpleDateFormat("ss").format( new Date() )+": This is a new line\n", null);
//                } catch( BadLocationException e1 ) {
//                    e1.printStackTrace();
//                } 
//            }
//        }), BorderLayout.NORTH);
        frame.setContentPane( pnl );
        frame.setVisible( true );
    }
}