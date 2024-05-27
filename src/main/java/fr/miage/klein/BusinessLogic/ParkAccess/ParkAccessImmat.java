package fr.miage.klein.BusinessLogic.ParkAccess;

import fr.miage.klein.BusinessLogic.Reservation;
import fr.miage.klein.Controller.IDatabaseController;

public class ParkAccessImmat extends ParkAccess {
    
    public ParkAccessImmat(String value, IDatabaseController db) throws Exception {
        accessValue = value;

        if(!db.existsImmat(value))
            throw new Exception("Immatriculation non valide");

        //vérifier validité réservation

    }
}
