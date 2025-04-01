package models;

import java.util.List;

public class Itineraire {
    private int id;
    private List<Service> services;

    public Itineraire(int id, List<Service> services) {
        this.id = id;
        this.services = services;
    }

    public int getId() { return id; }
    public List<Service> getServices() { return services; }

    public void setId(int id) {
        this.id = id;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
