package models;

import java.util.List;

public class Service {
    private int id;
    private int capacite;
    private List<Leg> parcours;
    private int volumeTransporte;

    public Service(int id, int capacite, List<Leg> parcours) {
        this.id = id;
        this.capacite = capacite;
        this.parcours = parcours;
        this.volumeTransporte = 0;
    }

    public boolean peutTransporter(int volume) {
        return volumeTransporte + volume <= capacite;
    }

    public void transporter(int volume) {
        if (peutTransporter(volume)) {
            volumeTransporte += volume;
        }
    }

    public int getId() { return id; }
    public int getCapacite() { return capacite; }
    public List<Leg> getParcours() { return parcours;}

    public void setId(int id) {
        this.id = id;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public void setParcours(List<Leg> parcours) {
        this.parcours = parcours;
    }
}
