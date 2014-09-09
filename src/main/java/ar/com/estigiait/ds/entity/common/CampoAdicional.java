package ar.com.estigiait.ds.entity.common;

import javax.xml.bind.annotation.XmlElement;

public class CampoAdicional {

	@XmlElement(name = "nombre", required = false)
	private String nombre;
	
}
