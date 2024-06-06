package fr.miage.klein.Controller;

import java.util.List;

import fr.miage.klein.BusinessLogic.Borne;
import fr.miage.klein.BusinessLogic.Client;
import fr.miage.klein.BusinessLogic.EBorneEtat;
import fr.miage.klein.BusinessLogic.Facture;
import fr.miage.klein.BusinessLogic.Immatriculation;
import fr.miage.klein.BusinessLogic.Mail;
import fr.miage.klein.BusinessLogic.NumReservation;
import fr.miage.klein.BusinessLogic.Reservation.Reservation;
import fr.miage.klein.BusinessLogic.Reservation.ReservationPermanente;

public interface IDatabaseController {
    //clients
    public Client getClient(Mail mail);
    public List<Client> getClients();
    public void deleteClient(Mail mail);
    public void updateClient(Client client);
    public void addClient(Client client);

    //Factures
    public Facture getFacture(int id);
    public List<Facture> getFactures();
    public void deleteFacture(int id);
    public void updateFacture(Facture facture);
    public void addFacture(Facture facture);

    //réservations
    public Reservation getReservation(NumReservation id);
    public List<Reservation> getReservations();
    public void deleteReservation(NumReservation id);
    public void updateReservation(Reservation reservation);
    public void addReservation(Reservation reservation);
    public void addReservationPermanente(ReservationPermanente reservation);

    //résevrations permanentes
    public List<ReservationPermanente> getReservationsPermanentesFromClient(Mail mailClient);

    //immat 
    public boolean existsImmat(Immatriculation immat);
    public List<Reservation> getReservationsFromImmat(Immatriculation immat);

    //présence 
    public void addPresence(Immatriculation immat);
    public boolean isPresent(Immatriculation immat);
    public void deletePresence(Immatriculation immat);

    //borne
    public void updateBorneEtat(EBorneEtat etat);
    public Borne getBorne(int id);
    public List<Immatriculation> getPresence();

    //settings 
    public String getCurrency();
    public void setCurrency(String cur);
    public float getTarifHoraire();
    public void setTarifHoraire(float value);
    public float getDureeProlongement();
    public void setDureeProlongement(float value);
    public float getTarifSupplement();
    public void setTarifSupplement(float value);
    public float getTarifHoraireCharge();
    public void setTarifHoraireCharge(float value);
    public float getTarifPenalite();
    public void setTarifPenalite(float value);
    public float getTauxAugmentationMin();
    public void setTauxAugmentationMin(float value);
}
