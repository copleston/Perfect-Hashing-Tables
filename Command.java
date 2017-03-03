// Tuple class to store the operation, operator pairs

public class Command {
    public final String com;
    public final Integer var;

    public Command(String com, Integer var) {
        this.com = com;
        this.var = var;
    }

    public String getCom() {
        return com;
    }

    public Integer getVar() {
        return var;
    }

    public void execute() {
        System.out.format("PERFORM OPERATION: %s %d\n", com, var);
    }
}
