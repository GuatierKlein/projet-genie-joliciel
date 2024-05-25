package fr.miage.klein;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Facture {
    
    public static final String deviseParDefaut = "EUR";
    public static final float tarifHoraireRes = 10;
    public static final float tarifSupplementResRetard = 2;
    public static final float tarifHoraireCharge = 5;
    public static final float tarifPenaliteInitial = 1;
    public static final float tauxAugmentationPenParMinute = (float) 1.05;
    

    private LocalDateTime dateFacture;
    private Frais fraisRecharge;
    private Frais fraisReservation;
    private Frais fraisPenalite;

    public Facture(Frais fraisRecharge, Frais fraisReservation, Frais fraisPenalite, LocalDateTime dateFacture) {
        this.fraisRecharge = fraisRecharge;
        this.fraisReservation = fraisReservation;
        this.fraisPenalite = fraisPenalite;
        this.dateFacture = dateFacture;
    }
    
    private float calculFraisReservation(LocalTime dureeCreneau, boolean accepteSupplement){
        if (accepteSupplement) 
            return  tarifSupplementResRetard + dureeCreneau.getHour() * tarifHoraireRes;
        return dureeCreneau.getHour() * tarifHoraireRes;
        }

    private float calculFraisPenalite(LocalDateTime dureeEnTrop){
        var coutPen = tarifPenaliteInitial + Math.pow(tauxAugmentationPenParMinute,dureeEnTrop.getMinute());
        return (float) coutPen;
    }

    private float calculFraisRecharge(LocalDateTime dureeCharge){
        return dureeCharge.getHour() * tarifHoraireCharge;
    }
    
    private float calcuFraisTotaux(float fraisReservation, float fraisRecharge, float fraisPenalite) {
        return fraisReservation + fraisRecharge + fraisPenalite;
    }
    
    

}
