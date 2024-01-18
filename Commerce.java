package gestiondechets;
import java.util.List;

import java.util.ArrayList;


public class Commerce {
    private String nom;
    private List<String> CategoriesProduits;
    private String adresse;

    public Commerce(String nom, String adresse, List<String> listeCategoriesProduits) {
        this.nom = nom;
        this.adresse = adresse;
        this.CategoriesProduits = new ArrayList<>(listeCategoriesProduits);
    }

    // Accesseurs mutateurs 
    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<String> getListeCategoriesProduits() {
        return this.CategoriesProduits;
    }

    public void ajouterCategorieProduit(String categorie) {
        this.CategoriesProduits.add(categorie);
    }

    public void supprimerCategorieProduit(String categorie) {
        this.CategoriesProduits.remove(categorie);
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    // autres méthodes ici

    public boolean appliquerReduction(Menage menage, String categorieProduit, double pourcentageReduction) {
        if (CategoriesProduits.contains(categorieProduit)) { // Correction ici
            // Appliquer la réduction pour le ménage sur la catégorie de produit
            // ...
            return true;
        } else {
            return false;
        }
    }
}
