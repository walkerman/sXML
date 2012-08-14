package br.com.scia.xml.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.scia.xml.entity.exception.RepositorioPecasException;
import br.com.scia.xml.entity.view.TipoPecaXY;
import br.com.scia.xml.entity.xml.Container;
import br.com.scia.xml.entity.xml.Object;
import br.com.scia.xml.entity.xml.Project;
import br.com.scia.xml.entity.xml.TableNode;
import br.com.scia.xml.util.SciaXMLContantes;

import com.thoughtworks.xstream.XStream;

public class RepositorioPecas {

	public static HashMap<String, Project> pecas;
	public static HashMap<String, TipoPecaXY> tiposPecasXY;
	
	static{
		if (pecas == null)
			RepositorioPecas.pecas = new HashMap<>();
			
		if (tiposPecasXY == null)
			RepositorioPecas.tiposPecasXY = new HashMap<>();
	}
	
	public static void addPecas(List<File> arquivos) throws RepositorioPecasException{
		if (arquivos != null && arquivos.size() > 0){
			boolean parseOK = true;
			ArrayList<Project> objetos = new ArrayList<Project>();
			
			try {
				// Primeiro tenta realizar o parse de todos os arquivos de entrada.
				// Se não consegue, informa qual deu erro e não adiciona nenhuma arquivo
				for (File f : arquivos) {
					Project p = parseXML(f);
					p.setFileName(f.getName().replaceAll(".xml", new String()));
					objetos.add(p);
				}
			}catch (Exception e) {
				parseOK = false;
				throw new RepositorioPecasException(e.getMessage());
			}finally{
				if (parseOK){
					for (Project project : objetos) {
						project = calcularComprimento(project);
						RepositorioPecas.pecas.put(project.getFileName(), project);
					}
					
				}
			}
		}	
	}
	
	private static Project calcularComprimento(Project project) {
		
		if (project != null){
			List<Container> containers = project.getContainers();
		
			if (containers != null && containers.size() > 0){
				Container nos = null;
				for (Container container : containers) {
					if (container.getT().contains(SciaXMLContantes.STRUCT_NODE)){
						nos = container;
						break;
					}
				}
				
				if (nos != null){
					TableNode t = nos.getTable();
					
					if (t != null){
						List<Object> objetos = t.getObjects();
						
						if (objetos != null && objetos.size() > 1){
							
							Object noInicial = objetos.get(0);
							Object noFinal = objetos.get(1);
														
							try{
								double noInicialP1 = Double.parseDouble(noInicial.getP1().getV());
								double noFinalP1 = Double.parseDouble(noFinal.getP1().getV());
								
								double noInicialP2 = Double.parseDouble(noInicial.getP2().getV());
								double noFinalP2 = Double.parseDouble(noFinal.getP2().getV());
								
								project.setComprimentoX(noFinalP1 - noInicialP1);
								project.setComprimentoY(noFinalP2 - noInicialP2);
							}catch (Exception e) {
								e.printStackTrace();
								System.out.println("Problemas durante o calculo.");
							}
						}
					}
				}
				
			}
		}
		
		
		
		return project;
	}

	private static Project parseXML(File file) throws RepositorioPecasException{
		Project retorno = null;
		
		try {
			XStream xStream = new XStream();
			xStream.autodetectAnnotations(true);
			xStream.alias("project", Project.class);
			
			retorno = (Project) xStream.fromXML(file);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			throw new RepositorioPecasException("Problemas durante o parse do arquivo: " + file.getAbsolutePath() + ". Verifique os arquivos do diretório de peças informado.");
		}
		
		return retorno;
	}
}
