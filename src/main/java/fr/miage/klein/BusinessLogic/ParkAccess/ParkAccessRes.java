package fr.miage.klein.BusinessLogic.ParkAccess;

import fr.miage.klein.BusinessLogic.Reservation;
import fr.miage.klein.Controller.IDatabaseController;

public class ParkAccessRes extends ParkAccess {
    public ParkAccessRes(String value, IDatabaseController db) throws Exception {
        accessValue = value;
        Reservation res = db.getReservation(Integer.parseInt(value));
        if(res == null)
            throw new Exception("Reservation non valide");

        //vérifier validité réservation

    }
}
