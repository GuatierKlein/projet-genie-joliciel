package fr.miage.klein.BusinessLogic.ParkAccess;

import java.util.List;

import fr.miage.klein.BusinessLogic.Immatriculation;
import fr.miage.klein.BusinessLogic.Reservation;
import fr.miage.klein.Controller.IDatabaseController;

public class ParkAccessImmat extends ParkAccess {
    private Immatriculation value;
    
    public ParkAccessImmat(Immatriculation value, IDatabaseController db) throws Exception {
        super(db);
        this.value = value;



    }

    private boolean isOneResValid(List<Reservation> reservations) {
        for (Reservation reservation : reservations) {
            if(reservation.isValidForAccess())
                return true;
        }
        return false;
    }

    @Override
    public boolean validate() {
        if(!db.existsImmat(value))
            return false;

        List<Reservation> res = db.getReservationsFromImmat(value);
        if(!isOneResValid(res))
            return false;
        return true;
    }

}
