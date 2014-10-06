package ar.com.estigiait.ds.ui;

import java.util.Properties;

import ar.com.estigiait.ds.tool.Constants;
import ar.com.estigiait.ds.tool.Util;

public class ToolsUI {

	 Properties conf = null;
     
  /**
   * 	 
   * @param constants
   * @return name of folder: Origen/Destino
   */
   public static String getFolderName(String constants){
//	    Properties conf = null;
//	    conf = getProperties();
//    	String[] resul = (conf.getProperty(constants)).split("file://");
//     	String[] resul2 = resul[1].split("\\?");
//    	return resul2[0];
    	
    	return null;
    	
    }
    
    
	 
    public static Properties getProperties(){
		Properties conf = null;
		conf = Util.getPropertiesLocal();
		// cargamos las propiedades en base al entorno de ejecucion -DEV/PROD-
		if (!conf.getProperty(Constants.MODE).equals(Constants.MODE_DEV)) {
			conf = Util.getPropertiesExternal();
		}

		return conf;
    	
    }
   
}
	
	
