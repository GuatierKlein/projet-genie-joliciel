package fr.miage.klein.BusinessLogic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.miage.klein.BusinessLogic.ParkAccess.ParkAccessImmat;
import fr.miage.klein.BusinessLogic.ParkAccess.ParkAccessRes;
import fr.miage.klein.Mocks.DBMock;

public class BusinessLogicTests {
    private DBMock db = new DBMock();

    @Test
    @DisplayName("Vérification Value Object immatriculation : invalides")
    public void testInvalidImmatriculation() {
        assertThrows(IllegalArgumentException.class, () -> new Immatriculation("AB-1234-CD"));
        assertThrows(IllegalArgumentException.class, () -> new Immatriculation("AB-12-CD"));
        assertThrows(IllegalArgumentException.class, () -> new Immatriculation("AB123CD"));
        assertThrows(IllegalArgumentException.class, () -> new Immatriculation("12345 ABC 12"));
        assertThrows(IllegalArgumentException.class, () -> new Immatriculation("1234 ABCD 12"));
        assertThrows(IllegalArgumentException.class, () -> new Immatriculation("12 ABC 123"));
        assertThrows(IllegalArgumentException.class, () -> new Immatriculation("1234AB"));

        Immatriculation immat = new Immatriculation("AB-123-CD");
        assertFalse(immat.patternMatches("AB-1234-CD"));
        assertFalse(immat.patternMatches("AB-12-CD"));
        assertFalse(immat.patternMatches("AB123CD"));
        assertFalse(immat.patternMatches("12345 ABC 12"));
        assertFalse(immat.patternMatches("1234 ABCD 12"));
        assertFalse(immat.patternMatches("12 ABC 123"));
        assertFalse(immat.patternMatches("1234AB"));
    }

    @Test
    @DisplayName("Vérification Value Object immatriculation : valides")
    public void validVerifImmatriculation() {
        assertDoesNotThrow(() -> new Immatriculation("AB-123-CD"));
        assertDoesNotThrow(() -> new Immatriculation("1234 ABC 12"));
        assertDoesNotThrow(() -> new Immatriculation("1 AB 34"));
        assertDoesNotThrow(() -> new Immatriculation("567 DEF 78"));
        assertDoesNotThrow(() -> new Immatriculation("7890 G 90"));
        assertDoesNotThrow(() -> new Immatriculation("1234AB12"));
        assertDoesNotThrow(() -> new Immatriculation("AB-000-CD"));
        assertDoesNotThrow(() -> new Immatriculation("ZZ-999-ZZ"));
        assertDoesNotThrow(() -> new Immatriculation("0 A 00"));
        assertDoesNotThrow(() -> new Immatriculation("9999 ZZZ 99"));
        assertDoesNotThrow(() -> new Immatriculation("9999ZZZ99"));

        Immatriculation immat = new Immatriculation("AB-123-CD");
        assertTrue(immat.patternMatches("AB-123-CD"));
        assertTrue(immat.patternMatches("1234 ABC 12"));
        assertTrue(immat.patternMatches("1 AB 34"));
        assertTrue(immat.patternMatches("567 DEF 78"));
        assertTrue(immat.patternMatches("7890 G 90"));
        assertTrue(immat.patternMatches("1234AB12"));
    }

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

    @Test
    @DisplayName("Test acces parking immat")
    public void testParkingImmat() {
        try {
            ParkAccessImmat access = new ParkAccessImmat(new Immatriculation("AA-229-AA"), db);
            assertTrue(access.validate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    @Test
    @DisplayName("Test acces parking res")
    public void testParkingRes() {
        try {
            ParkAccessRes access = new ParkAccessRes(new NumReservation(1234), db);
            assertTrue(access.validate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
