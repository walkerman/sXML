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
			String tipoEquipamento = controller.sumarioTipo.getText(); 
			String totalPecas = controller.totalPecas.getText();
			String espessura   = controller.sumarioEspessura.getText();
			String medidaLageX = controller.sumarioMedidaX.getText();
			String medidaLageY = controller.sumarioMedidaY.getText();
			String folgaLajeX1 = controller.sumarioFolgaX1.getText();
			String folgaLajeX2 = controller.sumarioFolgaX2.getText();
			String folgaLajeY1 = controller.sumarioFolgaY1.getText();
			String folgaLajeY2 = controller.sumarioFolgaY2.getText();
			String coordenadaX = controller.sumarioCoordenadaX.getText(); 
			String coordenadaY = controller.sumarioCoordenadaY.getText();
			String coordenadaZ = controller.sumarioCoordenadaZ.getText();
			
			List<String> travessasX = controller.sumarioTravessasX.getItems();
			List<Peca> pecasX = new ArrayList<Peca>();
			for (String tipo : travessasX) {
				Peca peca = new Peca();
				peca.setTipo(tipo);
				peca.setComprimentoPecaX(RepositorioPecas.pecas.get(tipo).getComprimentoX()*100);				
				
				System.out.println("ProjetoX " + peca.getComprimentoPecaX());
				
				pecasX.add(peca);
			}		
			
			List<String> travessasY = controller.sumarioTravessasY.getItems();
			List<Peca> pecasY = new ArrayList<Peca>();
			for (String tipo : travessasY) {
				Peca peca = new Peca();
				peca.setTipo(tipo);
				peca.setComprimentoPecaY(RepositorioPecas.pecas.get(tipo).getComprimentoX()*100);
				
				System.out.println("ProjetoY " + peca.getComprimentoPecaY());

				pecasY.add(peca);
			}
			
			String kidI = null;
			String kidH = null;
			List<String> vigasPrincipais = null;
			List<String> vigasSecundarias = null;
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
			RepositorioProjeto.projeto.setVigasPrincipais(vigasPrincipais);
			RepositorioProjeto.projeto.setVigasSecundarias(vigasSecundarias);
			RepositorioProjeto.projeto.setComposicaoTorres(composicaoTorres);
			RepositorioProjeto.projeto.setCoordenadaX(coordenadaX);
			RepositorioProjeto.projeto.setCoordenadaY(coordenadaY);
			RepositorioProjeto.projeto.setCoordenadaZ(coordenadaZ);
			
		}catch (Exception e) {
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
}
