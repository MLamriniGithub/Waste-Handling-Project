package gestiondechets;

import java.util.Date;
import java.util.List;

import java.util.ArrayList;
import java.time.LocalDate;

import java.util.Scanner;


public class MainTest {

    public static void main(String[] args) {

        System.out.println("Bienvenue dans le système de gestion des déchets !");

        // Créer des utilisateurs
        Utilisateur utilisateur1 = new Utilisateur("user1", "John Doe", "johndoe@example.com", "password");
        Utilisateur utilisateur2 = new Utilisateur("user2", "Jane Doe", "janedoe@example.com", "password");

        // Créer les ménages
        Menage menage1 = new Menage("1234", utilisateur1);
        Menage menage2 = new Menage("5678", utilisateur2);

        // Créer les catégories pour chaque type de poubelle
        List<String> categoriesVerte = List.of("verre");
        List<String> categoriesJaune = List.of("carton", "plastique", "canette", "converse");
        List<String> categoriesBleue = List.of("papier");
        List<String> categoriesClassique = List.of("autres");

        // Créez les poubelles intelligentes correspondantes
        PoubelleIntelligente poubelleVerte = new PoubelleIntelligente("1", 100, categoriesVerte, "Emplacement 1", "Poubelle Verte", "Quartier 1");
        PoubelleIntelligente poubelleJaune = new PoubelleIntelligente("2", 100, categoriesJaune, "Emplacement 2", "Poubelle Jaune", "Quartier 1");
        PoubelleIntelligente poubelleBleue = new PoubelleIntelligente("3", 100, categoriesBleue, "Emplacement 3", "Poubelle Bleue", "Quartier 2");
        PoubelleIntelligente poubelleClassique = new PoubelleIntelligente("4", 100, categoriesClassique, "Emplacement 4", "Poubelle Classique", "Quartier 2");

        // Créer un centre de tri
        CentreDeTri centreDeTri = new CentreDeTri("Centre de Tri 1", "Adresse du centre de tri 1");

        // Ajoutez les poubelles au centre de tri
        centreDeTri.ajouterPoubelle(poubelleVerte);
        centreDeTri.ajouterPoubelle(poubelleJaune);
        centreDeTri.ajouterPoubelle(poubelleBleue);
        centreDeTri.ajouterPoubelle(poubelleClassique);

        boolean continuer = true;
        Scanner scanner = new Scanner(System.in);

        while (continuer) {
            System.out.println("\nVeuillez choisir une option :");
            System.out.println("1. Utilisateur");
            System.out.println("2. Administrateur du centre de tri");
            System.out.println("3. Quitter");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le retour à la ligne restant

            switch (choix) {
                case 1:
                    System.out.println("Vous avez choisi Utilisateur.");
                    // Tester la connexion
            if (utilisateur1.connecter("password")) {
                System.out.println("Utilisateur 1 connecté avec succès.");
            } else {
                System.out.println("Échec de la connexion de l'utilisateur 1.");
            }

            // Modifier le mot de passe de l'utilisateur 1
            utilisateur1.modifierMotDePasse("password", "newPassword");

            // Vérifier si le mot de passe a été modifié avec succès
            if (utilisateur1.connecter("newPassword")) {
                System.out.println("Utilisateur 1 connecté avec succès avec le nouveau mot de passe.");
            } else {
                System.out.println("Échec de la connexion de l'utilisateur 1 avec le nouveau mot de passe.");
            }

            // Afficher les informations de l'utilisateur 1
            utilisateur1.afficherInformations();

                    break;
                case 2:
                
                    System.out.println("Vous avez choisi Administrateur.");
                    System.out.println("Veuillez choisir une option :");
                    System.out.println("1. Afficher les poubelles");
                    System.out.println("2. Collecter les déchets");
                    System.out.println("3. Afficher les points de fidélité des utilisateurs");
                    System.out.println("4. Afficher les membres des ménages");
                    System.out.println("5. Revenir au menu principal");

                    int choixAdmin = scanner.nextInt();

                    switch (choixAdmin) {
                        case 1:
                            System.out.println("Affichage des poubelles :");
                            centreDeTri.afficherPoubelles();
                            break;
                        case 2:
                            System.out.println("Collecte des déchets :");
                            centreDeTri.collecterDechets();
                            break;
                        case 3:
                            System.out.println("Affichage des points de fidélité des utilisateurs :");
                            System.out.println("Points de fidélité de l'utilisateur1: " + utilisateur1.getPointsFidelite());
                            System.out.println("Points de fidélité de l'utilisateur2: " + utilisateur2.getPointsFidelite());
                            break;
                        case 4:
                            System.out.println("Affichage des membres des ménages :");
                            System.out.println("Membres du ménage 1:");
                            for (Utilisateur membre : menage1.getMembres()) {
                                System.out.println("  - " + membre.getNom());
                            }
                            System.out.println("Membres du ménage 2:");
                            for (Utilisateur membre : menage2.getMembres()) {
                                System.out.println("  - " + membre.getNom());
                            }
                            break;
                        case 5:
                            System.out.println("Retour au menu principal.");
                            break;
                        default:
                            System.out.println("Option invalide. Retour au menu principal.");
                    }
                    break;
                case 3:
                    System.out.println("Vous avez choisi de quitter.");
                    continuer = false;
                    break;
                default:
                    System.out.println("Option invalide. Veuillez choisir une option valide.");
            }
        }

        scanner.close();
        System.out.println("Au revoir !");
    }
}
