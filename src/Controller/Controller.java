package Controller;

import View.*;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Der Controller verwaltet die Kommunikation zwischen View und Model.
 * Er behandelt alle Benutzeraktionen und steuert den Ablauf der Anwendung.
 */
public class Controller {

    private Model model;
    private View view;
    private Datenbank datenbank;

    /**
     * Konstruktor des Controllers.
     * Initialisiert View, Model und Datenbank, setzt Event-Handler und lädt die GUI.
     *
     * @param view  Die Benutzeroberfläche.
     * @param stage Die JavaFX-Stage (Fenster), das gesteuert wird.
     */
    public Controller(View view, Stage stage) {
        this.view = view;
        this.model = new Model();
        this.datenbank = new Datenbank();

        //Für Alertbox und speichern von Daten
        stage.setOnCloseRequest(event -> {
            event.consume();
            view.closeAlert(stage);
            datenbank.itemsSpeichern();
        });

        startDatenbank();
        loadBar();
        loadTable();

        view.getInventoryButton().setOnAction(event -> {
            view.inventoryScene();
        });

        view.getConfigButton().setOnAction(event -> {
            view.configScene();
        });

        view.getAddButton().setOnAction(event -> {
            clearChoise();
            view.addScene();
        });

        view.getBackButton().setOnAction(event -> {
            view.getAddStage().close();
            clearChoise();
        });

        view.getSaveButton().setOnAction(event -> {
            saveNewPiece();
            view.getAddStage().close();
            clearChoise();
            loadTable();
        });

        view.getDelButton().setOnAction(event -> {
            Pieces selectedItem = (Pieces) view.getInventoryTable().getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                view.getInventoryTable().getItems().remove(selectedItem);
                datenbank.getPieces().remove(selectedItem);
            }
        });

        view.getImageButton().setOnAction(event -> {
            view.imageSelction();
        });

        //Das Bearbeiten der einzelnen Elemente war eine Idee, wurde aqbernicht umgesetzt
//        view.getInventoryTable().setOnMouseClicked(event -> {
//            Pieces selectedItem = (Pieces) view.getInventoryTable().getSelectionModel().getSelectedItem();
//        });

        view.getResetButton().setOnAction(event -> {
            clearChoise();
        });

