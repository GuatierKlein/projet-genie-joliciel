package fr.miage.klein.BusinessLogic.ParkAccess;

import fr.miage.klein.BusinessLogic.NumReservation;
import fr.miage.klein.BusinessLogic.Reservation;
import fr.miage.klein.Controller.IDatabaseController;

public class ParkAccessRes extends ParkAccess {
    private NumReservation value;

    public ParkAccessRes(NumReservation value, IDatabaseController db) throws Exception {
        super(db);
        this.value = value;
        Reservation res = db.getReservation(value);
        if(res == null)
            throw new Exception("Reservation non valide");

        if(res.isValidForAccess()) 
            throw new Exception("Reservation non valide");

    }
}
