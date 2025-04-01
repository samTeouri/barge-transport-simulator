package services;

import java.io.*;
import java.util.*;
import models.*;

public class DataLoaderService {
    public static List<Service> chargerServices(String fichier) throws IOException {
        List<Service> services = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            br.readLine(); // Ignorer la première ligne
            while ((ligne = br.readLine()) != null) {
                String[] valeurs = ligne.split("\t");
                services.add(new Service(
                        Integer.parseInt(valeurs[0]),
                        Integer.parseInt(valeurs[1]),
                        new ArrayList<>()
                ));
            }
        }
        return services;
    }

    public static List<Demande> chargerDemandes(String fichier, List<Terminal> terminaux) throws IOException {
        List<Demande> demandes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            br.readLine(); // Ignorer la première ligne
            while ((ligne = br.readLine()) != null) {
                String[] valeurs = ligne.split("\t");
                Terminal origine = trouverTerminal(terminaux, Integer.parseInt(valeurs[2]));
                Terminal destination = trouverTerminal(terminaux, Integer.parseInt(valeurs[3]));
                demandes.add(new Demande(
                        Integer.parseInt(valeurs[0]),
                        origine,
                        destination,
                        Integer.parseInt(valeurs[3])
                ));
            }
        }
        return demandes;
    }

    private static Terminal trouverTerminal(List<Terminal> terminaux, int id) {
        return terminaux.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }
}
