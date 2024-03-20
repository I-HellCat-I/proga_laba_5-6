package CommandExecution;

public record Command(String type, String[] args) {
    public void execute() {
        CommandManager.exec(this);
    }
}
