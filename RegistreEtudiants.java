import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class RegistreEtudiants {


    private ArrayList<ProfilEtudiant> liste;


    public RegistreEtudiants() {
        this.liste = new ArrayList<>();
    }


    public ArrayList<ProfilEtudiant> getListe() {
        return liste;
    }

    // Ajoute plusieurs étudiants d'un coup
    public void fusionner(ArrayList<ProfilEtudiant> nouveaux) {
        if (nouveaux == null || nouveaux.isEmpty()) {
            System.out.println("Aucune donnée n'a été ajoutée.");
            return;
        }
        for (ProfilEtudiant nouv : nouveaux) {
            // Vérifie si l'étudiant n'est pas déjà dans la liste
            if (trouverParNom(nouv.getNom()) == null) {
                liste.add(nouv);
            }
        }
    }

    // Inscrit un nouvel étudiant manuellement dans le système
    public void inscrire(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            System.out.println("Action impossible : nom manquant.");
            return;
        }
        if (trouverParNom(nom) != null) {
            System.out.println("Cet étudiant est déjà inscrit.");
            return;
        }
        liste.add(new ProfilEtudiant(nom));
        System.out.println("L'étudiant " + nom + " a bien été ajouté.");
    }

    // Ajoute une note à un étudiant précis
    public void enregistrerNote(String nom, String matiere, double note) {
        ProfilEtudiant etudiant = trouverParNom(nom);
        if (etudiant == null) {
            System.out.println("Impossible de trouver " + nom + ".");
            return;
        }
        etudiant.ajouterResultat(matiere, note);
    }

    // Supprime un étudiant de la liste
    public void retirer(String nom) {
        ProfilEtudiant etudiant = trouverParNom(nom);
        if (etudiant != null) {
            liste.remove(etudiant);
            System.out.println("Le dossier a été retiré avec succès.");
        } else {
            System.out.println("Dossier introuvable.");
        }
    }

    // Cherche un étudiant par son nom et affiche ses notes
    public void chercherEtAfficher(String nom) {
        ProfilEtudiant etudiant = trouverParNom(nom);
        if (etudiant != null) etudiant.afficherBulletinComplet();
        else System.out.println("Aucun résultat pour ce nom.");
    }

    // Classe les étudiants
    public void ordonnerParMoyenne() {
        Collections.sort(liste, new Comparator<ProfilEtudiant>() {
            @Override
            public int compare(ProfilEtudiant e1, ProfilEtudiant e2) {

                return Double.compare(e2.calculerMoyenne(), e1.calculerMoyenne());
            }
        });
    }

    // Affiche la liste simplifiée de tous les étudiants
    public void afficherListeSimplifiee() {
        if (liste.isEmpty()) {
            System.out.println("Le registre est actuellement vide.");
            return;
        }
        int rang = 1;
        for (ProfilEtudiant e : liste) {
            e.afficherLigneSynthese(rang++);
        }
    }

    // Affiche tous les détails de chaque étudiant
    public void afficherTousLesDetails() {
        if (liste.isEmpty()) return;
        for (ProfilEtudiant e : liste) e.afficherBulletinComplet();
    }

    // Méthode interne pour trouver un étudiant dans la liste avec son nom
    private ProfilEtudiant trouverParNom(String nom) {
        for (ProfilEtudiant e : liste) {
            if (e.getNom().equalsIgnoreCase(nom)) return e;
        }
        return null;
    }
}