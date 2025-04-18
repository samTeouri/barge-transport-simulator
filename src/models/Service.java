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

    public void printDemandeCalendar(Demande demande, int startTime) {
        if (!peutAffecterDemande(demande)) {
            System.out.printf("La demande #%d ne peut pas être affectée au service #%d.\n", demande.getId(), id);
            return;
        }

        // Find the starting leg (where origine matches demande origine)
        Leg firstLeg = getParcours().stream()
                .filter(leg -> leg.getOrigine().getId() == demande.getOrigine().getId())
                .findFirst()
                .orElse(null);
        
        int currentLegIndex = getParcours().indexOf(firstLeg);
        int currentTime = startTime;
        
        System.out.println("\nCalendrier pour Demande #" + demande.getId() + ":");
        System.out.println("Volume: " + demande.getVolume());
        System.out.println("Origine: " + demande.getOrigine().getNom());
        System.out.println("Destination: " + demande.getDestination().getNom());
        System.out.println("\nÉvénements:");
        
        // Print departure from origin
        System.out.printf("T=%d: Départ du terminal %s%n", currentTime, firstLeg.getOrigine().getNom());
        
        // Follow the path until we reach the destination
        while (currentLegIndex < getParcours().size()) {
            Leg currentLeg = getParcours().get(currentLegIndex);
            
            // Print arrival at next terminal
            currentTime += currentLeg.getDuree();
            System.out.printf("T=%d: Arrivée au terminal %s%n", 
                    currentTime, currentLeg.getDestination().getNom());
            
            // If we've reached the destination, break
            if (currentLeg.getDestination().getId() == demande.getDestination().getId()) {
                break;
            }
            
            // If not at destination, print departure from current terminal
            System.out.printf("T=%d: Départ du terminal %s%n", 
                    currentTime, currentLeg.getDestination().getNom());
            
            currentLegIndex++;
        }
        
        System.out.println("\nDurée totale: " + (currentTime - startTime) + "\n");
    }
}
