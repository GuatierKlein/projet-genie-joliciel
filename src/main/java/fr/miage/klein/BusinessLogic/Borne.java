package fr.miage.klein.BusinessLogic;

import java.io.Serializable;

public class Borne implements Serializable {
    private int id;
    private EBorneEtat etat;

    public Borne(int id, EBorneEtat etat) {
        this.id = id;
        this.etat = etat;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EBorneEtat getEtat() {
        return this.etat;
    }

    public void setEtat(EBorneEtat etat) {
        this.etat = etat;
    }

    // public Boolean seConnecter(){

    // }
    
}
