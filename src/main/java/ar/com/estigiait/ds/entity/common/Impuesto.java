package ar.com.estigiait.ds.entity.common;

import javax.xml.bind.annotation.XmlElement;

public class Impuesto {
	
	@XmlElement(name = "codigo", required = true)
	private Integer codigoInpuesto;
	@XmlElement(name = "codigoPorcentaje", required = true)
	private Integer codigoPorcentajeInpuesto;
	@XmlElement(name = "tarifa", required = true)
	private Integer tarifa;
	@XmlElement(name = "baseImponible", required = true)
	private String baseImponible;
	@XmlElement(name = "valor", required = true)
	private String valor;

}
