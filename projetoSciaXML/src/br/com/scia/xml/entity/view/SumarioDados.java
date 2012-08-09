package br.com.scia.xml.entity.view;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import br.com.scia.xml.entity.xml.Coordenada;

@XStreamAlias("SciaXMLProject")
public class SumarioDados {

	private String nomeArquivo;
	private String nomeProjeto;
	private String siglaProjeto;
	private String diretorioPecas;
	
	private String totalPecas;
	private String coordenadaX;
	private String coordenadaY;
	private String coordenadaZ;
	private String tipoEquipamento;
	private String medidaLageX;
	private String medidaLageY;
	private String espessura;
	private String folgaLajeX1;
	private String folgaLajeX2;
	private String folgaLajeY1;
	private String folgaLajeY2;
	private List<Peca> pecasX;
	private List<Peca> pecasY;
	private List<Coordenada> listaDeNos;
	private String kidI;
	private String kidH;
	private List<String> vigasPrincipais;
	private List<String> vigasSecundarias;
	private String composicaoTorres;
	private Double vaoDeApoioX;
	private Double vaoDeApoioY;
	
	public String getTotalPecas() {
		return totalPecas;
	}
	public void setTotalPecas(String totalPecas) {
		this.totalPecas = totalPecas;
	}
	public String getTipoEquipamento() {
		return tipoEquipamento;
	}
	public void setTipoEquipamento(String tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}
	public String getMedidaLageX() {
		return medidaLageX;
	}
	public void setMedidaLageX(String medidaLageX) {
		this.medidaLageX = medidaLageX;
	}
	public String getMedidaLageY() {
		return medidaLageY;
	}
	public void setMedidaLageY(String medidaLageY) {
		this.medidaLageY = medidaLageY;
	}
	public String getEspessura() {
		return espessura;
	}
	public void setEspessura(String espessura) {
		this.espessura = espessura;
	}
	public String getFolgaLajeX1() {
		return folgaLajeX1;
	}
	public void setFolgaLajeX1(String folgaLajeX1) {
		this.folgaLajeX1 = folgaLajeX1;
	}
	public String getFolgaLajeX2() {
		return folgaLajeX2;
	}
	public void setFolgaLajeX2(String folgaLajeX2) {
		this.folgaLajeX2 = folgaLajeX2;
	}
	public String getFolgaLajeY1() {
		return folgaLajeY1;
	}
	public void setFolgaLajeY1(String folgaLajeY1) {
		this.folgaLajeY1 = folgaLajeY1;
	}
	public String getFolgaLajeY2() {
		return folgaLajeY2;
	}
	public void setFolgaLajeY2(String folgaLajeY2) {
		this.folgaLajeY2 = folgaLajeY2;
	}
	public String getKidI() {
		return kidI;
	}
	public void setKidI(String kidI) {
		this.kidI = kidI;
	}
	public String getKidH() {
		return kidH;
	}
	public void setKidH(String kidH) {
		this.kidH = kidH;
	}
	public List<String> getVigasPrincipais() {
		return vigasPrincipais;
	}
	public void setVigasPrincipais(List<String> vigasPrincipais) {
		this.vigasPrincipais = vigasPrincipais;
	}
	public List<String> getVigasSecundarias() {
		return vigasSecundarias;
	}
	public void setVigasSecundarias(List<String> vigasSecundarias) {
		this.vigasSecundarias = vigasSecundarias;
	}
	public String getComposicaoTorres() {
		return composicaoTorres;
	}
	public void setComposicaoTorres(String composicaoTorres) {
		this.composicaoTorres = composicaoTorres;
	}
	public List<Peca> getPecasX() {
		return pecasX;
	}
	public void setPecasX(List<Peca> pecasX) {
		this.pecasX = pecasX;
	}
	public List<Peca> getPecasY() {
		return pecasY;
	}
	public void setPecasY(List<Peca> pecasY) {
		this.pecasY = pecasY;
	}
	public List<Coordenada> getListaDeNos() {
		return listaDeNos;
	}
	public void setListaDeNos(List<Coordenada> listaDeNos) {
		this.listaDeNos = listaDeNos;
	}
	public Double getVaoDeApoioX() {
		return vaoDeApoioX;
	}
	public void setVaoDeApoioX(Double vaoDeApoioX) {
		this.vaoDeApoioX = vaoDeApoioX;
	}
	public Double getVaoDeApoioY() {
		return vaoDeApoioY;
	}
	public void setVaoDeApoioY(Double vaoDeApoioY) {
		this.vaoDeApoioY = vaoDeApoioY;
	}
	public String getNomeProjeto() {
		return nomeProjeto;
	}
	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}
	public String getSiglaProjeto() {
		return siglaProjeto;
	}
	public void setSiglaProjeto(String siglaProjeto) {
		this.siglaProjeto = siglaProjeto;
	}
	public String getDiretorioPecas() {
		return diretorioPecas;
	}
	public void setDiretorioPecas(String diretorioPecas) {
		this.diretorioPecas = diretorioPecas;
	}
	

	public Double getComprimentoTotalEixoX(){		
		Double comprimento = 0.0;
		
		for (Peca peca : this.getPecasX()) {
			comprimento += peca.getComprimentoPecaX();			
		}
		
		return comprimento;		
	}
	
	public Double getComprimentoTotalEixoY(){		
		Double comprimento = 0.0;
		
		for (Peca peca : this.getPecasY()) {
			comprimento += peca.getComprimentoPecaY();			
		}
		
		return comprimento;		
	}	
	
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public String getCoordenadaX() {
		return coordenadaX;
	}
	public void setCoordenadaX(String coordenadaX) {
		this.coordenadaX = coordenadaX;
	}
	public String getCoordenadaY() {
		return coordenadaY;
	}
	public void setCoordenadaY(String coordenadaY) {
		this.coordenadaY = coordenadaY;
	}
	public String getCoordenadaZ() {
		return coordenadaZ;
	}
	public void setCoordenadaZ(String coordenadaZ) {
		this.coordenadaZ = coordenadaZ;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SumarioDados [nomeArquivo=");
		builder.append(nomeArquivo);
		builder.append(", nomeProjeto=");
		builder.append(nomeProjeto);
		builder.append(", siglaProjeto=");
		builder.append(siglaProjeto);
		builder.append(", diretorioPecas=");
		builder.append(diretorioPecas);
		builder.append(", totalPecas=");
		builder.append(totalPecas);
		builder.append(", coordenadaX=");
		builder.append(coordenadaX);
		builder.append(", coordenadaY=");
		builder.append(coordenadaY);
		builder.append(", coordenadaZ=");
		builder.append(coordenadaZ);
		builder.append(", tipoEquipamento=");
		builder.append(tipoEquipamento);
		builder.append(", medidaLageX=");
		builder.append(medidaLageX);
		builder.append(", medidaLageY=");
		builder.append(medidaLageY);
		builder.append(", espessura=");
		builder.append(espessura);
		builder.append(", folgaLajeX1=");
		builder.append(folgaLajeX1);
		builder.append(", folgaLajeX2=");
		builder.append(folgaLajeX2);
		builder.append(", folgaLajeY1=");
		builder.append(folgaLajeY1);
		builder.append(", folgaLajeY2=");
		builder.append(folgaLajeY2);
		builder.append(", pecasX=");
		builder.append(pecasX);
		builder.append(", pecasY=");
		builder.append(pecasY);
		builder.append(", listaDeNos=");
		builder.append(listaDeNos);
		builder.append(", kidI=");
		builder.append(kidI);
		builder.append(", kidH=");
		builder.append(kidH);
		builder.append(", vigasPrincipais=");
		builder.append(vigasPrincipais);
		builder.append(", vigasSecundarias=");
		builder.append(vigasSecundarias);
		builder.append(", composicaoTorres=");
		builder.append(composicaoTorres);
		builder.append(", vaoDeApoioX=");
		builder.append(vaoDeApoioX);
		builder.append(", vaoDeApoioY=");
		builder.append(vaoDeApoioY);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
}
