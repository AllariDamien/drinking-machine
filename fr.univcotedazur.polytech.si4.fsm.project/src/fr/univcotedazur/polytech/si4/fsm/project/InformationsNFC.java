package fr.univcotedazur.polytech.si4.fsm.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InformationsNFC {
	FileExtern fileExtern;
    Map<Long, Order> nfc;
    
    int nbCommande;
	Double moyenne;
    
    public  InformationsNFC() {
    	try {
			fileExtern = new FileExtern("./nfc.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
         nfc = new HashMap<Long, Order>();
         readNFC();
    }
    
    private void readNFC(){
    		
    	for(String line : fileExtern.read()){
            String[] separator = line.split(": ");
            if(separator[0].equals(""))
            	return;
          

            
            Order order = new Order(Integer.parseInt(separator[1]), Double.parseDouble(separator[2]));
            nfc.put(Long.parseLong(separator[0]), order);
            
        
        }
    }

    public void incrementeNFC(Long id, double price){
    	
    	

    	nbCommande = nfc.get(id).getNbCommande() + 1;
    	

    	moyenne = (nfc.get(id).getNbCommande() * nfc.get(id).getPrixMoyen() + price) /nbCommande;
    	
    	
    	nfc.put(id, new Order(nbCommande, moyenne));
    
        
        String newNFC = "";
        for(Long i: nfc.keySet()){
        	newNFC = newNFC + i + ": " + nfc.get(i).getNbCommande() + ": " + nfc.get(i).getPrixMoyen() + "\n";
        }
        fileExtern.write(newNFC);
    }
    
    public void addNFC(Long id, double price) {
    	
    	
		nfc.put(id, new Order(1,price));
		
		
    	String newNFC = "";
        for(Long i: nfc.keySet()){
        	newNFC = newNFC + i + ": " + nfc.get(i).getNbCommande() + ": " + nfc.get(i).getPrixMoyen() + "\n";
        }
        fileExtern.write(newNFC);
    }
    
    public void remove(Long id) {
    	nfc.remove(id);
    	String newNFC = "";
        for(Long i: nfc.keySet()){
        	newNFC = newNFC + i + ": " + nfc.get(i).getNbCommande() + ": " + nfc.get(i).getPrixMoyen() + "\n";
        }
        fileExtern.write(newNFC);
    }

	public Map<Long, Order> getNfc() {
		return nfc;
	}

	public void setNfc(Map<Long, Order> nfc) {
		this.nfc = nfc;
	}

	
	
}
