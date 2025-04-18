# Barge Transport Simulator

Une simulation de transport de marchandises par barge qui gère les demandes de transport entre différents terminaux.

## Structure du Projet

```
src/
├── main/
│   ├── App.java      # Point d'entrée de l'application
├── models/           # Classes de modélisation
│   ├── Terminal.java # Représente un terminal de transport
│   ├── Service.java  # Représente un service de transport
│   ├── Demande.java  # Représente une demande de transport
│   └── Leg.java      # Représente une étape du parcours
├── services/         # Services utilitaires
│   └── DataLoaderService.java # Service de chargement des données
└── simulation/       # Logique de la simulation
    └── Simulation.java # Classe principale de la simulation
```

## Fonctionnalités

- Gestion des terminaux de transport
- Gestion des services de transport avec capacités limitées
- Gestion des demandes de transport
- Calcul automatique des itinéraires
- Affichage du calendrier détaillé pour chaque demande
- Génération de statistiques de simulation

## Démarrage

1. Compiler le projet :
```bash
javac -d bin src/**/*.java
```

2. Exécuter la simulation :
```bash
java -cp bin main.App
```

Les résultats seront stockés dans le dossier `output/` :
- `Resultat_simulation.txt` : Résultats détaillés de l'affectation des demandes
- `stats.txt` : Statistiques de la simulation

## Format des fichiers de données

### Fichier des terminaux (`data/fichier_terminaux.txt`)
```
ID  NOM
1   A
2   B
3   C
4   D
```

### Fichier des services (`data/fichier_services.txt`)
```
ID_SERVICE  CAPACITE  DATE_DEPART  DATE_ARRIVEE  ORIGINE  DESTINATION  ROUTE
1           830       5            7             A        C            AB6;BC7
```

### Fichier des demandes (`data/fichier_demandes.txt`)
```
ID  VOLUME  ORIGINE  DESTINATION  DATE_DEBUT  DATE_FIN
1   100     A        D            0           1
```

## TODO - Améliorations possibles

- [ ] Implémenter une interface graphique pour visualiser les résultats
- [ ] Ajouter la gestion des priorités pour les demandes
- [ ] Ajouter des contraintes de temps pour les services
- [ ] Implémenter des algorithmes d'optimisation pour l'affectation des demandes
- [ ] Ajouter la gestion des coûts de transport
- [ ] Implémenter un système de reporting plus détaillé
- [ ] Ajouter des tests unitaires pour chaque composant
- [ ] Implémenter la gestion des retards et des imprévus

## Technologies Utilisées

- Java 17+
- JDK 23.0.2 Oracle
- VS Code avec extension Java Development Kit

## Auteurs

- Sam Teouri ([@samTeouri](https://github.com/samTeouri))
- Shahzad Abdulrahman ([@QuickoAbdul](https://github.com/QuickoAbdul))

## Licence

Ce projet est sous licence MIT - voir le fichier LICENSE pour plus de détails.
