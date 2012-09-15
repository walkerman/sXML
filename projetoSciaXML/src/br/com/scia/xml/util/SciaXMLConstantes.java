package br.com.scia.xml.util;

public class SciaXMLConstantes {

	// Calculo
	public static final String EIXO_X = "EIXO_X";
	public static final String EIXO_Y = "EIXO_Y";
	public static final String AMBOS_OS_EIXOS = "AMBOS_OS_EIXOS";
	public static final String INDEXADOR_NO = "N";
	public static final String INDEXADOR_PECA = "B";
	public static final Double PRECISAO_ENVIO_COORDENADAS_XML = 100.0;
	public static final Double VALOR_ALTURA_MAX = 0.2;
	public static final Double VALOR_ALTURA_MIN = 0.5;
	public static final Double TAMANHO_MIN_ESCORA_A =  0.55;
	public static final Double TAMANHO_MAX_ESCORA_A =  1.67;
	public static final Double TAMANHO_MIN_ESCORA_B =  1.55;
	public static final Double TAMANHO_MAX_ESCORA_B =  2.75;			
	public static final Double TAMANHO_MIN_ESCORA_C =  0.48;
	public static final Double TAMANHO_MAX_ESCORA_C =  1.38;
	public static final Double TAMANHO_MIN_ESCORA_D =  2.05;
	public static final Double TAMANHO_MAX_ESCORA_D =  3.50;
	public static final Double ALTURA_ROSACEA = 0.00750;
	public static final Double METADE_ESPESSURA_VIGA = -0.04;
	public static final Double METADE_COMPRIMENTO_DET_FORCADO = 0.1;
	public static final Double METADE_COMPRIMENTO_TOPO_FORCADO = 0.1;
	public static final Double TAMANHO_HORIZONTAL_CRUZETA = 0.66;    	// TAMANHO PECA HORIZONTAL CRUZETA
	public static final Double ALTURA_CRUZETA_HORIZONTAL = 0.11;    	// TAMANHO PECA HORIZONTAL CRUZETA
	public static final Double ALTURA_CONSOLE_DIAGONAL = 0.95;
	
	// Tipos de Equipamento em tela
	public static final String KIBLOC_LAJE = "tipoKIBLOCLaje";  
	public static final String KIBLOC_VIGA = "tipoKIBLOCViga";  
	public static final String ALUTOP = "tipoALUTOP";  
	public static final String ETEM = "tipoETEM";
	public static final String COM_KIDI = "comKIDI";
	public static final String ESQUERDA = "Esquerda";
	public static final String DIREITA = "Direita";
	
	// Tipos de Peças em tela
	public static final String TIPO_TRAVESSA = "Travessa";  
	public static final String TIPO_ESCORA = "Escora";  
	public static final String TIPO_ESCORA_A = "Escora A"; 
	public static final String TIPO_ESCORA_B = "Escora B"; 
	public static final String TIPO_ESCORA_C = "Escora C"; 
	public static final String TIPO_ESCORA_D = "Escora D"; 
	public static final String TIPO_CRUZETA = "Cruzeta";  
	public static final String TIPO_VIGA_HT20 = "HT20";
	public static final String TIPO_VIGA_V75 = "V7.5";
	public static final String TIPO_VIGA_V18 = "V18";
	public static final String TIPO_VIGA_VA18 = "VA18";
	
	// Validações em tela
	public static final String ORIGEM_X = "X";  
	public static final String ORIGEM_Y = "Y";  
	
