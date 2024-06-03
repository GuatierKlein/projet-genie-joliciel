package fr.miage.klein;

import java.io.FileNotFoundException;
import java.util.Scanner;

import fr.miage.klein.CLI.Command;
import fr.miage.klein.CLI.Interpreter;
import fr.miage.klein.Controller.IDatabaseController;
import fr.miage.klein.Mocks.DBMock_fileWriter;

public class App {

    private static IDatabaseController db;
    private static Interpreter interpreter;
    private static final Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            db = new DBMock_fileWriter();
            interpreter = new Interpreter(db);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Bienvenue dans ****");
        System.out.println("* help : obtenir de l'aide");
        System.out.println("* exit : quitter le programme");
        while (true) {
            System.out.print("> ");
            Command command = new Command(input.nextLine());
            try {
                interpreter.execute(command);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
        }
    }
}