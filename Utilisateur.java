package gestiondechets;

public class Utilisateur {
    private String identifiant;
    private String nom;
    private String email;
    private String motDePasse;
    private CentreDeTri centreDeTri;
    private int pointsFidelite;
    private String codeAcces;
    private PoubelleIntelligente poubelleAutorisee;
    private static final double CONVERSION_RATE = 0.01;

    public Utilisateur(String identifiant, String nom, String email, String motDePasse) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.pointsFidelite = 0;
        
    }

    // ... autres méthodes existantes ...

    public void ajouterPoints(int points) {
        this.pointsFidelite += points;
    }

    public void retirerPoints(int points) {
        this.pointsFidelite -= points;
        if (this.pointsFidelite < 0) {
            this.pointsFidelite = 0;
        }
    }

    public int getPointsFidelite() {
        return this.pointsFidelite;
    }

    // Accesseurs et mutateurs pour identifiant, nom, email et motDePasse
    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

   

    public void setPointsFidelite(int pointsFidelite) {
        this.pointsFidelite = pointsFidelite;
    }

    public CentreDeTri getCentreDeTri() {
        return centreDeTri;
    }

    public void setCentreDeTri(CentreDeTri centreDeTri) {
        this.centreDeTri = centreDeTri;
    }

    public String getCodeAcces() {
        return this.codeAcces;
    }

    public void setCodeAcces(String codeAcces) {
        this.codeAcces = codeAcces;
    }

    public boolean estAutorise(PoubelleIntelligente poubelle) {
        return poubelle.getUtilisateursAutorises().contains(this);
    }
    
    public PoubelleIntelligente getPoubelleAutorisee() {
        return poubelleAutorisee;
    }


    public void setPoubelleAutorisee(PoubelleIntelligente poubelleAutorisee) {
        this.poubelleAutorisee = poubelleAutorisee;
    }

    
    public BonDachat convertirPointsEnBonDachat(int pointsAConvertir, String categorieProduit) {
        if (pointsFidelite >= pointsAConvertir) {
            pointsFidelite -= pointsAConvertir;
            double valeur = pointsAConvertir * CONVERSION_RATE;
            double pourcentageReduction = 10.0;
            return new BonDachat(valeur, pourcentageReduction, categorieProduit);
        } else {
            System.out.println("Vous n'avez pas assez de points pour convertir en bon d'achat.");
            return null;
        }
    }
    
    public boolean connecter(String motDePasse) {
        return this.motDePasse.equals(motDePasse);
    }
    
    public void modifierMotDePasse(String ancienMotDePasse, String nouveauMotDePasse) {
        if (this.motDePasse.equals(ancienMotDePasse)) {
            this.motDePasse = nouveauMotDePasse;
        } else {
            System.out.println("L'ancien mot de passe est incorrect.");
        }
    }
    
    public void afficherInformations() {
        System.out.println("Nom: " + this.nom);
        System.out.println("Email: " + this.email);
        System.out.println("Points de fidélité: " + this.pointsFidelite);
    }
    
    
}
