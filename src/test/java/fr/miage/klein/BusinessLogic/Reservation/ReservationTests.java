package fr.miage.klein.BusinessLogic.Reservation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import fr.miage.klein.BusinessLogic.Immatriculation;
import fr.miage.klein.BusinessLogic.Mail;

public class ReservationTests {
    // Donner le jour actuel
    // Donner un autre jour

    JourPlage jp1 = new JourPlage(EJour.Dimanche, new PlageHoraire(LocalTime.of(9, 0), LocalTime.of(10, 0)));
    JourPlage jp2 = new JourPlage(EJour.Dimanche, new PlageHoraire(LocalTime.of(10, 0), LocalTime.of(11, 0)));
    JourPlage jp3 = new JourPlage(EJour.Dimanche, new PlageHoraire(LocalTime.of(11, 0), LocalTime.of(12, 0)));
    JourPlage jp4 = new JourPlage(EJour.Dimanche, new PlageHoraire(LocalTime.of(8, 0), LocalTime.of(9, 30)));
        
    // Création des réservations pour les tests
    ReservationPermanente reservation1 = new ReservationPermanente(Arrays.asList(jp1), new Mail("test1@example.com"), new Immatriculation("AB-123-CD"), EMois.Janvier, 1, 2024);
    ReservationPermanente reservation2 = new ReservationPermanente(Arrays.asList(jp2), new Mail("test2@example.com"), new Immatriculation("AB-123-CD"), EMois.Janvier, 1, 2024);

    @Test
    public void testValidVerifProblemePlagePerm() {
        //jp1 : 9:00-10:00
        //jp3 : 11:00-12:00
        reservation2.setJourPlage(Arrays.asList(jp3));

        assertFalse(reservation1.verifProblemePlagePerm(reservation2));
    }

    @Test
    public void testInalidVerifProblemePlagePerm() {
        //jp1 : 9:00-10:00
        //jp4 : 08:00-09:30
        reservation2.setJourPlage(Arrays.asList(jp4));

        assertTrue(reservation1.verifProblemePlagePerm(reservation2));
    }

    @Test
    public void testValidContigueVerifProblemePlagePerm() {
        //jp1 : 9:00-10:00
        //jp2 : 10:00-11:00
        reservation2.setJourPlage(Arrays.asList(jp2));

        assertFalse(reservation1.verifProblemePlagePerm(reservation2));
    }

    @Test
    public void testValidContigue2VerifProblemePlagePerm() {
        //jp1 : 9:00-10:00
        //jp4 : 08:00-09:30
        reservation2.setJourPlage(Arrays.asList(jp4));

        assertTrue(reservation1.verifProblemePlagePerm(reservation2));
    }

    @Test
    public void testValidVerifContiguePerm() {
        //jp1 : 9:00-10:00
        //jp2 : 10:00-11:00
        reservation2.setJourPlage(Arrays.asList(jp2));

        assertTrue(reservation1.verifContiguePerm(reservation2));
    }

    @Test
    public void testInvalidVerifContiguePerm() {
        //jp1 : 9:00-10:00
        //jp4 : 08:00-09:30
        reservation2.setJourPlage(Arrays.asList(jp4));

        assertFalse(reservation1.verifContiguePerm(reservation2));
    }
    


    @Test
    public void testInvalidPlageHoraire() {
        LocalTime debut = LocalTime.of(9, 0);
        LocalTime fin = LocalTime.of(9, 15);
        
        assertThrows(IllegalArgumentException.class, () -> new PlageHoraire(debut, fin));
    }

    @Test
    public void testInvalid2PlageHoraire() {
        LocalTime debut = LocalTime.of(9, 0);
        LocalTime fin = LocalTime.of(9, 29);
        
        assertThrows(IllegalArgumentException.class, () -> new PlageHoraire(debut, fin));
    }

    @Test
    public void testValidPlageHoraire() {
        LocalTime debut = LocalTime.of(9, 0);
        LocalTime fin = LocalTime.of(9, 31); // Just over 30 minutes
        
        assertDoesNotThrow(() -> new PlageHoraire(debut, fin));
    }

    @Test
    public void testInvalid3PlageHoraire() {
        LocalTime debut = LocalTime.of(11, 0);
        LocalTime fin = LocalTime.of(9, 31); // Just over 30 minutes
        
        assertThrows(IllegalArgumentException.class, () -> new PlageHoraire(debut, fin));
    }

