package fr.miage.klein.BusinessLogic.ParkAccess;

import java.time.LocalDateTime;

import fr.miage.klein.BusinessLogic.Reservation;
import fr.miage.klein.Controller.IDatabaseController;

public class ParkAccessRes extends ParkAccess {
    public ParkAccessRes(String value, IDatabaseController db) throws Exception {
        accessValue = value;
        Reservation res = db.getReservation(Integer.parseInt(value));
        if(res == null)
            throw new Exception("Reservation non valide");

        //settings période attente
        if(res.getDatetime().isBefore(LocalDateTime.now()) || LocalDateTime.now().isAfter(res.getDatetime().plusMinutes(10)))
            throw new Exception("Reservation non valide");

    }
}
