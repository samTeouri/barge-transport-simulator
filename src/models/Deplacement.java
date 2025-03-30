package models;

public class Deplacement {
    private Terminal origine;
    private Terminal destination;
    private int duree;

    public Deplacement(Terminal origine, Terminal destination, int duree) {
        this.origine = origine;
        this.destination = destination;
        this.duree = duree;
    }

    public Terminal getOrigine() { return origine; }
    public Terminal getDestination() { return destination; }
    public int getDuree() { return duree; }

    public void setOrigine(Terminal origine) {
        this.origine = origine;
    }

    public void setDestination(Terminal destination) {
        this.destination = destination;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
}
