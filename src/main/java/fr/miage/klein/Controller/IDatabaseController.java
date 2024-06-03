package fr.miage.klein.Controller;

import java.util.List;

import fr.miage.klein.BusinessLogic.Client;
import fr.miage.klein.BusinessLogic.Facture;
import fr.miage.klein.BusinessLogic.Immatriculation;
import fr.miage.klein.BusinessLogic.Mail;
import fr.miage.klein.BusinessLogic.NumReservation;
import fr.miage.klein.BusinessLogic.Reservation;

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

    //immat 
    public boolean existsImmat(Immatriculation immat);
    public List<Reservation> getReservationsFromImmat(Immatriculation immat);

    //présence 
    public void addPresence(Immatriculation immat);
    public boolean isPresent(Immatriculation immat);
    public void deletePresence(Immatriculation immat);
    public List<Immatriculation> getPresence();
}
