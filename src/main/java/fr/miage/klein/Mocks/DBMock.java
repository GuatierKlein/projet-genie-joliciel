package fr.miage.klein.Mocks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.miage.klein.BusinessLogic.Borne;
import fr.miage.klein.BusinessLogic.Client;
import fr.miage.klein.BusinessLogic.EBorneEtat;
import fr.miage.klein.BusinessLogic.EResEtat;
import fr.miage.klein.BusinessLogic.Facture;
import fr.miage.klein.BusinessLogic.Immatriculation;
import fr.miage.klein.BusinessLogic.Mail;
import fr.miage.klein.BusinessLogic.NumReservation;
import fr.miage.klein.BusinessLogic.Reservation.Reservation;
import fr.miage.klein.BusinessLogic.Reservation.ReservationPermanente;
import fr.miage.klein.BusinessLogic.Reservation.ReservationTemporaire;
import fr.miage.klein.Controller.IDatabaseController;

public class DBMock implements IDatabaseController {
    @Override
    public List<Client> getClients() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClients'");
    }

    @Override
    public void deleteClient(Mail mail) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteClient'");
    }

    @Override
    public void updateClient(Client client) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateClient'");
    }

    @Override
    public void addClient(Client client) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addClient'");
    }

    @Override
    public Facture getFacture(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFacture'");
    }

    @Override
    public List<Facture> getFactures() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFactures'");
    }

    @Override
    public void deleteFacture(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteFacture'");
    }

    @Override
    public void updateFacture(Facture facture) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateFacture'");
    }

    @Override
    public void addFacture(Facture facture) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addFacture'");
    }

    @Override
    public List<Reservation> getReservations() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReservations'");
    }

    @Override
    public void deleteReservation(NumReservation id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteReservation'");
    }

    @Override
    public void updateReservation(Reservation reservation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateReservation'");
    }

    @Override
    public void addReservation(Reservation reservation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addReservation'");
    }

    @Override
    public void updateBorneEtat(EBorneEtat etat) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateBorneEtat'");
    }

    @Override
    public Borne getBorne(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBorne'");
    }

    @Override
    public Reservation getReservation(NumReservation id) {
        return new ReservationTemporaire(id, LocalDateTime.now().plusMinutes(5), 60, EResEtat.EnAttente, new Mail("klein_gautier@yahoo.fr"), new Immatriculation("AA-229-AA"), 3);
    }

    @Override
    public boolean existsImmat(Immatriculation immat) {
        return true;
    }

    @Override
    public List<Reservation> getReservationsFromImmat(Immatriculation immat) {
        ArrayList<Reservation> res = new ArrayList<>();
        
        res.add(new ReservationTemporaire(LocalDateTime.now().plusMinutes(5), 60, new Mail("klein_gautier@yahoo.fr"), immat));
        return res;
    }

    @Override
    public void addPresence(Immatriculation immat) {

    }

    @Override
    public boolean isPresent(Immatriculation immat) {
        return false;
    }

    @Override
    public void deletePresence(Immatriculation immat) {
        
    }

    @Override
    public List<ReservationPermanente> getReservationsPermanentesFromClient(Mail mailClient) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReservationsPermanentesFromClient'");
    }

    @Override
    public void addReservationPermanente(ReservationPermanente reservation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addReservation'");
    }

    @Override
    public Client getClient(Mail mail) {
        return new Client("Théodore", "Muller", "26 rue des Théodore", "0645316575", mail, 123456);
    }

    @Override
    public List<Immatriculation> getPresence() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPresence'");
    }
    
}
