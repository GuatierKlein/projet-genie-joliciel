package fr.miage.klein.BusinessLogic.ParkAccess;

import java.util.List;

import fr.miage.klein.BusinessLogic.Reservation;
import fr.miage.klein.Controller.IDatabaseController;

public class ParkAccessImmat extends ParkAccess {
    
    public ParkAccessImmat(String value, IDatabaseController db) throws Exception {
        accessValue = value;

        if(!db.existsImmat(value))
            throw new Exception("Immatriculation non valide");

        List<Reservation> res = db.getReservationsFromImmat(value);
        if(!isOneResValid(res))
            throw new Exception("Aucune reservation valide");

    }

    private boolean isOneResValid(List<Reservation> reservations) {
        for (Reservation reservation : reservations) {
            if(reservation.isValidForAccess())
                return true;
        }
        return false;
    }
}
