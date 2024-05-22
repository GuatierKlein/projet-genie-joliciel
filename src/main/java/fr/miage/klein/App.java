package fr.miage.klein;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Client client = new Client("Olivier", "Jeandel", "58 boulevard Pompidou, Saint Dié des Vosges", "0663812317", new Mail("tttttt@tttttt.tttttt"), 0);
    }
}