package fr.miage.klein.CLI;

import java.text.ParseException;

import fr.miage.klein.BusinessLogic.Borne;
import fr.miage.klein.BusinessLogic.Client;
import fr.miage.klein.BusinessLogic.Immatriculation;
import fr.miage.klein.BusinessLogic.NumReservation;
import fr.miage.klein.BusinessLogic.ParkAccess.ParkAccess;
import fr.miage.klein.BusinessLogic.ParkAccess.ParkAccessImmat;
import fr.miage.klein.BusinessLogic.ParkAccess.ParkAccessRes;
import fr.miage.klein.BusinessLogic.Reservation.Reservation;
import fr.miage.klein.BusinessLogic.Reservation.ReservationPermanente;
import fr.miage.klein.BusinessLogic.Reservation.ReservationTemporaire;
import fr.miage.klein.Controller.IDatabaseController;

public class Interpreter {
    private final IDatabaseController db;
    
    public Interpreter(IDatabaseController db) {
        this.db = db;
    }

    public void execute(Command command) throws Exception {
        switch (command.getCommand()) {
            case "": break;
            case "create":
                create(command.getArgs());
                break;
            case "update":
                update(command.getArgs());
                break;
            case "access":
                access(command.getArgs());
                break;
            case "help":
                help();
                break;
            case "leave":
                leave(command.getArgs());
                break;
            case "exit":
                System.exit(0);
                break;
            case "get":
                get(command.getArgs());
                break;
        
            default:
                throw new IllegalArgumentException("Commande inexistante");
        }
    }

    private void help() {
        System.out.println("Commandes disponibles");
        System.out.println("**********");
        System.out.println("* create <type> : tapez create help pour plus d'info");
        System.out.println("* update <type> : tapez update help pour plus d'info");
        System.out.println("* access <type> <num> : tapez access help pour plus d'info");
        System.out.println("* leave <type> <num> : tapez leave help pour plus d'info");
        System.out.println("* get <type> : tapez get help pour plus d'info");
        System.out.println("**********");
    }

    private void create(String[] args) throws Exception {
        if(args.length == 0)
            throw new IllegalArgumentException("Arguments invalides");

        switch (args[0]) {
            case "help":
                Creator.helpCreate();
                break;
            case "client":
                Client newClient = Creator.createClientInteractive();
                db.addClient(newClient);
                break;
            case "reservationPermanente":
                ReservationPermanente res = Creator.createReservationPermanenteIntractive();
                db.addReservationPermanente(res);
            case "reservation":
                ReservationTemporaire reservation = Creator.createReservationIntractive(db);
                db.addReservationTemporaire(reservation);
                break;
            default:
            throw new IllegalArgumentException("Arguments invalides");
        }
    }

    private void accessHelp() {
        System.out.println("Commande access");
        System.out.println("Permet d'entrer dans le parking");
        System.out.println("**********");
        System.out.println("* access help : afficher l'aide sur la commande access");
        System.out.println("* access immat <num> : rentrer en utilisant un numéro d'immatriculation");
        System.out.println("* access res <num> : rentrer en utilisant un numéro de réservation");
        System.out.println("**********");
    }

    private void access(String[] args) throws Exception {
        ParkAccess access;

        switch (args[0]) {
            case "immat":
                if(args.length < 2 || args.length > 2)
                    throw new IllegalArgumentException("Arguments invalides");
                Immatriculation immat = new Immatriculation(args[1]);
                access = new ParkAccessImmat(immat, db);
                if(!access.validate())
                    throw new Exception("Entrée refusée");
                access.save();
                System.out.println("Acces autorisé");
                break;
            case "res":
                if(args.length < 2 || args.length > 2)
                    throw new IllegalArgumentException("Arguments invalides");
                NumReservation res = new NumReservation(args[1]);
                access = new ParkAccessRes(res, db);
                if(!access.validate())
                    throw new Exception("Entrée refusée");
                access.save();
                System.out.println("Acces autorisé");
                break;
            case "help": accessHelp();
                break;
        
            default:
            throw new IllegalArgumentException("Arguments invalides");
        }
    }

    private void leave(String[] args) throws Exception {
        switch (args[0]) {
            case "immat":
                if(args.length < 2 || args.length > 2)
                    throw new IllegalArgumentException("Arguments invalides");
                Immatriculation immat = new Immatriculation(args[1]);
                db.deletePresence(immat);
                break;
            case "res":
                if(args.length < 2 || args.length > 2)
                    throw new IllegalArgumentException("Arguments invalides");
                NumReservation numres = new NumReservation(args[1]);
                ReservationTemporaire res = db.getReservationTemporaires(numres);
                db.deletePresence(res.getImmat());
                break;
            case "help": leaveHelp();
                break;
        
            default:
            throw new IllegalArgumentException("Arguments invalides");
        }

        System.out.println("Sortie du véhicule");
    }

    private void leaveHelp() {
        System.out.println("Commande leave");
        System.out.println("Permet de quitter le parking");
        System.out.println("**********");
        System.out.println("* leave help : afficher l'aide sur la commande leave");
        System.out.println("* leave immat <num> : sortir en utilisant un numéro d'immatriculation");
        System.out.println("* leave res <num> : sortir en utilisant un numéro de réservation");
        System.out.println("**********");
    }

    private void update(String[] args) {
        if(args.length == 0)
            throw new IllegalArgumentException("Arguments invalides");

        try {
            switch (args[0]) {
                case "help":
                    Updator.helpUpdate();
                    break;
                case "etat-borne":
                    Borne borneUpdated = Updator.updateEtatBorne();
                    db.updateBorneEtat(borneUpdated.getEtat());
                    break;
                case "param-prix-res-h":
                    Updator.updateReservationFeePerHour(db);
                    break;
                case "param-prix-res-suppl":
                    Updator.updateReservationSupplement(db);
                    break;
                case "param-prix-charge-h":
                    Updator.updateChargeFeePerHour(db);
                    break;
                case "param-prix-pen":
                    Updator.updatePenaltyFeesParameters(db);
                    break;
                case "param-default-currency":
                    Updator.updateDefaultCurrency(db);
                    break;
                case "param-res-extension-duration":
                    Updator.updateReservationExtensionDuration(db);
                    break;
                case "param-waiting-delay":
                    Updator.updateReservationExtensionDuration(db);
                    break;
                default:
                throw new IllegalArgumentException("Arguments invalides");
            }
        } catch(ParseException e){
            System.out.println("Saisie invalide");
        }
    }
    
    private void get(String [] args) throws Exception {
        switch (args[0]) {
            case "clients": Getter.getClients(db);               
                break;
            case "reservations": Getter.getReservations(db);            
                break;
            case "factures": Getter.getFactures(db);               
                break;
            case "presences": Getter.getPresence(db);               
                break;
            case "help": Getter.helpGet();;               
                break;
        
            default:
            throw new IllegalArgumentException("Arguments invalides");
        }
    }
}


