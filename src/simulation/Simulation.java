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
            writer.write("id_demande\tid_service_utilise\tvolume_accepte\n");
            
            for (Demande demande : demandes) {
                boolean affecte = false;
                for (Service s : services) {
                    if (s.peutTransporter(demande.getVolume())) {
                        s.transporter(demande.getVolume());
                        affecte = true;
                        writer.write(demande.getId() + "\t" + s.getId() + "\t" + demande.getVolume() + "\n");
                        break;
                    }
                }
                if (!affecte) {
                    writer.write(demande.getId() + "\tAUCUN\t0\n");
                }
            }
        }
    }
}
