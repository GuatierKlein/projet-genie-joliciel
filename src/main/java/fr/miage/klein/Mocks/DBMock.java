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
    public List<ReservationTemporaire> getReservationsTemporaires() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReservations'");
    }

    @Override
    public void deleteReservationTemporaire(NumReservation id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteReservation'");
    }

    @Override
    public void updateReservationTemporaire(ReservationTemporaire reservation) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateReservation'");
    }

    @Override
    public void addReservationTemporaire(ReservationTemporaire reservation) {
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
    public ReservationTemporaire getReservationTemporaire(NumReservation id) {
        return new ReservationTemporaire(id, LocalDateTime.now().plusMinutes(5), 60, EResEtat.EnAttente, new Mail("klein_gautier@yahoo.fr"), new Immatriculation("AA-229-AA"), 3);
    }

    @Override
    public boolean existsImmat(Immatriculation immat) {
        return true;
    }

    @Override
    public List<ReservationTemporaire> getReservationsTemporaireFromImmat(Immatriculation immat) {
        ArrayList<ReservationTemporaire> res = new ArrayList<>();
        
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

    @Override
    public String getCurrency() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrency'");
    }

    @Override
    public void setCurrency(String cur) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCurrencu'");
    }

    @Override
    public float getTarifHoraire() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTarifHoraire'");
    }

    @Override
    public void setTarifHoraire(float value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTarifHoraire'");
    }

    @Override
    public float getDureeProlongement() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDureeProlongement'");
    }

    @Override
    public void setDureeProlongement(float value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDureeProlongement'");
    }

    @Override
    public float getTarifSupplement() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTarifSupplement'");
    }

    @Override
    public void setTarifSupplement(float value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTarifSupplement'");
    }

    @Override
    public float getTarifHoraireCharge() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTarifHoraireCharge'");
    }

    @Override
    public void setTarifHoraireCharge(float value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTarifHoraireCharge'");
    }

    @Override
    public float getTarifPenalite() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTarifPenalite'");
    }

    @Override
    public void setTarifPenalite(float value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTarifPenalite'");
    }

    @Override
    public float getTauxAugmentationMin() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTauxAugmentationMin'");
    }

    @Override
    public void setTauxAugmentationMin(float value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTauxAugmentationMin'");
    }

    @Override
    public List<Reservation> getReservations() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReservations'");
    }

    @Override
    public Reservation getReservation(NumReservation id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReservation'");
    }

    @Override
    public List<Reservation> getReservationsFromImmat(Immatriculation immat) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReservationsFromImmat'");
    }
    
}
