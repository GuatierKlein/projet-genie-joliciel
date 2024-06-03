package fr.miage.klein.BusinessLogic;

import java.io.Serializable;
import java.time.Duration;

public class Borne implements Serializable {
    private int id;
    private EBorneEtat etat;
    public static Duration dureeAttente = Duration.ofMinutes(10);

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

    public static Duration getDureeAttente() {
        return dureeAttente;
    }

    public static void setDureeAttente(Duration dureeAttente) {
        Borne.dureeAttente = dureeAttente;
    }

    // public Boolean seConnecter(){

    // }
    
}
