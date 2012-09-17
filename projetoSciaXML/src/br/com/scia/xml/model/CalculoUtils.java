package br.com.scia.xml.model;

import java.util.HashMap;

import javax.swing.JOptionPane;

import br.com.scia.xml.controller.PrincipalController;
import br.com.scia.xml.dao.RepositorioPecas;
import br.com.scia.xml.entity.exception.SciaXMLValidationException;
import br.com.scia.xml.entity.view.Peca;
import br.com.scia.xml.entity.view.SumarioDados;
import br.com.scia.xml.util.SciaXMLConstantes;
import br.com.scia.xml.util.SciaXMLUtils;

public class CalculoUtils {
	
	public static SumarioDados calcularEstruturaFinal(PrincipalController controller){
		SumarioDados sumario = null;
		
		try {
			sumario = SciaXMLUtils.popularSumarioDados(controller);
			
			try {
				sumario = Calculo.calculaEstrutura(sumario);
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(null, e2.getMessage(),SciaXMLConstantes.TITLE_VALIDACAO,JOptionPane.ERROR_MESSAGE);
			}
		} catch (SciaXMLValidationException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage(), SciaXMLConstantes.TITLE_VALIDACAO,JOptionPane.ERROR_MESSAGE);
		} catch (Exception e2) {
			e2.printStackTrace();
			JOptionPane.showMessageDialog(null, "Problemas durante a conversão dos dados. " +
					"Por favor, verifique os dados informados", SciaXMLConstantes.TITLE_VALIDACAO,JOptionPane.ERROR_MESSAGE);
		}
		
		return sumario;
	}
	
	public static HashMap<String, String> vinculaTipoPecas(){

		HashMap<String, String> map = new HashMap<String, String>();

		for (int i = 0; i < Calculo.dados.getPecasX().size(); i++) {
			for (int j = 0; j < Calculo.dados.getPecasY().size(); j++) {
				map.put(i+"|"+j, Calculo.dados.getPecasX().get(i).getTipo()+"|"+Calculo.dados.getPecasY().get(j).getTipo());
//				System.out.println(i+"|"+j+ "|" + Calculo.dados.getPecasX().get(i).getTipo()+"|"+Calculo.dados.getPecasY().get(j).getTipo());
			}
		}

		return map;
	}
	
	public static Double getAlturaUtil(){
		Double altura = 0.0;
		if (!"".equals(SciaXMLUtils.checkString(Calculo.dados.getPeDireito()))){
    		Double peDireito = Double.parseDouble(Calculo.dados.getPeDireito())/100.0;
    		Double espessuraLaje = Double.parseDouble(Calculo.dados.getEspessura())/100.0;
    		Double espessuraCompensado = Double.parseDouble(Calculo.dados.getEspessuraCompensado())/100.0;
    		Double espessuraVigaPrincipal = CalculoVigasPrincipais.getAlturaViga();
    		Double espessuraVigaSecundaria = CalculoVigasSecundarias.getAlturaViga(); 
    		Double tamanhoPosteEspecial = Calculo.dados.getPosteEspecial().getComprimento();
    		
    		altura = peDireito - espessuraCompensado - espessuraLaje - espessuraVigaPrincipal - espessuraVigaSecundaria - tamanhoPosteEspecial;
    	}
		return altura;
	} 
	
	public static Double getAlturaUtilCalculo(){
		return getAlturaUtil()*100.0;
	} 
	
	public static void populaListaForcado(){
		for (Peca peca : Calculo.dados.getPecasFinais()) {			
			if (peca.getTipo().contains(SciaXMLConstantes.FORCADO));
				RepositorioPecas.listaForcados.add(peca);			   
		}
	}
	
	public static Double getPisoAFundo () throws SciaXMLValidationException{
		Double retorno = 0.0;
		
		if (Calculo.dados == null)
			throw new SciaXMLValidationException();
		
		if (Calculo.dados.getPeDireito() == null)
			throw new SciaXMLValidationException();
		
		if (Calculo.dados.getEspessura() == null)
			throw new SciaXMLValidationException();
		
		Double peDireito = Double.parseDouble(Calculo.dados.getPeDireito());
		Double espessura = Double.parseDouble(Calculo.dados.getEspessura());
		
		retorno = peDireito - espessura;
		
		return retorno;
	}
	
	public static Double getAjusteTotal () throws SciaXMLValidationException{
		return getAlturaMacacoEForcado()*2.0*100.0;
	}
	
	public static Double getAlturaMacacoEForcado(){
		Double retorno = 0.0;
		
		retorno = (Calculo.getAlturaUtil() - getAlturaPostesFinais())/2;
		
		return retorno;
	}
	
	public static Double getAlturaPostesFinais (){
		Double retorno = 0.0;
		if (RepositorioPecas.listaPostes != null && RepositorioPecas.listaPostes.size() > 0){
			for (Peca p : RepositorioPecas.listaPostes) {
				retorno += p.getComprimento();
			}
		}
		return retorno;
	}
	
	public static Double getAjusteInferior () throws SciaXMLValidationException{
		return getAjusteTotal()/2.0;
	}
	
	public static Double getAjusteSuperior () throws SciaXMLValidationException{
		return getAjusteTotal()/2.0;
	}
	
}
