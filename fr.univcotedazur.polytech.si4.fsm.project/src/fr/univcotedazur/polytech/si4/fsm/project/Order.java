package fr.univcotedazur.polytech.si4.fsm.project;

public class Order {

	int nbCommande;
	double prixMoyen;
	public Order(int nbCommande, double prixMoyen) {
		this.nbCommande = nbCommande;
		this.prixMoyen = prixMoyen;
	}
	public int getNbCommande() {
		return nbCommande;
	}
	public void setNbCommande(int nbCommande) {
		this.nbCommande = nbCommande;
	}
	public double getPrixMoyen() {
		return prixMoyen;
	}
	public void setPrixMoyen(double prixMoyen) {
		this.prixMoyen = prixMoyen;
	}
	
}
