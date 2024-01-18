package gestiondechets;

public class DechetUtilisateur {
    private Dechet dechet;
    private Utilisateur utilisateur;

    public DechetUtilisateur(Dechet dechet, Utilisateur utilisateur) {
        this.dechet = dechet;
        this.utilisateur = utilisateur;
    }

    public Dechet getDechet() {
        return dechet;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
}

