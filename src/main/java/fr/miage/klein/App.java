package fr.miage.klein;

import java.util.Scanner;

public class App {
    private static final Interpreter interpreter = new Interpreter();
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