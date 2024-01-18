package gestiondechets;
import java.util.Date;


public class ContratPartenariat {
    private Date dateDebut;
    private Date dateFin;
    private Commerce commerce;
    private CentreDeTri centreDeTri;
    
    public ContratPartenariat(Date dateDebut, Date dateFin, Commerce commerce, CentreDeTri centreDeTri) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.commerce = commerce;
        this.centreDeTri = centreDeTri;
    }


    
    // Accesseurs/mutateurs

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Commerce getCommerce() {
        return commerce;
    }

    public void setCommerce(Commerce commerce) {
        this.commerce = commerce;
    }

    public CentreDeTri getCentreDeTri() {
        return centreDeTri;
    }

    public void setCentreDeTri(CentreDeTri centreDeTri) {
        this.centreDeTri = centreDeTri;
    }
}
