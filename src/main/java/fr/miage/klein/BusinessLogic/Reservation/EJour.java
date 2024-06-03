package fr.miage.klein.BusinessLogic.Reservation;

import java.time.DayOfWeek;

public enum EJour {
    Lundi (DayOfWeek.MONDAY),
    Mardi (DayOfWeek.TUESDAY),
    Mercredi (DayOfWeek.WEDNESDAY),
    Jeudi (DayOfWeek.THURSDAY),
    Vendredi (DayOfWeek.FRIDAY),
    Samedi (DayOfWeek.SATURDAY),
    Dimanche (DayOfWeek.SUNDAY);

    private final DayOfWeek day;

    EJour(DayOfWeek day) {
        this.day = day;
    }

    public DayOfWeek getDay(){
        return this.day;
    }
}
