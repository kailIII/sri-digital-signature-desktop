package ar.com.estigiait.ds.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import ar.com.estigiait.ds.entity.common.CampoAdicional;
import ar.com.estigiait.ds.entity.common.Detalle;
import ar.com.estigiait.ds.entity.common.Impuesto;
import ar.com.estigiait.ds.entity.common.InfoFactura;
import ar.com.estigiait.ds.entity.common.InfoTributaria;
import ar.com.estigiait.ds.entity.common.TotalImpuesto;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Factura {

	//Credito//InfoTributaria
	@XmlElement(name = "infoTributaria", required = true)
	private InfoTributaria infoTributaria;
	
	//Credito//InfoFactura
	@XmlElement(name = "infoFactura", required = true)
	private InfoFactura infoFactura;	
	
	//Credito//Detalles//Detalle
	@XmlElementWrapper(name = "detalles")
	@XmlElement(name = "detalle", required = true)
	private List<Detalle> detalle;	
		
	//Credito//InfoAdicional
	@XmlElementWrapper(name = "infoAdicional")
	@XmlElement(name = "campoAdicional", required = false)
	private List<CampoAdicional> campoAdicional;
	
}
