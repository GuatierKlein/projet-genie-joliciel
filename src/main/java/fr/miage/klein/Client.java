package fr.miage.klein;

public class Client {
    private String prenom;
    private String nom;
    private String adresse;
    private String numTel;
    private Mail email;
    private String numCb;
    private String immat;


    public String getPrenom() {
        return this.prenom;
    }

    public String getNom() {
        return this.nom;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumTel() {
        return this.numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public Mail getEmail() {
        return this.email;
    }

    public void setEmail(Mail email) {
        this.email = email;
    }

    public String getNumCb() {
        return this.numCb;
    }

    public void setNumCb(String numCb) {
        this.numCb = numCb;
    }

    public String getImmat() {
        return this.immat;
    }

    public void setImmat(String immat) {
        this.immat = immat;
    }

    public Client(String prenom, String nom, String adresse, String numTel, Mail email, String numCb, String immat) {
        this.prenom = prenom;
        this.nom = nom;
        this.adresse = adresse;
        this.numTel = numTel;
        this.email = email;
        this.numCb = numCb;
        this.immat = immat;
    }

    public Client(String prenom, String nom, String adresse, String numTel, Mail email, String numCb) {
        this.prenom = prenom;
        this.nom = nom;
        this.adresse = adresse;
        this.numTel = numTel;
        this.email = email;
        this.numCb = numCb;
    }
}
