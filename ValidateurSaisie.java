import java.util.Scanner;
//scanner unique
public class ValidateurSaisie {
    private static Scanner scanner = new Scanner(System.in);

    // Permet de saisir du texte (nom ou matière) avec des vérifications de base
    public static String lireTexte(String message, boolean autoriserChiffres) {
        while (true) {// Boucle qui recommence tant que la saisie est invalide
            System.out.print(message);
            String saisie = scanner.nextLine().trim();
            if (saisie.isEmpty()) {
                System.out.println("Merci d'écrire quelque chose.");
                continue;// Relance
            }

            if (!autoriserChiffres) {
                boolean contientChiffre = false;
                for (char c : saisie.toCharArray()) {
                    if (Character.isDigit(c)) { contientChiffre = true; break; }
                }
                if (contientChiffre) {
                    System.out.println("Les chiffres ne sont pas admis ici.");
                    continue;
                }
            }
            return saisie;
        }
    }

    // Demande une note et s'assure qu'elle est bien numérique et entre 0 et 100
    public static double lireNote(String message) {
        while (true) {
            System.out.print(message);
            String saisie = scanner.nextLine().trim().replace(',', '.');
            try {
                double valeur = Double.parseDouble(saisie);
                if (valeur >= 0 && valeur <= 100) return valeur;
                System.out.println("La note doit se situer entre 0 et 100.");
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide.");
            }
        }
    }

    // Gère la sélection dans le menu principal
    public static int lireChoix(String message, int min, int max) {
        while (true) {
            System.out.print(message);
            String saisie = scanner.nextLine().trim();
            try {
                int valeur = Integer.parseInt(saisie);
                if (valeur >= min && valeur <= max) return valeur;
                System.out.println("Merci de choisir un nombre entre " + min + " et " + max);
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Un nombre entier est attendu.");
            }
        }
    }
}