package br.com.scia.xml.entity.xml;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.entity.view.Peca;
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
	
	@XStreamOmitField
	private Double comprimentoZ;
	
	public Project(SumarioDados sumarioDados, String fileName) {
		
		if (sumarioDados != null){
			
			this.xmlns = SciaXMLContantes.XMLNS;
			this.def = new Definition(fileName+SciaXMLContantes.DEF);
			this.containers = new ArrayList<Container>();
			this.containers.add(getContainerNos(sumarioDados));
			this.containers.add(getContainerPecas(sumarioDados));
		}
		
	}
	
	private Container getContainerNos(SumarioDados sumarioDados) {
		List<Coordenada> nos= sumarioDados.getListaDeNos();
		Container containerNos = null;
		if (nos != null && nos.size() > 0){
			
			List<Object> objects = new ArrayList<Object>();
			
			for (Coordenada coordenada : nos) {
				Object o = new Object();
				o.setId(coordenada.getId());
				o.setNm(coordenada.getName());
				o.setP0(new ObjectItem(String.valueOf(coordenada.getName()), null, null, null, null, null));
				o.setP1(new ObjectItem(String.valueOf(coordenada.getX()), null, null, null, null, null));
				o.setP2(new ObjectItem(String.valueOf(coordenada.getY()), null, null, null, null, null));
				o.setP3(new ObjectItem(String.valueOf(coordenada.getZ()), null, null, null, null, null));
				objects.add(o);
			}
			
			Header header = getDefaultStructNodeHeader();
			TableNode tabelaNos = new TableNode(SciaXMLContantes.STRUCT_NODE_TABLE_ID, 
												SciaXMLContantes.STRUCT_NODE_TABLE, 
												SciaXMLContantes.STRUCT_NODE_TABLE_NAME, header, objects);
			
			containerNos = new Container(SciaXMLContantes.STRUCT_NODE_ID, SciaXMLContantes.STRUCT_NODE_TITLE, tabelaNos);
			
		}
		return containerNos;
	}
	
	private Container getContainerPecas(SumarioDados sumarioDados) {
		Container containerPecas = null;
		
		List<Object> objects = new ArrayList<Object>();
		objects.addAll(getPecas(sumarioDados.getPecasFinais()));
			
		Header header = getDefaultBeamHeader();
		TableNode tabelaPecas = new TableNode(SciaXMLContantes.BEAM_TABLE_ID, 
											SciaXMLContantes.BEAM_TABLE, 
											SciaXMLContantes.BEAM_TABLE_NAME, header, objects);
		
		containerPecas = new Container(SciaXMLContantes.BEAM_ID, SciaXMLContantes.BEAM, tabelaPecas);
		
		return containerPecas;
	}
	
	private Collection<Object> getPecas(List<Peca> pecas) {
		List<Object> retorno = new ArrayList<Object>();
		for (Peca peca : pecas) {
			Object o = new Object();
			Project pecaOrigem = RepositorioPecas.pecas.get(peca.getTipo());
			
			Container containerOrigem = null;
			List<Container> listaContainer = pecaOrigem.getContainers();
			
			for (Container container : listaContainer) {
				if (container.getT().contains(SciaXMLContantes.BEAM)){
					containerOrigem = container;
					break;
				}
			}
			
			// Um arquivo de peça só possui um object/peça no container Beam
			Object objectOrigem = containerOrigem.getTable().getObjects().get(0);
			
			o.setId(peca.getId());
			o.setNm(peca.getName());
			o.setP0(new ObjectItem(peca.getId(), null, null, null, null, null));
			o.setP1(new ObjectItem(null,String.valueOf(peca.getNoInicial()),String.valueOf(peca.getNoInicial()),null,null,null));
			o.setP2(new ObjectItem(null,String.valueOf(peca.getNoFinal()),String.valueOf(peca.getNoFinal()),null,null,null));
			o.setP3(objectOrigem.getP3());
			o.setP4(objectOrigem.getP4());
			o.setP5(objectOrigem.getP5());
			o.setP6(objectOrigem.getP6());
			o.setP7(objectOrigem.getP7());
			o.setP8(objectOrigem.getP8());
			o.setP9(objectOrigem.getP9());
			o.setP10(objectOrigem.getP10());
			o.setP11(objectOrigem.getP11());
			o.setP12(objectOrigem.getP12());
			
			// TODO: Se necessário atualizar os valores da tabela de geometria
			// o.getXX().setT(new String());
			
			retorno.add(o);
		}		
		
		return retorno;
	}
	
	private Header getDefaultBeamHeader() {
		Header retorno = new Header();
		retorno.setH0(new HeaderItem(SciaXMLContantes.BEAM_HEADER_H0));
		retorno.setH1(new HeaderItem(SciaXMLContantes.BEAM_HEADER_H1));
		retorno.setH2(new HeaderItem(SciaXMLContantes.BEAM_HEADER_H2));
		retorno.setH3(new HeaderItem(SciaXMLContantes.BEAM_HEADER_H3));
		retorno.setH4(new HeaderItem(SciaXMLContantes.BEAM_HEADER_H4));
		retorno.setH5(new HeaderItem(SciaXMLContantes.BEAM_HEADER_H5));
		retorno.setH6(new HeaderItem(SciaXMLContantes.BEAM_HEADER_H6));
		retorno.setH7(new HeaderItem(SciaXMLContantes.BEAM_HEADER_H7));
		retorno.setH8(new HeaderItem(SciaXMLContantes.BEAM_HEADER_H8));
		retorno.setH9(new HeaderItem(SciaXMLContantes.BEAM_HEADER_H9));
		retorno.setH10(new HeaderItem(SciaXMLContantes.BEAM_HEADER_H10));
		retorno.setH11(new HeaderItem(SciaXMLContantes.BEAM_HEADER_H11));
		retorno.setH12(new HeaderItem(SciaXMLContantes.BEAM_HEADER_H12));
		return retorno;
	}

	private Header getDefaultStructNodeHeader() {
		Header retorno = new Header();
		retorno.setH0(new HeaderItem(SciaXMLContantes.STRUCT_NODE_HEADER_H0));
		retorno.setH1(new HeaderItem(SciaXMLContantes.STRUCT_NODE_HEADER_H1));
		retorno.setH2(new HeaderItem(SciaXMLContantes.STRUCT_NODE_HEADER_H2));
		retorno.setH3(new HeaderItem(SciaXMLContantes.STRUCT_NODE_HEADER_H3));
		return retorno;
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

	public Double getComprimentoZ() {
		return comprimentoZ;
	}

	public void setComprimentoZ(Double comprimentoZ) {
		this.comprimentoZ = comprimentoZ;
	}

	
}
