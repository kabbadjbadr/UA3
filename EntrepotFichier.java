import java.io.*; //(lecture et écriture)
import java.util.ArrayList;


public class EntrepotFichier implements IPersistanceDonnees {

    @Override
    //lit le fichier CSV et le transforme en une liste d'objets ProfilEtudiant
    public ArrayList<ProfilEtudiant> lireDonnees(String chemin) {

        ArrayList<ProfilEtudiant> resultats = new ArrayList<>();

        BufferedReader lecteur = null;

        try {

            lecteur = new BufferedReader(new FileReader(chemin));

            // SKIP L'EN-TÊTE
            lecteur.readLine();


            String ligne;

            // lit le fichier en entier
            while ((ligne = lecteur.readLine()) != null) {

                // Saute la ligne si elle est vide
                if (ligne.trim().isEmpty()) continue;


                String[] segments = ligne.split(",");


                if (segments.length == 3) {

                    String nom = segments[0].trim();

                    String matiere = segments[1].trim();
                    // Convertit le texte de la note en nombre
                    double note = Double.parseDouble(segments[2].trim().replace(',', '.'));

                    ProfilEtudiant existant = null;
                    for (int i = 0; i < resultats.size(); i++) {
                        // Compare le nom de l'étudiant en cours avec ceux déjà enregistrés
                        if (resultats.get(i).getNom().equalsIgnoreCase(nom)) {
                            existant = resultats.get(i);
                            break; //Stop
                        }
                    }

                    // nouveau nom
                    if (existant == null) {

                        ProfilEtudiant nouveau = new ProfilEtudiant(nom);

                        nouveau.ajouterResultat(matiere, note);

                        resultats.add(nouveau);
                    } else {

                        existant.ajouterResultat(matiere, note);
                    }
                }
            }
        } catch (IOException e) {
            //si le fichier n'est pas trouvé ou est illisible
            System.out.println("Erreur : Impossible de lire le fichier.");
        } finally {

            try {
                if (lecteur != null) lecteur.close();
            } catch (IOException e) {
                System.out.println("Erreur lors de la fermeture du lecteur.");
            }
        }

        return resultats;
    }

    @Override

    public void sauvegarderDonnees(ArrayList<ProfilEtudiant> liste, String chemin) {

        BufferedWriter ecrivain = null;
        try {
            // écrase le contenu précédent
            ecrivain = new BufferedWriter(new FileWriter(chemin));

            ecrivain.write("Rang,Nom,Moyenne");

            ecrivain.newLine();


            for (int i = 0; i < liste.size(); i++) {
                ProfilEtudiant e = liste.get(i);

                String ligneCsv = (i + 1) + "," + e.getNom() + "," + String.format("%.2f", e.calculerMoyenne());

                ecrivain.write(ligneCsv);

                ecrivain.newLine();
            }
            System.out.println("Fichier mis à jour avec succès.");
        } catch (IOException e) {
            // Gère les erreurs d'écriture
            System.out.println("Erreur : Impossible de sauvegarder les données.");
        } finally {

            try {
                if (ecrivain != null) ecrivain.close();
            } catch (IOException e) {
                System.out.println("Erreur lors de la fermeture du fichier.");
            }
        }
    }
}