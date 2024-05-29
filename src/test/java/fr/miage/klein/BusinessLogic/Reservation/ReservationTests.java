package fr.miage.klein.BusinessLogic.Reservation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

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
        LocalTime fin = LocalTime.of(9, 30);
        
        assertThrows(IllegalArgumentException.class, () -> new PlageHoraire(debut, fin));
    }

    @Test
    public void testPlageHoraireJustOverThirtyMinutes() {
        LocalTime debut = LocalTime.of(9, 0);
        LocalTime fin = LocalTime.of(9, 31); // Just over 30 minutes
        
        assertDoesNotThrow(() -> new PlageHoraire(debut, fin));
    }
}
