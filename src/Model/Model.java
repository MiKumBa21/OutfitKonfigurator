package Model;

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
}
