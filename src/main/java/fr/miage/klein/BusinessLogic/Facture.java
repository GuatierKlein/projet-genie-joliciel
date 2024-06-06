package fr.miage.klein.BusinessLogic;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

import fr.miage.klein.Controller.IDatabaseController;

public class Facture implements Serializable {
    private LocalDateTime dateFacture;
    private Frais fraisRecharge;
    private Frais fraisReservation;
    private Frais fraisPenalite;
    private Frais fraisProlongation;
    private int Id;

    // Constructeur sans Id
    public Facture(Frais fraisRecharge, Frais fraisReservation, Frais fraisPenalite, Frais fraisProlongation, LocalDateTime dateFacture) {
        this.fraisRecharge = fraisRecharge;
        this.fraisReservation = fraisReservation;
        this.fraisPenalite = fraisPenalite;
        this.dateFacture = dateFacture;
    }

    // Constructeur avec Id
    public Facture(int Id, Frais fraisRecharge, Frais fraisReservation, Frais fraisPenalite, Frais fraisProlongation, LocalDateTime dateFacture) {
        this.fraisRecharge = fraisRecharge;
        this.fraisReservation = fraisReservation;
        this.fraisPenalite = fraisPenalite;
        this.dateFacture = dateFacture;
        this.Id = Id;
    }
    
    public static float calculFraisReservation(Duration dureeCreneau, boolean accepteSupplement, IDatabaseController db){
        float tarifH = db.getTarifHoraire();
        if (accepteSupplement) 
            return  tarifH + dureeCreneau.toHours() * tarifH;
        return dureeCreneau.toHours() * tarifH;
        }

    public static float calculFraisProlongementReservation(IDatabaseController db){ 
        return  db.getDureeProlongement() * db.getTarifHoraire(); 
        }

    public static float calculFraisPenalite(Duration dureeEnTrop, IDatabaseController db){
        var coutPen = db.getTarifPenalite() + Math.pow(db.getTauxAugmentationMin(),dureeEnTrop.toMinutes());
        return (float) coutPen;
    }

    public static float calculFraisRecharge(Duration dureeCharge, IDatabaseController db){
        return (float) dureeCharge.toHours() * db.getTarifHoraireCharge();
    }
    
    public float calcuFraisTotaux(float fraisReservation, float fraisRecharge, float fraisPenalite, float fraisProlongation) {
        return fraisReservation + fraisRecharge + fraisPenalite + fraisProlongation;
    }

    public LocalDateTime getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(LocalDateTime dateFacture) {
        this.dateFacture = dateFacture;
    }

    public Frais getFraisRecharge() {
        return fraisRecharge;
    }

    public void setFraisRecharge(Frais fraisRecharge) {
        this.fraisRecharge = fraisRecharge;
    }

    public Frais getFraisReservation() {
        return fraisReservation;
    }

    public void setFraisReservation(Frais fraisReservation) {
        this.fraisReservation = fraisReservation;
    }

    public Frais getFraisPenalite() {
        return fraisPenalite;
    }

    public void setFraisPenalite(Frais fraisPenalite) {
        this.fraisPenalite = fraisPenalite;
    }

    public Frais getFraisProlongation() {
        return fraisProlongation;
    }

    public void setFraisProlongation(Frais fraisProlongation) {
        this.fraisProlongation = fraisProlongation;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
}
