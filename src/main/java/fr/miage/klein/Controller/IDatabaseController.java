package fr.miage.klein.Controller;

import java.util.List;

import fr.miage.klein.BusinessLogic.Borne;
import fr.miage.klein.BusinessLogic.Client;
import fr.miage.klein.BusinessLogic.EBorneEtat;
import fr.miage.klein.BusinessLogic.Facture;
import fr.miage.klein.BusinessLogic.Immatriculation;
import fr.miage.klein.BusinessLogic.Mail;
import fr.miage.klein.BusinessLogic.NumReservation;
import fr.miage.klein.BusinessLogic.Reservation.ReservationPermanente;
import fr.miage.klein.BusinessLogic.Reservation.ReservationTemporaire;

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
    public ReservationTemporaire getReservationTemporaires(NumReservation id);
    public List<ReservationTemporaire> getReservationsTemporaires();
    public void deleteReservationTemporaire(NumReservation id);
    public void updateReservationTemporaire(ReservationTemporaire reservation);
    public void addReservationTemporaire(ReservationTemporaire reservation);

    //résevrations permanentes
    public List<ReservationPermanente> getReservationsPermanentesFromClient(Mail mailClient);
    public void addReservationPermanente(ReservationPermanente reservation);

    //immat 
    public boolean existsImmat(Immatriculation immat);
    public List<ReservationTemporaire> getReservationsTemporaireFromImmat(Immatriculation immat);

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
