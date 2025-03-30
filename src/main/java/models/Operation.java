package models;

import org.json.JSONObject;

public class Operation {
    private int id;
    private String nom;
    private int duree;
    private  Terminal terminal;

    public Operation() {
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

    public void loadFromJson(JSONObject json) {
        this.id = json.getInt("id");
        this.nom = json.getString("nom");
        this.duree = json.getInt("duree");

        if (json.has("terminal")) {
            JSONObject terminalJson = json.getJSONObject("terminal");
            this.terminal = new Terminal();
            this.terminal.loadFromJson(terminalJson);
        }
    }
}
