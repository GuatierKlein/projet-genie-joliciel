package fr.miage.klein.BusinessLogic.Reservation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.miage.klein.BusinessLogic.Immatriculation;
import fr.miage.klein.BusinessLogic.Mail;

public class ReservationTests {
    @Test
    public void testPlageHoraireTooShort() {
        LocalTime debut = LocalTime.of(9, 0);
        LocalTime fin = LocalTime.of(9, 15);
        
        assertThrows(IllegalArgumentException.class, () -> new PlageHoraire(debut, fin));
    }

    @Test
    public void testPlageHoraireExactlyThirtyMinutes() {
        LocalTime debut = LocalTime.of(9, 0);
        LocalTime fin = LocalTime.of(9, 29);
        
        assertThrows(IllegalArgumentException.class, () -> new PlageHoraire(debut, fin));
    }

    @Test
    public void testPlageHoraireJustOverThirtyMinutes() {
        LocalTime debut = LocalTime.of(9, 0);
        LocalTime fin = LocalTime.of(9, 31); // Just over 30 minutes
        
        assertDoesNotThrow(() -> new PlageHoraire(debut, fin));
    }

    @Test
    public void testPlageHoraire() {
        LocalTime debut = LocalTime.of(11, 0);
        LocalTime fin = LocalTime.of(9, 31); // Just over 30 minutes
        
        assertThrows(IllegalArgumentException.class, () -> new PlageHoraire(debut, fin));
    }



    @Test
    public void testVerifMoisAnnee_validCase() {
        List<JourPlage> jourPlage = new ArrayList<>();
        Mail mailClient = new Mail("test@example.com");
        Immatriculation immat = new Immatriculation("AB-123-CD");
        ReservationPermanente reservation = new ReservationPermanente(jourPlage, mailClient, immat, EMois.Janvier, 12, 2023);
        // Test avec une réservation valide
        reservation.setMois(EMois.Janvier);
        reservation.setNbMois(12); // 1 année
        reservation.setAnnee(LocalDate.now().getYear());

        assertTrue(reservation.verifMoisAnnee());
    }

    @Test
    public void testVerifMoisAnnee_invalidYear() {
        List<JourPlage> jourPlage = new ArrayList<>();
        Mail mailClient = new Mail("test@example.com");
        Immatriculation immat = new Immatriculation("AB-123-CD");
        ReservationPermanente reservation = new ReservationPermanente(jourPlage, mailClient, immat, EMois.Janvier, 12, 2023);
        
        // Test avec une année expirée
        reservation.setMois(EMois.Janvier);
        reservation.setNbMois(12); // 1 année
        reservation.setAnnee(LocalDate.now().getYear() - 2);

        assertFalse(reservation.verifMoisAnnee());
    }

    @Test
    public void testVerifMoisAnnee_invalidMonth() {
        List<JourPlage> jourPlage = new ArrayList<>();
        Mail mailClient = new Mail("test@example.com");
        Immatriculation immat = new Immatriculation("AB-123-CD");
        ReservationPermanente reservation = new ReservationPermanente(jourPlage, mailClient, immat, EMois.Janvier, 12, 2023);
        
        // Test avec un mois expiré dans l'année en cours
        reservation.setMois(EMois.Janvier);
        reservation.setNbMois(1);
        reservation.setAnnee(LocalDate.now().getYear());

        // Set to a past month
        reservation.setMois(EMois.Janvier);
        assertFalse(reservation.verifMoisAnnee());
    }

    @Test
    public void testVerifMoisAnnee_edgeCaseEndOfYear() {
        List<JourPlage> jourPlage = new ArrayList<>();
        Mail mailClient = new Mail("test@example.com");
        Immatriculation immat = new Immatriculation("AB-123-CD");
        ReservationPermanente reservation = new ReservationPermanente(jourPlage, mailClient, immat, EMois.Janvier, 12, 2023);
        
        // Test pour une réservation qui expire en décembre de l'année en cours
        reservation.setMois(EMois.Decembre);
        reservation.setNbMois(1);
        reservation.setAnnee(LocalDate.now().getYear());

        assertTrue(reservation.verifMoisAnnee());
    }

