package br.com.scia.xml.model;

import br.com.scia.xml.entity.view.Peca;
import br.com.scia.xml.util.SciaXMLConstantes;
import br.com.scia.xml.util.SciaXMLUtils;

public class Formulas {

	public Double RetornaMomentoMaxViga(String tipoViga) {

		Double momentoMaximo = 0.0;

		switch (tipoViga) {
		case "HT20":
			momentoMaximo = 0.5;
			break;

		case "V18":
			momentoMaximo = 1.150;
			break;

		case "V7.5":

			momentoMaximo = 0.280;
			break;

		case "VA18":

			momentoMaximo = 1.400;
			break;

		default:

			momentoMaximo = 0.0;
			break;
		}

		return momentoMaximo;

	}

	public Double RetornaEViga(String tipoViga) {

		Double E = 0.0;

		switch (tipoViga) {
		case "HT20":
			E = 75000.0;
			break;

		case "V18":
			E = 2000000.0;
			break;

		case "V7.5":

			E = 2000000.0;
			break;

		case "VA18":

			E = 700000.0;
			break;

		default:

			E = 0.0;
			break;
		}

		return E;

	}

	public Double RetornaIViga(String tipoViga) {

		Double I = 0.0;

		switch (tipoViga) {
		case "HT20":
			I = 4610.0;
			break;

		case "V18":
			I = 741.0;
			break;

		case "V7.5":

			I = 75.0;
			break;

		case "VA18":

			I = 811.0;
			break;

		default:

			I = 0.0;
			break;
		}

		return I;

	}

	public Double CalculoPesoA() {
		Double retorno = 0.0;
		if (Calculo.dados.getAlturaViga() != null
				&& !"".equals(SciaXMLUtils.checkString(Calculo.dados
						.getAlturaViga()))
				&& Calculo.dados.getLarguraViga() != null
				&& !"".equals(SciaXMLUtils.checkString((Calculo.dados
						.getLarguraViga())))) {

			Double alturaViga = Double.parseDouble(Calculo.dados
					.getAlturaViga())
					/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;
			Double larguraViga = Double.parseDouble(Calculo.dados
					.getLarguraViga())
					/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;

			Double x = alturaViga * 2650;
			Double resultadoFinal = (x + 200) * larguraViga;

			retorno = resultadoFinal;
		}
		return retorno;
	}

	public Double CalculoPesoB() {
		Double retorno = 0.0;
		if (Calculo.dados.getEspessura() != null
				&& !"".equals(SciaXMLUtils.checkString(Calculo.dados
						.getEspessura()))
				&& Calculo.dados.getInfluenciaLaje() != null
				&& !"".equals(SciaXMLUtils.checkString(Calculo.dados
						.getInfluenciaLaje()))) {

			Double espessuraLaje = Double.parseDouble(Calculo.dados
					.getEspessura())
					/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;
			Double inflLajeViga = Double.parseDouble(Calculo.dados
					.getInfluenciaLaje())
					/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;

			Double x = espessuraLaje * 2650;
			Double resultadoFinal = ((x + 200) * inflLajeViga) + CalculoPesoA();

			retorno = resultadoFinal;
		}
		return retorno;

	}

	public Double CalculoPesoC() {
		Double retorno = 0.0;
		if (Calculo.dados.getEspessura() != null
				&& !"".equals(SciaXMLUtils.checkString(Calculo.dados
						.getEspessura()))) {
			Double espessuraLaje = Double.parseDouble(Calculo.dados
					.getEspessura())
					/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;

			Double x = espessuraLaje * 2650;
			Double resultadoFinal = x + 200;

			retorno = resultadoFinal;
		}
		return retorno;
	}

	public Double CalculoVigaSecundariaMomento() {
		Double comprimentoTravessa = 0.0;
		Double maiorComprimentoY = 0.0;
		Double calculoC = CalculoPesoC();

		for (Peca peca : Calculo.dados.getPecasY()) {
			if (peca.getTipo().contains(SciaXMLConstantes.KITRV))
				;
			{
				comprimentoTravessa = peca.getComprimento()/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;
				break;
			}
		}

		Double vaoApoioY = Calculo.dados.getVaoDeApoioY()
				/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;
		Double espacamentoEntreVigas = Double.parseDouble(Calculo.dados
				.getEspacamentoEntreVigasSecundarias())
				/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;

		if (vaoApoioY > comprimentoTravessa) {
			maiorComprimentoY = vaoApoioY;
		}

		else {

			maiorComprimentoY = comprimentoTravessa;
		}

		Double resultadoFinal = ((calculoC / 1000.0)
				* espacamentoEntreVigas * (maiorComprimentoY * maiorComprimentoY)) / 8.0;

		return (resultadoFinal*100)/100;

	}

