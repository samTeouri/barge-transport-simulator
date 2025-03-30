package models;

import java.util.List;

public class Service {
    private int id;
    private int duree;
    private int debut;
    private int fin;
    private Terminal terminalOrigine;
    private Terminal terminalDestination;
    private int capacite;
    private List<Barge> bargesAssignees;
    private Route route;

    public Service(
        List<Barge> bargesAssignees,
        int capacite,
        int debut,
        int duree,
        int fin,
        int id,
        Terminal terminalDestination,
        Terminal terminalOrigine,
        Route route
    ) {
        this.bargesAssignees = bargesAssignees;
        this.capacite = capacite;
        this.debut = debut;
        this.duree = duree;
        this.fin = fin;
        this.id = id;
        this.terminalDestination = terminalDestination;
        this.terminalOrigine = terminalOrigine;
        this.route = route;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getDebut() {
        return debut;
    }

    public void setDebut(int debut) {
        this.debut = debut;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    public Terminal getTerminalOrigine() {
        return terminalOrigine;
    }

    public void setTerminalOrigine(Terminal terminalOrigine) {
        this.terminalOrigine = terminalOrigine;
    }

    public Terminal getTerminalDestination() {
        return terminalDestination;
    }

    public void setTerminalDestination(Terminal terminalDestination) {
        this.terminalDestination = terminalDestination;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public List<Barge> getBargesAssignees() {
        return bargesAssignees;
    }

    public void setBargesAssignees(List<Barge> bargesAssignees) {
        this.bargesAssignees = bargesAssignees;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

}
