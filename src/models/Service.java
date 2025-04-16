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

    public int getId() { return id; }
    public int getCapacite() { return capacite; }
    public int getVolumeTransporte() { return volumeTransporte; }
    public List<Leg> getParcours() { return parcours;}

    public void setId(int id) { this.id = id; }
    public void setCapacite(int capacite) { this.capacite = capacite; }
    public void setVolumeTransporte(int volumeTransporte) { this.volumeTransporte = volumeTransporte; }
    public void setParcours(List<Leg> parcours) { this.parcours = parcours; }

    public boolean peutTransporter(int volume) {
        return volumeTransporte + volume <= capacite;
    }

    public void transporter(int volume) {
        volumeTransporte += volume;
    }

    public boolean peutAffecterDemande(Demande demande) {
        Leg firstLeg = getParcours().stream().filter(leg -> leg.getOrigine().getId() == demande.getOrigine().getId()).findFirst().orElse(null);
        int firstLegIndex = getParcours().indexOf(firstLeg);
        Leg lastLeg = getParcours().subList(firstLegIndex, getParcours().size() - 1).stream().filter(leg -> leg.getDestination().getId() == demande.getDestination().getId()).findFirst().orElse(null);

        return firstLeg != null && lastLeg != null && peutTransporter(demande.getVolume());
    }
}
