package models;

public class Operation {
    private Terminal terminal;
    private String type;

    public Operation(Terminal terminal, String type) {
        this.terminal = terminal;
        this.type = type;
    }

    public Terminal getTerminal() { return terminal; }
    public String getType() { return type; }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public void setType(String type) {
        this.type = type;
    }
}
