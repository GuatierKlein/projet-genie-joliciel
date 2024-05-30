package fr.miage.klein.BusinessLogic.Reservation;

import java.time.LocalDate;
import java.util.List;

import fr.miage.klein.BusinessLogic.EResEtat;
import fr.miage.klein.BusinessLogic.Immatriculation;
import fr.miage.klein.BusinessLogic.Mail;
import fr.miage.klein.BusinessLogic.NumReservation;

public class ReservationPermanente extends Reservation{
    private List<JourPlage> jourPlage;
    private EMois mois;
    private int nbMois;
    private int annee;

    public ReservationPermanente(NumReservation id, EResEtat etat, Mail mailClient, Immatriculation immat, int idBorne, List<JourPlage> jourPlage, EMois mois, int nbMois, int annee){
        super(id, etat, mailClient, immat, idBorne);
        this.jourPlage = jourPlage;
        this.mois = mois;
        this.nbMois = nbMois;
        this.annee = annee;
    }

    public ReservationPermanente(List<JourPlage> jourPlage, Mail mailClient, Immatriculation immat, EMois mois, int nbMois, int annee){
        super(mailClient, immat);
        this.jourPlage = jourPlage;
        this.mois = mois;
        this.nbMois = nbMois;
        this.annee = annee;
    }

    public boolean verifReservationPermanente() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'verifReservationPermanente'");
    }

    @Override
    public boolean isValidForAccess() {
        // TODO Auto-generated method stub

        return verifJour() && verifMoisAnnee();
    }

    public boolean verifMoisAnnee(){
        return LocalDate.now().isBefore(LocalDate.of(this.annee, this.mois.getMonth().getValue(), 1).plusMonths(nbMois));
    }

    public boolean verifJour(){
        for (JourPlage jourPlage : this.jourPlage) {
            if(LocalDate.now().getDayOfWeek() == jourPlage.getJour().getDay())
                return true;
        }
        return false;
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

    public int getAnnee() {
        return this.annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }


}
