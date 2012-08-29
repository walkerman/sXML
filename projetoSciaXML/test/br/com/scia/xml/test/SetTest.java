package br.com.scia.xml.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetTest {
	public static void main(String[] args) {
		List<Integer> lista = new ArrayList<Integer>();
		lista.add(1);
		lista.add(1);
		lista.add(3);
		lista.add(2);
		lista.add(1);

		Set<Integer> set = new HashSet<Integer>();
		set.addAll(lista);

		System.out.println(lista.toString());
		System.out.println(set.toString());
	}
}
