package fr.miage.klein.Mocks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import fr.miage.klein.BusinessLogic.Borne;
import fr.miage.klein.BusinessLogic.Client;
import fr.miage.klein.BusinessLogic.EBorneEtat;
import fr.miage.klein.BusinessLogic.Facture;
import fr.miage.klein.BusinessLogic.Immatriculation;
import fr.miage.klein.BusinessLogic.Mail;
import fr.miage.klein.BusinessLogic.NumReservation;
import fr.miage.klein.BusinessLogic.Reservation.Reservation;
import fr.miage.klein.BusinessLogic.Reservation.ReservationPermanente;
import fr.miage.klein.BusinessLogic.Reservation.ReservationTemporaire;
import fr.miage.klein.Controller.IDatabaseController;

public class DBMock_fileWriter implements IDatabaseController, Serializable {

    private List<Client> clientList = new ArrayList<>();
    private List<fr.miage.klein.BusinessLogic.Reservation.ReservationTemporaire> reservationTemporaireList = new ArrayList<>();
    private List<fr.miage.klein.BusinessLogic.Reservation.ReservationPermanente> reservationPermanenteList = new ArrayList<>();
    private List<Borne> borneList = new ArrayList<>();
    private List<Immatriculation> presenceList = new ArrayList<>();
    private List<Facture> factureList = new ArrayList<>();
    private String DEVISE_PAR_DEFAUT = "EUR";
    private float TARIF_HORAIRE_RES = 10;
    private float DUREE_PROLONGEMENT_HEURE = (float) 0.25; // 15 min
    private float TARIF_SUPPLEMENT_RES = 2;
    private float TARIF_HORAIRE_CHARGE = 5;
    private float TARIF_PENALITE_INIT = 1;
    private float TAUX_AUGMENTATION_MIN = (float) 1.05;

    public DBMock_fileWriter() throws FileNotFoundException {
        File f = new File("database.ser");
        if(f.exists())
            read();
    }

