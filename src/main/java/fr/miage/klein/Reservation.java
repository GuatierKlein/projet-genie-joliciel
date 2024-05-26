package fr.miage.klein;

import java.time.LocalDateTime;

public class Reservation {
    int id;
    LocalDateTime datetime;
    int duree;
    EResEtat etat;
    Mail mailClient;
    String immat;
    int idBorne;


    public Reservation(int id, LocalDateTime datetime, int duree, EResEtat etat, Mail mailClient, String immat, int idBorne) {
        this.id = id;
        this.datetime = datetime;
        this.duree = duree;
        this.etat = etat;
        this.mailClient = mailClient;
        this.immat = immat;
        this.idBorne = idBorne;
    }

    public void changerEtat(EResEtat etat){
        this.etat = etat;
    }

}
