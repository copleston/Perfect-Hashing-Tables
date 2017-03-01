// Tuple class to store the operation, operator pairs

public class Command<C, V> {
    public final C com;
    public final V var;

    public Command(C com, V var) {
        this.com = com;
        this.var = var;
    }
}
