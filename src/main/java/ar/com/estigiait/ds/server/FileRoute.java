package ar.com.estigiait.ds.server;

import java.util.Properties;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;

import ar.com.estigiait.ds.signature.XadesBesSignature;
import ar.com.estigiait.ds.tool.Constants;
import ar.com.estigiait.ds.tool.Util;

/**
 * File route permite poolear un directorio para leer un archivo {@code app.file.origin},
 * transformar su contenido agregando
 * su firma digital y luego guardarlo en otro directorio {@code app.file.destiny}  
 * 
 */
public class FileRoute extends RouteBuilder {
	
	Properties conf = null;
		
	/**
	 * Constructor
	 */
	public FileRoute(){ 
	}
	
	/**
	 * Constructor inicializado con una configuracion
	 * @param configuration
	 * 					archivo de configuracion
	 */
	public FileRoute(Properties configuration){
		conf = configuration;
	}

    @Override
    public void configure() throws Exception {
        
        // lets shutdown faster in case of in-flight messages stack up
        getContext().getShutdownStrategy().setTimeout(10);

        from(conf.getProperty(Constants.FOLDER_ORIGIN))
        	.routeId("FileRoute")
        	.process(new SignatureProcessor(conf))
            .to(conf.getProperty(Constants.FOLDER_DESTINY))
            .log("Firmado del archivo: ${file:name} completo.");

        // use system out so it stand out
//        System.out.println("*********************************************************************************");
//        System.out.println("Este proceso firma archivos desde una carpeta origen: " + conf.getProperty(Constans.FOLDER_ORIGIN)
//                + " y guarda su contenido en una carpeta destino: " + conf.getProperty(Constans.FOLDER_DESTINY) 
//                + " tomando los certificados del directorio :" + conf.getProperty(Constans.FOLDER_CERTIFICATE) );
//        System.out.println("*********************************************************************************");
    }
    
    
    /**
     * Proceso que permite transformar el contenido 
     * del archivo pooleado, agregando su firma
     * digital.
     *
     */
    private static final class SignatureProcessor implements Processor {
    	
    	Properties conf = null;
    	
    	public SignatureProcessor(Properties configuration) {
    		this.conf=configuration;
		}
            	
    	public void process(Exchange exchange) throws Exception {
    		
    		
            String body = exchange.getIn().getBody(String.class);
            exchange.getOut().setHeader("CamelFileName", exchange.getIn().getHeader("CamelFileName") );
            //exchange.getOut().setBody(XadesBesSignature.firmar(body,FtpRoute.class.getResource("/usr0061.p12").getPath(), "usr0061"));
            //System.getProperty("user.dir")+"/resources/1791897498001.pfx"
            try{ 
              exchange.getOut().setBody(XadesBesSignature.firmar(body, conf.getProperty(Constants.FOLDER_CERTIFICATE), conf.getProperty(Constants.CERTIFICATE_PASS), conf));
            }catch(Exception e){
            	e.printStackTrace();
            }
              
        }
    }


	public Properties getConf() {
		return conf;
	}

	public void setConf(Properties conf) {
		this.conf = conf;
	}
}