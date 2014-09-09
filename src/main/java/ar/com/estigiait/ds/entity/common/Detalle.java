package ar.com.estigiait.ds.entity.common;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Detalle {
	
	@XmlElement(name = "codigoPrincipal", required = false)
	private String codigoPrincipal;
	@XmlElement(name = "codigoAuxiliar", required = false)
	private String codigoAuxiliar;
	@XmlElement(name = "descripcion", required = true)
	private String descripcion;
	@XmlElement(name = "cantidad", required = true)
	private String cantidad;
	@XmlElement(name = "precioUnitario", required = true)
	private String precioUnitario;
	@XmlElement(name = "descuento", required = true)
	private String descuento;
	@XmlElement(name = "precioTotalSinImpuesto", required = true)
	private String precioTotalSinImpuesto;
	
	//Credito//Inpuestos//inpuesto
	@XmlElementWrapper(name = "impuestos")
	@XmlElement(name = "impuesto", required = true)
	private List<Impuesto> impuesto;
	               
		
	//Credito//DetallesAdicionales
	@XmlElementWrapper(name = "detallesAdicionales", required = false)
	@XmlElement(name = "detAdicional", required = false)
	private List<DetalleAdicional> detAdicional;

}
