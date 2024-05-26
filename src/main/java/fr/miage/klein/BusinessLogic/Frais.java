package fr.miage.klein.BusinessLogic;

public class Frais {
    private Float montant; 
    private String devise;

    public Frais(){
        montant = (float) 0;
        devise = Facture.deviseParDefaut;
    }

    public Frais(Float montant, String devise) {
        this.montant = montant;
        this.devise = devise;
    }
    
}


