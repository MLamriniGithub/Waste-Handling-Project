package gestiondechets;




import java.time.LocalDateTime;



public class Depot {
    private String idPoubelle;
    private LocalDateTime heureDepot;
    private double quantiteDechets;
    private TypeDechet typeDechet;
    private int pointsGagnes;

    public Depot(String idPoubelle, LocalDateTime heureDepot, TypeDechet typeDechet, double quantiteDechets, int pointsGagnes) {
        this.idPoubelle = idPoubelle;
        this.heureDepot = heureDepot;
        this.quantiteDechets = quantiteDechets;
        this.typeDechet = typeDechet;
        this.pointsGagnes = pointsGagnes;
    }

    // Getters et setters
    public String getIdPoubelle() {
        return idPoubelle;
    }

    public void setIdPoubelle(String idPoubelle) {
        this.idPoubelle = idPoubelle;
    }

    public LocalDateTime getHeureDepot() {
        return heureDepot;
    }

    public void setHeureDepot(LocalDateTime heureDepot) {
        this.heureDepot = heureDepot;
    }

    public double getQuantiteDechets() {
        return quantiteDechets;
    }

    public void setQuantiteDechets(double quantiteDechets) {
        this.quantiteDechets = quantiteDechets;
    }

    public TypeDechet getTypeDechet() {
        return typeDechet;
    }

    public void setTypeDechet(TypeDechet typeDechet) {
        this.typeDechet = typeDechet;
    }

    public int getPointsGagnes() {
        return pointsGagnes;
    }

    public void setPointsGagnes(int pointsGagnes) {
        this.pointsGagnes = pointsGagnes;
    }
}
