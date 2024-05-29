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
import java.util.List;

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
    public Client getClient(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClient'");
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
    public void deleteClient(int id) {
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
        clientList.add(client);
        write();
    }

    @Override
    public Client getFacture(int id) {
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
    public Reservation getReservation(NumReservation id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReservation'");
    }

    @Override
    public List<Reservation> getReservations() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReservations'");
    }

    @Override
    public void deleteReservation() {
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
    public boolean existsImmat(Immatriculation immat) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'existsImmat'");
    }

    @Override
    public List<Reservation> getReservationsFromImmat(Immatriculation immat) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReservationsFromImmat'");
    }

    @Override
    public void addPresence(Immatriculation immat) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addPresence'");
    }

    @Override
    public boolean isPresent(Immatriculation immat) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isPresent'");
    }

    @Override
    public void deletePresence(Immatriculation immat) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePresence'");
    }

}