	// Código das Peças
	public static final String KITRV = "KITRV";					// TRAVESSAS
	public static final String MACACO = "MACACO";				// MACACO
	public static final String FORCADO = "FORCADO"; 			// FORCADO
	public static final String KIP = "KIP";						// POSTES
	public static final String CONSOLE = "CONSOLE";				// CONSOLE
	public static final String CONSOLE_DIAGONAL = "CONSOLE_DIAGONAL";	//CONSOLE_DIAGONAL
	public static final String VM20 = "VM20";					// VIGAS TIPO HT20
	public static final String VI07 = "VI07";					// VIGAS TIPO V75
	public static final String VI18 = "VI18";					// VIGAS TIPO V18
	public static final String VA18 = "VA18";					// VIGAS TIPO VA18
	public static final String KID = "KID";						// DIAGONAIS VERTICAIS
	public static final String KIDH = "KIDH";					// DIAGONAIS HORIZONTAIS
	public static final String CRU = "Cru";						// CRUZETAS
	public static final String CRUZETA_HORIZONTAL = "Horizontal_Cr";// CRUZETAS HORIZONTAL
	public static final String ESC = "Esc";						// ESCORAS
	public static final String BASE_ESCORA="BASE_SIMPLES_ESCORA"; // BASE ESCORA
	public static final String TOPO_ESCORA="TOPO_ESCORA";        // TOPO ESCORA
	public static final String FORC = "FORC";					// FORCADOS
	public static final String KIP025RE = "KIP025RE";			// POSTE_ESPECIAL
	public static final String KIP037R1 = "KIP037R1";			// POSTE_ESPECIAL
	public static final String KIP037R2 = "KIP037R2";			// POSTE_ESPECIAL
	public static final String KIP050RE = "KIP050RE";			// POSTE_ESPECIAL
	public static final String KIP070R2 = "KIP070R2";			// POSTE_ESPECIAL
	public static final String KIP025R1 = "KIP025R1";			// POSTE
	public static final String KIP050R1 = "KIP050R1";			// POSTE
	public static final String KIP100R2 = "KIP100R2";			// POSTE
	public static final String KIP150R3 = "KIP150R3";			// POSTE
	public static final String KIP200R4 = "KIP200R4";			// POSTE
	public static final String KIP300R6 = "KIP300R6";			// POSTE
	public static final String ROSACEA = "ROSACEA";				// ROSACEA
	public static final String TOPO_FORCADO = "TOPO_FORCADO";				// TOPO_FORCADO
	public static final String DET_FORCADO  = "DET_FORCADO";				// DET_FORCADO
	public static final String KIDI1215 = "KIDI1215";			// TRAVESSAS DIAGONAIS VERTICAIS
	public static final String KIDI1220 = "KIDI1220";			// TRAVESSAS DIAGONAIS VERTICAIS
	public static final String KIDI1715 = "KIDI1715";			// TRAVESSAS DIAGONAIS VERTICAIS
	public static final String KIDI1720 = "KIDI1720";			// TRAVESSAS DIAGONAIS VERTICAIS
	public static final String KIDI2420 = "KIDI2420";			// TRAVESSAS DIAGONAIS VERTICAIS
	public static final String KIDH085 = "KIDH085";				// TRAVESSAS DIAGONAIS HORIZONTAIS
	public static final String KIDH120 = "KIDH120";				// TRAVESSAS DIAGONAIS HORIZONTAIS
	public static final String KIDH240 = "KIDH240";				// TRAVESSAS DIAGONAIS HORIZONTAIS
	public static final String KIDH1285 = "KIDH1285";			// TRAVESSAS DIAGONAIS HORIZONTAIS
	public static final String KIDH1712 = "KIDH1712";			// TRAVESSAS DIAGONAIS HORIZONTAIS
	public static final String KIDH1785 = "KIDH1785";			// TRAVESSAS DIAGONAIS HORIZONTAIS
	public static final String KIDH2412 = "KIDH2412";			// TRAVESSAS DIAGONAIS HORIZONTAIS
	public static final String KIDH2417 = "KIDH2417";			// TRAVESSAS DIAGONAIS HORIZONTAIS
	public static final String KIDH2485 = "KIDH2485";			// TRAVESSAS DIAGONAIS HORIZONTAIS
	
	// Código dos Containers
	// Container de Nós
	public static final String STRUCT_NODE = "StructNode";
	public static final String STRUCT_NODE_TITLE = "EP_DSG_Elements.EP_StructNode.1";
	public static final String STRUCT_NODE_ID = "{39A7F468-A0D4-4DFF-8E5C-5843E1807D13}";
	public static final String STRUCT_NODE_HEADER_H0 = "Name";
	public static final String STRUCT_NODE_HEADER_H1 = "Coord X";
	public static final String STRUCT_NODE_HEADER_H2 = "Coord Y";
	public static final String STRUCT_NODE_HEADER_H3 = "Coord Z";
	public static final String STRUCT_NODE_TABLE = "EP_DSG_Elements.EP_StructNode.1";
	public static final String STRUCT_NODE_TABLE_ID = "C4D79AFB-6512-4D56-BD04-E1BEA1C3B085";
	public static final String STRUCT_NODE_TABLE_NAME = "Node";
	
