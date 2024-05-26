package fr.miage.klein;

import java.text.ParseException;
import java.util.Scanner;

public class Creator {
    public static Client createClientInteractive() throws ParseException {
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
        Mail email = new Mail(in.nextLine());
        System.out.print("Num CB : ");
        long numCb = Long.parseLong(in.nextLine());
        in.close();
        return new Client(prenom, nom, adresse, numTel, email, numCb); 
    }

    public static void helpCreate() {
        System.out.println("Commande create");
        System.out.println("**********");
        System.out.println("* create help : afficher l'aide sur la commande créer");
        System.out.println("* create client : créer un nouveau client en mode interactif");
        System.out.println("**********");
    }
}
