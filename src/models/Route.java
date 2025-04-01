package models;

import java.util.List;

public class Route {
    private int id;
    private List<Leg> deplacements;
    private List<Operation> operations;
    private int duree;

    public Route(int id, List<Leg> deplacements, int duree) {
        this.id = id;
        this.deplacements = deplacements;
        this.duree = duree;
    }

    public int getId() { return id; }
    public List<Leg> getDeplacements() { return deplacements; }
    public List<Operation> getOperations() { return operations; }
    public int getDuree() { return duree; }

    public void setId(int id) {
        this.id = id;
    }

    public void setDeplacements(List<Leg> deplacements) {
        this.deplacements = deplacements;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
}
