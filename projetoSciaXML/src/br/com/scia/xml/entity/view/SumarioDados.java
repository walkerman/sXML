package br.com.scia.xml.entity.view;

import java.util.List;

import br.com.scia.xml.entity.xml.Coordenada;
import br.com.scia.xml.util.SciaXMLContantes;

import com.thoughtworks.xstream.annotations.XStreamAlias;

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
	private String tipoVigaPrincipal;
	private String transpassePrincipais;
	private String tipoVigaSecundaria;
	private String transpasseSecundarias;
	private String composicaoTorres;
	private String alturaVigaEsquerdaX;
	private String alturaVigaDireitaX;
	private String distanciaCruzetasX;
	private String alturaVigaEsquerdaY;
	private String alturaVigaDireitaY;
	private String distanciaCruzetasY;
	private String peDireito;
	private String espessuraCompensado;
	private Double vaoDeApoioX;
	private Double vaoDeApoioY;		
	private Peca kidI;
	private Peca kidH;
	private Peca posteEspecial;
	private Peca macaco;
	private Peca forcado;
	private List<Peca> pecasX;
	private List<Peca> pecasY;
	private List<Peca> vigasPrincipais;
	private List<Peca> vigasSecundarias;
	private List<Peca> postes;
	private List<Peca> pecasFinais;
	private List<Coordenada> listaDeNos;
	
	public Double getComprimentoTotalEixoX(){		
		Double comprimento = 0.0;
		
		for (Peca peca : this.getPecasX()) {
			comprimento += peca.getComprimento();			
		}
		
		return comprimento;		
	}
	
	public Double getComprimentoTotalEixoY(){		
		Double comprimento = 0.0;
		
		for (Peca peca : this.getPecasY()) {
			comprimento += peca.getComprimento();			
		}
		
		return comprimento;		
	}

	public Double getComprimentoTotalEixoXViga(){		
		Double comprimento = 0.0;
		
		for (Peca peca : this.getPecasX()) {
			if (peca.getTipo().contains(SciaXMLContantes.KITRV))
				comprimento += peca.getComprimento();
			if (peca.getTipo().contains(SciaXMLContantes.ESC))
				comprimento += (peca.getComprimento() + (vaoDeApoioX*2));
			if (peca.getTipo().contains(SciaXMLContantes.CRU))
				comprimento += (peca.getComprimento() + (vaoDeApoioX*2));
		}
		
		return comprimento;		
	}
	
	public Double getComprimentoTotalEixoYViga(){		
		Double comprimento = 0.0;
		
		for (Peca peca : this.getPecasY()) {
			if (peca.getTipo().contains(SciaXMLContantes.KITRV))
				comprimento += peca.getComprimento();
			if (peca.getTipo().contains(SciaXMLContantes.ESC))
				comprimento += (peca.getComprimento() + (vaoDeApoioY*2));
			if (peca.getTipo().contains(SciaXMLContantes.CRU))
				comprimento += (peca.getComprimento() + (vaoDeApoioY*2));
		}
		
		return comprimento;		
	}
	
	public String getTipoVigaPrincipal() {
		return tipoVigaPrincipal;
	}

	public void setTipoVigaPrincipal(String tipoVigaPrincipal) {
		this.tipoVigaPrincipal = tipoVigaPrincipal;
	}

	public String getTipoVigaSecundaria() {
		return tipoVigaSecundaria;
	}

	public void setTipoVigaSecundaria(String tipoVigaSecundaria) {
		this.tipoVigaSecundaria = tipoVigaSecundaria;
	}
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
	public Peca getKidI() {
		return kidI;
	}
	public void setKidI(Peca kidI) {
		this.kidI = kidI;
	}
	public Peca getKidH() {
		return kidH;
	}
	public void setKidH(Peca kidH) {
		this.kidH = kidH;
	}
	public List<Peca> getVigasPrincipais() {
		return vigasPrincipais;
	}
	public void setVigasPrincipais(List<Peca> vigasPrincipais) {
		this.vigasPrincipais = vigasPrincipais;
	}
	public List<Peca> getVigasSecundarias() {
		return vigasSecundarias;
	}
	public void setVigasSecundarias(List<Peca> vigasSecundarias) {
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
	public List<Peca> getPecasFinais() {
		return pecasFinais;
	}
	public void setPecasFinais(List<Peca> pecasFinais) {
		this.pecasFinais = pecasFinais;
	}
	public String getAlturaVigaEsquerdaX() {
		return alturaVigaEsquerdaX;
	}
	public void setAlturaVigaEsquerdaX(String alturaVigaEsquerdaX) {
		this.alturaVigaEsquerdaX = alturaVigaEsquerdaX;
	}
	public String getAlturaVigaDireitaX() {
		return alturaVigaDireitaX;
	}
	public void setAlturaVigaDireitaX(String alturaVigaDireitaX) {
		this.alturaVigaDireitaX = alturaVigaDireitaX;
	}
	public String getDistanciaCruzetasX() {
		return distanciaCruzetasX;
	}
	public void setDistanciaCruzetasX(String distanciaCruzetasX) {
		this.distanciaCruzetasX = distanciaCruzetasX;
	}
	public String getAlturaVigaEsquerdaY() {
		return alturaVigaEsquerdaY;
	}
	public void setAlturaVigaEsquerdaY(String alturaVigaEsquerdaY) {
		this.alturaVigaEsquerdaY = alturaVigaEsquerdaY;
	}
	public String getAlturaVigaDireitaY() {
		return alturaVigaDireitaY;
	}
	public void setAlturaVigaDireitaY(String alturaVigaDireitaY) {
		this.alturaVigaDireitaY = alturaVigaDireitaY;
	}
	public String getDistanciaCruzetasY() {
		return distanciaCruzetasY;
	}
	public void setDistanciaCruzetasY(String distanciaCruzetasY) {
		this.distanciaCruzetasY = distanciaCruzetasY;
	}
	
	public String getTranspassePrincipais() {
		return transpassePrincipais;
	}

	public void setTranspassePrincipais(String transpassePrincipais) {
		this.transpassePrincipais = transpassePrincipais;
	}

	public String getTranspasseSecundarias() {
		return transpasseSecundarias;
	}

	public void setTranspasseSecundarias(String transpasseSecundarias) {
		this.transpasseSecundarias = transpasseSecundarias;
	}

	
	public String getPeDireito() {
		return peDireito;
	}

	public void setPeDireito(String peDireito) {
		this.peDireito = peDireito;
	}

	public String getEspessuraCompensado() {
		return espessuraCompensado;
	}

	public void setEspessuraCompensado(String espessuraCompensado) {
		this.espessuraCompensado = espessuraCompensado;
	}

	public Peca getPosteEspecial() {
		return posteEspecial;
	}

	public void setPosteEspecial(Peca posteEspecial) {
		this.posteEspecial = posteEspecial;
	}

	public Peca getMacaco() {
		return macaco;
	}

	public void setMacaco(Peca macaco) {
		this.macaco = macaco;
	}

	public Peca getForcado() {
		return forcado;
	}

	public void setForcado(Peca forcado) {
		this.forcado = forcado;
	}

	public List<Peca> getPostes() {
		return postes;
	}

	public void setPostes(List<Peca> postes) {
		this.postes = postes;
	}
	
}
