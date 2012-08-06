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
	public static final String DATA_LAYER = "DataLayer";
	public static final String BEAM = "Beam";
	public static final String STRUCT_NODE = "StructNode";
	public static final String LAYER = "Layer";
	
	// SciaXML File
	public static final String SXML = ".sxml";
	
	// XML Final Default Values
	public static final String ENCODING = "<?xml version=\"1.0\" encoding=\"UTF-16\" standalone=\"yes\"?>";
	public static final String XMLNS = "http://www.scia.cz";
	public static final String DEF = ".def";
	public static final String XML = ".xml";
	public static final String ID_CONTAINER = "{D3885EC4-BAE5-11D4-B3FA-00104BC3B531}";
	
	// Mensagens de validação
	public static final String TITLE_VALIDACAO = "SciaXML Validação";
	public static final String CAMPOS_EM_OBRIGATORIOS = "Favor verificar os campos em destaque antes de prosseguir.";
	public static final String DIRETORIO_VALIDO = "O diretório informado é inválido.";
	public static final String NENHUM_ARQUIVO_PECA = "Nenhum arquivo de peça carregado no sistema.";
	
	// SciaXML CSS Classes
	public static final String CSS_FIELD_OK = "validationFieldOK"; 
	public static final String CSS_FIELD_ERROR = "validationFieldError";
}