    ArrayList<JourPlage> jourPlage = new ArrayList<>();
    Mail mailClient = new Mail("test@example.com");
    Immatriculation immat = new Immatriculation("AB-123-CD");

    @Test
    public void testValidMoisVerifMoisAnnee() {
        ReservationPermanente reservation = new ReservationPermanente(jourPlage, mailClient, immat, EMois.Janvier, 12, 2023);
        reservation.setMois(EMois.Janvier);
        reservation.setNbMois(12);
        reservation.setAnnee(LocalDate.now().getYear());

        assertTrue(reservation.verifMoisAnnee());
    }

    @Test
    public void testInvalidAnneeVerifMoisAnnee() {
        ReservationPermanente reservation = new ReservationPermanente(jourPlage, mailClient, immat, EMois.Janvier, 12, 2023);
        
        reservation.setMois(EMois.Janvier);
        reservation.setNbMois(12);
        reservation.setAnnee(LocalDate.now().getYear() - 2);

        assertFalse(reservation.verifMoisAnnee());
    }

    @Test
    public void testInvalidMoisVerifMoisAnnee() {
        ReservationPermanente reservation = new ReservationPermanente(jourPlage, mailClient, immat, EMois.Janvier, 12, 2023);
        
        reservation.setMois(EMois.Janvier);
        reservation.setNbMois(1);
        reservation.setAnnee(LocalDate.now().getYear());

        reservation.setMois(EMois.Janvier);
        assertFalse(reservation.verifMoisAnnee());
    }

    @Test
    public void testValidFinAnneesVerifMoisAnnee() {
        ReservationPermanente reservation = new ReservationPermanente(jourPlage, mailClient, immat, EMois.Janvier, 12, 2023);
        
        reservation.setMois(EMois.Decembre);
        reservation.setNbMois(1);
        reservation.setAnnee(LocalDate.now().getYear());

        assertTrue(reservation.verifMoisAnnee());
    }

    @Test
    public void testValidMultiAnneesVerifMoisAnnee() {
        ReservationPermanente reservation = new ReservationPermanente(jourPlage, mailClient, immat, EMois.Janvier, 12, 2023);
        
        reservation.setMois(EMois.Janvier);
        reservation.setNbMois(24); // 2 ans
        reservation.setAnnee(LocalDate.now().getYear() - 1);

        assertTrue(reservation.verifMoisAnnee());
    }

    @Test
    public void testValidVerifJour() {
        ReservationPermanente reservation = new ReservationPermanente(jourPlage, mailClient, immat, EMois.Janvier, 12, 2023);
        jourPlage.add(new JourPlage(EJour.Vendredi, new PlageHoraire(LocalTime.of(9,30,0), LocalTime.of(10,0,0))));

        reservation.setJourPlage(jourPlage);

        assertTrue(reservation.verifJour());
    }

    @Test
    public void testInvalidVerifJour() {
        jourPlage.add(new JourPlage(EJour.Mercredi, new PlageHoraire(LocalTime.of(9,30,0), LocalTime.of(10,0,0))));
        ReservationPermanente reservation = new ReservationPermanente(jourPlage, mailClient, immat, EMois.Janvier, 12, 2023);

        reservation.setJourPlage(jourPlage);

        assertFalse(reservation.verifJour());
    }

    @Test
    public void testValidPlusieursJoursVerifJour() {
        jourPlage.add(new JourPlage(EJour.Vendredi, new PlageHoraire(LocalTime.of(9,30,0), LocalTime.of(10,0,0))));
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

        jourPlage.add(new JourPlage(EJour.Mardi, new PlageHoraire(LocalTime.of(9,30,0), LocalTime.of(10,0,0))));
        jourPlage.add(new JourPlage(EJour.Mercredi, new PlageHoraire(LocalTime.of(9,30,0), LocalTime.of(10,0,0))));
        ReservationPermanente reservation = new ReservationPermanente(jourPlage, mailClient, immat, EMois.Janvier, 12, 2023);

        reservation.setJourPlage(jourPlage);

        assertFalse(reservation.verifJour());
    }

    @Test
    public void testInvalidVideVerifJour() {
        ReservationPermanente reservation = new ReservationPermanente(jourPlage, mailClient, immat, EMois.Janvier, 12, 2023);

        reservation.setJourPlage(new ArrayList<>());

        assertFalse(reservation.verifJour());
    }
}
