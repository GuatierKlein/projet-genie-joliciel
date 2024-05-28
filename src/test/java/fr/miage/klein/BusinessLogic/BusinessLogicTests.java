package fr.miage.klein.BusinessLogic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BusinessLogicTests {

    @Test
    @DisplayName("Vérification Value Object mail : invalides")
    public void validVerifMails() {
        assertDoesNotThrow(() -> {
            new Mail("example@example.com");
        });

        assertDoesNotThrow(() -> {
            new Mail("user.name@domain.co");
        });

        assertDoesNotThrow(() -> {
            new Mail("user_name@domain.co.in");
        });

        assertDoesNotThrow(() -> {
            new Mail("user-name@domain.com");
        });

        assertDoesNotThrow(() -> {
            new Mail("username123@sub.domain.com");
        });
    }

    @Test
    @DisplayName("Vérification Value Object mail : valides")
    public void invalidVerifMails() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Mail("example@example");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Mail("example@.com");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Mail("@example.com");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Mail("example@-example.com");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Mail("example@example..com");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Mail("example@example.c");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Mail("example@.com.");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Mail("example@com");
        });
    }

    @Test
    @DisplayName("Vérification Value Object numéro de réservation : valides")
    public void validVerifNumReservation(){
        NumReservation reservation = new NumReservation(0);
        assertTrue(reservation.verifNumReservation(0));
        assertTrue(reservation.verifNumReservation(99999));

        NumReservation reservation1 = new NumReservation(0);
        assertEquals(0, reservation1.getNum());

        NumReservation reservation2 = new NumReservation(50000);
        assertEquals(50000, reservation2.getNum());

        NumReservation reservation3 = new NumReservation(99999);
        assertEquals(99999, reservation3.getNum());

        NumReservation reservation4 = new NumReservation(0);
        assertEquals(0, reservation4.getNum());

        NumReservation reservation5 = new NumReservation(99999);
        assertEquals(99999, reservation5.getNum());
    }

    @Test
    @DisplayName("Vérification Value Object numéro de réservation : invalides")
    public void invalidVerifNumReservation(){
        NumReservation reservation = new NumReservation(0); // This is to instantiate the class for calling the method
        assertFalse(reservation.verifNumReservation(-1));
        assertFalse(reservation.verifNumReservation(100000));
        
        assertThrows(IllegalArgumentException.class, () -> {
            new NumReservation(-1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new NumReservation(100000);
        });

        assertFalse(reservation.verifNumReservation(-10));
        assertFalse(reservation.verifNumReservation(100001));
    }
}