    @Test
    public void testVerifMoisAnnee_validMultiYear() {
        List<JourPlage> jourPlage = new ArrayList<>();
        Mail mailClient = new Mail("test@example.com");
        Immatriculation immat = new Immatriculation("AB-123-CD");
        ReservationPermanente reservation = new ReservationPermanente(jourPlage, mailClient, immat, EMois.Janvier, 12, 2023);
        
        // Test avec une réservation valide sur plusieurs années
        reservation.setMois(EMois.Janvier);
        reservation.setNbMois(24); // 2 ans
        reservation.setAnnee(LocalDate.now().getYear() - 1);

        assertTrue(reservation.verifMoisAnnee());
    }

    public void testVerifJour_validCase() {
        ArrayList<JourPlage> jourPlage = new ArrayList<>();
        Mail mailClient = new Mail("test@example.com");
        Immatriculation immat = new Immatriculation("AB-123-CD");
        
        // Initialisation d'une réservation avec des valeurs par défaut
        ReservationPermanente reservation = new ReservationPermanente(jourPlage, mailClient, immat, EMois.Janvier, 12, 2023);
        // Ajoute le jour de la semaine actuel
        jourPlage.add(new JourPlage(EJour.Jeudi, new PlageHoraire(LocalTime.of(9,30,0), LocalTime.of(10,0,0))));

        reservation.setJourPlage(jourPlage);

        assertTrue(reservation.verifJour());
    }

    @Test
    public void testVerifJour_invalidCase() {
        ArrayList<JourPlage> jourPlage = new ArrayList<>();
        Mail mailClient = new Mail("test@example.com");
        Immatriculation immat = new Immatriculation("AB-123-CD");
        // Ajoute un jour de la semaine différent du jour actuel
        jourPlage.add(new JourPlage(EJour.Mercredi, new PlageHoraire(LocalTime.of(9,30,0), LocalTime.of(10,0,0))));
        ReservationPermanente reservation = new ReservationPermanente(jourPlage, mailClient, immat, EMois.Janvier, 12, 2023);

        reservation.setJourPlage(jourPlage);

        assertFalse(reservation.verifJour());
    }

    @Test
    public void testVerifJour_multipleDaysValid() {
        ArrayList<JourPlage> jourPlage = new ArrayList<>();
        Mail mailClient = new Mail("test@example.com");
        Immatriculation immat = new Immatriculation("AB-123-CD");
        // Ajoute plusieurs jours incluant le jour actuel
        jourPlage.add(new JourPlage(EJour.Jeudi, new PlageHoraire(LocalTime.of(9,30,0), LocalTime.of(10,0,0))));
        jourPlage.add(new JourPlage(EJour.Mardi, new PlageHoraire(LocalTime.of(9,30,0), LocalTime.of(10,0,0))));
        ReservationPermanente reservation = new ReservationPermanente(jourPlage, mailClient, immat, EMois.Janvier, 12, 2023);

        reservation.setJourPlage(jourPlage);

        assertTrue(reservation.verifJour());
    }

    @Test
    public void testVerifJour_multipleDaysInvalid() {
        ArrayList<JourPlage> jourPlage = new ArrayList<>();
        Mail mailClient = new Mail("test@example.com");
        Immatriculation immat = new Immatriculation("AB-123-CD");
        // Ajoute plusieurs jours n'incluant pas le jour actuel
        jourPlage.add(new JourPlage(EJour.Mardi, new PlageHoraire(LocalTime.of(9,30,0), LocalTime.of(10,0,0))));
        jourPlage.add(new JourPlage(EJour.Mercredi, new PlageHoraire(LocalTime.of(9,30,0), LocalTime.of(10,0,0))));
        ReservationPermanente reservation = new ReservationPermanente(jourPlage, mailClient, immat, EMois.Janvier, 12, 2023);

        reservation.setJourPlage(jourPlage);

        assertFalse(reservation.verifJour());
    }

    @Test
    public void testVerifJour_emptyDays() {
        ArrayList<JourPlage> jourPlage = new ArrayList<>();
        Mail mailClient = new Mail("test@example.com");
        Immatriculation immat = new Immatriculation("AB-123-CD");
        ReservationPermanente reservation = new ReservationPermanente(jourPlage, mailClient, immat, EMois.Janvier, 12, 2023);
        // Pas de jours ajoutés
        reservation.setJourPlage(new ArrayList<>());

        assertFalse(reservation.verifJour());
    }
}
