package fr.miage.klein.BusinessLogic;

import java.io.Serializable;

public class Frais implements Serializable {
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


