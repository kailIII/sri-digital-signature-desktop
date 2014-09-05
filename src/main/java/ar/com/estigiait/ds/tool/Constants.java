package ar.com.estigiait.ds.tool;

public final class Constants {
	
	//especifican el modo en cual se corre la aplicacion
	public final static String MODE = "app.dev.mode";
	public final static String MODE_DEV = "DEV";
	public final static String MODE_PROD = "PROD";
	//especifican de donde leer las propiedade en base al modo de ejecucion
	public final static String PROP_LOCAL = "/signature.properties";
	public final static String PROP_EXTERNAL = "conf/signature.properties";
	//configuracion de la aplicacion
	public final static String FOLDER_ORIGIN = "app.folder.origin";
	public final static String FOLDER_DESTINY = "app.folder.destiny";
	public final static String FOLDER_CERTIFICATE = "app.folder.certificate";
	public final static String CERTIFICATE_PASS = "app.certificate.pass";
	
}
