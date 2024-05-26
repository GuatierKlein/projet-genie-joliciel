package fr.miage.klein;

import java.util.Scanner;

import fr.miage.klein.CLI.Command;
import fr.miage.klein.CLI.Interpreter;
import fr.miage.klein.Controller.IDatabaseController;
import fr.miage.klein.Mocks.DBMock;

public class App {
    private static IDatabaseController db = new DBMock();
    private static final Interpreter interpreter = new Interpreter(db);
    private static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        
        System.out.println("Bienvenue dans ****");
        System.out.println("* help : obtenir de l'aide");
        System.out.println("* exit : quitter le programme");
        while (true) {
            System.out.print("> ");
            Command command = new Command(input.nextLine());
            interpreter.execute(command);
        }
    }
}