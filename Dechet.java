package gestiondechets;
import java.time.LocalDate;

public class Dechet {
    private String type;
    private double poids;
    private String categorie;
    private double densite;
    private LocalDate date;
    
    
    public Dechet(String type, double poids, String categorie, double densite, LocalDate date) {
        this.type = type;
        this.poids = poids;
        this.categorie = categorie;
        this.densite = densite;
        this.date = LocalDate.now();

    }
    
    public String getType() {
        return type;
    }
    
    public double getPoids() {
        return poids;
    }

    public String getCategorie() {
        return categorie;
    }

    public double getDensite() {
        return densite;
    }

    public LocalDate getDate() {
        return date;
    }
}