import java.util.ArrayList;

public class ProfilEtudiant {


    private String nom;
    private ArrayList<String> matieres;
    private ArrayList<Double> notes;


    public ProfilEtudiant(String nom) {

        this.nom   = nom.trim();
        this.matieres = new ArrayList<>();
        this.notes = new ArrayList<>();
    }


    public String getNom() { return nom; }

    // Ajoute une matière et une note après vérification
    public void ajouterResultat(String matiere, double note) {
        // Vérifie si la note est logique (entre 0 et 100)
        if (note >= 0 && note <= 100) {
            // Ajoute les données dans les deux listes parallèles
            this.matieres.add(matiere.trim());
            this.notes.add(note);
        }
    }

    // Calcule la moyenne de l'étudiant en parcourant sa liste de notes
    public double calculerMoyenne() {
        //Si l'étudiant n'a pas de notes on renvoie 0
        if (notes.isEmpty()) return 0.0;
        double total = 0.0;
        //additionne les notes
        for (double n : notes) {
            total += n;
        }
        // Calcule la moyenne
        return total / notes.size();
    }

    // Affiche une ligne résumée du classement
    public void afficherLigneSynthese(int rang) {
        // l'affichage
        System.out.printf("%d. %-20s | Moyenne : %.2f / 100%n", rang, nom, calculerMoyenne());
    }

    // Affiche le bulletin complet avec le détail par matière
    public void afficherBulletinComplet() {
        System.out.println("\nBulletin de : " + nom);
        // Parcourt les matières pour afficher chaque note
        for (int i = 0; i < matieres.size(); i++) {
            System.out.printf("  - %-15s : %.2f%n", matieres.get(i), notes.get(i));
        }
        //Affiche la conclusion du bulletin avec la moyenne générale
        System.out.printf("  MOYENNE GÉNÉRALE : %.2f%n", calculerMoyenne());
    }
}