package ar.com.estigiait.ds.entity.common;

import javax.xml.bind.annotation.XmlElement;

public class TotalImpuesto {
	
	@XmlElement(name = "codigo", required = true)
	private Integer codigo;
	@XmlElement(name = "codigoPorcentaje", required = true)
	private Integer codigoPorcentaje;
	@XmlElement(name = "baseImponible", required = true)
	private String baseImponible;
	@XmlElement(name = "valor", required = true)
	private String valorTotalInpuesto;

}
