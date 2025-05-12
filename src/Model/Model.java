package Model;

public class Model {
    Datenbank datenbank;

    public Model() {
        datenbank = new Datenbank();
    }

    public void test(){
        datenbank.addItem(new Pieces("Bären Shirt", "Schwarz", "Streetware", "Sommer", "www.beispiel.at"));

        for (int i = 0; i < datenbank.getItems().size(); i++) {
            System.out.println(datenbank.getItems().get(i).toString());
        }
    }
}
