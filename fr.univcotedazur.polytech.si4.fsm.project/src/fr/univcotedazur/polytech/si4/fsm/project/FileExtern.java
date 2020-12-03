package fr.univcotedazur.polytech.si4.fsm.project;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;


public class FileExtern {
	public File fileName;
	public String path;
	
	 public FileExtern(String path) throws IOException {
	        fileName = new File(path);
	        if(!fileName.exists()){
	            System.out.println("fichier existe pas");
	        }
	        this.path = path;
	       
	    }
	 
	 public ArrayList<String> read(){
	        ArrayList<String> line = new ArrayList<String>();
	        try {
				Scanner scanner = new Scanner(fileName);
				
				while(scanner.hasNextLine())
			      {
			        line.add(scanner.nextLine());
			      }
			      scanner.close();  
			      
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	       return line;
	    }
	 
	 public void write(String modification) {
		 try {
	            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
	            writer.print(modification);
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	 }
}
