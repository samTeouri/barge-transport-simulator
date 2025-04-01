package models;

public class Barge {
    private int id;
    private int capacite;

    public Barge(int id, int capacite) {
        this.id = id;
        this.capacite = capacite;
    }

    public int getId() { return id; }
    public int getCapacite() { return capacite; }

    public void setId(int id) {
        this.id = id;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }
}
