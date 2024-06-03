package fr.miage.klein.BusinessLogic.Reservation;

import java.time.LocalDateTime;

import fr.miage.klein.BusinessLogic.EResEtat;
import fr.miage.klein.BusinessLogic.Immatriculation;
import fr.miage.klein.BusinessLogic.Mail;
import fr.miage.klein.BusinessLogic.NumReservation;

public class ReservationTemporaire extends Reservation{

    private LocalDateTime datetime;
    private int duree; //en minute

    public ReservationTemporaire(NumReservation id, LocalDateTime datetime, int duree, EResEtat etat, Mail mailClient, Immatriculation immat, int idBorne){
        super(id, etat, mailClient, immat, idBorne);
        this.datetime = datetime;
        this.duree = duree;
    }

    public ReservationTemporaire(LocalDateTime datetime, int duree, Mail mailClient, Immatriculation immat){
        super(mailClient, immat);
        this.datetime = datetime;
        this.duree = duree;
    }

    public boolean isValidForAccess() {
        return !(getDatetime().isBefore(LocalDateTime.now()) || LocalDateTime.now().isAfter(getDatetime().plusMinutes(10))); // TODO changer for settings value
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

    
}
