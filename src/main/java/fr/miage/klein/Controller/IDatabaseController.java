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
    public Client getClient(int id);
    public Client getClient(Mail mail);
    public List<Client> getClients();
    public void deleteClient();
    public void updateClient(Client client);
    public void addClient(Client client);

    //Factures
    public Client getFacture(int id);
    public List<Facture> getFactures();
    public void deleteFacture();
    public void updateFacture(Facture facture);
    public void addFacture(Facture facture);

    //réservations
    public Reservation getReservation(NumReservation id);
    public List<Reservation> getReservations();
    public void deleteReservation();
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
}