	public Double CalculoVigaSecundariaFlecha(String tipoViga) {
		Double comprimentoTravessa = 0.0;
		Double maiorComprimentoY = 0.0;

		for (Peca peca : Calculo.dados.getPecasY()) {
			if (peca.getTipo().contains(SciaXMLConstantes.KITRV))
				;
			{
				comprimentoTravessa = peca.getComprimento()/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;
				break;
			}
		}

		Double vaoApoioY = Calculo.dados.getVaoDeApoioY()
				/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;
		Double espacamentoEntreVigas = Double.parseDouble(Calculo.dados
				.getEspacamentoEntreVigasSecundarias())
				/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;

		if (vaoApoioY > comprimentoTravessa) {
			maiorComprimentoY = vaoApoioY;
		}

		else {

			maiorComprimentoY = comprimentoTravessa;
		}

		Double resultadoFinal = (50 * ((CalculoPesoC() - 200) * (espacamentoEntreVigas)) / 100 * (Math.pow(4, (maiorComprimentoY*100))))	/ (384 * RetornaEViga(tipoViga) * RetornaIViga(tipoViga));
		resultadoFinal = resultadoFinal/1000;
		
		return (resultadoFinal*100)/100;

	}

	public Double CalculoVigaSecundariaFlechaMaxima(String tipoViga) {
		Double comprimentoTravessa = 0.0;
		Double maiorComprimentoY = 0.0;
		Double resultadoFinal = 0.0;

		for (Peca peca : Calculo.dados.getPecasY()) {
			if (peca.getTipo().contains(SciaXMLConstantes.KITRV))
				;
			{
				comprimentoTravessa = peca.getComprimento()/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;
				break;
			}
		}

		Double vaoApoioY = Calculo.dados.getVaoDeApoioY()
				/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;

		if (vaoApoioY > comprimentoTravessa) {
			maiorComprimentoY = vaoApoioY;
		}

		else {

			maiorComprimentoY = comprimentoTravessa;
		}

		if (tipoViga == "HT20") {
			resultadoFinal = (maiorComprimentoY * 1000) / 500;
		} else {
			resultadoFinal = (maiorComprimentoY * 1000) / 350;
		}

		return (resultadoFinal*100)/100;

	}

	public Double CalculoVigaPrincipalMomento() {
		Double comprimentoTravessa = 0.0;
		Double maiorComprimentoY = 0.0;
		double espacamento = 0.0;

		for (Peca peca : Calculo.dados.getPecasY()) {
			if (peca.getTipo().contains(SciaXMLConstantes.KITRV))
				;
			{
				comprimentoTravessa = peca.getComprimento()/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;
				break;
			}
		}

		Double vaoApoioY = Calculo.dados.getVaoDeApoioY()
				/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;

		espacamento = vaoApoioY / 2 + comprimentoTravessa / 2;

		if (vaoApoioY > comprimentoTravessa) {
			maiorComprimentoY = vaoApoioY;
		}

		else {

			maiorComprimentoY = comprimentoTravessa;
		}

		Double resultadoFinal = ((CalculoPesoC() / 1000) * espacamento * (maiorComprimentoY * maiorComprimentoY)) / 8.0;

		return (resultadoFinal*100)/100;

	}

	public Double CalculoVigaPrincipalFlecha(String tipoViga) {
		Double comprimentoTravessa = 0.0;
		Double maiorComprimentoY = 0.0;
		double espacamento = 0.0;

		for (Peca peca : Calculo.dados.getPecasX()) {
			if (peca.getTipo().contains(SciaXMLConstantes.KITRV))
				;
			{
				comprimentoTravessa = peca.getComprimento()/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;
				break;
			}
		}

		Double vaoApoioY = Calculo.dados.getVaoDeApoioY()
				/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;
		espacamento = vaoApoioY / 2 + comprimentoTravessa / 2;

		if (vaoApoioY > comprimentoTravessa) {
			maiorComprimentoY = vaoApoioY;
		}

		else {

			maiorComprimentoY = comprimentoTravessa;
		}

		Double resultadoFinal = (50 * ((CalculoPesoC() - 200) * espacamento) / 100 * (Math.pow(4, (maiorComprimentoY*100)))) / (384 * RetornaEViga(tipoViga) * RetornaIViga(tipoViga));
		resultadoFinal = resultadoFinal/1000;
		
		return (resultadoFinal*100)/100;

	}

