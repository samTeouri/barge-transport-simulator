package simulation;

import java.io.*;
import java.util.*;
import models.*;
import services.DataLoaderService;

public class Simulation {
    private List<Service> services;
    private List<Demande> demandes;
    private List<Terminal> terminaux;

    public void chargerDonnees(String fichierServices, String fichierDemandes, String fichierTerminaux) throws IOException {
        terminaux = DataLoaderService.chargerTerminaux(fichierTerminaux);
        services = DataLoaderService.chargerServices(fichierServices, terminaux);
        demandes = DataLoaderService.chargerDemandes(fichierDemandes, terminaux);
    }

    public void executerSimulation(String fichierSortie) throws IOException {
        try (FileWriter writer = new FileWriter(fichierSortie)) {
            writer.write("id_demande\tid_service_utilise\n");
            
            for (Demande demande : demandes) {
                boolean affecte = false;
                for (Service s : services) {
                    s.printDemandeCalendar(demande, 0);
                    if (s.peutAffecterDemande(demande)) {
                        s.transporter(demande.getVolume());
                        writer.write(demande.getId() + "\t" + s.getId() + "\n");
                        affecte = true;
                        break; // Sortir de la boucle une fois la demande affectée
                    }
                }
                if (!affecte) {
                    writer.write(demande.getId() + "\tAUCUN\n");
                }
            }
        }
        
        // Générer les statistiques
        genererStatistiques(fichierSortie);
    }

    private void genererStatistiques(String fichierSortie) throws IOException {
        // Calculer les statistiques
        int nbDemandes = demandes.size();
        int nbSatisfaites = 0;
        int volumeTotal = 0;
        int volumeTransporte = 0;
        int volumeNonTransporte = 0;
        
        // Compter les demandes par service
        Map<Integer, Integer> demandesParService = new HashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(fichierSortie))) {
            String ligne;
            br.readLine(); // Ignorer l'en-tête
            while ((ligne = br.readLine()) != null) {
                String[] valeurs = ligne.split("\t");
                int idDemande = Integer.parseInt(valeurs[0]);
                String service = valeurs[1];
                
                Demande demande = demandes.stream()
                    .filter(d -> d.getId() == idDemande)
                    .findFirst()
                    .orElse(null);
                
                if (demande != null) {
                    volumeTotal += demande.getVolume();
                    if (!"AUCUN".equals(service)) {
                        nbSatisfaites++;
                        volumeTransporte += demande.getVolume();
                        demandesParService.merge(Integer.parseInt(service), 1, Integer::sum);
                    } else {
                        volumeNonTransporte += demande.getVolume();
                    }
                }
            }
        }

        // Écrire les statistiques dans le fichier
        String fichierStats = "output/stats.txt";
        try (FileWriter writer = new FileWriter(fichierStats)) {
            writer.write("Statistiques de la simulation\n\n");
            writer.write(String.format("Nombre total de demandes: %d\n", nbDemandes));
            writer.write(String.format("Demandes satisfaites: %d (%.1f%%)\n", 
                nbSatisfaites, (double)nbSatisfaites/nbDemandes*100));
            writer.write(String.format("Demandes non satisfaites: %d (%.1f%%)\n", 
                nbDemandes - nbSatisfaites, (double)(nbDemandes - nbSatisfaites)/nbDemandes*100));
            writer.write("\n");
            writer.write(String.format("Volume total demandé: %d\n", volumeTotal));
            writer.write(String.format("Volume transporté: %d (%.1f%%)\n", 
                volumeTransporte, (double)volumeTransporte/volumeTotal*100));
            writer.write(String.format("Volume non transporté: %d (%.1f%%)\n", 
                volumeNonTransporte, (double)volumeNonTransporte/volumeTotal*100));
            writer.write("\n");
            writer.write("Répartition des demandes par service:\n");
            for (Map.Entry<Integer, Integer> entry : demandesParService.entrySet()) {
                writer.write(String.format("Service #%d: %d demandes\n", entry.getKey(), entry.getValue()));
            }
            writer.write("\n");
            writer.write(String.format("Capacité totale utilisée: %d/%d (%.1f%%)\n", 
                services.stream().mapToInt(Service::getVolumeTransporte).sum(),
                services.stream().mapToInt(Service::getCapacite).sum(),
                (double)services.stream().mapToInt(Service::getVolumeTransporte).sum() / 
                services.stream().mapToInt(Service::getCapacite).sum() * 100));
        }
    }
}
