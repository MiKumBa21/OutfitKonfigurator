package Model;

import java.util.ArrayList;

public class Model {
    Datenbank datenbank;

    public Model() {
        datenbank = new Datenbank();
    }

    public void test(){
        datenbank.addItem(new Pieces("Bären Shirt", "Schwarz", "Streetware","T-Shirt",new ArrayList<String>(), new ArrayList<String>(), "www.beispiel.at"));

        for (int i = 0; i < datenbank.getPieces().size(); i++) {
            System.out.println(datenbank.getPieces().get(i).toString());
        }
    }
}
