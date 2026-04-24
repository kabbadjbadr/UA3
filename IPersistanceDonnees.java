import java.util.ArrayList;

public interface IPersistanceDonnees {
    ArrayList<ProfilEtudiant> lireDonnees(String chemin);
    void sauvegarderDonnees(ArrayList<ProfilEtudiant> liste, String chemin);
}