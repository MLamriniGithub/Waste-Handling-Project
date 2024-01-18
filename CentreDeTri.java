package gestiondechets;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CentreDeTri {
    private String nom;
    private String adresse;
    private List<PoubelleIntelligente> poubelles;
    private List<List<PoubelleIntelligente>> quartiersPoubelles;
    private List<Utilisateur> utilisateurs;
    private List<ContratPartenariat> contratsPartenariat;

    public CentreDeTri(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
        this.poubelles = new ArrayList<>();
        this.quartiersPoubelles = new ArrayList<>();
        this.contratsPartenariat = new ArrayList<>();
      
    }



    // Accesseurs mutateurs

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public List<PoubelleIntelligente> getPoubelles() {
        return poubelles;
    }

    public void setPoubelles(List<PoubelleIntelligente> poubelles) {
        this.poubelles = poubelles;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

        
    public List<ContratPartenariat> getContratsPartenariat() {
        return contratsPartenariat;
    }

    public void setContratsPartenariat(List<ContratPartenariat> contratsPartenariat) {
        this.contratsPartenariat = contratsPartenariat;
    }





    //Methodes ici 

    public void afficherPoubelles() {
        System.out.println("Liste des poubelles :");
        for (PoubelleIntelligente poubelle : poubelles) {
            System.out.println(poubelle.getNom());
        }
    }
    
    
    public void ajouterPoubelle(PoubelleIntelligente poubelle) {
        poubelles.add(poubelle);
    }
    
    public void retirerPoubelle(PoubelleIntelligente poubelle) {
        poubelles.remove(poubelle);
    }
    
    public void placerPoubellesDansQuartiers(List<String> quartiers) {
        int nbPoubelles = poubelles.size();
        int nbQuartiers = quartiers.size();
        int poubellesParQuartier = nbPoubelles / nbQuartiers;
        int poubellesRestantes = nbPoubelles % nbQuartiers;
        
        Iterator<PoubelleIntelligente> iter = poubelles.iterator();
        for (String quartier : quartiers) {
            List<PoubelleIntelligente> poubellesQuartier = new ArrayList<>();
            int nbPoubellesQuartier = poubellesParQuartier;
            if (poubellesRestantes > 0) {
                nbPoubellesQuartier++;
                poubellesRestantes--;
            }
            while (nbPoubellesQuartier > 0 && iter.hasNext()) {
                poubellesQuartier.add(iter.next());
                nbPoubellesQuartier--;
            }
            quartiersPoubelles.add(poubellesQuartier);
        }
    }

 
    public void collecterDechets() {
        for (PoubelleIntelligente poubelle : poubelles) {
            // Vider la poubelle et obtenir les poids des déchets par utilisateur
            List<Object[]> poidsDechetsParUtilisateur = poubelle.viderPoubelle();
    
            // Attribuer des points de fidélité aux utilisateurs
            for (Object[] poidsDechets : poidsDechetsParUtilisateur) {
                Utilisateur utilisateur = (Utilisateur) poidsDechets[0];
                double poids = (double) poidsDechets[1];
    
                // Ajouter des points de fidélité à l'utilisateur en fonction du poids des déchets
                int points = (int) poids; // Vous pouvez ajuster le nombre de points attribués en fonction du poids des déchets
                utilisateur.ajouterPoints(points);
            }
        }
    }
    
    public void recevoirNotificationPoubellePleine(PoubelleIntelligente poubelle) {
        System.out.println("Notification : La " + poubelle.getNom() + " est pleine. Veuillez procéder à la collecte des déchets.");
    }
    

    // Méthode pour ajouter un contrat de partenariat

    public void ajouterContratPartenariat(ContratPartenariat contrat) {
        this.contratsPartenariat.add(contrat);
    }

    public List<List<PoubelleIntelligente>> getQuartiersPoubelles() {
        return quartiersPoubelles;
    }
    
     // Calculer le poids total des déchets par catégorie
     public double getPoidsTotalDechetsParCategorie(String categorie) {
        double poidsTotal = 0;
        for (PoubelleIntelligente poubelle : poubelles) {
            for (Dechet dechet : poubelle.getDechets()) {
                if (dechet.getCategorie().equalsIgnoreCase(categorie)) {
                    poidsTotal += dechet.getPoids();
                }
            }
        }
        return poidsTotal;
    }

    
      // Calculer le poids total des déchets par période (en jours)
      public double getPoidsTotalDechetsParPeriode(int nbJours) {
        double poidsTotal = 0;
        LocalDate dateLimite = LocalDate.now().minusDays(nbJours);

        for (PoubelleIntelligente poubelle : poubelles) {
            for (Dechet dechet : poubelle.getDechets()) {
                // Cette méthode nécessite d'ajouter une date (LocalDate) à chaque déchet ajouté à la poubelle
                LocalDate dateDechet = dechet.getDate(); // Ajoutez une méthode getDate() dans la classe Dechet

                // Comparer cette date à la date actuelle pour calculer le poids total des déchets pour la période donnée
                if (dateDechet.isAfter(dateLimite) || dateDechet.isEqual(dateLimite)) {
                    poidsTotal += dechet.getPoids();
                }
            }
        }
        return poidsTotal;
    }

    // Ajouter les méthodes pour gérer les partenariats
    public boolean etablirPartenariat(Date dateDebut, Date dateFin, Commerce commerce) {
        ContratPartenariat contrat = new ContratPartenariat(dateDebut, dateFin, commerce, this);
        return contratsPartenariat.add(contrat);
    }

    public boolean renouvelerContrat(ContratPartenariat contrat, Date nouvelleDateDebut, Date nouvelleDateFin) {
        if (contratsPartenariat.contains(contrat)) {
            contrat.setDateDebut(nouvelleDateDebut);
            contrat.setDateFin(nouvelleDateFin);
            return true;
        } else {
            return false;
        }
    }
    
}
