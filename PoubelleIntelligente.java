package gestiondechets;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class PoubelleIntelligente {
    private String id;
    private int capaciteMax;
    private List<String> categories;
    private int[] quantiteDechets;
    private String emplacement;
    private List<Utilisateur> utilisateursAutorises;
    private Utilisateur utilisateurActuel;
    private int capaciteActuelle;
    private String nom;
    private String localisation;
    private List<Dechet> dechets;
    private List<DechetUtilisateur> dechetsUtilisateurs;
    private String quartier;


    public PoubelleIntelligente(String id, int capaciteMax, List<String> categories, String emplacement, String nom, String quartier) {
        this.id = id;
        this.capaciteMax = capaciteMax;
        this.categories = categories;
        this.quantiteDechets = new int[categories.size()];
        this.emplacement = emplacement;
        this.utilisateursAutorises = new ArrayList<>();
        this.utilisateurActuel = null;
        this.dechets = new ArrayList<>();
        this.dechetsUtilisateurs = new ArrayList<>();
        this.nom = nom;
        this.quartier = quartier;
    }


    //Accesseurs et mutateurs 

    public String getId() {
        return id;
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public List<Utilisateur> getUtilisateursAutorises() {
        return utilisateursAutorises;
    }

    public Utilisateur getUtilisateurActuel() {
        return utilisateurActuel;
    }

    public void setUtilisateurActuel(Utilisateur utilisateurActuel) {
        this.utilisateurActuel = utilisateurActuel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public List<Dechet> getDechets() {
        return dechets;
    }

    public void setDechets(List<Dechet> dechets) {
        this.dechets = dechets;
    }

    // Getter et setter pour quartier
    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }


    public double getQuantite(Dechet dechet, String unite) {
        String categorie = dechet.getCategorie();
        int index = categories.indexOf(categorie);
        if (index == -1) {
            return 0.0;
        }
        double densite = dechet.getDensite(); // appel à la méthode getDensite() de l'objet dechet
        double volume = (double) quantiteDechets[index] / densite;
        if (unite.equals("m3")) {
            return volume;
        } else if (unite.equals("kg")) {
            return quantiteDechets[index];
        } else {
            return 0.0;
        }
    }
    

    public boolean estCategorieValide(String categorie) {
        return categories.contains(categorie);
    }

    public boolean estDechetValide(Dechet dechet) {
        return estCategorieValide(dechet.getCategorie());
    }

    public boolean estAutorise(Utilisateur utilisateur) {
        return utilisateursAutorises.contains(utilisateur);
    }

    public boolean estPleine() {
        int totalQuantite = 0;
        for (int quantite : quantiteDechets) {
            totalQuantite += quantite;
        }
        return totalQuantite >= capaciteMax;
    }

    public boolean verifierConformite(Dechet dechet) {
        String categorie = dechet.getCategorie();
        return categories.contains(categorie);
    }

    public void retirerPointsFidelite(int penalite) {
        if (utilisateurActuel != null) {
            utilisateurActuel.setPointsFidelite(utilisateurActuel.getPointsFidelite() - penalite);
        }
    }
    
    public void ajouterDechet(Dechet dechet, Utilisateur utilisateur, CentreDeTri centreDeTri) {
        String categorie = dechet.getCategorie();
        int index = categories.indexOf(categorie);
        dechets.add(dechet);
        if (index != -1) {
            double poidsDechet = dechet.getPoids();
            quantiteDechets[index] += poidsDechet;
    
            // Ajoute des points de fidélité à l'utilisateur actuel
            int points = (int) poidsDechet; // Vous pouvez ajuster le nombre de points attribués en fonction de la quantité de déchets déposée
            utilisateurActuel = utilisateur; // Mettez à jour l'utilisateur actuel
            ajouterPointsFidelite(points);
    
            // Mettre à jour la liste des utilisateurs autorisés de la poubelle
            if (!utilisateursAutorises.contains(utilisateur)) {
                utilisateursAutorises.add(utilisateur);
            }
        } else {
            System.out.println("Les déchets ne sont pas conformes à la catégorie de la poubelle.");
    
            // Retire des points de fidélité à l'utilisateur actuel
            int penalite = (int) dechet.getPoids(); // Vous pouvez ajuster la pénalité en fonction de la quantité de déchets déposée
            utilisateurActuel = utilisateur; // Mettez à jour l'utilisateur actuel
            retirerPointsFidelite(penalite);
            System.out.println("L'utilisateur " + utilisateurActuel.getNom() + " a reçu une pénalité de " + penalite + " points pour avoir ajouté un déchet non conforme.");
        }
        if (estPleine()) {
            centreDeTri.recevoirNotificationPoubellePleine(this);
        }
    }
         
    


    public boolean estConforme(Dechet dechet) {
        String categorie = dechet.getCategorie();
        if (!categories.contains(categorie)) {
            // Le déchet n'appartient pas à la catégorie de la poubelle
            // Pénalité à appliquer ou autre traitement
            return false;
        }
        return true;
    }
    

    public List<Object[]> viderPoubelle() {
        List<Object[]> poidsDechetsParUtilisateur = new ArrayList<>();
    
        for (DechetUtilisateur dechetUtilisateur : dechetsUtilisateurs) {
            Utilisateur utilisateur = dechetUtilisateur.getUtilisateur();
            double poids = dechetUtilisateur.getDechet().getPoids();
    
            // Ajouter le poids total des déchets de l'utilisateur à la liste
            poidsDechetsParUtilisateur.add(new Object[]{utilisateur, poids});
        }
    
        // Vider les déchets de la poubelle
        dechets.clear();
        dechetsUtilisateurs.clear();
    
        return poidsDechetsParUtilisateur;
    }
    
    
    
    public boolean identifierUtilisateur(Utilisateur utilisateur) {
        return utilisateursAutorises.contains(utilisateur);
    }
    
    public int getQuantiteParTypePourUtilisateur(String categorie, Utilisateur utilisateur) {
        int index = categories.indexOf(categorie);
        if (index == -1) {
            return 0;
        }
        int quantite = 0;
        for (int i = 0; i < quantiteDechets.length; i++) {
            if (utilisateursAutorises.get(i).equals(utilisateur)) {
                quantite += quantiteDechets[i];
            }
        }
        return quantite;
    }
    
    public void ajouterUtilisateurAutorise(Utilisateur utilisateur) {
        utilisateursAutorises.add(utilisateur);
    }
    
    public void supprimerUtilisateurAutorise(Utilisateur utilisateur) {
        utilisateursAutorises.remove(utilisateur);
    }
    
    public void ajouterPointsFidelite(int points) {
        if (utilisateurActuel != null) {
            utilisateurActuel.ajouterPoints(points);
        }
    }

    public void setCapaciteMax(int capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    public int getCapaciteActuelle() {
        return capaciteActuelle;
    }

    public void setCapaciteActuelle(int capaciteActuelle) {
        this.capaciteActuelle = capaciteActuelle;
    }

    public double getTauxRemplissage() {
        int totalQuantite = 0;
        for (int quantite : quantiteDechets) {
            totalQuantite += quantite;
        }
        if (capaciteMax == 0) {
            return 0;
        }
        capaciteActuelle = totalQuantite;
        return (double) capaciteActuelle / capaciteMax * 100;
    }


    //quelques statitistiques 
    public void afficherInfo() {
        System.out.println("Nom de la poubelle: " + nom);
        System.out.println("Localisation: " + localisation);
        System.out.println("Capacité actuelle: " + capaciteActuelle);
        System.out.println("Capacité maximale: " + capaciteMax);
        System.out.println("Taux de remplissage: " + getTauxRemplissage() + "%");
        System.out.println("Liste des déchets:");

        for (Dechet dechet : dechets) {
            System.out.println("  - " + dechet);
        }
    }


    
  

    public List<DechetUtilisateur> getDechetsUtilisateurs() {
        return dechetsUtilisateurs;
    }


    
    
   
    
   
}
    
    
    


