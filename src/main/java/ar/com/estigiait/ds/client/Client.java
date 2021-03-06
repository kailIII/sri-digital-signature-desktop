package ar.com.estigiait.ds.client;

import java.util.Properties;

import org.apache.camel.main.Main;

import ar.com.estigiait.ds.route.FileRoute;
import ar.com.estigiait.ds.tool.Constants;
import ar.com.estigiait.ds.tool.Util;

/**
 * Clase que contiene un ejecutor sin UI
 * del proceso de firmado con el objeto de poder 
 * probar el proceso antes de generar su GUI.
 * 
 */
public final class Client {

    public static void main(String[] args) throws Exception {
        
         Properties conf = null;
         
         conf = Util.getPropertiesLocal();
         
         //cargamos las propiedades en base al entorno de ejecucion -DEV/PROD-
         if (!conf.getProperty(Constants.MODE).equals(Constants.MODE_DEV)){
        	 conf = Util.getPropertiesExternal();
         }
          
         //se crea el hilo ejecutor del proceso de firmado
         Main main = new Main();
	     main.addRouteBuilder(new FileRoute(conf));
	     main.enableHangupSupport();
	     main.run();
    }

}
