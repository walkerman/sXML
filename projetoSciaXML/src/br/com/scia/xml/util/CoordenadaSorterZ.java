package br.com.scia.xml.util;

import java.util.Comparator;

import br.com.scia.xml.entity.xml.Coordenada;

public class CoordenadaSorterZ implements Comparator<Coordenada>
{

	@Override
	public int compare(Coordenada c1, Coordenada c2) {
		if (c1.getZ() >= c2.getZ())
			return 1;
		else if (c1.getZ() <= c2.getZ())
			return -1;
		else 
			return 0;
	}

}
