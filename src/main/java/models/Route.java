package models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private int id;
    private List<Deplacement> deplacements;
    private List<Operation> operations;
    private int duree;

    public Route() {
        this.deplacements = new ArrayList<>();
        this.operations = new ArrayList<>();
    }

    public int getId() { return id; }
    public List<Deplacement> getDeplacements() { return deplacements; }
    public List<Operation> getOperations() { return operations; }
    public int getDuree() { return duree; }

    public void setId(int id) {
        this.id = id;
    }

    public void setDeplacements(List<Deplacement> deplacements) {
        this.deplacements = deplacements;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void loadFromJson(JSONObject json) {
        this.id = json.getInt("id");
        this.duree = json.getInt("duree");

        // Charger les déplacements
        this.deplacements = new ArrayList<>();
        if (json.has("deplacements")) {
            JSONArray deplacementsJson = json.getJSONArray("deplacements");
            for (int i = 0; i < deplacementsJson.length(); i++) {
                JSONObject deplacementJson = deplacementsJson.getJSONObject(i);
                Deplacement deplacement = new Deplacement();
                deplacement.loadFromJson(deplacementJson);
                this.deplacements.add(deplacement);
            }
        }

        // Charger les opérations
        this.operations = new ArrayList<>();
        if (json.has("operations")) {
            JSONArray operationsJson = json.getJSONArray("operations");
            for (int i = 0; i < operationsJson.length(); i++) {
                JSONObject operationJson = operationsJson.getJSONObject(i);
                Operation operation = new Operation();
                operation.loadFromJson(operationJson);
                this.operations.add(operation);
            }
        }
    }
}
