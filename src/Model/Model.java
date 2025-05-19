package Model;

public class Model {
    Datenbank datenbank;

    public Model() {
        datenbank = new Datenbank();
    }

    public void test(){
        datenbank.addItem(new Pieces("Bären Shirt", "Schwarz", "Streetware","T-Shirt","Sonnig", "Sommer", "www.beispiel.at"));

        for (int i = 0; i < datenbank.getPieces().size(); i++) {
            System.out.println(datenbank.getPieces().get(i).toString());
        }
    }
}
