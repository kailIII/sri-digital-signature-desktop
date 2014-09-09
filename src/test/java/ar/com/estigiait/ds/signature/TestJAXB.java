package ar.com.estigiait.ds.signature;

import java.io.File;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import ar.com.estigiait.ds.entity.Factura;
import ar.com.estigiait.ds.tool.EntityValidationEventHandler;

public class TestJAXB {
	public static void main(String[] args) {
 
	 try {
 
		URL url = TestJAXB.class.getResource("/EC1A00900009192014_FC.xml");
		System.out.println(url.getPath());
		File file = new File(TestJAXB.class.getResource("/EC1A00900009192014_FC.xml").getPath());
		JAXBContext jaxbContext = JAXBContext.newInstance(Factura.class);
 
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		jaxbUnmarshaller.setEventHandler(new EntityValidationEventHandler());
		Factura factura = (Factura) jaxbUnmarshaller.unmarshal(file);
		System.out.println(factura);
 
	  } catch (JAXBException e) {
		e.printStackTrace();
	  }
 
	}
}