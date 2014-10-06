package ar.com.estigiait.ds.ui;

import java.awt.BorderLayout;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Tabla dinamica 
 * @author Emilio Watemberg
 *
 */
@SuppressWarnings("serial")
public class TablePanel{
	
	   private JPanel mainPanel = new JPanel();
	   private DefaultTableModel dm;
	   private JTable table = new JTable();
	   private JButton addRowTable = new JButton();
	   private JScrollPane scrollpane = new JScrollPane(table);

	   public TablePanel(String title, Object[][] data, Object[] columnNames) {
	      dm = new DefaultTableModel(data, columnNames);
	      table.setModel(dm);
	      JPanel btnPanel = new JPanel();
	      btnPanel.add(addRowTable);

	      mainPanel.setBorder(BorderFactory.createTitledBorder(title));
	      mainPanel.setLayout(new BorderLayout(1, 1));
	      mainPanel.add(scrollpane, BorderLayout.CENTER);
	      mainPanel.add(btnPanel, BorderLayout.PAGE_END);
	   }

	   public void setButtonAction(Action action) {
		   addRowTable.setAction(action);
	   }

	   public void setTableModelDataVector(Object[][] data, Object[] columnNames) {
	      dm.setDataVector(data, columnNames);
	   }

	   public void fireTableDataChanged() {
	      dm.fireTableDataChanged();
	   }

	   public JScrollPane getScrollPane() {
	      return scrollpane;
	   }

	   public JComponent getMainPanel() {
	      return mainPanel;
	   }

}
