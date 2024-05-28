package fr.miage.klein.CLI;

import java.text.ParseException;

import fr.miage.klein.BusinessLogic.Client;
import fr.miage.klein.BusinessLogic.Immatriculation;
import fr.miage.klein.BusinessLogic.NumReservation;
import fr.miage.klein.BusinessLogic.ParkAccess.ParkAccess;
import fr.miage.klein.BusinessLogic.ParkAccess.ParkAccessImmat;
import fr.miage.klein.BusinessLogic.ParkAccess.ParkAccessRes;
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
            case "access":
                access(command.getArgs());
                break;
            case "help":
                help();
                break;
            case "exit":
                System.exit(0);
                break;
        
            default:
                throw new IllegalArgumentException("Commande inexistante");
        }
    }

    private void help() {
        System.out.println("Commandes disponibles");
        System.out.println("**********");
        System.out.println("* create <type> : tapez create help pour plus d'info");
        System.out.println("**********");
    }

    private void create(String[] args) throws ParseException {
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
        
            default:
            throw new IllegalArgumentException("Arguments invalides");
        }
    }

    private void access(String[] args) throws Exception {
        if(args.length < 2 || args.length > 2)
            throw new IllegalArgumentException("Arguments invalides");

        ParkAccess access;

        switch (args[0]) {
            case "immat":
                Immatriculation immat = new Immatriculation(args[1]);
                access = new ParkAccessImmat(immat, db);
                if(!access.validate())
                    throw new Exception("Entrée refusée");
                break;
            case "reservation":
                NumReservation res = new NumReservation(args[1]);
                access = new ParkAccessRes(res, db);
                if(!access.validate())
                    throw new Exception("Entrée refusée");
                break;
        
            default:
            throw new IllegalArgumentException("Arguments invalides");
        }
        
        System.out.println("Cher client, bienvenue!");
    }
}
