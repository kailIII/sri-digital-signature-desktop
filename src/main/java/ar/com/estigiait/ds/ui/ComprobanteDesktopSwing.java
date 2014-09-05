package ar.com.estigiait.ui;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class ComprobanteDesktopSwing extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ComprobanteDesktopSwing() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Se crea un boton para abrir el archivo
		JButton btn = new JButton("Origen");
		btn.setBounds(15, 125, 121, 25);
		btn.addActionListener(this);
		getContentPane().setLayout(null);
		getContentPane().add(btn);
		// Se crea el editor de texto y se agrega a un scroll
		txp = new JTextPane();
		txp.setEditable(false);
		txp.setBounds(166, 125, 255, 25);
		getContentPane().add(txp);
		getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btn}));
		
		
		
		
		JButton btn1 = new JButton("Destino");
		btn1.setBounds(15, 163, 121, 25);
		btn1.addActionListener(this);
		getContentPane().setLayout(null);
		getContentPane().add(btn1);
		// Se crea el editor de texto y se agrega a un scroll
	
		txp1 = new JTextPane();
		txp1.setEditable(false);
		txp1.setBounds(166, 162, 255, 25);
		
		getContentPane().add(txp1);
		
		JButton btn2 = new JButton("Certificado");
		btn2.setBounds(15, 199, 121, 25);
		getContentPane().add(btn2);
		
		JTextPane txp2 = new JTextPane();
		txp2.setEditable(false);
		txp2.setBounds(166, 199, 255, 25);
		getContentPane().add(txp2);
		
		JLabel titleLabel = new JLabel("Seleccione:");
		titleLabel.setFont(new Font("DejaVu Sans Condensed", Font.BOLD | Font.ITALIC, 16));
		titleLabel.setToolTipText("");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(183, 67, 146, 15);
		getContentPane().add(titleLabel);
		
		JTextPane textPanelogs = new JTextPane();
		textPanelogs.setBounds(96, 258, 415, 152);
		getContentPane().add(textPanelogs);
		
		JButton btnNewButton = new JButton("Listo");
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton.setBounds(148, 450, 293, 56);
		getContentPane().add(btnNewButton);
		getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btn1}));
		
		
		setTitle("Estigia IT");
		// Tamaño de la ventana
		setSize(600, 600);
		// Esto sirve para centrar la ventana
		setLocationRelativeTo(null);
		// Hacemos visible la ventana
		setVisible(true);
	}

	// ------------------------------Action
	// Performed-------------------------------//
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if (btn.getText().equals("Origen") || btn.getText().equals("Destino") || btn.getText().equals("Certificado")) {
			if (abrirArchivo == null)
				abrirArchivo = new JFileChooser();
			// Con esto solamente podamos abrir archivos
			abrirArchivo.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			int seleccion = abrirArchivo.showOpenDialog(this);
			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File f = abrirArchivo.getSelectedFile();
				try {
					String nombre = f.getName();
					String path = f.getAbsolutePath();
					String contenido = getArchivo(path);
					// En el editor de texto colocamos su contenido
					if (btn.getText().equals("Origen")){
						txp.setText(path);
					}else if (btn.getText().equals("Destino")){
						txp1.setText(path);
					}else {
						txp2.setText(path);
					}
					
				} catch (Exception exp) {
				}
			}
		}
	}

	// -----------------------------------------------------------------------------//
	// -------------------------Se obtiene el contenido del
	// Archivo----------------//
	public String getArchivo(String ruta) {
		FileReader fr = null;
		BufferedReader br = null;
		// Cadena de texto donde se guardara el contenido del archivo
		String contenido = "";
		try {
			// ruta puede ser de tipo String o tipo File
			fr = new FileReader(ruta);
			br = new BufferedReader(fr);
			String linea;
			// Obtenemos el contenido del archivo linea por linea
			while ((linea = br.readLine()) != null) {
				contenido += linea + "\n";
			}
		} catch (Exception e) {
		}
		finally {
			try {
				br.close();
			} catch (Exception ex) {
			}
		}
		return contenido;
	}

	// -----------------------------------------------------------------------------//
	public static void main(String[] arg) {
		try {
			// Cambiamos el Look&Feel
			JFrame.setDefaultLookAndFeelDecorated(true);
			UIManager
					.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
		} catch (Exception e) {
		}
		new ComprobanteDesktopSwing();
	}

	JTextPane txp;
	JTextPane txp1;
	JTextPane txp2;
	JFileChooser abrirArchivo;
}