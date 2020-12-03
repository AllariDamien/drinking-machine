package fr.univcotedazur.polytech.si4.fsm.project;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Stock {
	FileExtern fileExtern;
    Map<String, Integer> stock;
    
    public Stock() {
         try {
			fileExtern = new FileExtern("./stock.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         stock = new HashMap<String, Integer>();
         readStock();
    }
    
    private void readStock(){
        for(String line : fileExtern.read()){
            String[] splitLine = line.split(": ");
            stock.put(splitLine[0], Integer.parseInt(splitLine[1]));
        }
    }

    public void decrementStock(String name, int value){
        stock.replace(name, stock.get(name)-value);
        
        String next = "";
        for(String i: stock.keySet()){
            next = next + i + ": " + stock.get(i) + "\n";
        }
        fileExtern.write(next);
    }
    
	public Map<String, Integer> getStock() {
		return stock;
	}

	public void setStock(Map<String, Integer> stock) {
		this.stock = stock;
	}
    
	
    
}
