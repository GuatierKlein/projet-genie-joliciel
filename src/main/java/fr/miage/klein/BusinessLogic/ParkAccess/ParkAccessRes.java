package fr.miage.klein.BusinessLogic.ParkAccess;

import fr.miage.klein.BusinessLogic.NumReservation;
import fr.miage.klein.BusinessLogic.Reservation.Reservation;
import fr.miage.klein.Controller.IDatabaseController;

public class ParkAccessRes extends ParkAccess {
    private NumReservation value;

    public ParkAccessRes(NumReservation value, IDatabaseController db) throws Exception {
        super(db);
        this.value = value;
    }

    @Override
    public boolean validate() {
        Reservation res = db.getReservation(value);
        if(res == null)
            return false;

        return res.isValidForAccess() && !db.isPresent(res.getImmat());
    }

    @Override
    public void save() {
        Reservation res = db.getReservationTemporaire(value);
        db.addPresence(res.getImmat());
    }
}
