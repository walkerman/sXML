package br.com.scia.xml.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import br.com.scia.xml.entity.exception.RepositorioPecasException;
import br.com.scia.xml.entity.view.Peca;
import br.com.scia.xml.entity.xml.Container;
import br.com.scia.xml.entity.xml.Object;
import br.com.scia.xml.entity.xml.Project;
import br.com.scia.xml.entity.xml.TableNode;
import br.com.scia.xml.util.ObjectSorter;
import br.com.scia.xml.util.SciaXMLConstantes;

import com.thoughtworks.xstream.XStream;

public class RepositorioPecas {

	public static HashMap<String, Project> pecas;
	public static List<Peca> listaTravessas;
	public static List<Peca> listaTravessasReplicadas;
	public static List<Peca> listaRosaceas;
	public static List<Peca> listaPostes;
	
	static{
		if (RepositorioPecas.pecas == null)
			RepositorioPecas.pecas = new HashMap<>();
			
		if (RepositorioPecas.listaTravessas == null)
			RepositorioPecas.listaTravessas = new ArrayList<>();
				
		if (RepositorioPecas.listaRosaceas == null)
			RepositorioPecas.listaRosaceas = new ArrayList<>();
			
		if (RepositorioPecas.listaPostes == null)
			RepositorioPecas.listaPostes = new ArrayList<>();
			
		if (RepositorioPecas.listaTravessasReplicadas == null)
			RepositorioPecas.listaTravessasReplicadas = new ArrayList<>();
	}
	
	public static void addPeca(File f) throws RepositorioPecasException{
		if (f != null){
			boolean parseOK = true;
			ArrayList<Project> objetos = new ArrayList<Project>();
			
			try {
				// Primeiro tenta realizar o parse de todos os arquivos de entrada.
				// Se não consegue, informa qual deu erro e não adiciona nenhuma arquivo
				if (f.isDirectory()){
					File[] arquivos = f.listFiles();
					for (File file : arquivos) {
						RepositorioPecas.addPeca(file);
					}
				}
				
				if (f.isFile() && f.getAbsolutePath().endsWith(SciaXMLConstantes.XML)){
					Project p = parseXML(getUTF16File(f), f.getName());
					p.setFileName(f.getName().replaceAll(SciaXMLConstantes.XML, new String()));
					objetos.add(p);
				}
				
			}catch (Exception e) {
				parseOK = false;
				e.printStackTrace();
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
	
	private static Project parseXML(InputStreamReader file, String nomeArquivo) throws RepositorioPecasException{
		Project retorno = null;
		
		try {
			XStream xStream = new XStream();
			xStream.autodetectAnnotations(true);
			xStream.alias("project", Project.class);
			
			retorno = (Project) xStream.fromXML(file);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro: " + e.getMessage());
			throw new RepositorioPecasException("Problemas durante o parse do arquivo: " + nomeArquivo + ". Verifique os arquivos do diretório de peças informado.");
		}
		
		return retorno;
	}
	
	private static Project calcularComprimento(Project project) {
		
		if (project != null){
			List<Container> containers = project.getContainers();
		
			if (containers != null && containers.size() > 0){
				Container nos = null;
				for (Container container : containers) {
					if (container.getT().contains(SciaXMLConstantes.STRUCT_NODE)){
						nos = container;
						break;
					}
				}
				
				if (nos != null){
					TableNode t = nos.getTable();
					
					if (t != null){
						List<Object> objetos = t.getObjects();
						Collections.sort(objetos, new ObjectSorter());
						
						if (objetos != null && objetos.size() > 1){
							
							Object noInicial = objetos.get(0);
							Object noFinal = objetos.get(1);
														
							try{
								double noInicialX = Double.parseDouble(noInicial.getP1().getV());
								double noFinalX = Double.parseDouble(noFinal.getP1().getV());
								
								double noInicialY = Double.parseDouble(noInicial.getP2().getV());
								double noFinalY = Double.parseDouble(noFinal.getP2().getV());
								
								double noInicialZ = Double.parseDouble(noInicial.getP3().getV());
								double noFinalZ = Double.parseDouble(noFinal.getP3().getV());
								
								double x = (noFinalX - noInicialX);
								double y = (noFinalY - noInicialY);
								double z = (noFinalZ - noInicialZ);
								
								project.setComprimentoX(x);
								project.setComprimentoY(y);
								project.setComprimentoZ(z);
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
	
	private static InputStreamReader getUTF16File (File f){
		InputStreamReader retorno = null;
		try {
			InputStream is = new FileInputStream(f);
			retorno = new InputStreamReader(is, "UTF-16");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return retorno;
	}
}
