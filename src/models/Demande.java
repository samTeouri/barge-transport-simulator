package models;

public class Demande {
    private int id;
    private Terminal origine;
    private Terminal destination;
    private int volume;

    public Demande(int id, Terminal origine, Terminal destination, int volume) {
        this.id = id;
        this.origine = origine;
        this.destination = destination;
        this.volume = volume;
    }

    public int getId() { return id; }
    public Terminal getOrigine() { return origine; }
    public Terminal getDestination() { return destination; }
    public int getVolume() { return volume; }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrigine(Terminal origine) {
        this.origine = origine;
    }

    public void setDestination(Terminal destination) {
        this.destination = destination;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
