package br.com.scia.xml.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.RadioButton;

import javax.swing.JOptionPane;

import br.com.scia.xml.controller.PrincipalController;
import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.dao.RepositorioProjeto;
import br.com.scia.xml.entity.exception.SciaXMLFileManagerException;
import br.com.scia.xml.entity.view.Peca;
import br.com.scia.xml.entity.view.SumarioDados;
import br.com.scia.xml.entity.view.Tipo;
import br.com.scia.xml.entity.view.TipoPoste;
import br.com.scia.xml.entity.view.TipoViga;
import br.com.scia.xml.entity.xml.Project;

public class SciaXMLUtils {

    public static String checkString (String s){
    	if (s != null && !"".equals(s))
    		return s;
    	else
    		return "";
    }
    
    public static SumarioDados popularSumarioDados(PrincipalController controller){
		try{
			String tipoEquipamento = checkString(controller.sumarioTipo.getText()); 
			String totalPecas  = checkString(controller.totalPecas.getText());
			String espessura   = checkString(controller.sumarioEspessura.getText());
			String medidaLageX = checkString(controller.sumarioMedidaX.getText());
			String medidaLageY = checkString(controller.sumarioMedidaY.getText());
			String folgaLajeX1 = checkString(controller.sumarioFolgaX1.getText());
			String folgaLajeX2 = checkString(controller.sumarioFolgaX2.getText());
			String folgaLajeY1 = checkString(controller.sumarioFolgaY1.getText());
			String folgaLajeY2 = checkString(controller.sumarioFolgaY2.getText());
			String coordenadaX = checkString(controller.sumarioCoordenadaX.getText()); 
			String coordenadaY = checkString(controller.sumarioCoordenadaY.getText());
			String coordenadaZ = checkString(controller.sumarioCoordenadaZ.getText());
			String espacamentoEntreVigasSecundarias = checkString(controller.entreVigas.getText());
			String tipoVigaPrincipal = checkString(controller.tiposVigasPrincipais.getSelectionModel().getSelectedItem());
			String transpassePrincipais = checkString(controller.transpassePrincipais.getText());
			String tipoVigaSecundaria = checkString(controller.tiposVigasSecundarias.getSelectionModel().getSelectedItem());
			String transpasseSecundarias = checkString(controller.transpasseSecundarias.getText());			
			String peDireito = checkString(controller.peDireito.getText());
			String espessuraCompensado = checkString(controller.espessuraCompensado.getText());
			String composicaoTorres = checkString(controller.sumarioComposicao.getText());
			
			String postesEspeciais = controller.postesEspeciais.getSelectionModel() != null ? checkString(controller.postesEspeciais.getSelectionModel().getSelectedItem()) : null;
			String forcados = controller.forcados.getSelectionModel() != null ? checkString(controller.forcados.getSelectionModel().getSelectedItem()) : null;
			String forcadosX = controller.forcadosX.getSelectionModel() != null ? checkString(controller.forcadosX.getSelectionModel().getSelectedItem()) : null;
			String forcadosY = controller.forcadosY.getSelectionModel() != null ? checkString(controller.forcadosY.getSelectionModel().getSelectedItem()) : null;
			String macacos = controller.macacos.getSelectionModel() != null ? checkString(controller.macacos.getSelectionModel().getSelectedItem()) : null;
			
			if (controller.kidi.getSelectedToggle() != null){
				RadioButton selecao = (RadioButton) controller.kidi.getSelectedToggle();
				String id = selecao.getId();
				if ("comKIDI".equals(id))
					RepositorioProjeto.projeto.setKidI(true);
				else
					RepositorioProjeto.projeto.setKidI(false);
			}
			
			if (controller.kidh.isSelected()){
				RepositorioProjeto.projeto.setKidH(true);
			}
			
			if (controller.entreTravessas.getSelectedToggle() != null){
				RadioButton selecao = (RadioButton) controller.entreTravessas.getSelectedToggle();
				String espaco = selecao.getText();
				RepositorioProjeto.projeto.setEspacamentoEntreTravessas(espaco);
				System.out.println("Espaco informado KITRV = " + espaco);
			}
			
			List<String> travessasX = controller.sumarioPecasX.getItems();
			List<Peca> pecasX = new ArrayList<Peca>();
			for (String tipo : travessasX) {
				Peca peca = new Peca();
				peca.setTipo(tipo);
				peca.setComprimento(RepositorioPecas.pecas.get(tipo).getComprimentoX()*100);				
				pecasX.add(peca);
			}		
			
			List<String> travessasY = controller.sumarioPecasY.getItems();
			List<Peca> pecasY = new ArrayList<Peca>();
			for (String tipo : travessasY) {
				Peca peca = new Peca();
				peca.setTipo(tipo);
				peca.setComprimento(RepositorioPecas.pecas.get(tipo).getComprimentoX()*100);	
				
				pecasY.add(peca);
			}
			
			if (!controller.areaCruzetaX.isDisable()){
				RepositorioProjeto.projeto.setPossuiCruzetaX(true);
				
				String alturaVigaEsquerdaX = checkString(controller.alturaVigaEsquerdaX.getText());
				String alturaVigaDireitaX = checkString(controller.alturaVigaDireitaX.getText());
				String distanciaCruzetasX = checkString(controller.distanciaCruzetasX.getText());
				
				RepositorioProjeto.projeto.setAlturaVigaEsquerdaX(alturaVigaEsquerdaX);
				RepositorioProjeto.projeto.setAlturaVigaDireitaX(alturaVigaDireitaX);
				RepositorioProjeto.projeto.setDistanciaCruzetasX(distanciaCruzetasX);
			}
			
			if (!controller.areaCruzetaY.isDisable()){
				RepositorioProjeto.projeto.setPossuiCruzetaY(true);
				
				String alturaVigaEsquerdaY = checkString(controller.alturaVigaEsquerdaY.getText());
				String alturaVigaDireitaY = checkString(controller.alturaVigaDireitaY.getText());
				String distanciaCruzetasY = checkString(controller.distanciaCruzetasY.getText());
				
				RepositorioProjeto.projeto.setAlturaVigaEsquerdaY(alturaVigaEsquerdaY);
				RepositorioProjeto.projeto.setAlturaVigaDireitaY(alturaVigaDireitaY);
				RepositorioProjeto.projeto.setDistanciaCruzetasY(distanciaCruzetasY);
			}

			if (!controller.areaEscoraX.isDisable()){
				RepositorioProjeto.projeto.setPossuiEscoraX(true);
				
				if (controller.tripeX.isSelected())
					RepositorioProjeto.projeto.setTripeX(true);
				
				Peca forcadoX = new Peca();
				forcadoX.setTipo(forcadosX);
				forcadoX.setComprimento(RepositorioPecas.pecas.get(forcadosX).getComprimentoZ());
				RepositorioProjeto.projeto.setForcadoX(forcadoX);
			}
			
			if (!controller.areaEscoraY.isDisable()){
				RepositorioProjeto.projeto.setPossuiEscoraY(true);
				
				if (controller.tripeY.isSelected())
					RepositorioProjeto.projeto.setTripeY(true);
				
				Peca forcadoY = new Peca();
				forcadoY.setTipo(forcadosY);
				forcadoY.setComprimento(RepositorioPecas.pecas.get(forcadosY).getComprimentoZ());
				RepositorioProjeto.projeto.setForcadoY(forcadoY);
			}
			
			List<String> vigasPrincipais = controller.sumarioVigasPrincipais.getItems();
			List<Peca> vigasP = new ArrayList<Peca>();
			for (String tipo : vigasPrincipais) {
				Peca peca = new Peca();
				peca.setTipo(tipo);
				peca.setComprimento(RepositorioPecas.pecas.get(tipo).getComprimentoX());		
				vigasP.add(peca);
			}		
			
			List<String> vigasSecundarias = controller.sumarioVigasSecundarias.getItems();
			List<Peca> vigasS = new ArrayList<Peca>();
			for (String tipo : vigasSecundarias) {
				Peca peca = new Peca();
				peca.setTipo(tipo);
				peca.setComprimento(RepositorioPecas.pecas.get(tipo).getComprimentoX());		
				vigasS.add(peca);
			}
			
			List<Tipo> postesSelecionados = controller.postes.getItems();
			List<Peca> postes = new ArrayList<Peca>();
			for (Tipo tipo : postesSelecionados) {
				if (tipo instanceof TipoPoste){
					TipoPoste tipoPoste = (TipoPoste) tipo;
					
					if (tipoPoste.getHabilitar().isSelected()){
						Peca peca = new Peca();
						peca.setTipo(tipo.getItem());						
						peca.setComprimento(RepositorioPecas.pecas.get(tipo.getItem()).getComprimentoZ());	
						postes.add(peca);
					}
				}
			}
			
			RepositorioProjeto.projeto.setTotalPecas(totalPecas);
			RepositorioProjeto.projeto.setTipoEquipamento(tipoEquipamento);
			RepositorioProjeto.projeto.setMedidaLageX(medidaLageX);
			RepositorioProjeto.projeto.setMedidaLageY(medidaLageY);
			RepositorioProjeto.projeto.setEspessura(espessura);
			RepositorioProjeto.projeto.setFolgaLajeX1(folgaLajeX1);
			RepositorioProjeto.projeto.setFolgaLajeX2(folgaLajeX2);
			RepositorioProjeto.projeto.setFolgaLajeY1(folgaLajeY1);
			RepositorioProjeto.projeto.setFolgaLajeY2(folgaLajeY2);
			RepositorioProjeto.projeto.setPecasX(pecasX);
			RepositorioProjeto.projeto.setPecasY(pecasY);
			RepositorioProjeto.projeto.setVigasPrincipais(vigasP);
			RepositorioProjeto.projeto.setTipoVigaPrincipal(tipoVigaPrincipal);
			RepositorioProjeto.projeto.setTranspassePrincipais(transpassePrincipais);
			RepositorioProjeto.projeto.setTipoVigaSecundaria(tipoVigaSecundaria);
			RepositorioProjeto.projeto.setEspacamentoEntreVigasSecundarias(espacamentoEntreVigasSecundarias);
			RepositorioProjeto.projeto.setTranspasseSecundarias(transpasseSecundarias);
			RepositorioProjeto.projeto.setVigasSecundarias(vigasS);
			RepositorioProjeto.projeto.setComposicaoTorres(composicaoTorres);
			RepositorioProjeto.projeto.setCoordenadaX(coordenadaX);
			RepositorioProjeto.projeto.setCoordenadaY(coordenadaY);
			RepositorioProjeto.projeto.setCoordenadaZ(coordenadaZ);			
			RepositorioProjeto.projeto.setPeDireito(peDireito);
			RepositorioProjeto.projeto.setEspessuraCompensado(espessuraCompensado);
			RepositorioProjeto.projeto.setPostes(postes);
			
			if (!"".equals(postesEspeciais)){
				Peca posteEspecial = new Peca();
				posteEspecial.setTipo(postesEspeciais);
				posteEspecial.setComprimento(RepositorioPecas.pecas.get(posteEspecial.getTipo()).getComprimentoZ());
				RepositorioProjeto.projeto.setPosteEspecial(posteEspecial);
			}
			
			if (!"".equals(forcados)){
				Peca forcado = new Peca();
				forcado.setTipo(forcados);
				forcado.setComprimento(RepositorioPecas.pecas.get(forcado.getTipo()).getComprimentoZ());
				RepositorioProjeto.projeto.setForcado(forcado);
			}
			
			if (!"".equals(macacos)){
				Peca macaco = new Peca();
				macaco.setTipo(macacos);
				macaco.setComprimento(RepositorioPecas.pecas.get(macaco.getTipo()).getComprimentoZ());
				RepositorioProjeto.projeto.setMacaco(macaco);
			}
						
		}catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Problemas durante a conversão dos dados. " +
					"Por favor, verifique os dados informados", "SciaXML",JOptionPane.ERROR_MESSAGE);
		}
		
		return RepositorioProjeto.projeto;
    }
    
    public static void construirProject(SumarioDados s, File f) {
		Project p = new Project(s,f.getName()+SciaXMLContantes.XML);
		
		try {
			SciaXMLFileManager.project2XML(p, new File(f.getAbsolutePath()+SciaXMLContantes.XML));
		} catch (SciaXMLFileManagerException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(),SciaXMLContantes.TITLE_VALIDACAO,JOptionPane.ERROR_MESSAGE);
		}
	}

    public static ArrayList<String> getItensTipo (ArrayList<Tipo> tipo){
    	ArrayList<String> retorno = new ArrayList<String>();
    	
    	for (Tipo t : tipo) {
    		if (t instanceof TipoViga){
    			if (((TipoViga) t).getHabilitar().isSelected())
    				retorno.add(t.getItem());
    		}else{
    			retorno.add(t.getItem());
    		}
		}
    	
    	return retorno;
    }
    
    public static void getSelectedTiposViga (List<Tipo> vigas, List<Peca> pecas){
    	List<Tipo> retorno = new ArrayList<Tipo>();
    	for (Tipo tipoViga : vigas) {
			((TipoViga) tipoViga).getHabilitar().setSelected(false);
			
			for (Peca peca : pecas) {
				if (tipoViga.getItem().equals(peca.getTipo())){
					((TipoViga) tipoViga).getHabilitar().setSelected(true);
					break;
				}
			}
			
			retorno.add(tipoViga);
		}
    	vigas = retorno;
    }
}
