package models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
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

    public Service() {
        // Constructeur vide pour la création d'instance avant le chargement
        this.bargesAssignees = new ArrayList<>();
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

    public void loadFromJson(JSONObject json) {
        this.id = json.getInt("id");
        this.duree = json.getInt("duree");
        this.debut = json.getInt("debut");
        this.fin = json.getInt("fin");
        this.capacite = json.getInt("capacite");

        // Charger le terminal d'origine
        JSONObject origineJson = json.getJSONObject("terminalOrigine");
        this.terminalOrigine = new Terminal();
        this.terminalOrigine.loadFromJson(origineJson);

        // Charger le terminal de destination
        JSONObject destinationJson = json.getJSONObject("terminalDestination");
        this.terminalDestination = new Terminal();
        this.terminalDestination.loadFromJson(destinationJson);

        // Charger les barges assignées
        this.bargesAssignees = new ArrayList<>();
        JSONArray bargesJson = json.getJSONArray("bargesAssignees");
        for (int i = 0; i < bargesJson.length(); i++) {
            JSONObject bargeJson = bargesJson.getJSONObject(i);
            Barge barge = new Barge();
            barge.loadFromJson(bargeJson);
            this.bargesAssignees.add(barge);
        }

        // Charger la route
        if (json.has("route")) {
            JSONObject routeJson = json.getJSONObject("route");
            this.route = new Route();
            this.route.loadFromJson(routeJson);
        }
    }
}
