package fr.miage.klein;

public class Interpreter {
    public void execute(Command command) {
        switch (command.getCommand()) {
            case "create":
                create(command.getArgs());
                break;
        
            default:
                throw new IllegalArgumentException("Commande inexistante");
        }
    }

    private void create(String[] args) {
        if(args.length == 0)
            throw new IllegalArgumentException("Arguments invalides");
        switch (args[0]) {
            case "client":
                Creator.createClientInteractive();
                //add to db
                break;
        
            default:
            throw new IllegalArgumentException("Arguments invalides");
        }
    }
}
