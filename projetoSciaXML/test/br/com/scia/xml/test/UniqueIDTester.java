package br.com.scia.xml.test;

import java.util.UUID;

public class UniqueIDTester {
	public static void main(String[] args) {
		String uuid = UUID.randomUUID().toString();
		System.out.println("uuid = " + uuid);
	}
}
