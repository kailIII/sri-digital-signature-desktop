package ar.com.estigiait.ds.entity.common;

import javax.xml.bind.annotation.XmlElement;

public class DetalleAdicional {
	
	@XmlElement(name = "nombre", required = false)
	private String nombre;
	@XmlElement(name = "valor", required = false)
	private String valor;

}
