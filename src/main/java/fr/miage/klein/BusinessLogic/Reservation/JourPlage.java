package fr.miage.klein.BusinessLogic.Reservation;

public class JourPlage {
    private EJour jour;
    private PlageHoraire plageHoraire;
    
    public JourPlage(EJour jour, PlageHoraire plageHoraire){
        this.jour = jour;
        this.plageHoraire = plageHoraire;
    }

    public EJour getJour() {
        return this.jour;
    }

    public void setJour(EJour jour) {
        this.jour = jour;
    }

    public PlageHoraire getPlageHoraire() {
        return this.plageHoraire;
    }

    public void setPlageHoraire(PlageHoraire plageHoraire) {
        this.plageHoraire = plageHoraire;
    }

}
