package fr.miage.klein.BusinessLogic.Reservation;

import java.util.List;

import fr.miage.klein.BusinessLogic.EResEtat;
import fr.miage.klein.BusinessLogic.Immatriculation;
import fr.miage.klein.BusinessLogic.Mail;
import fr.miage.klein.BusinessLogic.NumReservation;

public class ReservationPermatente extends Reservation{
    private List<JourPlage> jourPlage;
    private EMois mois;
    private int nbMois;

    public ReservationPermatente(NumReservation id, EResEtat etat, Mail mailClient, Immatriculation immat, int idBorne, List<JourPlage> jourPlage, EMois mois, int nbMois){
        super(id, etat, mailClient, immat, idBorne);
        this.jourPlage = jourPlage;
        this.mois = mois;
        this.nbMois = nbMois;
    }

    public ReservationPermatente(List<JourPlage> jourPlage, Mail mailClient, Immatriculation immat, EMois mois, int nbMois){
        super(mailClient, immat);
        this.jourPlage = jourPlage;
        this.mois = mois;
        this.nbMois = nbMois;
    }

    public boolean verifReservationPermanente() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verifReservationPermanente'");
    }

    @Override
    public boolean isValidForAccess() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isValidForAccess'");
    }

    public List<JourPlage> getJourPlage() {
        return this.jourPlage;
    }

    public void setJourPlage(List<JourPlage> jourPlage) {
        this.jourPlage = jourPlage;
    }

    public EMois getMois() {
        return this.mois;
    }

    public void setMois(EMois mois) {
        this.mois = mois;
    }

    public int getNbMois() {
        return this.nbMois;
    }

    public void setNbMois(int nbMois) {
        this.nbMois = nbMois;
    }


}
