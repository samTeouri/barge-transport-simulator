package models;

public class Operation {
    private int id;
    private String nom;
    private int duree;
    private  Terminal terminal;

    public Operation(int id, String nom, int duree) {
        this.id = id;
        this.nom = nom;
        this.duree = duree;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getDuree() {
        return duree;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }
}
