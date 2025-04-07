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
                String[] valeurs = ligne.split("  ");
                System.out.println(Arrays.toString(valeurs));
                services.add(new Service(
                        Integer.parseInt(valeurs[0]),
                        Integer.parseInt(valeurs[1]),
                        new ArrayList<>()));
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
                System.out.println("Valeurs: " + Arrays.toString(valeurs));
                Terminal origine = trouverTerminal(terminaux, (valeurs[2]));
                Terminal destination = trouverTerminal(terminaux, (valeurs[3]));
                demandes.add(new Demande(
                        Integer.parseInt(valeurs[0]),
                        origine,
                        destination,
                        Integer.parseInt(valeurs[1])
                )
            );
        }
        return demandes;
    }
      
    public static List<Terminal> chargerTerminaux(String fichierTerminaux) throws IOException {
        List<Terminal> terminaux = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fichierTerminaux))) {
            String ligne;
            br.readLine(); // Ignorer la première ligne
            while ((ligne = br.readLine()) != null) {
                String[] valeurs = ligne.split("\t");
                terminaux.add(new Terminal(Integer.parseInt(valeurs[0]), valeurs[1]));
            }
        }
        return terminaux;
    }

    private static Terminal trouverTerminal(List<Terminal> terminaux, String name) {
        return terminaux.stream().filter(t -> t.getNom() == name).findFirst().orElse(null);

    }
}
