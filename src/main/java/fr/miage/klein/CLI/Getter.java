package fr.miage.klein.CLI;

import java.util.List;

import fr.miage.klein.BusinessLogic.Client;
import fr.miage.klein.BusinessLogic.Facture;
import fr.miage.klein.BusinessLogic.Immatriculation;
import fr.miage.klein.BusinessLogic.Reservation.Reservation;
import fr.miage.klein.BusinessLogic.Reservation.ReservationTemporaire;
import fr.miage.klein.Controller.IDatabaseController;

public class Getter {


    public static void helpGet() {
        System.out.println("Commande get");
        System.out.println("**********");
        System.out.println("* get help : afficher l'aide sur la commande get");
        System.out.println("* get clients : afficher les clients");
        System.out.println("* get reservations : afficher les reservation");
        System.out.println("* get presences : afficher les présences");
        System.out.println("* get factures : afficher les factures");
        System.out.println("**********");
    }

    public static void getClients(IDatabaseController db) {
        List<Client> clients = db.getClients();
        System.out.println("Liste des clients :");
        for (Client client : clients) {
            System.out.println("--");
            System.out.println("Nom : " + client.getNom() + " " + client.getPrenom());
            System.out.println("Mail : " + client.getEmail());
            System.out.println("--");
        }
    } 

    public static void getReservations(IDatabaseController db) {
        List<ReservationTemporaire> reservations = db.getReservationsTemporaires();
        System.out.println("Liste des réservations :");
        for (Reservation reservation : reservations) {
            System.out.println("--");
            System.out.println("Numéro : " + reservation.getId());
            System.out.println("Borne : " + reservation.getIdBorne());
            // System.out.println("Horaire : " + reservation.getDatetime());
            // System.out.println("Durée : " + reservation.getDuree() + " minutes");
            System.out.println("--");
        }
    } 

    public static void getPresence(IDatabaseController db) {
        List<Immatriculation> immats = db.getPresence();
        System.out.println("Liste des véhicules présents :");
        for (Immatriculation immat : immats) {
            System.out.println(immat);
        }
    } 

    public static void getFactures(IDatabaseController db) {
        List<Facture> factures = db.getFactures();
        System.out.println("Liste des factures :");
        for (Facture facture : factures) {
            System.out.println("--");
            // System.out.println("" + facture.);
            System.out.println("--");
        }
    }
}
