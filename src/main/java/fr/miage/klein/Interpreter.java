package fr.miage.klein;

import java.text.ParseException;

public class Interpreter {
    public void execute(Command command) {
        switch (command.getCommand()) {
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
        switch (args[0]) {
            case "help":
                helpCreate();
                break;
            case "client":
                try {
                    Creator.createClientInteractive();
                    //add to db
                } catch (ParseException e) {
                    System.out.println("Valeurs saisies invalides");
                }
                break;
        
            default:
            throw new IllegalArgumentException("Arguments invalides");
        }
    }

    private void helpCreate() {
        System.out.println("Commande create");
        System.out.println("**********");
        System.out.println("* create help : afficher l'aide sur la commande créer");
        System.out.println("* create client : créer un nouveau client en mode interactif");
        System.out.println("**********");
    }
}
