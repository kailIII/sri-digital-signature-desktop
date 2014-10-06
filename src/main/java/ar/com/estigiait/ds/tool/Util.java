package ar.com.estigiait.ds.tool;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Esta clase un conjunto de herramientas utiles.
 * 
 * @author Emilio Watemberg <emilio.watemberg@gmail.com.ar>
 *
 */
public class Util{
			
	Logger log = Logger.getLogger(this.getClass());
	
	/**
	 * Convierte un objeto Date en un String con formato (MM/dd/yyyy)
	 * @param String date
	 * @return Date
	 */
	public static Date dateFormatToDate (String date){
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date convertDate = null;
        try {
            convertDate = format.parse(date);
            return convertDate;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
	}
	
	/**
	 * Convierte un objeto String en Date con formato (MM/dd/yyyy)
	 * @param Date date
	 * @return String
	 */
	public static String dateFormatToString(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
    	String fecha = sdf.format(date);
    	return fecha;
	}
	
	/**
	 * Retorna un objeto Properties con las propiedades de la aplicacion.
	 * Estas propiedades se encuentran afuera adentro .jar
	 * @return Properties
	 */
	public static Properties getPropertiesLocal(){
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {
	 		URL fileURL = Util.class.getResource(Constants.PROP_LOCAL);	
	 		System.out.println(fileURL);
			input = fileURL.openStream();
	 
			//cargamos el archivo de propiedades
			prop.load(input);
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return prop;
		
	}
	
	/**
	 * Retorna un objeto Properties con las propiedades de la aplicacion.
	 * Estas propiedades se encuentran afuera del .jar
	 * @return Properties
	 */
	public static Properties getPropertiesExternal(){
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {			
	 		URL fileURL = makeUrlExternal(Constants.PROP_EXTERNAL);	
			input = fileURL.openStream();
	 
			//cargamos el archivo de propiedades
			prop.load(input);
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return prop;
		
	}
	
    /**
     * Devuelve el Document correspondiente al
     * resource pasado como parámetro
     * 
     * @param resource
     *            El recurso que se desea obtener
     * @return Document
     * 			El Document asociado al resource
     */
    public static Document getDocument(String resource){
         Document doc = null;
         DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
         dbf.setNamespaceAware(true);
         InputStream is = new ByteArrayInputStream(resource.getBytes(StandardCharsets.UTF_8));
         try {
            DocumentBuilder db = dbf.newDocumentBuilder();
           
            doc=db.parse(is);
         } catch (ParserConfigurationException ex) {
             System.err.println("Error al parsear el documento");
             ex.printStackTrace();
             //System.exit(-1);
         } catch (SAXException ex) {
             System.err.println("Error al parsear el documento");
             ex.printStackTrace();
             //System.exit(-1);
         } catch (IOException ex) {
             System.err.println("Error al parsear el documento");
             ex.printStackTrace();
             //System.exit(-1);
         } catch (IllegalArgumentException ex) {
            System.err.println("Error al parsear el documento");
             ex.printStackTrace();
            //System.exit(-1);
         }
         return doc;
     }
    
    /**
     * Construct a file: URL for a path name.
     *
     * <p>URLs in the file: scheme can be constructed for paths on
     * the local file system. Several possibilities need to be considered:
     * </p>
     *
     * <ul>
     * <li>If the path does not begin with a slash, then it is assumed
     * to reside in the users current working directory
     * (System.getProperty("user.dir")).</li>
     * <li>On Windows machines, the current working directory uses
     * backslashes (\\, instead of /).</li>
     * <li>If the current working directory is "/", don't add an extra
     * slash before the base name.</li>
     * </ul>
     *
     * <p>This method is declared static so that other classes
     * can use it directly.</p>
     *
     * <p>This method has been updated to fix the ASF Bugzilla 'Bug 28719 -
     * Resolver generates an incorrect base URL when user.dir is "/"' bug, in
     * accordance with Drew Wills's contribution (comment).</p>
     *
     * @param pathname The path name component for which to construct a URL.
     *
     * @return The appropriate file: URL.
     *
     * @throws java.net.MalformedURLException if the pathname can't be turned into
     *         a proper URL.
     */
    public static URL makeUrlExternal(String pathname) throws MalformedURLException {
        if (pathname.startsWith("/")) {
          return new URL("file://" + pathname);
        }
        String userdir = System.getProperty("user.dir");
        return new File(userdir, pathname).toURL();

    }
    
    /**
     * Este metodo devuelve el nombre de la sociedad de un path relativo
     * 	@return String name
     * 					of society
     * 
     */
    public static String getSocietyNameFromRelativePathFile(String relativePath){
    	String result = null;
    	if (relativePath != null){
    		result = relativePath.substring(0, relativePath.indexOf("\\"));
    	}
    	return result;
    }
    
    /**
     * Este metodo convierte un inputstream to string
     * 	@return InputStream is
     */
 	public static String getStringFromInputStream(InputStream is) {
  
 		BufferedReader br = null;
 		StringBuilder sb = new StringBuilder();
  
 		String line;
 		try {
  
 			br = new BufferedReader(new InputStreamReader(is));
 			while ((line = br.readLine()) != null) {
 				sb.append(line);
 			}
  
 		} catch (IOException e) {
 			e.printStackTrace();
 		} finally {
 			if (br != null) {
 				try {
 					br.close();
 				} catch (IOException e) {
 					e.printStackTrace();
 				}
 			}
 		}
 		return sb.toString();
 	}
 	
 	  /**
 	   * Retorna una url sin su prefijo file://
 	   * @param String path
 	   * @return String path sin prefijo
 	   */
 	   public static String getPathWithoutPrefixFile(String path){
 		   path = path.replaceAll("\\\\", "");
 		   System.out.println(path);
 		   if (path.contains("file://")){
 			   String[] result = path.split("file://");
 		   	   return result[1];
 		   }else
 			   return path;
 	    	
 	    }
	
}