    public void write() {
        ObjectOutputStream oos = null;
        FileOutputStream fichierOut;
        try {
            fichierOut = new FileOutputStream("database.ser");
            oos = new ObjectOutputStream(fichierOut);
            oos.writeObject(this);
            oos.flush();
        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture");
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                System.out.println("Erreur lors de la fermeture du fichier");
                ex.printStackTrace();
            }
        }
        System.out.println("Fichier écrit");
    }

    public void read() {
        ObjectInputStream ois = null;
        FileInputStream fichierIn;

        try {
            fichierIn = new FileInputStream("database.ser");
            ois = new ObjectInputStream(fichierIn);
            final DBMock_fileWriter data = (DBMock_fileWriter) ois.readObject();
            clientList = data.clientList;
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }

        System.out.println("DB lue");
    }

    @Override
    public Client getClient(Mail mail) {
        for (Client client : clientList) {
            if(client.getEmail().equals(mail))
                return client;
        }
        return null;
    }

    @Override
    public List<Client> getClients() {
        return clientList;
    }

    @Override
    public void deleteClient(Mail mail) {
        clientList.removeIf(x -> x.getEmail().equals(mail));
        write();
    }

    @Override
    public void updateClient(Client client) {
        clientList.removeIf(x -> x.getEmail().equals(client.getEmail()));
        clientList.add(client);
        write();
    }

    @Override
    public void addClient(Client client) {
        clientList.add(client);
        write();
    }

    @Override
    public Facture getFacture(int id) {
        // return factureList.stream().filter(x -> x.)
        return null;
    }

    @Override
    public List<Facture> getFactures() {
        return factureList;
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
        factureList.add(facture);
        write();
    }

    @Override
    public ReservationTemporaire getReservationTemporaire(NumReservation id) {
        Optional<ReservationTemporaire> res = reservationTemporaireList.stream().filter(x -> x.getId() == id).findAny();
        return res.get();
    }

    @Override
    public List<ReservationTemporaire> getReservationsTemporaires() {
        return reservationTemporaireList;
    }

    @Override
    public void deleteReservationTemporaire(NumReservation id) {
        reservationTemporaireList.removeIf(x -> x.getId().equals(id));
        write();
    }

    @Override
    public void updateReservationTemporaire(ReservationTemporaire reservation) {
        reservationTemporaireList.removeIf(x -> x.getId().equals(reservation.getId()));
        reservationTemporaireList.add(reservation);
        write();
    }

    @Override
    public void addReservationTemporaire(ReservationTemporaire reservation) {
        reservationTemporaireList.add(reservation);
        write();
    }

    @Override
    public boolean existsImmat(Immatriculation immat) {
        return true;
    }

    @Override
    public List<ReservationTemporaire> getReservationsTemporaireFromImmat(Immatriculation immat) {
        List<ReservationTemporaire> res = new LinkedList<>();
        res.addAll(reservationTemporaireList.stream().filter(x -> x.getImmat().equals(immat)).toList());
        return res;
    }

    @Override
    public void addPresence(Immatriculation immat) {
        presenceList.add(immat);
        write();
    }

    @Override
    public boolean isPresent(Immatriculation immat) {
        return presenceList.contains(immat);
    }

    @Override
    public void deletePresence(Immatriculation immat) {
        presenceList.remove(immat);
        write();
    }

    @Override
    public List<Immatriculation> getPresence() {
        return presenceList;
    }

    @Override
    public void addReservationPermanente(ReservationPermanente reservation) {
        reservationPermanenteList.add(reservation);
        write();
    }

    @Override
    public List<ReservationPermanente> getReservationsPermanentesFromClient(Mail mailClient) {
        return reservationPermanenteList.stream().filter(x -> x.getMailClient().equals(mailClient)).toList();
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
    public String getCurrency() {
        return DEVISE_PAR_DEFAUT;
    }

    @Override
    public void setCurrency(String cur) {
        DEVISE_PAR_DEFAUT = cur;
        write();
    }

    @Override
    public float getTarifHoraire() {
        return TARIF_HORAIRE_RES;
    }

    @Override
    public void setTarifHoraire(float value) {
        TARIF_HORAIRE_RES = value;
        write();
    }

    @Override
    public float getDureeProlongement() {
        return DUREE_PROLONGEMENT_HEURE;
    }

    @Override
    public void setDureeProlongement(float value) {
        DUREE_PROLONGEMENT_HEURE = value;
        write();
    }

    @Override
    public float getTarifSupplement() {
        return TARIF_SUPPLEMENT_RES;
    }

    @Override
    public void setTarifSupplement(float value) {
        TARIF_SUPPLEMENT_RES = value;
        write();
    }

    @Override
    public float getTarifHoraireCharge() {
        return TARIF_HORAIRE_CHARGE;
    }

    @Override
    public void setTarifHoraireCharge(float value) {
        TARIF_HORAIRE_CHARGE = value;
        write();
    }

    @Override
    public float getTarifPenalite() {
        return TARIF_PENALITE_INIT;
    }

    @Override
    public void setTarifPenalite(float value) {
        TARIF_PENALITE_INIT = value;
        write();
    }

    @Override
    public float getTauxAugmentationMin() {
        return TAUX_AUGMENTATION_MIN;
    }

    @Override
    public void setTauxAugmentationMin(float value) {
        TAUX_AUGMENTATION_MIN = value;
        write();
    }

    @Override
    public List<Reservation> getReservations() {
        List<Reservation> res = new ArrayList<>();
        res.addAll(reservationTemporaireList);
        res.addAll(reservationPermanenteList);
        return res;
    }

    @Override
    public Reservation getReservation(NumReservation id) {
        List<Reservation> res = new ArrayList<>();
        res.addAll(reservationTemporaireList);
        res.addAll(reservationPermanenteList);
        return res.stream().filter(x -> x.getId().equals(id)).findAny().get();
    }

    @Override
    public List<Reservation> getReservationsFromImmat(Immatriculation immat) {
        List<Reservation> res = new ArrayList<>();
        res.addAll(reservationTemporaireList);
        res.addAll(reservationPermanenteList);
        return res.stream().filter(x -> x.getImmat().equals(immat)).toList();
    }

}
