package br.com.scia.xml.util;

public class SciaXMLContantes {

	// Calculo
	public static final String EIXO_X = "EIXO_X";
	public static final String EIXO_Y = "EIXO_Y";
	
	// Tipos de Equipamento em tela
	public static final String KIBLOC_LAJE = "tipoKIBLOCLaje";  
	public static final String KIBLOC_VIGA = "tipoKIBLOCViga";  
	public static final String ALUTOP = "tipoALUTOP";  
	public static final String ETEM = "tipoETEM";
	
	// Código das Peças
	public static final String KITRV = "KITRV";
	
	// Código dos Containers
	public static final String STRUCT_NODE = "StructNode";
	public static final String STRUCT_NODE_ID = "{D3885EC4-BAE5-11D4-B3FA-00104BC3B531}";
	public static final String STRUCT_NODE_HEADER_H0 = "Name";
	public static final String STRUCT_NODE_HEADER_H1 = "Coord X";
	public static final String STRUCT_NODE_HEADER_H2 = "Coord Y";
	public static final String STRUCT_NODE_HEADER_H3 = "Coord Z";
	public static final String STRUCT_NODE_TABLE = "StructNode";
	public static final String STRUCT_NODE_TABLE_ID = "C4D79AFB-6512-4D56-BD04-E1BEA1C3B085";
	public static final String STRUCT_NODE_TABLE_NAME = "Node";
	
	public static final String BEAM = "Beam";
	public static final String BEAM_ID = "{ECB5D684-7357-11D4-9F6C-00104BC3B443}";
	public static final String BEAM_HEADER_H0 = "Name";
	public static final String BEAM_HEADER_H1 = "Beg. node";
	public static final String BEAM_HEADER_H2 = "End node";
	public static final String BEAM_HEADER_H3 = "CrossSection";
	public static final String BEAM_HEADER_H4 = "FEM type";
	public static final String BEAM_HEADER_H5 = "Member system-line at";
	public static final String BEAM_HEADER_H6 = "ey";
	public static final String BEAM_HEADER_H7 = "ez";
	public static final String BEAM_HEADER_H8 = "Table of geometry";
	public static final String BEAM_TABLE = "Beam";
	public static final String BEAM_TABLE_ID = "BDA197C4-2542-44BC-B851-AB3F37E45882";
	public static final String BEAM_TABLE_NAME = "Beam";
	
	public static final String DATA_LAYER = "DataLayer";
	public static final String LAYER = "Layer";
	
	// SciaXML File
	public static final String SXML = ".sxml";
	
	// XML Final Default Values
	public static final String ENCODING = "<?xml version=\"1.0\" encoding=\"UTF-16\" standalone=\"yes\"?>";
	public static final String XMLNS = "http://www.scia.cz";
	public static final String DEF = ".def";
	public static final String XML = ".xml";
	public static final String CONTAINER_DEFAULT_ID = "";
	
	// Mensagens de validação
	public static final String TITLE_VALIDACAO = "SciaXML Validação";
	public static final String CAMPOS_EM_OBRIGATORIOS = "Favor verificar os campos em destaque antes de prosseguir.";
	public static final String DIRETORIO_VALIDO = "O diretório informado é inválido.";
	public static final String NENHUM_ARQUIVO_PECA = "Nenhum arquivo de peça carregado no sistema.";
	
	// SciaXML CSS Classes
	public static final String CSS_FIELD_OK = "validationFieldOK"; 
	public static final String CSS_FIELD_ERROR = "validationFieldError";
}
