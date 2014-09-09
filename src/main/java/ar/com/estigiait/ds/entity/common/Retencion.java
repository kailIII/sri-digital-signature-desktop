package ar.com.estigiait.ds.entity.common;

import javax.xml.bind.annotation.XmlElement;

public class Retencion {

	@XmlElement(name = "fechaEmision", required = true)
	private String fechaEmision;
	@XmlElement(name = "dirEstablecimiento", required = false)
	private String dirEstablecimiento;
	
}
