package br.com.scia.xml.model;

public class Identificadores {

	private static Integer identificadorNo = 1;
	private static Integer identificarPecas= 1;
	
	public static Integer getIdentificadorNo() {
		return identificadorNo++;
	}
	public static Integer getIdentificarPecas() {
		return identificarPecas++;
	}

}
