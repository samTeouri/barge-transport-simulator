package services;

import java.io.*;
import java.util.*;
import models.*;

public class DataLoaderService {
    public static List<Service> chargerServices(String fichier, List<Terminal> terminaux) throws IOException {
        List<Service> services = new ArrayList<>();
        List<Leg> parcours = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            br.readLine(); // Ignorer la première ligne
            while ((ligne = br.readLine()) != null) {
                String[] valeurs = ligne.split("  ");
                System.out.println("Valeurs Services : " + Arrays.toString(valeurs));
                String[] parcoursString = valeurs[6].split(";");
                for (String s : parcoursString) {
                    parcours.add(new Leg(
                        trouverTerminal(terminaux, s.substring(0,1)),
                        trouverTerminal(terminaux, s.substring(1,2)),
                        Integer.parseInt(s.substring(2))
                    ));
                }
                services.add(new Service(
                        Integer.parseInt(valeurs[0]),
                        Integer.parseInt(valeurs[1]),
                        parcours));
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
                String[] valeurs = ligne.split("  ");
                System.out.println("Valeurs Demandes : " + Arrays.toString(valeurs));
                Terminal origine = trouverTerminal(terminaux, valeurs[2]);
                Terminal destination = trouverTerminal(terminaux, valeurs[3]);
                demandes.add(new Demande(
                        Integer.parseInt(valeurs[0]),
                        origine,
                        destination,
                        Integer.parseInt(valeurs[1])));
            }
            return demandes;
        }
    }

    public static List<Terminal> chargerTerminaux(String fichierTerminaux) throws IOException {
        List<Terminal> terminaux = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fichierTerminaux))) {
            String ligne;
            br.readLine(); // Ignorer la première ligne
            while ((ligne = br.readLine()) != null) {
                String[] valeurs = ligne.split("  ");
                terminaux.add(new Terminal(Integer.parseInt(valeurs[0]), valeurs[1]));
            }
        }
        return terminaux;
    }

    private static Terminal trouverTerminal(List<Terminal> terminaux, String name) {
        return terminaux.stream().filter(t -> t.getNom().equals(name)).findFirst().orElse(null);

    }
}
