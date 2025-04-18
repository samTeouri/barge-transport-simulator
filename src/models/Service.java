package models;

import java.util.List;

/**
 * Représente un service de transport qui peut traiter des demandes.
 * Un service a une capacité maximale et un parcours défini entre les terminaux.
 */
public class Service {
    private int id;
    private int capacite;
    private List<Leg> parcours;
    private int volumeTransporte;

    /**
     * Crée un nouveau service avec ses caractéristiques.
     * @param id L'identifiant unique du service
     * @param capacite La capacité maximale de transport
     * @param parcours Le parcours défini par une liste de legs
     */
    public Service(int id, int capacite, List<Leg> parcours) {
        this.id = id;
        this.capacite = capacite;
        this.parcours = parcours;
        this.volumeTransporte = 0;
    }

    /**
     * Vérifie si le service peut affecter une demande.
     * Une demande peut être affectée si :
     * - Le service a suffisamment de capacité
     * - Le parcours du service passe par l'origine de la demande
     * - Le parcours passe par la destination de la demande
     */
    public boolean peutAffecterDemande(Demande demande) {
        Leg firstLeg = getParcours().stream()
                .filter(leg -> leg.getOrigine().getId() == demande.getOrigine().getId())
                .findFirst()
                .orElse(null);
        int firstLegIndex = getParcours().indexOf(firstLeg);
        Leg lastLeg = getParcours().subList(firstLegIndex, getParcours().size() - 1).stream()
                .filter(leg -> leg.getDestination().getId() == demande.getDestination().getId())
                .findFirst()
                .orElse(null);

        return firstLeg != null && lastLeg != null && peutTransporter(demande.getVolume());
    }

    /**
     * Affiche le calendrier détaillé d'une demande.
     * Pour chaque étape du parcours, affiche les heures de départ et d'arrivée,
     * ainsi que la durée totale du transport.
     */
    public void printDemandeCalendar(Demande demande, int startTime) {
        if (!peutAffecterDemande(demande)) {
            System.out.printf("La demande #%d ne peut pas être affectée au service #%d.\n", demande.getId(), id);
            return;
        }

        // Trouve la première étape qui correspond à l'origine de la demande
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
        
        // Affiche le départ de l'origine
        System.out.printf("T=%d: Départ du terminal %s%n", currentTime, firstLeg.getOrigine().getNom());
        
        // Suive le parcours jusqu'à la destination
        while (currentLegIndex < getParcours().size()) {
            Leg currentLeg = getParcours().get(currentLegIndex);
            
            // Affiche l'arrivée au terminal suivant
            currentTime += currentLeg.getDuree();
            System.out.printf("T=%d: Arrivée au terminal %s%n", 
                    currentTime, currentLeg.getDestination().getNom());
            
            // Si on est arrivé à destination, on s'arrête
            if (currentLeg.getDestination().getId() == demande.getDestination().getId()) {
                break;
            }
            
            // Sinon, on affiche le départ du terminal actuel
            System.out.printf("T=%d: Départ du terminal %s%n", 
                    currentTime, currentLeg.getDestination().getNom());
            
            currentLegIndex++;
        }
        
        System.out.println("\nDurée totale: " + (currentTime - startTime) + "\n");
    }

    public int getId() { return id; }
    public int getCapacite() { return capacite; }
    public int getVolumeTransporte() { return volumeTransporte; }
    public List<Leg> getParcours() { return parcours; }

    public void setId(int id) { this.id = id; }
    public void setCapacite(int capacite) { this.capacite = capacite; }
    public void setVolumeTransporte(int volumeTransporte) { this.volumeTransporte = volumeTransporte; }
    public void setParcours(List<Leg> parcours) { this.parcours = parcours; }

    /**
     * Vérifie si le service peut transporter un volume supplémentaire.
     * @param volume Le volume à ajouter
     * @return true si le volume total ne dépasse pas la capacité maximale
     */
    public boolean peutTransporter(int volume) {
        return volumeTransporte + volume <= capacite;
    }

    /**
     * Ajoute un volume au volume déjà transporté.
     * @param volume Le volume à ajouter
     */
    public void transporter(int volume) {
        volumeTransporte += volume;
    }
}
