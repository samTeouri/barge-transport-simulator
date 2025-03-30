package models;

import org.json.JSONObject;

public class Terminal {
    int id;
    String nom;

    public Terminal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void loadFromJson(JSONObject json) {
        this.id = json.getInt("id");
        this.nom = json.getString("nom");
    }
}