	// Container de Peças
	public static final String BEAM = "Beam";
	public static final String BEAM_TITLE = "EP_DSG_Elements.EP_Beam.1";
	public static final String BEAM_ID = "{ECB5D684-7357-11D4-9F6C-00104BC3B443}";
	public static final String BEAM_HEADER_H0 = "Name";
	public static final String BEAM_HEADER_H1 = "Beg. node";
	public static final String BEAM_HEADER_H2 = "End node";
	public static final String BEAM_HEADER_H3 = "CrossSection";
	public static final String BEAM_HEADER_H4 = "Alpha";
	public static final String BEAM_HEADER_H5 = "Member system-line at";
	public static final String BEAM_HEADER_H6 = "ey";
	public static final String BEAM_HEADER_H7 = "ez";
	public static final String BEAM_HEADER_H8 = "LCS Rotation";
	public static final String BEAM_HEADER_H9 = "Layer";
	public static final String BEAM_HEADER_H10 = "Perp. alignment";
	public static final String BEAM_HEADER_H11 = "Eccentricity y";
	public static final String BEAM_HEADER_H12 = "Eccentricity z";
	public static final String BEAM_TABLE = "EP_DSG_Elements.EP_Beam.1";
	public static final String BEAM_TABLE_ID = "C803A5AE-8F20-4D47-BC52-1685A71CB594";
	public static final String BEAM_TABLE_NAME = "Member 1D";
	
	// Container de Camadas
	public static final String DATA_LAYER = "Layer";
	public static final String DATA_LAYER_TITLE = "EP_DSG_Elements.EP_DataLayer.1";
	public static final String DATA_LAYER_ID = "{D3885EC4-BAE5-11D4-B3FA-00104BC3B531}";
	public static final String DATA_LAYER_HEADER_H0 = "Name";
	public static final String DATA_LAYER_HEADER_H1 = "Structural model only";
	public static final String DATA_LAYER_TABLE = "EP_DSG_Elements.EP_DataLayer.1";
	public static final String DATA_LAYER_TABLE_ID = "E751D264-310F-4636-9FCB-A27B096B0ABC";
	public static final String DATA_LAYER_TABLE_NAME = "Layers";
	
	
	// SciaXML File
	public static final String SXML = ".sxml";
	
	// XML Final Default Values
	public static final String ENCODING = "<?xml version=\"1.0\" encoding=\"UTF-16\" standalone=\"yes\"?>";
	public static final String XMLNS = "http://www.scia.cz";
	public static final String DEF = ".def";
	public static final String XML = ".xml";
	public static final String DEF_PADRAO = "etc/padrao.xml.def";
	
	// Mensagens de validação
	public static final String TITLE_VALIDACAO = "SciaXML Validação";
	public static final String CAMPOS_EM_OBRIGATORIOS = "Favor verificar os campos em destaque antes de prosseguir.";
	public static final String DIRETORIO_VALIDO = "O diretório informado é inválido.";
	public static final String NENHUM_ARQUIVO_PECA = "Nenhum arquivo de peça carregado no sistema.";
	public static final String LAJE_IMCOMPATIVEL = "Tamanho da LAJE insuficiente para os parâmetros.";
	public static final String VAO_IMCOMPATIVEL = "Tamanho do VÃO insuficiente para os parâmetros.";
	public static final String ESCORA_IMCOMPATIVEL = "Tipo de escora não compatível com a estrutura. Escolha outro modelo.";
	public static final String ESCORA_NAO_CADASTRADA = "Tipo de escora não cadastrada. Entre em contato com a Nemetschek.";
	public static final String COMBINACAO_DE_POSTES_NAO_ENCONTRADA = "Nenhuma combinação de postes foi encontrada.";
	public static final String COMBINACAO_DE_VIGAS_PRINCIPAIS_NAO_ENCONTRADA = "Não foi possível encontrar composição de vigas principais.";
	public static final String COMBINACAO_DE_VIGAS_SECUNDARIAS_NAO_ENCONTRADA = "Não foi possível encontrar composição de vigas secundárias.";
	
	// SciaXML CSS Classes
	public static final String CSS_FIELD_OK = "validationFieldOK"; 
	public static final String CSS_FIELD_ERROR = "validationFieldError";
}
