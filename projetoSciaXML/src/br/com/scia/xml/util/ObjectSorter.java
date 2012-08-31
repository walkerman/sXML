package br.com.scia.xml.util;

import java.util.Comparator;
import br.com.scia.xml.entity.xml.Object;

public class ObjectSorter implements Comparator<Object>
{
	@Override
	public int compare(Object o1, Object o2) {
		return o1.getNm().compareTo(o2.getNm());
	}
}
