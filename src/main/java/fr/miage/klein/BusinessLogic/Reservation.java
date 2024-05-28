package fr.miage.klein.BusinessLogic;

import java.time.LocalDateTime;

public class Reservation {
    private NumReservation id;
    private LocalDateTime datetime;
    private int duree;
    private EResEtat etat;
    private Mail mailClient;
    private String immat;
    private int idBorne;

    public Reservation(NumReservation id, LocalDateTime datetime, int duree, EResEtat etat, Mail mailClient, String immat, int idBorne) {
        this.id = id;
        this.datetime = datetime;
        this.duree = duree;
        this.etat = etat;
        this.mailClient = mailClient;
        this.immat = immat;
        this.idBorne = idBorne;
    }

    public Reservation(LocalDateTime datetime, int duree, Mail mailClient, String immat) {
        this.datetime = datetime;
        this.duree = duree;
        this.mailClient = mailClient;
        this.immat = immat;
    }

    public NumReservation getId() {
        return this.id;
    }

    public void setId(NumReservation id) {
        this.id = id;
    }

    public LocalDateTime getDatetime() {
        return this.datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public int getDuree() {
        return this.duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public EResEtat getEtat() {
        return this.etat;
    }

    public void setEtat(EResEtat etat) {
        this.etat = etat;
    }

    public Mail getMailClient() {
        return this.mailClient;
    }

    public void setMailClient(Mail mailClient) {
        this.mailClient = mailClient;
    }

    public String getImmat() {
        return this.immat;
    }

    public void setImmat(String immat) {
        this.immat = immat;
    }

    public int getIdBorne() {
        return this.idBorne;
    }

    public void setIdBorne(int idBorne) {
        this.idBorne = idBorne;
    }

}
