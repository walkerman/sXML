package br.com.scia.xml.util;

import java.util.Comparator;

import br.com.scia.xml.entity.view.Peca;

public class PecaComprimentoSorter implements Comparator<Peca>
{

	@Override
	public int compare(Peca p1, Peca p2) {
		if (p1.getComprimento() >= p2.getComprimento())
			return 1;
		else if (p1.getComprimento() <= p2.getComprimento())
			return -1;
		else 
			return 0;
	}

}
