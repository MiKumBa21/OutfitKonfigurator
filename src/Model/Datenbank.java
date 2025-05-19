package Model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Datenbank {

    private Gson gson = new Gson();
    private static ArrayList<Pieces> pieces;
    private File piecesFile = null;

    public Datenbank() {
        pieces = new ArrayList<Pieces>();
        piecesFile = new File("./pieces/pieces.json");
    }

    public void itemsSpeichern() {
        try (FileWriter writer = new FileWriter(piecesFile)) {
            gson.toJson(pieces, writer);
            System.out.println("Liste erfolgreich gespeichert.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void itemsLaden() {
        if (piecesFile.exists() && piecesFile.length() > 0) {
            try (FileReader reader = new FileReader(piecesFile)) {
                Type piecesListeTyp = new TypeToken<ArrayList<Pieces>>() {
                }.getType();
                pieces = gson.fromJson(reader, piecesListeTyp);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addItem(Pieces pieces) {
        Datenbank.pieces.add(pieces);
    }

    public void deleteFileContent() {
        try (FileWriter writer = new FileWriter(piecesFile)) {
            writer.write("");
            System.out.println("Der Inhalt wurde geleert");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Pieces> getPieces() {
        return pieces;
    }
}
