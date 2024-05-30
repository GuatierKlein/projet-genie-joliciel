package fr.miage.klein.BusinessLogic.Reservation;

import java.time.Duration;
import java.time.LocalTime;

public class PlageHoraire {
    private LocalTime debut;
    private LocalTime fin;

    public PlageHoraire(LocalTime debut, LocalTime fin){
        if (!verifPlageHoraire(debut, fin))
            throw new IllegalArgumentException();
        this.debut = debut;
        this.fin = fin;
    }

    private boolean verifPlageHoraire(LocalTime debut, LocalTime fin) {
        Duration duration = Duration.between(debut, fin);
        return duration.toMinutes() >= 30 && duration.toHours() < 24;
    }

    public LocalTime getDebut() {
        return this.debut;
    }

    public void setDebut(LocalTime debut) {
        this.debut = debut;
    }

    public LocalTime getFin() {
        return this.fin;
    }

    public void setFin(LocalTime fin) {
        this.fin = fin;
    }

}
