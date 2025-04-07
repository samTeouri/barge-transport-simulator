import java.io.IOException;
import java.util.ArrayList;
import simulation.Simulation;

public class App {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        try {
            simulation.chargerDonnees("data/fichier_services.txt", "data/fichier_demandes.txt", new ArrayList<>());
            // simulation.executerSimulation("output/Resultat_simulation.txt");

            System.out.println("Simulation terminée.");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
