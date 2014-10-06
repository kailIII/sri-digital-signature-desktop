package ar.com.estigiait.ds.route;

import java.io.IOException;
import java.util.Properties;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.GenericFileOperationFailedException;
import org.apache.camel.component.properties.PropertiesComponent;
import org.w3c.dom.Document;
import org.xml.sax.SAXParseException;

import ar.com.estigiait.ds.signature.XadesBesSignature;
import ar.com.estigiait.ds.tool.Constants;
import ar.com.estigiait.ds.tool.Util;

/**
 * File route permite poolear un directorio para leer un archivo,
 * transformar su contenido agregando
 * su firma digital y luego guardarlo en otro directorio  
 * 
 */
public class FileRoute extends RouteBuilder {
	
	public  org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(getClass());
	
	Properties conf = null;
	
	public final static String PROPERTIES_CONSUMER = "?delay=1000&noop=false&recursive=true&include=.*.xml&idempotentKey=${file:name}-${file:modified}&readLock=changed&readLockTimeout=10000";
	public final static String PROPERTIES_PRODUCER = "?allowNullBody=true";
		
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
       // getContext().getShutdownStrategy().setTimeout(10);
    	
        from(conf.getProperty(Constants.FOLDER_ORIGIN) + PROPERTIES_CONSUMER )
        	.routeId("FileRoute")
        	.log("Procesado del archivo: ${file:name} iniciado.")
        	.process(new SignatureProcessor(conf))
            .to(conf.getProperty(Constants.FOLDER_DESTINY) + PROPERTIES_PRODUCER)
            .log("Procesado del archivo: ${file:name} finalizado.");
        
        // use system out so it stand out
        log.info("*********************************************************************************\n");
        log.info("Este proceso firma tramites desde una carpeta origen: " + conf.getProperty(Constants.FOLDER_ORIGIN)
                + " y guarda su contenido en una carpeta destino: " + conf.getProperty(Constants.FOLDER_DESTINY));
        log.info("*********************************************************************************\n");
    }
    
    
    /**
     * Proceso que permite transformar el contenido 
     * del archivo pooleado, agregando su firma
     * digital.
     *
     */
    private static final class SignatureProcessor implements Processor {
    	
    	public  org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(getClass());
    	
    	Properties conf = null;
    	
    	public SignatureProcessor(Properties configuration) {
    		this.conf=configuration;
		}
            	
    	public void process(Exchange exchange) throws Exception {
    		
    		String society = null;
    		    		
            String body = exchange.getIn().getBody(String.class);
            exchange.getOut().setHeader("CamelFileName", exchange.getIn().getHeader("CamelFileName") );
            
            Document doc = null;
            
            try{ 
            	//obtenemos la carpeta padre del path para obtener la sociedad a la cual pertenece el tramite
            	society = Util.getSocietyNameFromRelativePathFile(exchange.getIn().getHeader("CamelFileRelativePath", String.class)); 
            	
            	String pathCertificate =  conf.getProperty(Constants.CERTIFICATE_ORIGIN) + conf.getProperty(Constants.CERTIFICATE_PATH+society);
            	
            	doc = XadesBesSignature.firmar(body, pathCertificate , conf.getProperty(Constants.CERTIFICATE_PASS+society), conf);
            	
            	if (doc!=null){
            	//guardamos el tramite firmado en la carpeta destino
            		exchange.getOut().setBody(doc);
            		log.info("El tramite fue firmado con exito!");
            	}else{
            		exchange.getOut().setBody("ERROR: Exitió un error al firmar el tramite, intente nuevamente...");
            		log.error("Existe un error al firmar el tramite, el archivo esta corrupto o mal formado");
            	}
            	
            }catch(GenericFileOperationFailedException e){
            	e.printStackTrace();
            }catch (Exception e) {
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