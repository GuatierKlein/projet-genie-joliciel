package fr.miage.klein.BusinessLogic;

import java.io.Serializable;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDateTime;

public class Facture implements Serializable {
    
    private static String DEVISE_PAR_DEFAUT = "EUR";
    private static float TARIF_HORAIRE_RES = 10;
    private static float DUREE_PROLONGEMENT_HEURE = (float) 0.25; // 15 min
    private static float TARIF_SUPPLEMENT_RES = 2;
    private static float TARIF_HORAIRE_CHARGE = 5;
    private static float TARIF_PENALITE_INIT = 1;
    private static float TAUX_AUGMENTATION_MIN = (float) 1.05;

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
    
    public static float calculFraisReservation(Duration dureeCreneau, boolean accepteSupplement){
        if (accepteSupplement) 
            return  TARIF_SUPPLEMENT_RES + dureeCreneau.toHours() * TARIF_HORAIRE_RES;
        return dureeCreneau.toHours() * TARIF_HORAIRE_RES;
        }

    public static float calculFraisProlongementReservation(){ 
        return  DUREE_PROLONGEMENT_HEURE * TARIF_HORAIRE_RES; 
        }

    public static float calculFraisPenalite(Duration dureeEnTrop){
        var coutPen = TARIF_PENALITE_INIT + Math.pow(TAUX_AUGMENTATION_MIN,dureeEnTrop.toMinutes());
        return (float) coutPen;
    }

    public static float calculFraisRecharge(Duration dureeCharge){
        return (float) dureeCharge.toHours() * TARIF_HORAIRE_CHARGE;
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
    
    public static String getDEVISE_PAR_DEFAUT() {
        return DEVISE_PAR_DEFAUT;
    }

    public static void setDEVISE_PAR_DEFAUT(String DEVISE_PAR_DEFAUT) {
        Facture.DEVISE_PAR_DEFAUT = DEVISE_PAR_DEFAUT;
    }

    public static float getTARIF_HORAIRE_RES() {
        return TARIF_HORAIRE_RES;
    }

    public static void setTARIF_HORAIRE_RES(float TARIF_HORAIRE_RES) {
        Facture.TARIF_HORAIRE_RES = TARIF_HORAIRE_RES;
    }

    public static float getDUREE_PROLONGEMENT_HEURE() {
        return DUREE_PROLONGEMENT_HEURE;
    }

    public static void setDUREE_PROLONGEMENT_HEURE(float DUREE_PROLONGEMENT_HEURE) {
        Facture.DUREE_PROLONGEMENT_HEURE = DUREE_PROLONGEMENT_HEURE;
    }

    public static float getTARIF_SUPPLEMENT_RES() {
        return TARIF_SUPPLEMENT_RES;
    }

    public static void setTARIF_SUPPLEMENT_RES(float TARIF_SUPPLEMENT_RES) {
        Facture.TARIF_SUPPLEMENT_RES = TARIF_SUPPLEMENT_RES;
    }

    public static float getTARIF_HORAIRE_CHARGE() {
        return TARIF_HORAIRE_CHARGE;
    }

    public static void setTARIF_HORAIRE_CHARGE(float TARIF_HORAIRE_CHARGE) {
        Facture.TARIF_HORAIRE_CHARGE = TARIF_HORAIRE_CHARGE;
    }

    public static float getTARIF_PENALITE_INIT() {
        return TARIF_PENALITE_INIT;
    }

    public static void setTARIF_PENALITE_INIT(float TARIF_PENALITE_INIT) {
        Facture.TARIF_PENALITE_INIT = TARIF_PENALITE_INIT;
    }

    public static float getTAUX_AUGMENTATION_MIN() {
        return TAUX_AUGMENTATION_MIN;
    }

    public static void setTAUX_AUGMENTATION_MIN(float TAUX_AUGMENTATION_MIN) {
        Facture.TAUX_AUGMENTATION_MIN = TAUX_AUGMENTATION_MIN;
    }

}
