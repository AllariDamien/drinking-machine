package fr.univcotedazur.polytech.si4.fsm.project;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stock stock = new Stock();
		System.out.println("Avant \n");
		if(stock.getStock().isEmpty())
			System.out.println("merde");
		else if(stock.getStock().containsKey("Sucre")) {
		System.out.println(stock.getStock().get("Sucre"));
		System.out.println("\n");
		System.out.println(stock.getStock().get("Bois"));
		}

		
		System.out.println("Apres \n");
		stock.decrementStock("Sucre", 2);
		stock.decrementStock("Bois", 4);
		System.out.println(stock.getStock().get("Sucre"));
		System.out.println(stock.getStock().get("Bois"));
	
	}

}
