package fr.miage.klein.CLI;

import java.text.ParseException;

import fr.miage.klein.BusinessLogic.Client;
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
                try {
                    create(command.getArgs());
                } catch (ParseException e) {
                    System.out.println("Saisie échouée");
                }
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
}
