package ar.com.estigiait.ds.entity.common;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class InfoFactura {
	
	@XmlElement(name = "fechaEmision", required = true)
	private String fechaEmision;
	@XmlElement(name = "dirEstablecimiento", required = false)
	private String dirEstablecimiento;
	@XmlElement(name = "contribuyenteEspecial", required = false)
	private String contribuyenteEspecial;
	@XmlElement(name = "obligadoContabilidad", required = true)
	private String obligadoContabilidad;
	@XmlElement(name = "tipoIdentificacionComprador", required = false)
	private Integer tipoIdentificacionComprador;
	@XmlElement(name = "guiaRemision", required = false)
	private String guiaRemision;
	@XmlElement(name = "razonSocialComprador", required = true)
	private String razonSocialComprador;
	@XmlElement(name = "identificacionComprador", required = true)
	private String identificacionComprador;
	@XmlElement(name = "totalDescuento", required = true)
	private String totalDescuento;
	
	
	@XmlElementWrapper(name = "totalConImpuestos")
	@XmlElement(name = "totalImpuesto", required = true)
	private List<TotalImpuesto> totalImpuesto;
	
	@XmlElement(name = "propina", required = true)
	private String propina;
	@XmlElement(name = "importeTotal", required = true)
	private String importeTotal;
	@XmlElement(name = "moneda", required = false)
	private String moneda;
}

