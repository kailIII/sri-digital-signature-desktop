package ar.com.estigiait.ds.entity.common;

import javax.xml.bind.annotation.XmlElement;

public class FechaEmision {
	
	@XmlElement(name = "fechaEmision", required = true)
	private String fechaEmision;
	@XmlElement(name = "dirEstablecimiento", required = true)
	private String dirEstablecimiento;
	@XmlElement(name = "contribuyenteEspecial", required = false)
	private Integer contribuyenteEspecial;
	@XmlElement(name = "obligadoContabilidad", required = false)
	private String obligadoContabilidad;
	@XmlElement(name = "tipoIdentificacionComprador", required = true)
	private Integer tipoIdentificacionComprador;
	@XmlElement(name = "guiaRemision", required = false)
	private String guiaRemision;
	@XmlElement(name = "razonSocialComprador", required = true)
	private String razonSocialComprador;
	@XmlElement(name = "identificacionComprador", required = true)
	private String identificacionComprador;
	@XmlElement(name = "totalSinInpuestos", required = true)
	private Float totalSinInpuestos;
	@XmlElement(name = "totalDescuento", required = true)
	private Float totalDescuento;
	@XmlElement(name = "propina", required = true)
	private Float propina;
	@XmlElement(name = "importeTotal", required = true)
	private Float importeTotal;
	@XmlElement(name = "moneda", required = true)
	private String moneda;	

}
