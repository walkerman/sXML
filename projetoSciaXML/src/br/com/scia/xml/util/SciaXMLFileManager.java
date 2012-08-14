package br.com.scia.xml.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import br.com.scia.xml.dao.RepositorioProjeto;
import br.com.scia.xml.entity.exception.SciaXMLFileManagerException;
import br.com.scia.xml.entity.view.SumarioDados;
import br.com.scia.xml.entity.xml.Project;

import com.thoughtworks.xstream.XStream;

public class SciaXMLFileManager {

	public static void salvarProjeto(File arquivo) throws SciaXMLFileManagerException{
		try{
			if (arquivo != null){
				XStream xs = new XStream();
				xs.autodetectAnnotations(true);
				xs.setMode(XStream.NO_REFERENCES);
				String xml = xs.toXML(RepositorioProjeto.projeto);
				
				FileWriter fw = new FileWriter(arquivo);
				fw.write(xml);
				fw.flush();
				fw.close();
			}
		}catch (IOException e) {
			throw new SciaXMLFileManagerException("Problemas para salvar o arquivo.");
		}
	}
	
	public static void carregarProjeto(File arquivo) throws SciaXMLFileManagerException{
		try{
			if (arquivo != null){
				XStream xs = new XStream();
				xs.processAnnotations(SumarioDados.class);
				RepositorioProjeto.projeto = (SumarioDados) xs.fromXML(arquivo);
			}
		}catch (Exception e) {
			e.printStackTrace();
			throw new SciaXMLFileManagerException("Problemas para carregar o arquivo.");
		}
	}

	public static void project2XML(Project p, File f) throws SciaXMLFileManagerException{
		try{
			if (p != null && f != null){
				XStream xs = new XStream();
				xs.autodetectAnnotations(true);
				xs.setMode(XStream.NO_REFERENCES);
				
				String xml = SciaXMLContantes.ENCODING + xs.toXML(p);
								
				OutputStream stream = new FileOutputStream(f);  
				Writer fw = new OutputStreamWriter(stream, "UTF-16");
				
				fw.write(xml);
				fw.flush();
				fw.close();
			}else{
				throw new SciaXMLFileManagerException("Impossível gerar o arquivo final. Verifique os parâmetros de entrada.");
			}
		}catch (IOException e) {
			throw new SciaXMLFileManagerException(e.getMessage());
		}
	}
}
