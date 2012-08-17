package br.com.scia.xml.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.channels.FileChannel;

import br.com.scia.xml.dao.RepositorioProjeto;
import br.com.scia.xml.entity.exception.SciaXMLFileManagerException;
import br.com.scia.xml.entity.view.SumarioDados;
import br.com.scia.xml.entity.xml.Project;

import com.thoughtworks.xstream.XStream;

public class SciaXMLFileManager {

	public static void salvarProjeto(File arquivo)
			throws SciaXMLFileManagerException {
		try {
			if (arquivo != null) {
				XStream xs = new XStream();
				xs.autodetectAnnotations(true);
				xs.setMode(XStream.NO_REFERENCES);
				String xml = xs.toXML(RepositorioProjeto.projeto);

				FileWriter fw = new FileWriter(arquivo);
				fw.write(xml);
				fw.flush();
				fw.close();
			}
		} catch (IOException e) {
			throw new SciaXMLFileManagerException(
					"Problemas para salvar o arquivo.");
		}
	}

	public static void carregarProjeto(File arquivo)
			throws SciaXMLFileManagerException {
		try {
			if (arquivo != null) {
				XStream xs = new XStream();
				xs.processAnnotations(SumarioDados.class);
				RepositorioProjeto.projeto = (SumarioDados) xs.fromXML(arquivo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SciaXMLFileManagerException(
					"Problemas para carregar o arquivo.");
		}
	}

	public static void project2XML(Project p, File f)
			throws SciaXMLFileManagerException {
		try {
			if (p != null && f != null) {
				XStream xs = new XStream();
				xs.autodetectAnnotations(true);
				xs.setMode(XStream.NO_REFERENCES);

				String xml = SciaXMLContantes.ENCODING + xs.toXML(p);

				OutputStream stream = new FileOutputStream(f);
				Writer fw = new OutputStreamWriter(stream, "UTF-16");

				fw.write(xml);
				fw.flush();
				fw.close();
				
				gerarDefFile(f);
			} else {
				throw new SciaXMLFileManagerException(
						"Impossível gerar o arquivo final. Verifique os parâmetros de entrada.");
			}
		} catch (IOException e) {
			throw new SciaXMLFileManagerException(e.getMessage());
		}
	}

	private static void gerarDefFile(File f) throws SciaXMLFileManagerException{
		try{
			if (f != null){
				File origem = new File(SciaXMLFileManager.class.getResource(SciaXMLContantes.DEF_PADRAO).getFile());
				File destino = new File(f.getAbsoluteFile()+SciaXMLContantes.DEF);
				copyFile(origem, destino);
			}else{
				throw new SciaXMLFileManagerException("Impossível gerar o arquivo final. Verifique os parâmetros de entrada.");
			}
		}catch (IOException e) {
			throw new SciaXMLFileManagerException(e.getMessage());
		}
	}

	private static void copyFile(File sourceFile, File destFile)
			throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}
}
