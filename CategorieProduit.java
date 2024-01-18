package gestiondechets;


public class CategorieProduit {
    private String nom;
    private int pointsRequisPourReduction;

    public CategorieProduit(String nom, int pointsRequisPourReduction) {
        this.nom = nom;
        this.pointsRequisPourReduction = pointsRequisPourReduction;
    }

    // Getter et setter pour les champs, ainsi que d'autres méthodes utiles
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPointsRequisPourReduction() {
        return pointsRequisPourReduction;
    }

    public void setPointsRequisPourReduction(int pointsRequisPourReduction) {
        this.pointsRequisPourReduction = pointsRequisPourReduction;
    }
}
