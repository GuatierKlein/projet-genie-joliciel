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
import fr.miage.klein.BusinessLogic.Facture;
import fr.miage.klein.BusinessLogic.Immatriculation;
import fr.miage.klein.BusinessLogic.Mail;
import fr.miage.klein.BusinessLogic.NumReservation;
import fr.miage.klein.BusinessLogic.Reservation;
import fr.miage.klein.Controller.IDatabaseController;

public class DBMock_fileWriter implements IDatabaseController, Serializable {

    List<Client> clientList = new ArrayList<>();
    List<Reservation> reservationList = new ArrayList<>();
    List<Borne> borneList = new ArrayList<>();
    List<Immatriculation> presenceList = new ArrayList<>();
    List<Facture> factureList = new ArrayList<>();

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
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
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
    public Reservation getReservation(NumReservation id) {
        Optional<Reservation> res = reservationList.stream().filter(x -> x.getId() == id).findAny();
        return res.get();
    }

    @Override
    public List<Reservation> getReservations() {
        return reservationList;
    }

    @Override
    public void deleteReservation(NumReservation id) {
        reservationList.removeIf(x -> x.getId().equals(id));
        write();
    }

    @Override
    public void updateReservation(Reservation reservation) {
        reservationList.removeIf(x -> x.getId().equals(reservation.getId()));
        reservationList.add(reservation);
        write();
    }

    @Override
    public void addReservation(Reservation reservation) {
        reservationList.add(reservation);
        write();
    }

    @Override
    public boolean existsImmat(Immatriculation immat) {
        return true;
    }

    @Override
    public List<Reservation> getReservationsFromImmat(Immatriculation immat) {
        List<Reservation> res = new LinkedList<>();
        res.addAll(reservationList.stream().filter(x -> x.getImmat().equals(immat)).toList());
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

}
