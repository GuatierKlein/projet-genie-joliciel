package fr.miage.klein.CLI;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.miage.klein.BusinessLogic.Client;
import fr.miage.klein.BusinessLogic.Immatriculation;
import fr.miage.klein.BusinessLogic.Mail;
import fr.miage.klein.BusinessLogic.Reservation.EJour;
import fr.miage.klein.BusinessLogic.Reservation.EMois;
import fr.miage.klein.BusinessLogic.Reservation.JourPlage;
import fr.miage.klein.BusinessLogic.Reservation.PlageHoraire;
import fr.miage.klein.BusinessLogic.Reservation.Reservation;
import fr.miage.klein.BusinessLogic.Reservation.ReservationPermanente;
import fr.miage.klein.BusinessLogic.Reservation.ReservationTemporaire;

public class Creator {
    public static Client createClientInteractive() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.println("Création interactive d'un client :");
        System.out.print("Nom : ");
        String nom = in.nextLine();
        System.out.print("Prénom : ");
        String prenom = in.nextLine();
        System.out.print("Adresse : ");
        String adresse = in.nextLine();
        System.out.print("Num téléphone : ");
        String numTel = in.nextLine();
        System.out.print("EMail : ");
        Mail email = new Mail(in.nextLine());
        System.out.print("Num CB : ");
        long numCb = Long.parseLong(in.nextLine());
        in.close();
        return new Client(prenom, nom, adresse, numTel, email, numCb); 
    }

    public static ReservationTemporaire createReservationIntractive() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.println("Création interactive d'une réservation :");
        System.out.print("Mail : ");
        Mail mail = new Mail(in.nextLine());
        System.out.print("Quand : ");
        LocalDateTime time = LocalDateTime.parse(in.nextLine());
        System.out.print("Durée : ");
        int duree = Integer.parseInt(in.nextLine());
        System.out.print("Immatriculation : ");
        Immatriculation immat = new Immatriculation(in.nextLine());

        in.close();
        return new ReservationTemporaire(time, duree, mail, immat);
    }

    public static ReservationPermanente createReservationPermanenteIntractive() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.println("Création interactive d'une réservation :");
        System.out.print("Mail : ");
        Mail mail = new Mail(in.nextLine());
        System.out.print("Immatriculation : ");
        Immatriculation immat = new Immatriculation(in.nextLine());

        ArrayList<JourPlage> jp = new ArrayList<JourPlage>();
        boolean continuer = true;
        do {
            System.out.print("Jour : (Lundi, Mardi...)");
            EJour jour = EJour.Lundi;
            for (EJour j : EJour.values()) {
                if (j.toString().equalsIgnoreCase(in.nextLine())) {
                    jour = j;
                }
            }
            System.out.print("Horaire de début :");
            LocalTime debut = LocalTime.parse(in.nextLine());
            System.out.print("Horaire de fin :");
            LocalTime fin = LocalTime.parse(in.nextLine());
            JourPlage jourPlage = new JourPlage(jour, new PlageHoraire(debut, fin));
            jp.add(jourPlage);

            System.out.print("Ajouter une autre plage horaire : 1\n Continuer : 0");
            if(Integer.parseInt(in.nextLine()) == 0)
                continuer = false;
        } while (continuer);
        System.out.print("A partir de quel mois :");
        EMois mois = EMois.Janvier;
        for (EMois m : EMois.values()) {
            if (m.toString().equalsIgnoreCase(in.nextLine())) {
                mois = m;
            }
        }
        System.out.print("Quelle annee :");
        int annee = Integer.parseInt(in.nextLine());
        System.out.print("Combien de mois :");
        int nbMois = Integer.parseInt(in.nextLine());
        in.close();
        return new ReservationPermanente(jp, mail, immat, mois, nbMois, annee);
    }

    public static void helpCreate() {
        System.out.println("Commande create");
        System.out.println("**********");
        System.out.println("* create help : afficher l'aide sur la commande créer");
        System.out.println("* create client : créer un nouveau client en mode interactif");
        System.out.println("* create reservation : créer une nouvelle reservation en mode interactif");
        System.out.println("* create reservationPermanente : créer une nouvelle reservation permanente en mode interactif");
        System.out.println("**********");
    }
}
