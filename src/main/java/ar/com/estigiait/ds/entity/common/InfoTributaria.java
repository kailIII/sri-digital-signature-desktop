package ar.com.estigiait.ds.entity.common;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;

public class InfoTributaria {
	
	public final static String SHORT_MSG = "EMILIO ES UN GROSO";

	@XmlElement(name = "ambiente", required = true)
	private Integer ambiente;
	@XmlElement(name = "tipoEmision", required = true)
	private Integer tipoEmision;
	@XmlElement(name = "razonSocial", required = false)
	private String razonSocial;
	@XmlElement(name = "nombreComercial", required = true)
	private String nombreComercial;
	@XmlElement(name = "ruc", required = true)
	private Integer ruc;
	@XmlElement(name = "claveAcceso", required = true)
	@Pattern(regexp = "{\\d}",message = SHORT_MSG)
	private String claveAcceso;
	@XmlElement(name = "codDoc", required = true)
	private Integer codDoc;
	@XmlElement(name = "estab", required = true)
	private Integer estab;
	@XmlElement(name = "ptoEmi", required = true)
	private Integer ptoEmi;
	@XmlElement(name = "secuencial", required = true)
	private Integer secuencial;
	@XmlElement(name = "dirMatriz", required = true)
	private String dirMatriz;
	
}
