package fr.miage.klein.BusinessLogic.Reservation;

import java.io.Serializable;

import fr.miage.klein.BusinessLogic.EResEtat;
import fr.miage.klein.BusinessLogic.Immatriculation;
import fr.miage.klein.BusinessLogic.Mail;
import fr.miage.klein.BusinessLogic.NumReservation;

public abstract class Reservation implements Serializable{
    private NumReservation id;
    
    private EResEtat etat;
    private Mail mailClient;
    private Immatriculation immat;
    private int idBorne;

    public Reservation(NumReservation id, EResEtat etat, Mail mailClient, Immatriculation immat, int idBorne){
        this.id = id;
        this.etat = etat;
        this.mailClient = mailClient;
        this.immat = immat;
        this.idBorne = idBorne;
    }

    public Reservation(Mail mailClient, Immatriculation immat){
        this.mailClient = mailClient;
        this.immat = immat;
    }

    public NumReservation getId() {
        return this.id;
    }

    public void setId(NumReservation id) {
        this.id = id;
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

    public Immatriculation getImmat() {
        return this.immat;
    }

    public void setImmat(Immatriculation immat) {
        this.immat = immat;
    }

    public int getIdBorne() {
        return this.idBorne;
    }

    public void setIdBorne(int idBorne) {
        this.idBorne = idBorne;
    }

    public abstract boolean isValidForAccess();

}
