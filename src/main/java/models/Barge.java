package models;

import org.json.JSONArray;
import org.json.JSONObject;


public class Barge {
    private int id;
    private String nom;
    private int capacite;

    public Barge() {
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public int getCapacite() { return capacite; }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void loadFromJson(JSONObject json) {
        this.id = json.getInt("id");
        this.nom = json.getString("nom");
        this.capacite = json.getInt("capacite");
    }

    @Override
    public String toString() {
        return "Barge{id=" + id + ", nom='" + nom + "', capacite=" + capacite + "}";
    }

}
