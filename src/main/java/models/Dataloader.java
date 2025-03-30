package models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Dataloader {
    private List<Barge> barges;
    private List<Deplacement> deplacements;
    private List<Operation> operations;
    private List<Route> routes;
    private List<Service> services;
    private List<Terminal> terminals;

    public Dataloader() {
        this.barges = new ArrayList<>();
        this.deplacements = new ArrayList<>();
        this.operations = new ArrayList<>();
        this.routes = new ArrayList<>();
        this.services = new ArrayList<>();
        this.terminals = new ArrayList<>();
    }

    public void loadFromJson() {
        loadBarges();
        loadDeplacements();
        loadOperations();
        loadRoutes();
        loadServices();
        loadTerminals();
    }

    public void print(){
        for(Service service : services) {
            for (Barge barge : service.getBargesAssignees()) {
                System.out.println("Nom de la barge : " + barge.getNom());
                System.out.println("Capacité de la barge : " + barge.getCapacite());
            }
        }
    }

    private void loadBarges() {
        InputStream inputStream = this.getClass().getResourceAsStream("/barges.json");
        BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        String content = reader.lines().collect(Collectors.joining());
        JSONArray jsonBarges = new JSONArray(content);

        barges = new ArrayList<>();
        for (int i = 0; i < jsonBarges.length(); i++) {
            JSONObject jsonBarge = jsonBarges.getJSONObject(i);
            Barge barge = new Barge();
            barge.loadFromJson(jsonBarge);
            barges.add(barge);
        }

        System.out.println("Chargement des barges : " + barges.size() + " barges chargées");
    }

    private void loadDeplacements() {
        InputStream inputStream = this.getClass().getResourceAsStream("/deplacements.json");

        if (inputStream == null) {
            System.out.println("Fichier deplacements.json introuvable");
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        String content = reader.lines().collect(Collectors.joining());
        JSONArray jsonDeplacements = new JSONArray(content);

        deplacements = new ArrayList<>();
        for (int i = 0; i < jsonDeplacements.length(); i++) {
            JSONObject jsonDeplacement = jsonDeplacements.getJSONObject(i);
            Deplacement deplacement = new Deplacement();
            deplacement.loadFromJson(jsonDeplacement);
            deplacements.add(deplacement);
        }

        System.out.println("Chargement des déplacements : " + deplacements.size() + " déplacements chargés");
    }

    private void loadOperations() {
        InputStream inputStream = this.getClass().getResourceAsStream("/operations.json");

        if (inputStream == null) {
            System.out.println("Fichier operations.json introuvable");
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        String content = reader.lines().collect(Collectors.joining());
        JSONArray jsonOperations = new JSONArray(content);

        operations = new ArrayList<>();
        for (int i = 0; i < jsonOperations.length(); i++) {
            JSONObject jsonOperation = jsonOperations.getJSONObject(i);
            Operation operation = new Operation();
            operation.loadFromJson(jsonOperation);
            operations.add(operation);
        }

        System.out.println("Chargement des opérations : " + operations.size() + " opérations chargées");
    }

    private void loadRoutes() {
        InputStream inputStream = this.getClass().getResourceAsStream("/routes.json");

        if (inputStream == null) {
            System.out.println("Fichier routes.json introuvable");
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        String content = reader.lines().collect(Collectors.joining());
        JSONArray jsonRoutes = new JSONArray(content);

        routes = new ArrayList<>();
        for (int i = 0; i < jsonRoutes.length(); i++) {
            JSONObject jsonRoute = jsonRoutes.getJSONObject(i);
            Route route = new Route();
            route.loadFromJson(jsonRoute);
            routes.add(route);
        }

        System.out.println("Chargement des routes : " + routes.size() + " routes chargées");
    }

    private void loadServices() {
        InputStream inputStream = this.getClass().getResourceAsStream("/services.json");

        if (inputStream == null) {
            System.out.println("Fichier services.json introuvable");
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        String content = reader.lines().collect(Collectors.joining());
        JSONArray jsonServices = new JSONArray(content);

        services = new ArrayList<>();
        for (int i = 0; i < jsonServices.length(); i++) {
            JSONObject jsonService = jsonServices.getJSONObject(i);
            Service service = new Service();
            service.loadFromJson(jsonService);
            services.add(service);
        }

        System.out.println("Chargement des services : " + services.size() + " services chargés");
    }

    private void loadTerminals() {
        InputStream inputStream = this.getClass().getResourceAsStream("/terminals.json");

        if (inputStream == null) {
            System.out.println("Fichier terminals.json introuvable");
            return;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        String content = reader.lines().collect(Collectors.joining());
        JSONArray jsonTerminals = new JSONArray(content);

        terminals = new ArrayList<>();
        for (int i = 0; i < jsonTerminals.length(); i++) {
            JSONObject jsonTerminal = jsonTerminals.getJSONObject(i);
            Terminal terminal = new Terminal();
            terminal.loadFromJson(jsonTerminal);
            terminals.add(terminal);
        }

        System.out.println("Chargement des terminaux : " + terminals.size() + " terminaux chargés");
    }

    // Getters
    public List<Barge> getBarges() {
        return barges;
    }

    public List<Deplacement> getDeplacements() {
        return deplacements;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public List<Service> getServices() {
        return services;
    }

    public List<Terminal> getTerminals() {
        return terminals;
    }
}