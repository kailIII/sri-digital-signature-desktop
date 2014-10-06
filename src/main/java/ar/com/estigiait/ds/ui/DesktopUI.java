package ar.com.estigiait.ds.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.apache.camel.main.Main;

import ar.com.estigiait.ds.route.FileRoute;
import ar.com.estigiait.ds.tool.Constants;
import ar.com.estigiait.ds.tool.Util;

public class DesktopUI extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JTextPane textPaneOrigenTramite, textPaneDestinoTramite, textPaneOrigenCertificados;
	private JFileChooser abrirArchivo;
	private JScrollPane panelCompany;
	private JTable tableCompany;
	private JButton buttonOrigenTramite, buttonEjecutar, buttonAgregar, buttonLimpiar, buttonEliminar, buttonDestinoTramite, buttonOrigenCertificados;
	private JTextField inputCertificado, inputSociedad, inputPass;
	private DefaultTableModel model;
	private JLabel lblSeleccioneLa;
	private JPanel loadingPanel;
	
	public  org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(getClass());
	
	
	public DesktopUI() {
		setResizable(false);
		
		/**
		 * Propiedades del Frame
		 */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(".: Configuración ** Firma Digital :.");
		setSize(1124, 701);
		setLocationRelativeTo(null);
		
		/**
		 * Label Titulo Frame
		 */
		JLabel titleLabel = new JLabel("Firma Digital");
		titleLabel.setFont(new Font("DFKai-SB", Font.BOLD, 24));
		titleLabel.setToolTipText("");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(376, 27, 309, 25);
		getContentPane().add(titleLabel);
		
		
		/**
		 * Boton para seleccionar carpeta root del Origen de los tramites
		 */
		buttonOrigenTramite = new JButton("Directorio");
		buttonOrigenTramite.setBounds(106, 103, 121, 25);
		buttonOrigenTramite.addActionListener(this);
		getContentPane().setLayout(null);
		getContentPane().add(buttonOrigenTramite);
		
		/**
		 * Input folder root origen tramite
		 */
		textPaneOrigenTramite = new JTextPane();
		textPaneOrigenTramite.setEditable(false);
		textPaneOrigenTramite.setBounds(255, 103, 767, 25);
		textPaneOrigenTramite.setText("");
		getContentPane().add(textPaneOrigenTramite);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		
		//Carga la tabla de certificados
	    loadCertificateTable();
		
		panelCompany = new JScrollPane(tableCompany);
		panelCompany.setLocation(29, 339);
		panelCompany.setSize(690,188);
		
		/**
		 * Label sociedad
		 */
		JLabel lblCompany = new JLabel("Sociedad:");
		lblCompany.setBounds(766, 359, 91, 15);
		getContentPane().add(lblCompany);
		
		/**
		 * Input sociedad
		 */
		inputSociedad = new JTextField();
		inputSociedad.setBounds(869, 354, 178, 25);
		getContentPane().add(inputSociedad);
		inputSociedad.setColumns(10);
		getContentPane().add(panelCompany);
		
		/**
		 * Label certificado
		 */
		JLabel lblCertificate = new JLabel("Certificado:");
		lblCertificate.setBounds(766, 397, 91, 15);
		getContentPane().add(lblCertificate);
		
		/**
		 * Input certificado
		 */
		inputCertificado = new JTextField();
		inputCertificado.setBounds(869, 392, 178, 25);
		getContentPane().add(inputCertificado);
		inputCertificado.setColumns(10);
		getContentPane().add(panelCompany);
		
		/**
		 * Boton agregar fila
		 */
		buttonAgregar = new JButton("Agregar");
		buttonAgregar.setBounds(749, 486, 92, 25);
		buttonAgregar.addActionListener(this);
		getContentPane().add(buttonAgregar);
		
		/**
		 * Boton limpiar campos
		 */
		buttonLimpiar = new JButton("Limpiar");
		buttonLimpiar.setBounds(853, 486, 104, 25);
		buttonLimpiar.addActionListener(this);
		getContentPane().add(buttonLimpiar);
				
	    /**
	     * Boton ejecutar
	     */
		buttonEjecutar = new JButton("Ejecutar");
		buttonEjecutar.setFont(new Font("Dialog", Font.BOLD, 12));
		buttonEjecutar.setBounds(409, 557, 287, 43);
		buttonEjecutar.addActionListener(this);
		getContentPane().add(buttonEjecutar);
		
		/**
		 * Label 1)
		 */
		JLabel lblSeleccioneElDirectorio = new JLabel("1) Seleccione el directorio raiz donde se ecuentran los tramites a firmar:");
		lblSeleccioneElDirectorio.setBounds(71, 65, 603, 25);
		getContentPane().add(lblSeleccioneElDirectorio);
		
		/**
		 * Label 2)
		 */
		lblSeleccioneLa = new JLabel("2) Seleccione el directorio destino donde se ubicaran los tramites firmados:");
		lblSeleccioneLa.setBounds(71, 141, 603, 25);
		getContentPane().add(lblSeleccioneLa);
				
	    /**
	     * Boton seleccionar destino tramite
	     */
		buttonDestinoTramite = new JButton("Directorio");
		buttonDestinoTramite.addActionListener(this);
		buttonDestinoTramite.setBounds(106, 179, 121, 25);
		getContentPane().add(buttonDestinoTramite);
		
		/**
		 * TextPane destino tramites
		 */
		textPaneDestinoTramite = new JTextPane();
		textPaneDestinoTramite.setText("");
		textPaneDestinoTramite.setEditable(false);
		textPaneDestinoTramite.setBounds(255, 179, 767, 25);
		getContentPane().add(textPaneDestinoTramite);
		
		/**
		 * Label 3)
		 */
		JLabel lblSeleccioneEl = new JLabel("3) Seleccione el directorio origen donde se encuentran los certificados:");
		lblSeleccioneEl.setBounds(71, 217, 603, 25);
		getContentPane().add(lblSeleccioneEl);
		
		/**
		 * Boton directorio origen certificados
		 */
		buttonOrigenCertificados = new JButton("Directorio");
		buttonOrigenCertificados.addActionListener(this);
		buttonOrigenCertificados.setBounds(106, 259, 121, 25);
		getContentPane().add(buttonOrigenCertificados);
		
		/**
		 * TextPane origen certificados
		 */
		textPaneOrigenCertificados = new JTextPane();
		textPaneOrigenCertificados.setText("");
		textPaneOrigenCertificados.setEditable(false);
		textPaneOrigenCertificados.setBounds(255, 259, 767, 25);
		getContentPane().add(textPaneOrigenCertificados);
		
		inputPass = new JTextField();
		inputPass.setColumns(10);
		inputPass.setBounds(869, 430, 178, 25);
		getContentPane().add(inputPass);
		
		/**
		 * Label contraseña
		 */
		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setBounds(766, 438, 92, 15);
		getContentPane().add(lblContrasea);
		
		/**
		 * Barra progreso
		 */
		loadingPanel = new JPanel();
				loadingPanel.setBounds(270, 613, 587, 43);
				getContentPane().add(loadingPanel);

		
		/**
		 * Label 4)
		 */
		JLabel lblAsocieEl = new JLabel("4) Asocie el certificado a la sociedad que se desea firmar:");
		lblAsocieEl.setBounds(71, 301, 603, 25);
		getContentPane().add(lblAsocieEl);
		
		buttonEliminar = new JButton("Eliminar");
		buttonEliminar.setBounds(969, 486, 104, 25);
		buttonEliminar.addActionListener(this);
		getContentPane().add(buttonEliminar);
		
		
		//CARGAMOS LOS PARAMETROS INICIALES
		this.loadInitParameters();
			
		setVisible(true);
		
	}

	/**
	 * Tabla Company
	 */
		
	private void loadCertificateTable() {
		
		
		model = new DefaultTableModel();// definimos el objeto tableModel
		tableCompany = new JTable();// creamos la instancia de la tabla
		tableCompany.setModel(model);
		model.addColumn("Sociedad");
		model.addColumn("Certificado");
		model.addColumn("Contraseña");
		
	}
	
	/**
	 * Cargamos los propiedades iniciales iniciales
	 */
	private void loadInitParameters(){
		
		//las propiedades se leen /conf/signatures-conf.properties
		Properties conf = Util.getPropertiesLocal();
        
        //cargamos las propiedades en base al entorno de ejecucion -DEV/PROD-
        if (!conf.getProperty(Constants.MODE).equals(Constants.MODE_DEV)){
       	 conf = Util.getPropertiesExternal();
        }
		
		//directorio origen tramites
		textPaneOrigenTramite.setText(Util.getPathWithoutPrefixFile(conf.getProperty(Constants.FOLDER_ORIGIN)));
		
		//directorio destino tramites
		textPaneDestinoTramite.setText(Util.getPathWithoutPrefixFile(conf.getProperty(Constants.FOLDER_DESTINY)));
		
		//directorio origen certificados
		textPaneOrigenCertificados.setText(Util.getPathWithoutPrefixFile(conf.getProperty(Constants.CERTIFICATE_ORIGIN)));
				
		//cargamos la tabla
		loadRows(model, conf);
		
	}

	/**
	 * 
	 * @param model
	 * Load Rows Certificate Table
	 */
	private void loadRows(DefaultTableModel model, Properties prop) {
		//TODO Cargar las filas del Properties.
		
		Map<String, String> map = new HashMap<String, String>();
		
		for (final String name: prop.stringPropertyNames()){		
			if (name.contains("app.path.certificate.")){
				String[] fila = new String[3];
				fila[0] = name.replaceAll("app.path.certificate.", ""); //sociedad
		    	fila[1] = prop.getProperty(name);//certificado
		    	fila[2] = prop.getProperty(name.replaceAll("path", "pass"));//contraseña
		    	model.addRow(fila);
			}
			
		}	
	}

	/**
	 * 
	 * Action Events Button Seleccion de directorios
	 *
	 */

	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();

		//boton seleccionar origen tramites
		if (btn == buttonOrigenTramite) {
			if (abrirArchivo == null)
				abrirArchivo = new JFileChooser();
			abrirArchivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int seleccion = abrirArchivo.showOpenDialog(this);
			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File f = abrirArchivo.getSelectedFile();
				try {
					String nombre = f.getName();
					String path = f.getAbsolutePath();
					@SuppressWarnings("unused")
					String contenido = getArchivo(path);
					textPaneOrigenTramite.setText(path);

				} catch (Exception exp) {
					log.error(exp);
				}
			}
		}else if (btn == buttonDestinoTramite){
			if (abrirArchivo == null)
				abrirArchivo = new JFileChooser();
			abrirArchivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int seleccion = abrirArchivo.showOpenDialog(this);
			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File f = abrirArchivo.getSelectedFile();
				try {
					String nombre = f.getName();
					String path = f.getAbsolutePath();
					@SuppressWarnings("unused")
					String contenido = getArchivo(path);
					textPaneDestinoTramite.setText(path);

				} catch (Exception exp) {
					log.error(exp);
				}
			}
		}else if (btn == buttonOrigenCertificados){
			if (abrirArchivo == null)
				abrirArchivo = new JFileChooser();
			abrirArchivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int seleccion = abrirArchivo.showOpenDialog(this);
			if (seleccion == JFileChooser.APPROVE_OPTION) {
				File f = abrirArchivo.getSelectedFile();
				try {
					String nombre = f.getName();
					String path = f.getAbsolutePath();
					@SuppressWarnings("unused")
					String contenido = getArchivo(path);
					textPaneOrigenCertificados.setText(path);

				} catch (Exception exp) {
					log.error(exp);
				}
			}
		}else if (btn == buttonAgregar) {
			//Falta validar que los campos no sean null
			
			if (inputSociedad.getText().length()!=0 && inputCertificado.getText().length()!=0 && inputPass.getText().length() != 0){				
				String[] row = {inputSociedad.getText(), "/"+inputCertificado.getText(), inputPass.getText() };
				DefaultTableModel model = (DefaultTableModel) tableCompany.getModel();
				model.addRow(row);	
				cleanInputs();
			}else{
				JOptionPane.showMessageDialog(this, "La sociedad, el certificado y la contraseña son datos obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else if (btn == buttonLimpiar) {
			cleanInputs();				
		}else if (btn == buttonEliminar) {
			int row = tableCompany.getSelectedRow();
			if(row != -1){
				model = (DefaultTableModel) tableCompany.getModel();
				model.removeRow(row);
				log.info("Se elimino la fila: " + row);
			} else { 
				JOptionPane.showMessageDialog(this, "No se seleccionó ninguna fila de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}else if (btn == buttonEjecutar) {
	   		
			ImageIcon loading = new ImageIcon(getClass().getResource("/bar-loader.gif").getPath());
	   		loadingPanel.add(new JLabel(loading));
	   		loadingPanel.setVisible(true);
			
			//las propiedades se leen /conf/signatures-conf.properties
			Properties conf = Util.getPropertiesLocal();
	        
	        //cargamos las propiedades en base al entorno de ejecucion -DEV/PROD-
	        if (!conf.getProperty(Constants.MODE).equals(Constants.MODE_DEV)){
	       	 conf = Util.getPropertiesExternal();
	        }
	        
	        conf.clear();	        
	        //propiedad obligatoria
	        conf.setProperty(Constants.MODE, "PROD");
			
			//Validamos que no falten datos obligatorios
	        if ((textPaneOrigenTramite.getText().length()!=0)
	        	&& (textPaneDestinoTramite.getText().length()!=0)
	        	&& (textPaneOrigenCertificados.getText().length()!=0)
	        	&& (model.getRowCount()!=0)){
	        
		        //seteamos en directorio origen de los tramites
		        conf.setProperty(Constants.FOLDER_ORIGIN, "file://"+textPaneOrigenTramite.getText().trim());
		        
		        //seteamos el directorio destino de los tramites
		        conf.setProperty(Constants.FOLDER_DESTINY, "file://"+textPaneDestinoTramite.getText().trim());
		        
		        //seteamos el directorio origen de los certificados
		        conf.setProperty(Constants.CERTIFICATE_ORIGIN, textPaneOrigenCertificados.getText().trim());
		        		        
		        //cargamos la tabla con su correspondiente formato
		        for (int i = 0; i < model.getRowCount(); i++) {
		        	String sociedad="";
		        	String certificado="";
		        	String pass="";
		        	for (int j = 0; j < model.getColumnCount(); j++) {
		        		String celda = model.getValueAt(i,j).toString().trim();
		        		if (j==0){// columna sociedad
		        			sociedad = celda;
		        		}else if (j==1){// columna certificado
		        			certificado=celda;
		        		}else{// columna contraseña
		        			pass=celda;
		        		}	        		
					}
		        	
		        	//cargamos la relacion entre sociedad/certificado/pass
		        	conf.setProperty("app.path.certificate."+sociedad, certificado);
		        	conf.setProperty("app.pass.certificate."+sociedad, pass);
	
		        }
		        
		        //persiste el archivo properties
		        File file = new File(Constants.PROP_EXTERNAL);
		        OutputStream out;
				try {
					out = new FileOutputStream(file);
					conf.store(out,"Por favor, lea la documentacion antes de modificar estas propiedades");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
					
				
				try {
				
				//se crea el hilo ejecutor del proceso de firmado
				Main main = new Main();
				main.addRouteBuilder(new FileRoute(conf));
				main.enableHangupSupport();

					main.run();
				}catch (Exception e1) {
					log.error("ERROR: Al ejecutar el proceso de firmado, verifique los datos cargados en la UI");
					e1.printStackTrace();
				}
	        }else{
	        	JOptionPane.showMessageDialog(this, "Existen datos obligatorios que debe cargar, verifique la documentación", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	        	
		}
	  
	}
		

	/**
	 * Vacia los input de la tabla
	 */
	private void cleanInputs() {
		inputCertificado.setText("");
		inputSociedad.setText("");
		inputPass.setText("");
	}

	/**
	 * 
	 * @param ruta
	 * @return String
	 * Obtiene el contenido del archivo
	 */
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
	


	/**
	 * 
	 * @param arg
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] arg) {
		try {
			// Cambiamos el Look&Feel
			JFrame.setDefaultLookAndFeelDecorated(true);
			UIManager
					.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
		} catch (Exception e) {
		}
		new DesktopUI();
	}
}
