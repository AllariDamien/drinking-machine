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
    	//ArrayList<Double> temp = new ArrayList<>();
    	
    	
        for(String line : fileExtern.read()){
            String[] splitLine = line.split(": ");
            if(splitLine[0].equals(""))
            	return;
          
//            temp.add(Double.parseDouble(splitLine[1]));
//            temp.add(Double.parseDouble(splitLine[2]));
//            nfc.put(Integer.parseInt(splitLine[0]), temp);
            
            Order order = new Order(Integer.parseInt(splitLine[1]), Double.parseDouble(splitLine[2]));
            nfc.put(Long.parseLong(splitLine[0]), order);
            
        
        }
    }

    public void incrementeNFC(Long id, double price){
    	
    	
//    	nbCommande = nfc.get(id).get(0) + 1;
//    	listeId.add(nbCommande);
    	nbCommande = nfc.get(id).getNbCommande() + 1;
    	//nfc.get(id).setNbCommande(nbCommande);
    	
//    	moyenne = (nfc.get(id).get(0) * nfc.get(id).get(1) + price)/nbCommande;
//    	listeId.add(moyenne);
    	moyenne = (nfc.get(id).getNbCommande() * nfc.get(id).getPrixMoyen() + price) /nbCommande;
    	//nfc.get(id).setPrixMoyen(moyenne);
    	
    	nfc.put(id, new Order(nbCommande, moyenne));
    
        
        String next = "";
        for(Long i: nfc.keySet()){
            next = next + i + ": " + nfc.get(i).getNbCommande() + ": " + nfc.get(i).getPrixMoyen() + "\n";
        }
        fileExtern.write(next);
    }
    
    public void addNFC(Long id, double price) {
    	
    	
    	//listeId.add(1.0);
    	//nfc.get(id).setNbCommande(1);
		//moyenne = price;
		//nfc.get(id).setPrixMoyen(price);
		nfc.put(id, new Order(1,price));
		
		
    	String next = "";
        for(Long i: nfc.keySet()){
            next = next + i + ": " + nfc.get(i).getNbCommande() + ": " + nfc.get(i).getPrixMoyen() + "\n";
        }
        fileExtern.write(next);
    }
    
    public void remove(Long id) {
    	nfc.remove(id);
    	String next = "";
        for(Long i: nfc.keySet()){
            next = next + i + ": " + nfc.get(i).getNbCommande() + ": " + nfc.get(i).getPrixMoyen() + "\n";
        }
        fileExtern.write(next);
    }

	public Map<Long, Order> getNfc() {
		return nfc;
	}

	public void setNfc(Map<Long, Order> nfc) {
		this.nfc = nfc;
	}

//	public List<Double> getListeId() {
//		return listeId;
//	}
//
//	public void setListeId(List<Double> listeId) {
//		this.listeId = listeId;
//	}
	
	
}
