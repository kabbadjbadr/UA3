import java.util.ArrayList;

public class PortailApplication {


    private static IPersistanceDonnees depot = new EntrepotFichier();
    private static RegistreEtudiants gestion = new RegistreEtudiants();


    public static void main(String[] args) {
        souhaiterBienvenue();

        int choix;
        do {
            // Affiche les options et lit le choix de l'utilisateur
            afficherMenuPrincipal();
            choix = ValidateurSaisie.lireChoix("Votre choix : ", 1, 10);

            // Exécute l'action demandée si ce n'est pas "Quitter"
            if (choix != 10) {
                executerAction(choix);
            }

        } while (choix != 10); // Recommence

        System.out.println("Merci d'avoir utilisé le portail. À bientôt !");
    }

    // Gère la logique pour chaque option du menu
    private static void executerAction(int choix) {
        System.out.println();

        switch (choix) {
            case 1 -> {
                // Lit les données depuis le fichier
                System.out.println("Lecture du fichier...");
                gestion.fusionner(depot.lireDonnees("etudiants.csv"));
            }
            case 2 -> {
                // Affiche la liste  des inscrits
                System.out.println("Liste des inscrits :");
                gestion.afficherListeSimplifiee();
            }
            case 3 -> {
                // Trie par moyenne
                System.out.println("Classement par performance :");
                gestion.ordonnerParMoyenne();
                gestion.afficherListeSimplifiee();
            }
            case 4 -> gestion.inscrire(ValidateurSaisie.lireTexte("Nom de l'étudiant : ", false));
            case 5 -> {
                // Demande les informations pour enregistrer une nouvelle note
                String nomNote = ValidateurSaisie.lireTexte("Nom de l'étudiant : ", false);
                String matiere = ValidateurSaisie.lireTexte("Matière : ", true);
                double note = ValidateurSaisie.lireNote("Note (0-100) : ");
                gestion.enregistrerNote(nomNote, matiere, note);
            }
            case 6 -> gestion.chercherEtAfficher(ValidateurSaisie.lireTexte("Nom à rechercher : ", false));
            case 7 -> {
                // Affiche les notes détaillées de chaque étudiant
                System.out.println("Bulletins détaillés :");
                gestion.afficherTousLesDetails();
            }
            case 8 -> gestion.retirer(ValidateurSaisie.lireTexte("Nom de l'étudiant à supprimer : ", false));
            case 9 -> {
                // Sauvegarde les résultats
                System.out.println("Sauvegarde en cours...");
                gestion.ordonnerParMoyenne();
                depot.sauvegarderDonnees(gestion.getListe(), "resultats.csv");
            }
        }
    }

    //message d'accueil
    private static void souhaiterBienvenue() {
        System.out.println("Système de Gestion des Dossiers Étudiants");
    }

    // Liste toutes les fonctionnalités disponibles
    private static void afficherMenuPrincipal() {
        System.out.println("\nMENU PRINCIPAL");
        System.out.println("1. Charger les données (CSV)");
        System.out.println("2. Voir la liste des inscrits");
        System.out.println("3. Voir le classement");
        System.out.println("4. Inscrire un étudiant");
        System.out.println("5. Ajouter une note");
        System.out.println("6. Rechercher un étudiant");
        System.out.println("7. Bulletins détaillés");
        System.out.println("8. Supprimer un dossier");
        System.out.println("9. Enregistrer les changements");
        System.out.println("10. Quitter");
    }
}