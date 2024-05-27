package fr.miage.klein.CLI;

import java.text.ParseException;

import fr.miage.klein.BusinessLogic.Client;
import fr.miage.klein.BusinessLogic.Reservation;
import fr.miage.klein.Controller.IDatabaseController;

public class Interpreter {
    private final IDatabaseController db;
    
    public Interpreter(IDatabaseController db) {
        this.db = db;
    }

    public void execute(Command command) {
        switch (command.getCommand()) {
            case "": break;
            case "create":
                create(command.getArgs());
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

    private void create(String[] args) {
        if(args.length == 0)
            throw new IllegalArgumentException("Arguments invalides");

        try {
            switch (args[0]) {
                case "help":
                    Creator.helpCreate();
                    break;
                case "client":
                    Client newClient = Creator.createClientInteractive();
                    db.addClient(newClient);
                    break;
                case "reservation":
                    Reservation reserv = Creator.createReservationIntractive();
                    db.addReservation(reserv);
                    break;
                default:
                throw new IllegalArgumentException("Arguments invalides");
            }
        } catch (Exception e) {
            System.out.println("Saisie invalide ou erreur");
        }

    }
}
