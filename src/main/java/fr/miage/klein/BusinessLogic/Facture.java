package fr.miage.klein.BusinessLogic;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;

public class Facture implements Serializable {
    
    public static final String deviseParDefaut = "EUR";
    public static final float tarifHoraireRes = 10;
    public static final float dureeProlongementEnHeure = (float) 0.25; // 15 min
    public static final float tarifSupplementResRetard = 2;
    public static final float tarifHoraireCharge = 5;
    public static final float tarifPenaliteInitial = 1;
    public static final float tauxAugmentationPenParMinute = (float) 1.05;
    

    private LocalDateTime dateFacture;
    private Frais fraisRecharge;
    private Frais fraisReservation;
    private Frais fraisPenalite;
    private Frais fraisProlongation;

    public Facture(Frais fraisRecharge, Frais fraisReservation, Frais fraisPenalite, Frais fraisProlongation, LocalDateTime dateFacture) {
        this.fraisRecharge = fraisRecharge;
        this.fraisReservation = fraisReservation;
        this.fraisPenalite = fraisPenalite;
        this.dateFacture = dateFacture;
    }
    
    public static float calculFraisReservation(Time dureeCreneau, boolean accepteSupplement){
        if (accepteSupplement) 
            return  tarifSupplementResRetard + dureeCreneau.getTime()/3600000 * tarifHoraireRes;
        return dureeCreneau.getTime()/3600000 * tarifHoraireRes;
        }

    public static float calculFraisProlongementReservation(){ 
        return  dureeProlongementEnHeure * tarifHoraireRes; 
        }

    public static float calculFraisPenalite(Time dureeEnTrop){
        var coutPen = tarifPenaliteInitial + Math.pow(tauxAugmentationPenParMinute,dureeEnTrop.getTime()/60000);
        return (float) coutPen;
    }

    public static float calculFraisRecharge(Time dureeCharge){
        return (float) dureeCharge.getTime()/3600000 * tarifHoraireCharge;
    }
    
    public float calcuFraisTotaux(float fraisReservation, float fraisRecharge, float fraisPenalite, float fraisProlongation) {
        return fraisReservation + fraisRecharge + fraisPenalite + fraisProlongation;
    }
    
    

}
