package fr.univcotedazur.polytech.si4.fsm.project;

import java.awt.List;
import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Stock stock = new Stock();
//		System.out.println("Avant \n");
//		if(stock.getStock().isEmpty())
//			System.out.println("merde");
//		else if(stock.getStock().containsKey("Sucre")) {
//		System.out.println(stock.getStock().get("Sucre"));
//		System.out.println("\n");
//		System.out.println(stock.getStock().get("Bois"));
//		}
//
//		
//		System.out.println("Apres \n");
//		stock.decrementStock("Sucre", 2);
//		stock.decrementStock("Bois", 4);
//		System.out.println(stock.getStock().get("Sucre"));
//		System.out.println(stock.getStock().get("Bois"));
		
		InformationsNFC nfc = new InformationsNFC();
		
		//nfc.addNFC((long) 5678, 2.0);
		//nfc.incrementeNFC((long)5678, 10.0);
		//nfc.addNFC((long) 1234, 2.0);
		//nfc.incrementeNF C((long)1234, 10.0);
		nfc.remove((long)1234);
		System.out.println(nfc.getNfc().toString());
		
//	
	}
		

}
