package fr.miage.klein;

import java.util.Scanner;

public class Creator {
    public static Client createClientInteractive() {
        Scanner in = new Scanner(System.in);
        System.out.println("Création interactive d'un client :");
        System.out.print("Nom : ");
        String nom = in.nextLine();
        System.out.print("Prénom : ");
        String prenom = in.nextLine();
        System.out.print("Adresse : ");
        String adresse = in.nextLine();
        System.out.print("Num téléphone : ");
        String numTel = in.nextLine();
        System.out.print("EMail : ");
        String email = in.nextLine();
        System.out.print("Num CB : ");
        String numCb = in.nextLine();
        return new Client(prenom, nom, adresse, numTel, email, numCb); 
    }
}
