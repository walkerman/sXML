package br.com.scia.xml.entity.xml;

import java.util.List;

import br.com.scia.xml.entity.view.SumarioDados;
import br.com.scia.xml.util.SciaXMLContantes;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

@XStreamAlias("project")
public class Project {
	
	@XStreamAsAttribute
	private String xmlns;
	
	private Definition def;
	
	@XStreamImplicit(itemFieldName="container")
	private List<Container> containers;
	
	@XStreamOmitField
	private String fileName;
	
	@XStreamOmitField
	private Double comprimentoX;
	
	@XStreamOmitField
	private Double comprimentoY;
	
	public Project(SumarioDados sumarioDados, String fileName) {
		
		if (sumarioDados != null){
			
			this.xmlns = SciaXMLContantes.XMLNS;
			this.def = new Definition(fileName+SciaXMLContantes.DEF);
			
			List<Coordenada> nos= sumarioDados.getListaDeNos();
			if (nos != null && nos.size() > 0){
				
				Header header = getDefaultStructNodeHeader();
				//TableNode tabelaNos = new TableNode(id, t, name, h, objects)
				
				for (Coordenada coordenada : nos) {
					
				}
				
				//Container containerNos = new Container(SciaXMLContantes.ID_CONTAINER, SciaXMLContantes.STRUCT_NODE, tabelaNos);
			}
			
		}
		
	}
	
	private Header getDefaultStructNodeHeader() {
		Header retorno = new Header();
		return null;
	}

	public Project(String xmlns, Definition def, List<Container> containers) {
		this.xmlns = xmlns;
		this.def = def;
		this.containers = containers;
	}

	public Definition getDef() {
		return def;
	}

	public List<Container> getContainers() {
		return containers;
	}

	public void setDef(Definition def) {
		this.def = def;
	}

	public void setContainers(List<Container> containers) {
		this.containers = containers;
	}

	public String getXmlns() {
		return xmlns;
	}

	public void setXmlns(String xmlns) {
		this.xmlns = xmlns;
	}

	@Override
	public String toString() {
		return "Project [xmlns=" + xmlns + ", def=" + def + ", container="
				+ containers + "]";
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Double getComprimentoX() {
		return comprimentoX;
	}

	public void setComprimentoX(Double comprimentoX) {
		this.comprimentoX = comprimentoX;
	}

	public Double getComprimentoY() {
		return comprimentoY;
	}

	public void setComprimentoY(Double comprimentoY) {
		this.comprimentoY = comprimentoY;
	}

	
}
