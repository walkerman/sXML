package br.com.scia.xml.test;

import java.io.File;
import java.io.FileWriter;

import br.com.scia.xml.entity.xml.Project;

import com.thoughtworks.xstream.XStream;

public class XML2ObjectTest {
	public static void main(String[] args){
		
		String header = "<?xml version=\"1.0\" encoding=\"UTF-16\" standalone=\"yes\"?>\n";
		
		XStream xStream = new XStream();
		xStream.autodetectAnnotations(true);
		xStream.alias("project", Project.class);
		
		try{
			File file = new File(Thread.currentThread().getContextClassLoader().getResource("teste.xml").getFile());
			Project project = (Project) xStream.fromXML(file);
			
			String xmlFinal = header + xStream.toXML(project);	
			
			System.out.println("### Resultado da conversão xml -> objeto ->xml: ###\n");
			System.out.println(xmlFinal);
		
			
// Comparação arquivo origem x arquivo final não bate devido a identação gerada
// TODO: Validar via sistema Scia mesmo
			
			FileWriter writer = new FileWriter("C:\\Teste\\resultado.xml");
			writer.write(xmlFinal);
			writer.close();
			
		}catch (Exception e) {
			System.out.println("Problemas para carregar o arquivo .xml.");
			e.printStackTrace();
		}
	}
}
