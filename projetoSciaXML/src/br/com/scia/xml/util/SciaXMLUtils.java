package br.com.scia.xml.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.scia.xml.controller.PrincipalController;
import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.dao.RepositorioProjeto;
import br.com.scia.xml.entity.exception.SciaXMLFileManagerException;
import br.com.scia.xml.entity.view.Peca;
import br.com.scia.xml.entity.view.SumarioDados;
import br.com.scia.xml.entity.view.Tipo;
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
			String tipoVigaPrincipal = checkString(controller.tiposVigasPrincipais.getSelectionModel().getSelectedItem());
			String tipoVigaSecundaria = checkString(controller.tiposVigasSecundarias.getSelectionModel().getSelectedItem());
			String alturaVigaEsquerdaX = checkString(controller.alturaVigaEsquerdaX.getText());
			String alturaVigaDireitaX = checkString(controller.alturaVigaDireitaX.getText());
			String distanciaCruzetasX = checkString(controller.distanciaCruzetasX.getText());
			String alturaVigaEsquerdaY = checkString(controller.alturaVigaEsquerdaY.getText());
			String alturaVigaDireitaY = checkString(controller.alturaVigaDireitaY.getText());
			String distanciaCruzetasY = checkString(controller.distanciaCruzetasY.getText());
			
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
			
			List<String> vigasPrincipais = controller.sumarioVigasPrincipais.getItems();
			List<Peca> vigasP = new ArrayList<Peca>();
			for (String tipo : vigasPrincipais) {
				Peca peca = new Peca();
				peca.setTipo(tipo);
				peca.setComprimento(RepositorioPecas.pecas.get(tipo).getComprimentoX()*100);		
				vigasP.add(peca);
			}		
			
			List<String> vigasSecundarias = controller.sumarioVigasSecundarias.getItems();
			List<Peca> vigasS = new ArrayList<Peca>();
			for (String tipo : vigasSecundarias) {
				Peca peca = new Peca();
				peca.setTipo(tipo);
				peca.setComprimento(RepositorioPecas.pecas.get(tipo).getComprimentoX()*100);		
				vigasS.add(peca);
			}
			
			String kidI = null;
			String kidH = null;
			String composicaoTorres = null;
			
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
			RepositorioProjeto.projeto.setKidH(kidH);
			RepositorioProjeto.projeto.setKidI(kidI);
			RepositorioProjeto.projeto.setVigasPrincipais(vigasP);
			RepositorioProjeto.projeto.setTipoVigaPrincipal(tipoVigaPrincipal);
			RepositorioProjeto.projeto.setTipoVigaSecundaria(tipoVigaSecundaria);
			RepositorioProjeto.projeto.setVigasSecundarias(vigasS);
			RepositorioProjeto.projeto.setComposicaoTorres(composicaoTorres);
			RepositorioProjeto.projeto.setCoordenadaX(coordenadaX);
			RepositorioProjeto.projeto.setCoordenadaY(coordenadaY);
			RepositorioProjeto.projeto.setCoordenadaZ(coordenadaZ);			
			RepositorioProjeto.projeto.setAlturaVigaEsquerdaX(alturaVigaEsquerdaX);
			RepositorioProjeto.projeto.setAlturaVigaDireitaX(alturaVigaDireitaX);
			RepositorioProjeto.projeto.setDistanciaCruzetasX(distanciaCruzetasX);
			RepositorioProjeto.projeto.setAlturaVigaEsquerdaY(alturaVigaEsquerdaY);
			RepositorioProjeto.projeto.setAlturaVigaDireitaY(alturaVigaDireitaY);
			RepositorioProjeto.projeto.setDistanciaCruzetasY(distanciaCruzetasY);
			
		}catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Problemas durante a conversão dos dados. " +
					"Por favor, verifique os dados informados", "SciaXML",JOptionPane.ERROR_MESSAGE);
		}
		
		return RepositorioProjeto.projeto;
    }
    
    public static void construirProject(SumarioDados s, File f) {

		// TODO: O método mais zica		
		
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
    
    public static ArrayList<Peca> getComposicaoPostes (PrincipalController controller){
    	ArrayList<Peca> retorno = null;
    	
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
