package br.com.scia.xml.test;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class RegraPaginacaoPostes {
	private static Double ALTURA_UTIL = 0.0; // Parametro de tela
	private static Double ALTURA_MIN = 0.0;
	private static Double ALTURA_MAX = 0.0;
	private static final ArrayList<Short> POSTES = new ArrayList<Short>(Arrays.asList(new Short[]{25,50,100,150,200,300})); // Parametro de tela
	private static final ArrayList<Short> COMBINACAO_FINAL = new ArrayList<Short>();
	
	public static void main(String[] args){ 
		String valor = JOptionPane.showInputDialog(null,"Informe a área útil:");
		
		if (valor != null){			
			ALTURA_UTIL = Double.parseDouble(valor);
			ALTURA_MAX = ALTURA_UTIL - 20.0;
			ALTURA_MIN = ALTURA_UTIL - 50.0;
			
			boolean achou = false;
			
			short valor1 = 0;
			short valor2 = 0;
			short valor3 = 0;
			
			for (int i = 0; i <= 5; i++) {
				valor1 = POSTES.get(i);
				
				for (int j = 5; j >= 0; j--) {
					
					if ((ALTURA_UTIL < 600) && (POSTES.get(j) >= 300))
						continue;
					
					valor2 = POSTES.get(j);
					
					if ((valor1 + valor2) <= ALTURA_MAX){
	
						for (int j2 = j; j2 >= 0; j2--) {
							valor3 = POSTES.get(j2);
							
							if ((valor1+valor2+valor3) >= ALTURA_MIN && (valor1+valor2+valor3) <= ALTURA_MAX){
								achou = true;
								break;
							}
						}
					}
					if(achou){					
						break;
					}
				}
				if(achou){					
					break;
				}
			}
			
			if(achou){					
				COMBINACAO_FINAL.addAll(Arrays.asList(new Short[]{valor1,valor2,valor3}));
				System.out.println(COMBINACAO_FINAL.toString());
			}else{
				System.out.println("Não Achou Combinação");
			}
		}		
	}
}
