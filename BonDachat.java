package gestiondechets;

public class BonDachat {
    private double valeur;
    private double pourcentageReduction;
    private String categorieProduit;
    

    public BonDachat(double valeur, double pourcentageReduction, String categorieProduit) {
        this.valeur = valeur;
        this.pourcentageReduction = pourcentageReduction;
        this.categorieProduit = categorieProduit;
    }

    // Getters et setters
    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public double getPourcentageReduction() {
        return pourcentageReduction;
    }

    public void setPourcentageReduction(double pourcentageReduction) {
        this.pourcentageReduction = pourcentageReduction;
    }

    public String getCategorieProduit() {
        return categorieProduit;
    }

    public void setCategorieProduit(String categorieProduit) {
        this.categorieProduit = categorieProduit;
    }

    // Autres méthodes utiles
    // ...
    public void utiliser(double montantUtilise) {
        if (montantUtilise <= valeur) {
            valeur -= montantUtilise;
        } else {
            System.out.println("Le montant utilisé dépasse la valeur du bon d'achat.");
        }
    }
}
