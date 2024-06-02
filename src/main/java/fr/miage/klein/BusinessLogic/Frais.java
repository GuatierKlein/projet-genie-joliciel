package fr.miage.klein.BusinessLogic;

public class Frais {
    private Float montant; 
    private String devise;

    public Frais(){
        montant = (float) 0;
        devise = Facture.getDEVISE_PAR_DEFAUT();
    }

    public Frais(Float montant, String devise) {
        this.montant = montant;
        this.devise = devise;
    }

    public Float getMontant() {
        return montant;
    }

    public void setMontant(Float montant) {
        this.montant = montant;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }
    
}


