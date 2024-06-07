package fr.miage.klein.CLI;

import java.text.ParseException;
import java.time.Duration;
import java.util.Scanner;

import fr.miage.klein.BusinessLogic.Borne;
import fr.miage.klein.BusinessLogic.EBorneEtat;
import fr.miage.klein.Controller.IDatabaseController;
import fr.miage.klein.Mocks.DBMock;

public class Updator {
    public static Borne updateEtatBorne() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.println("Modification interactive de l'etat d'une borne : ");
        System.out.print("Saisie du numéro de la borne  : ");
        int numBorne = 0;
        try {
            numBorne = in.nextInt();
        } catch (Exception e) {
            System.err.println(e);
        }
        if (numBorne != 0) {
            DBMock db = new DBMock();
            Borne borne = db.getBorne(numBorne);
            if (borne != null) {
                System.out.println("saisir l'état de la borne parmis Disponible, Indisponible, Reservee, Occupee: ");
                String etat = in.next();
                try {
                    EBorneEtat e = EBorneEtat.valueOf(etat);
                    borne.setEtat(e);
                    }
                catch (IllegalArgumentException | NullPointerException error) {
                        System.err.println(error);
                    }
            } 
            return borne;    
        }
        return null;
    }

    public static void updateSettings() {

    }

    public static void updateSettingWaitingPeriod() {

    }

    public static void updateReservationFeePerHour(IDatabaseController db) {
        Scanner in = new Scanner(System.in);
        System.out.println("Modification interactive du paramètre prix de réservation par heure : ");
        System.out.print("Saisie du prix : ");

        String userInput = in.nextLine();

        try {
            float tarifHoraireRes = Float.parseFloat(userInput);
            db.setTarifHoraire(tarifHoraireRes);
            System.out.println("Le nouveau tarif horaire des réservations est de : " + tarifHoraireRes + " "
                    + db.getCurrency());
        } catch (NumberFormatException e) {
            System.err.println(e);
        }
        in.close();
    }

    public static void updateReservationSupplement(IDatabaseController db) {
        Scanner in = new Scanner(System.in);
        System.out.println("Modification interactive du paramètre prix du supplément de réservation : ");
        System.out.print("Saisie du prix : ");

        String userInput = in.nextLine();

        try {
            float tarifSupplRes = Float.parseFloat(userInput);
            db.setTarifSupplement(tarifSupplRes);
            System.out.println("Le nouveau tarif du supplément de réservation edt de : " + tarifSupplRes + " "
                    + db.getCurrency());
        } catch (NumberFormatException e) {
            System.err.println(e);
        }
        in.close();
    }

    public static void updateChargeFeePerHour(IDatabaseController db) {
        Scanner in = new Scanner(System.in);
        System.out.println("Modification interactive du paramètre prix de charge par heure : ");
        System.out.print("Saisie du prix : ");

        String userInput = in.nextLine();

        try {
            float tarifHoraireCharge = Float.parseFloat(userInput);
            db.setTarifHoraireCharge(tarifHoraireCharge);
            System.out.println("Le nouveau tarif horaire de charge est de : " + tarifHoraireCharge + " "
                    + db.getCurrency());
        } catch (NumberFormatException e) {
            System.err.println(e);
        }
        in.close();
    }

    public static void updatePenaltyFeesParameters(IDatabaseController db) {
        Scanner in = new Scanner(System.in);
        System.out.println("Modification interactive des paramètres permettant de calculer les frais de pénalités : ");
        System.out.print("Saisie du prix initial de la pénalité : ");
        String userInput = in.nextLine();
        System.out.print("Saisie du coefficient d'augmentation du prix initial chaque minute : ");
        String userInput2 = in.nextLine();

        try {
            float tarifPenInit = Float.parseFloat(userInput);
            db.setTarifPenalite(tarifPenInit);
            float coeffAugPen = Float.parseFloat(userInput2);
            db.setTauxAugmentationMin(coeffAugPen);
            System.out.println("Le nouveau prix intial est de : " + tarifPenInit + " " + db.getCurrency()
                    + " ce prix sera multiplié chaque minute par " + coeffAugPen);
        } catch (NumberFormatException e) {
            System.err.println(e);
        }
        in.close();
    }

    public static void updateDefaultCurrency(IDatabaseController db) {
        Scanner in = new Scanner(System.in);
        System.out.println("Modification interactive de la devise par défaut : ");
        System.out.print("Saisie de la devise : ");
        String userInput = in.nextLine();

        if (!userInput.isEmpty()) {
            db.setCurrency(userInput);
            System.out.println("La nouvelle devise est " + userInput);
        }
        in.close();
    }

    public static void updateReservationExtensionDuration(IDatabaseController db) {
        Scanner in = new Scanner(System.in);
        System.out.println("Modification interactive de la durée du prolongement d'une réservation : ");
        System.out.print("Saisie le nombre de minutes ajouté à une réservation lors d'un prolongement : ");

        String userInput = in.nextLine();

        try {
            float nbMinProl = Float.parseFloat(userInput);
            db.setDureeProlongement(nbMinProl / 60);
            System.out.println("La durée du prolongement est de " + nbMinProl + "min, = " + nbMinProl / 60 + "h");
        } catch (NumberFormatException e) {
            System.err.println(e);
        }
        in.close();
    }

    public static void updateWaitingDelay() {
        Scanner in = new Scanner(System.in);
        System.out.println("Modification interactive du délai d'attente d'une borne en cas de retard : ");
        System.out.print(
                "Saisie le nombre de minutes (nombre entier) ajouté à une réservation lors d'un prolongement : ");

        try {
            Borne.setDureeAttente(Duration.ofMinutes(in.nextLong()));
            System.out.println("Le nouveau délai d'attente est de " + Borne.getDureeAttente() + " min");
        } catch (NumberFormatException e) {
            System.err.println(e);
        }
        in.close();
    }

    public static void helpUpdate() {
        System.out.println("Commande update");
        System.out.println("**********");
        System.out.println("* update help : afficher l'aide sur la commande modifiée");
        System.out.println("* update etat-borne : modifie l'etat d'une borne en mode interactif");
        System.out.println("* update param-prix-res-h : modifie le tarif horaire de réservation");
        System.out.println("* update param-prix-res-suppl : modifie le prix du supplément de réservation");
        System.out.println("* update param-prix-charge-h : modifie le tarif horaire de charge");
        System.out.println(
                "* update param-prix-pen : modifie les paramètres permettant de calculer les frais de pénalités");
        System.out.println("* update param-default-currency : modifie la devise par défaut");
        System.out
                .println("* update param-res-extension-duration : modifie la durée du prolongement d'une réservation");
        System.out.println("* update param-waiting-delay : modifie le délai d'attente d'une borne en cas de retard");
        System.out.println("**********");
    }
}
