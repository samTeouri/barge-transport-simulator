package models;

import org.json.JSONObject;

public class Deplacement {
    private Terminal origine;
    private Terminal destination;
    private int duree;

    public Deplacement() {

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

    public void loadFromJson(JSONObject json) {
        this.duree = json.getInt("duree");

        // Charger l'origine
        JSONObject origineJson = json.getJSONObject("origine");
        this.origine = new Terminal();
        this.origine.loadFromJson(origineJson);

        // Charger la destination
        JSONObject destinationJson = json.getJSONObject("destination");
        this.destination = new Terminal();
        this.destination.loadFromJson(destinationJson);
    }
}
