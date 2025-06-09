import Controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import View.View;

/**
 * Hauptklasse zum Starten der JavaFX-Anwendung "Outfit-Konfigurator".
 * Diese Klasse erstellt das View- und Controller-Objekt und zeigt die Benutzeroberfläche an.
 */
public class Main extends Application {

    /**
     * Einstiegspunkt der Anwendung.
     * Ruft die JavaFX-Methode launch() auf, um die Anwendung zu starten.
     */
    public static void main(String[] args) {
        launch(args);
    }

    // Die grafische Benutzeroberfläche (View)
    private View view;

    // Der Controller, der die Logik steuert und View/Model verbindet
    private Controller controller;

    /**
     * Startmethode der JavaFX-Anwendung.
     * Erstellt View und Controller, initialisiert die Oberfläche und zeigt das Hauptfenster an.
     * @param primaryStage Das Hauptfenster der Anwendung
     */
    @Override
    public void start(Stage primaryStage) {
        view = new View(); // Oberfläche erstellen
        controller = new Controller(view, primaryStage); // Controller erstellen und verbinden
        view.start(primaryStage); // GUI initialisieren
        primaryStage.show(); // Fenster anzeigen
    }
}
