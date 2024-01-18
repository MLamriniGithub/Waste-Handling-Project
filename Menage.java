package gestiondechets;

import java.util.List;

import java.util.ArrayList;




public class Menage {
    private String id;
    private int pointsFidelite;
    private List<Depot> historiqueDepots;
    private List<BonDachat> bonsDachat;
    private List<Utilisateur> membres;

    public Menage(String id, Utilisateur utilisateur) {
        this.id = id;
        this.pointsFidelite = 0;
        this.historiqueDepots = new ArrayList<>();
        bonsDachat = new ArrayList<>();
        this.membres = new ArrayList<>();
        ajouterMembre(utilisateur);

    }

    // Getters et setters
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public int getPointsFidelite() {
        return pointsFidelite;
    }

    public void setPointsFidelite(int pointsFidelite) {
        this.pointsFidelite = pointsFidelite;
    }

    public List<Depot> getHistoriqueDepots() {
        return historiqueDepots;
    }

    public void setHistoriqueDepots(List<Depot> historiqueDepots) {
        this.historiqueDepots = historiqueDepots;
    }

    // methodes

    public void ajouterDepot(Depot depot) {
        historiqueDepots.add(depot);
    }

    public int consulterPointsFidelite() {
        return this.pointsFidelite;
    }

    public boolean utiliserPointsFidelite(int pointsUtilises, Commerce commerce, String categorieProduit, double pourcentageReduction) {
        if (pointsUtilises <= this.pointsFidelite) {
            this.pointsFidelite -= pointsUtilises;
            // Appliquer les réductions ou bons d'achat dans le commerce pour la catégorie de produit
            // ...
            return true;
        } else {
            return false;
        }
    }

    // ...
    
    public boolean utiliserPointsFidelitePourBonAchat(int pointsUtilises, Commerce commerce, double valeurBonAchat) {
        if (pointsUtilises <= this.pointsFidelite) {
            this.pointsFidelite -= pointsUtilises;
            // Créer et utiliser un bon d'achat pour le ménage dans le commerce
            // ...
            return true;
        } else {
            return false;
        }
    }

    public BonDachat utiliserPointsFidelitePourBonDachat(int pointsUtilises, String categorieProduit, double pourcentageReduction) {
        if (pointsUtilises <= this.pointsFidelite) {
            this.pointsFidelite -= pointsUtilises;
            double valeur = pointsUtilises / 100.0; // Supposons que 100 points valent 1 unité monétaire
            BonDachat bonDachat = new BonDachat(valeur, pourcentageReduction, categorieProduit);
            return bonDachat;
        } else {
            System.out.println("Points insuffisants pour créer un bon d'achat.");
            return null;
        }
    }

    public BonDachat convertirPointsEnBonDachat(int pointsRequis, double pourcentageReduction, String categorieProduit) {
        if (pointsFidelite >= pointsRequis) {
            pointsFidelite -= pointsRequis;
            BonDachat bonDachat = new BonDachat(pointsRequis, pourcentageReduction, categorieProduit);
            return bonDachat;
        } else {
            return null;
        }
    }

    
    public void ajouterBonDachat(Utilisateur utilisateur, BonDachat bonDachat) {
        bonsDachat.add(bonDachat);
    }

    public void utiliserBonDachat(BonDachat bonDachat, int montantUtilise) {
        bonDachat.utiliser(montantUtilise);
    }


    
    public void retirerMembre(Utilisateur utilisateur) {
        membres.remove(utilisateur);
    }

    public List<Utilisateur> getMembres() {
        return membres;
    }

    public void ajouterMembre(Utilisateur utilisateur) {
        if (!membres.contains(utilisateur)) {
            membres.add(utilisateur);
        }
    }
    
    public List<BonDachat> getBonsDachat() {
        return bonsDachat;
    }
    
}