        //Es ist uns bewusst, dass der Suchalgorithmus nicht ganz funktioniert
        view.getApplyButton().setOnAction(event -> {
            System.out.println("Apply");
            String style = view.getStyleField().getText();
            String color = view.getColorField().getText();
            ArrayList<String> seasons = new ArrayList<>();
            ArrayList<String> weather = new ArrayList<>();

            if (view.getSunnyCheckBox().isSelected()) {
                weather.add("Sonnig");
            }
            if (view.getRainCheckBox().isSelected()) {
                weather.add("Regen");
            }
            if (view.getSnowyCheckBox().isSelected()) {
                weather.add("Schnee");
            }
            if (view.getWindyCheckBox().isSelected()) {
                weather.add("Windig");
            }

            if (view.getWinterCheckBox().isSelected()) {
                seasons.add("Winter");
            }
            if (view.getSpringCheckBox().isSelected()) {
                seasons.add("Frühling");
            }
            if (view.getSummerCheckBox().isSelected()) {
                seasons.add("Sommer");
            }
            if (view.getAutumnCheckBox().isSelected()) {
                seasons.add("Herbst");
            }
            ArrayList<Pieces> configOutfit = model.suchAlg(style, color, seasons, weather);

            view.getShoesImage().setImage(null);
            view.getBottomImage().setImage(null);
            view.getTopImage().setImage(null);
            view.getHeadImage().setImage(null);
            view.getAccessoriesImage().setImage(null);

            for (int i = 0; i < configOutfit.size(); i++) {
                if (configOutfit.get(i).getType().equals("Schuhe")) {
                    view.getShoesImage().setImage(new Image("File:" + configOutfit.get(i).getImageSource()));
                }
                if (configOutfit.get(i).getType().equals("Unterteil")) {
                    view.getBottomImage().setImage(new Image("File:" + configOutfit.get(i).getImageSource()));
                }
                if (configOutfit.get(i).getType().equals("Oberteil")) {
                    view.getTopImage().setImage(new Image("File:" + configOutfit.get(i).getImageSource()));
                }
                if (configOutfit.get(i).getType().equals("Kopfbedekung")) {
                    view.getHeadImage().setImage(new Image(configOutfit.get(i).getImageSource()));
                }
                if (configOutfit.get(i).getType().equals("Accessories")) {
                    view.getAccessoriesImage().setImage(new Image("File:" + configOutfit.get(i).getImageSource()));

                }
            }
        });

    }

    /**
     * Simuliert einen Ladebalken mit Verlauf und wechselt nach Abschluss zur Inventarseite.
     */
    public void loadBar() {
        new Thread(() -> {
            for (int i = 0; i <= 1000; i++) {
                final int progress = i;

                javafx.application.Platform.runLater(() -> {
                    view.getPbar().setProgress(progress / 1000.0);
                    view.getPbar().lookup(".bar").setStyle(
                            "-fx-background-color: linear-gradient(to right, hotpink, #88ccff);"
                    );
                    if (progress == 1000) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            System.out.println("Failed");
                        }
                        view.inventoryScene();
                    }
                });

                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("Failed");
                }
            }
        }).start();
    }

    /**
     * Startet die Datenbank, lädt gespeicherte Items und leert temporäre Dateien.
     */
    public void startDatenbank() {
        datenbank.itemsLaden();
        datenbank.deleteFileContent();
    }

    /**
     * Lädt alle Items aus der Datenbank in die Tabelle der View.
     */
    public void loadTable() {
        ObservableList<Pieces> observableList = FXCollections.observableArrayList(datenbank.getPieces());
        view.getInventoryTable().setItems(observableList);
    }

    /**
     * Speichert ein neu erstelltes Kleidungsstück in die Datenbank.
     * Liest alle Eingaben aus der View aus.
     */
    public void saveNewPiece() {
        String name = view.getNameField().getText();
        String color = view.getColorField().getText();
        String style = view.getStyleField().getText();
        String type;
        if (view.getTypeChoice().getValue() != null) {
            type = view.getTypeChoice().getValue().toString();
        } else {
            type = "";
        }

        String imageSource;

        ArrayList<String> weather = new ArrayList<>();
        ArrayList<String> season = new ArrayList<>();
        if (view.getLastFile() != null) {
            imageSource = view.getLastFile();
            System.out.println(view.getLastFile());
        } else {
            imageSource = "";
        }

        if (view.getSunnyCheckBox().isSelected()) {
            weather.add("Sonnig");
        }
        if (view.getRainCheckBox().isSelected()) {
            weather.add("Regen");
        }
        if (view.getSnowyCheckBox().isSelected()) {
            weather.add("Schnee");
        }
        if (view.getWindyCheckBox().isSelected()) {
            weather.add("Windig");
        }

        if (view.getWindyCheckBox().isSelected()) {
            season.add("Winter");
        }
        if (view.getSpringCheckBox().isSelected()) {
            season.add("Frühling");
        }
        if (view.getSummerCheckBox().isSelected()) {
            season.add("Sommer ");
        }
        if (view.getAutumnCheckBox().isSelected()) {
            season.add("Herbst");
        }

        Pieces piece = new Pieces(name, color, style, type, weather, season, imageSource);
        datenbank.addItem(piece);
    }

    /**
     * Setzt alle Eingabefelder und Auswahlboxen im Add-Dialog zurück.
     */
    public void clearChoise() {
        view.getNameField().clear();
        view.getColorField().clear();
        view.getStyleField().clear();
        view.getTypeChoice().getItems().clear();
        view.getWinterCheckBox().setSelected(false);
        view.getSpringCheckBox().setSelected(false);
        view.getSummerCheckBox().setSelected(false);
        view.getAutumnCheckBox().setSelected(false);
        view.getRainCheckBox().setSelected(false);
        view.getSunnyCheckBox().setSelected(false);
        view.getWindyCheckBox().setSelected(false);
        view.getSnowyCheckBox().setSelected(false);
        view.setLastFile("");
    }
}
