package init;


public class Initialize {

    public static void main(String[] args) {
        try {
            InitializeWorker.initializePostgresDatabase("bank", "postgres");
        } catch (Exception e) {
            throw new RuntimeException("685BFvOL3B :: could not initialized ", e);
        }
    }
}