	public Double CalculoVigaPrincipalFlechaMaxima(String tipoViga) {
		Double comprimentoTravessa = 0.0;
		Double maiorComprimentoY = 0.0;
		Double resultadoFinal = 0.0;

		for (Peca peca : Calculo.dados.getPecasX()) {
			if (peca.getTipo().contains(SciaXMLConstantes.KITRV))
				;
			{
				comprimentoTravessa = peca.getComprimento()/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;
				break;
			}
		}

		Double vaoApoioY = Calculo.dados.getVaoDeApoioY()
				/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;

		if (vaoApoioY > comprimentoTravessa) {
			maiorComprimentoY = vaoApoioY;
		}

		else {

			maiorComprimentoY = comprimentoTravessa;
		}

		if (tipoViga == "HT20") {
			resultadoFinal = (maiorComprimentoY * 1000) / 500;
		} else {
			resultadoFinal = (maiorComprimentoY * 1000) / 350;
		}

		return (resultadoFinal*100)/100;

	}

	public Double CalculoCargasAreadeInfluenciaPoste() {
		double comprimentoMaiorTravessaY = 0.0;
		double comprimentoMaiorTravessaX = 0.0;
		double vaoApoioY = 0.0;
		double vaoApoioX = 0.0;

		Double resultadoFinal = 0.0;

		for (Peca peca : Calculo.dados.getPecasX()) {
			if (peca.getTipo().contains(SciaXMLConstantes.KITRV))
				;
			{
				comprimentoMaiorTravessaX = peca.getComprimento();
				break;
			}
		}

		for (Peca peca : Calculo.dados.getPecasY()) {
			if (peca.getTipo().contains(SciaXMLConstantes.KITRV))
				;
			{
				comprimentoMaiorTravessaY = peca.getComprimento();
				break;
			}
		}

		vaoApoioX = Calculo.dados.getVaoDeApoioX()
				/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;
		vaoApoioY = Calculo.dados.getVaoDeApoioY()
				/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;

		resultadoFinal = (vaoApoioX / 2 + comprimentoMaiorTravessaX / 2)
				* (vaoApoioY / 2 + comprimentoMaiorTravessaY / 2);

		return (resultadoFinal*100)/100;

	}

	public Double CalculoCargasAreadeInfluenciaEscora() {
		double comprimentoMaiorTravessaY = 0.0;
		double comprimentoMaiorTravessaX = 0.0;
		double vaoApoioY = 0.0;
		double vaoApoioX = 0.0;

		Double resultadoFinal = 0.0;

		for (Peca peca : Calculo.dados.getPecasX()) {
			if (peca.getTipo().contains(SciaXMLConstantes.KITRV))
				;
			{
				comprimentoMaiorTravessaX = peca.getComprimento();
				break;
			}
		}

		for (Peca peca : Calculo.dados.getPecasY()) {
			if (peca.getTipo().contains(SciaXMLConstantes.KITRV))
				;
			{
				comprimentoMaiorTravessaY = peca.getComprimento();
				break;
			}
		}

		vaoApoioX = Calculo.dados.getVaoDeApoioX()
				/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;
		vaoApoioY = Calculo.dados.getVaoDeApoioY()
				/ SciaXMLConstantes.PRECISAO_ENVIO_COORDENADAS_XML;

		resultadoFinal = (vaoApoioX / 2 + comprimentoMaiorTravessaX / 2)
				* (vaoApoioY / 2 + comprimentoMaiorTravessaY / 2);

		return (resultadoFinal*100)/100;

	}

	public Double CalculoCargasPosteMaisCarregado() {

		Double resultadoFinal = 0.0;

		resultadoFinal = (CalculoCargasAreadeInfluenciaPoste() / 10000.0)
				* (CalculoPesoC() / 1000.0);

		return (resultadoFinal*100)/100;

	}

	public Double CalculoCargasPosteContinuaBiApoiada() {

		Double resultadoFinal = 0.0;

		resultadoFinal = CalculoCargasPosteMaisCarregado() * 1.25;

		return (resultadoFinal*100)/100;

	}

	public Double CalculoCargasPosteContinuaContinua() {

		Double resultadoFinal = 0.0;

		resultadoFinal = CalculoCargasPosteMaisCarregado() * 1.56;

		return (resultadoFinal*100)/100;

	}

	public Double CalculoCargasEscoraMaisCarregada() {

		Double resultadoFinal = 0.0;

		resultadoFinal = (CalculoCargasAreadeInfluenciaEscora() / 10000.0)
				* (CalculoPesoC() / 1000.0);

		return (resultadoFinal*100)/100;

	}

	public Double CalculoCargasEscoraContinuaBiApoiada() {

		Double resultadoFinal = 0.0;

		resultadoFinal = CalculoCargasEscoraMaisCarregada() * 1.25;

		return (resultadoFinal*100)/100;

	}

	public Double CalculoCargasEscoraContinuaContinua() {

		Double resultadoFinal = 0.0;

		resultadoFinal = CalculoCargasEscoraMaisCarregada() * 1.56;

		return (resultadoFinal*100)/100;

	}

	public Double RetornaLimiteCargaPoste() {

		return 3.0;

	}

	public Double RetornaLimiteCargaEscora(String escora) {

		return 3.0;

	}

}
