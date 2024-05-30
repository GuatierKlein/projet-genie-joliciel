package fr.miage.klein.BusinessLogic.Reservation;

import java.time.Month;

public enum EMois {
    Janvier (Month.JANUARY),
    Février (Month.FEBRUARY),
    Mars (Month.MARCH),
    Avril (Month.APRIL),
    Mai (Month.MAY),
    Juin (Month.JUNE),
    Juillet (Month.JULY),
    Août (Month.AUGUST),
    Septembre (Month.SEPTEMBER),
    Octobre (Month.OCTOBER),
    Novembre (Month.NOVEMBER),
    Decembre (Month.DECEMBER);

    private final Month mois;

    EMois(Month mois) {
        this.mois = mois;
    }

    public Month getMonth(){
        return this.mois;
    }
}
