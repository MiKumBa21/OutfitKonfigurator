package Model;

import java.util.ArrayList;

/**
 * Die Model-Klasse dient als zentrale Stelle für die Datenlogik der Anwendung.
 * Sie enthält eine Referenz auf die {@link Datenbank}, die für das Laden und Speichern der Kleidungsstücke zuständig ist.
 */
public class Model {
    Datenbank datenbank;

    /**
     * Konstruktor der Model-Klasse.
     * Initialisiert die Datenbank.
     */
    public Model() {
        datenbank = new Datenbank();
    }

    public ArrayList<Pieces> suchAlg(String style, String color, ArrayList<String> seasons, ArrayList<String> weather) {
        ArrayList<Pieces> temp = new ArrayList<Pieces>();

        for (int i = 0; i < datenbank.getPieces().size(); i++) {
            Pieces piece = datenbank.getPieces().get(i);

            if (piece.getStyle().equals(style)) {
                temp.add(piece);
            }

        }

        for (int i = temp.size() - 1; i >= 0; i--) {
            if (!temp.get(i).getColor().equals(color)) {
                temp.remove(i);
            }
        }

        for (int i = temp.size() - 1; i >= 0; i--) {
            boolean oneSeasonsMatch = false;
            for (int j = 0; j < seasons.size(); j++) {
                if (temp.get(i).getSeason().contains(seasons.get(j))) {
                    oneSeasonsMatch = true;
                    j = seasons.size();
                }
            }
            if (!oneSeasonsMatch) {
                temp.remove(i);
            }
        }

        for (int i = temp.size() - 1; i >= 0; i--) {
            boolean oneWeatherMatch = false;
            for (int j = 0; j < weather.size(); j++) {
                if (temp.get(i).getWeather().contains(weather.get(j))) {
                    oneWeatherMatch = true;
                    j = weather.size();
                }
            }
            if (!oneWeatherMatch) {
                temp.remove(i);
            }
        }

        return temp;
    }
}
