package fr.miage.klein.Controller;

import java.util.List;

import fr.miage.klein.BusinessLogic.Client;
import fr.miage.klein.BusinessLogic.Facture;
import fr.miage.klein.BusinessLogic.NumReservation;
import fr.miage.klein.BusinessLogic.Reservation;

public interface IDatabaseController {
    //clients
    public Client getClient(int id);
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
    public Client getReservation(NumReservation id);
    public List<Reservation> getReservations();
    public void deleteReservation();
    public void updateReservation(Reservation reservation);
    public void addReservation(Reservation reservation);
}
