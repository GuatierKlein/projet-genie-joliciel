package fr.miage.klein;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Client client = new Client("Olivier", "Jeandel", "58 boulevard Pompidou, Saint Dié des Vosges", "0663812317", new Mail("tttttt@tttttt.tttttt"), "0");
    
        Scanner sc = new Scanner(System.in);
        

        System.out.println("Veuillez saisir votre immatriculation");
        String saisiePlaque = sc.next();
        boolean vehiculePerso = client.getImmat() != null ? client.getImmat().equalsIgnoreCase(saisiePlaque) : false; 
    
        // Simulation des différents scénarios aboutissant à une facturation
        // Il faudra intégrer plus tard les réservations, pour l'instant on simule 
        // simplement pour avoir des tests executables pour le premier sprint

        Time dureeCreneauRes = new Time(3600000); // 1h pour le test, à remplacer plus tard quand on aura réservation 
        Time dureeCreneauResRetard = new Time(5400000); // 1h30 
        Time dureeCreneauPen = new Time(900000); // 15 min
        Time dureeCreneauCharge;

        int choixUtilisateur;
        float coutRes = 0;
        float coutProl = 0;
        float coutCharge = 0;
        float coutPen = 0;

        System.out.println("---------------------------------------------------------------------------");
        System.out.println("1 - Le client se présente à l'heure pour sa réservation");
        System.out.println("2 - Le client se présente en retard à sa réservation");
        System.out.println("3 - Le client ne se présente pas pour sa réservation");
        System.out.println("4 - Le client n'a pas de réservation");
        System.out.println("Tapez 1, 2, 3 ou 4");
        System.out.println("---------------------------------------------------------------------------");

        try {
            choixUtilisateur = sc.nextInt();
            switch (choixUtilisateur) {
                //////////////////////////////////////////// Cas 1 ////////////////////////////////////////////
                case 1: 
                    System.out.println("---------------------------------------------------------------------------");
                    System.out.println("1 - Le client part avant la fin de sa réservation");
                    System.out.println("2 - Le client part à la fin de sa réservation");
                    System.out.println("3 - Le client veut prolonger sa réservation");
                    System.out.println("4 - Le client ne part pas à la fin de sa réservation et n'a pas prolongé");
                    System.out.println("Tapez 1, 2, 3 ou 4");
                    System.out.println("---------------------------------------------------------------------------");
                    try {
                        choixUtilisateur = sc.nextInt();
                        switch (choixUtilisateur) {
                            // Cas 1 - 1
                            case 1:
                                coutRes = Facture.calculFraisReservation(dureeCreneauRes, false);
                                System.out.println("Saisissez le temps de charge effectifs en minutes entières");
                                try {
                                    dureeCreneauCharge = new Time(sc.nextInt()*60000);
                                    coutCharge = Facture.calculFraisRecharge(dureeCreneauCharge);
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                                break;
                            // Cas 1 - 2
                            case 2:
                                coutRes = Facture.calculFraisReservation(dureeCreneauRes, false);
                                System.out.println("Saisissez le temps de charge effectifs en minutes entières");
                                try {
                                    dureeCreneauCharge = new Time(sc.nextInt()*60000);
                                    coutCharge = Facture.calculFraisRecharge(dureeCreneauCharge);
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                                break;
                            // Cas 1 - 3
                            case 3:
                                coutRes = Facture.calculFraisReservation(dureeCreneauRes, false);
                                System.out.println("Saisissez le nombre de prolongements ? (1 fois = 15 min - 3 prolongements max autorisés)");
                                try {
                                    int nbProlongement = sc.nextInt();
                                    if (nbProlongement >= 1 && nbProlongement <= 3){
                                        coutProl = nbProlongement * Facture.calculFraisProlongementReservation();
                                    }
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                                System.out.println("Saisissez le temps de charge effectifs en minutes entières");
                                try {
                                    dureeCreneauCharge = new Time(sc.nextInt()*60000);
                                    coutCharge = Facture.calculFraisRecharge(dureeCreneauCharge);
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                                break;
                            // Cas 1 - 4
                            case 4:
                                coutRes = Facture.calculFraisReservation(dureeCreneauRes, false);
                                coutPen = Facture.calculFraisPenalite(dureeCreneauPen);
                                System.out.println("Saisissez le temps de pénalité en minutes entières et 0 si aucun dépassement");
                                try {
                                    int duree = sc.nextInt();
                                    dureeCreneauPen = new Time(duree*60000);
                                    if (duree != 0){
                                        coutPen = Facture.calculFraisPenalite(dureeCreneauPen);}
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                                System.out.println("Saisissez le temps de charge effectifs en minutes entières");
                                try {
                                    dureeCreneauCharge = new Time(sc.nextInt()*60000);
                                    coutCharge = Facture.calculFraisRecharge(dureeCreneauCharge);
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                            default:
                                break;
                            }
                    } catch (Exception e) {
                            System.out.println(e);
                    }break;
                
                    //////////////////////////////////////////// Cas 2 ////////////////////////////////////////////

                case 2:
                    System.out.println("---------------------------------------------------------------------------");
                    System.out.println("1 - Le client arrive pendant la période d'attente");
                    System.out.println("2 - Le client arrive après la période d'attente mais une borne est disponible");
                    System.out.println("3 - Le client arrive après la période d'attente mais aucune borne n'est disponible");
                    System.out.println("Tapez 1, 2 ou 3");
                    System.out.println("---------------------------------------------------------------------------");
                    try {
                        choixUtilisateur = sc.nextInt();
                        switch (choixUtilisateur) {
                            // Cas 2 - 1
                            case 1:
                                System.out.println("Saisissez 1 si vous voulez prolonger, 0 ou un autre nombre sinon");
                                int prolonger = 0;
                                try {
                                    prolonger = sc.nextInt();
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                                if(prolonger == 1) {
                                    coutRes = Facture.calculFraisReservation(dureeCreneauResRetard, true);
                                    System.out.println("Saisissez le nombre de prolongements ? (1 fois = 15 min - 3 prolongements max autorisés)");
                                    try {
                                        int nbProlongement = sc.nextInt();
                                        if (nbProlongement >= 1 && nbProlongement <= 3){
                                            coutProl = nbProlongement * Facture.calculFraisProlongementReservation();
                                        }
                                    } catch (Exception e) {
                                        System.err.println(e);
                                    }
                                }
                                else{
                                    coutRes = Facture.calculFraisReservation(dureeCreneauResRetard, false);
                                }
                                System.out.println("Saisissez le temps de charge effectifs en minutes entières");
                                try {
                                    dureeCreneauCharge = new Time(sc.nextInt()*60000);
                                    coutCharge = Facture.calculFraisRecharge(dureeCreneauCharge);
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                                break;
                            case 2:
                                System.out.println("Saisissez 1 si vous voulez prolonger, 0 ou un autre nombre sinon");
                                prolonger = 0;
                                try {
                                    prolonger = sc.nextInt();
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                                if(prolonger == 1) {
                                    coutRes = Facture.calculFraisReservation(dureeCreneauResRetard, true);
                                    System.out.println("Saisissez le nombre de prolongements ? (1 fois = 15 min - 3 prolongements max autorisés)");
                                    try {
                                        int nbProlongement = sc.nextInt();
                                        if (nbProlongement >= 1 && nbProlongement <= 3){
                                            coutProl = nbProlongement * Facture.calculFraisProlongementReservation();
                                        }
                                    } catch (Exception e) {
                                        System.err.println(e);
                                    }
                                }
                                else{
                                    coutRes = Facture.calculFraisReservation(dureeCreneauResRetard, false);
                                }
                                System.out.println("Saisissez le temps de charge effectifs en minutes entières");
                                try {
                                    dureeCreneauCharge = new Time(sc.nextInt()*60000);
                                    coutCharge = Facture.calculFraisRecharge(dureeCreneauCharge);
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                                break;
                            case 3:
                                System.out.println("Aucune borne n'est disponible, au revoir");
                                break;
                            default:
                                break;
                        }
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                    break;

                    //////////////////////////////////////////// Cas 3 ////////////////////////////////////////////
                
                case 3:
                    coutRes = Facture.calculFraisReservation(dureeCreneauPen, false);
                    break;

                    //////////////////////////////////////////// Cas 4 ////////////////////////////////////////////

                case 4:
                    System.out.println("---------------------------------------------------------------------------");
                    System.out.println("1 - Le client à une borne disponible");
                    System.out.println("2 - Le client n'a pas de borne disponible");
                    System.out.println("Tapez 1 ou 2");
                    System.out.println("---------------------------------------------------------------------------");
                    try {
                        choixUtilisateur = sc.nextInt();
                        switch (choixUtilisateur) {
                            //Cas 4 - 1
                            case 1:
                                System.out.println("Saisissez le temps de charge effectifs en minutes entières");
                                try {
                                    dureeCreneauCharge = new Time(sc.nextInt()*60000);
                                    coutCharge = Facture.calculFraisRecharge(dureeCreneauCharge);
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                                System.out.println("Saisissez le temps de réservation prévu en minutes entières");
                                try {
                                    dureeCreneauRes = new Time(sc.nextInt()*60000);
                                    coutRes = Facture.calculFraisReservation(dureeCreneauRes, false);
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                                System.out.println("Saisissez le temps de pénalité en minutes entières et 0 si aucun dépassement");
                                try {
                                    int duree = sc.nextInt();
                                    dureeCreneauPen = new Time(duree*60000);
                                    if (duree != 0){
                                        coutPen = Facture.calculFraisPenalite(dureeCreneauPen);}
                                } catch (Exception e) {
                                    System.err.println(e);
                                }
                                break;
                            // Cas 4 - 2    
                            case 2:
                                System.err.println("Aucune borne n'est disponible, au revoir");
                                break;
                            default:
                                throw new AssertionError();
                        }
                    } catch (Exception e) {
                        System.err.println(e);
                    }

                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        Frais fraisCharge = new Frais(coutCharge, Facture.deviseParDefaut);
        Frais fraisRes = new Frais(coutRes, Facture.deviseParDefaut);
        Frais fraisPen = new Frais(coutPen, Facture.deviseParDefaut);
        Frais fraisProl = new Frais(coutProl, Facture.deviseParDefaut);

        Facture facture = new Facture(fraisCharge, fraisRes, fraisPen, fraisProl, LocalDateTime.now());
        float montantTotal = facture.calcuFraisTotaux(coutCharge,coutRes,coutPen,coutProl);
        System.out.println(coutCharge);
        System.out.println("Montant total : "+ montantTotal + facture.deviseParDefaut);

    }
}