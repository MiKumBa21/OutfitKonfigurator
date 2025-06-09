package View;

import Model.Pieces;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Diese Klasse stellt die Benutzeroberfläche für den Outfit-Konfigurator dar.
 * Sie zeigt verschiedene Szenen wie Start, Inventar und Konfigurator an.
 */
public class View {

    private Stage stage = new Stage();
    private ProgressBar pbar = new ProgressBar();
    private BorderPane root = new BorderPane();
    private Scene mainScene = new Scene(root, 1250, 900);

    private GridPane startGrid = new GridPane();

    private Button inventoryButton = new Button("Inventar");
    private Button configButton = new Button("Konfigurator");

    private MenuBar menuBar = new MenuBar();
    private GridPane inventoryGrid = new GridPane();

    private TableView<Pieces> inventoryTable = makeTable();

    private Button addButton = new Button("Hinzufügen");
    private Button delButton = new Button("Löschen");

    private static JFileChooser fileChooser = new JFileChooser();

    private Stage addStage = new Stage();
    private GridPane addGrid = new GridPane();
    private Scene addScene = new Scene(addGrid, 500, 500);

    private TextField nameField = new TextField();
    private TextField colorField = new TextField();
    private TextField styleField = new TextField();

    private HBox seasonsBox = new HBox();
    private CheckBox winterCheckBox = new CheckBox("⛄ Winter");
    private CheckBox springCheckBox = new CheckBox("🌷 Frühling");
    private CheckBox summerCheckBox = new CheckBox("🌞 Sommer");
    private CheckBox autumnCheckBox = new CheckBox("🍂 Herbst");

    private ChoiceBox<String> typeChoice = new ChoiceBox<>();

    private HBox weatherBox = new HBox();
    private CheckBox sunnyCheckBox = new CheckBox("☀ Sonnig");
    private CheckBox rainCheckBox = new CheckBox("🌧 Regen");
    private CheckBox snowyCheckBox = new CheckBox("❆ Schnee");
    private CheckBox windyCheckBox = new CheckBox("༄ Windig");

    private Button imageButton = new Button("Bild hinzufügen");
    private Button saveButton = new Button("Speichern");
    private Button backButton = new Button("Zurück");

    private GridPane configGrid = new GridPane();

    /**
     * Startet die Anwendung mit dem Startfenster.
     */
    public void start(Stage primaryStage) {
        stage = primaryStage;
        stage.setTitle("Outfit-Konfigurator");
        stage.getIcons().add(new Image("file:./images/icon.png"));
        mainScene.getStylesheets().add(getClass().getResource("/View/style.css").toExternalForm());
        startScene();
        stage.setScene(mainScene);
        stage.setResizable(false);
        stage.show();
        addStage.initOwner(stage);
    }

    /**
     * Zeigt ein Bestätigungsfenster zum Schließen der App.
     */
    public void closeAlert(Stage stage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Möchtest du wirklich das Programm schließen");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage.close();
        }
    }

    /**
     * Baut die Startszene mit Titel und Fortschrittsbalken auf.
     */
    public void startScene() {
        Label titel = new Label("Outfit Konfigurator");
        pbar.setProgress(0.0);
        pbar.setPrefWidth(450);
        pbar.setPrefHeight(10);
        titel.setTextAlignment(TextAlignment.CENTER);
        titel.setFont(Font.font("Calibri-Light", FontWeight.BOLD, 50));
        startGrid.add(titel, 0, 0);
        startGrid.add(pbar, 0, 1);
        startGrid.setAlignment(Pos.CENTER);
        root.setCenter(startGrid);

        inventoryButton.setOnAction(e -> {
            inventoryScene();
            inventoryButton.getStyleClass().add("active");
            configButton.getStyleClass().remove("active");
        });

        configButton.setOnAction(e -> {
            configScene();
            configButton.getStyleClass().add("active");
            inventoryButton.getStyleClass().remove("active");
        });
    }

    /**
     * Zeigt die Inventar-Szene mit der Tabelle der Kleidungsstücke.
     */
    public void inventoryScene() {
        inventoryButton.getStyleClass().add("active");
        configButton.getStyleClass().remove("active");

        inventoryGrid.getChildren().clear();
        seasonsBox.getChildren().clear();

        Label titel = new Label("Inventar");
        titel.setFont(Font.font("Calibri-Light", FontWeight.BOLD, 25));
        inventoryGrid.add(titel, 0, 0);

        inventoryGrid.setAlignment(Pos.CENTER);
        root.setCenter(inventoryGrid);
        root.setTop(menuBar());

        inventoryGrid.add(inventoryTable, 0, 0);

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(addButton, delButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.setStyle("-fx-padding: 10 20 10 10;");

        BorderPane bottomPane = new BorderPane();
        bottomPane.setRight(buttonBox);
        root.setBottom(bottomPane);
    }

    /**
     * Zeigt die Szene zum Hinzufügen eines neuen Kleidungsstücks.
     */
    public void addScene() {
        addGrid.getChildren().clear();
        seasonsBox.getChildren().clear();
        weatherBox.getChildren().clear();
        addScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        addStage.setTitle("Kleidungsstück hinzufügen");
        addGrid.setAlignment(Pos.CENTER);
        addGrid.setHgap(10);
        addGrid.setVgap(10);
        addStage.getIcons().add(new Image("file:./images/icon.png"));

        Label addLabel = new Label("Kleidungsstück hinzufügen");
        addLabel.setId("addSceneLabel");
        addLabel.setFont(Font.font("Calibri-Light", FontWeight.BOLD, 25));
        addGrid.add(addLabel, 0, 0);

        GridPane inputGrid = new GridPane();
        inputGrid.setAlignment(Pos.CENTER);
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);

        inputGrid.add(new Label("Name: "), 0, 0);
        inputGrid.add(nameField, 2, 0);

        inputGrid.add(new Label("Farbe: "), 0, 2);
        inputGrid.add(colorField, 2, 2);

        inputGrid.add(new Label("Stil: "), 0, 4);
        inputGrid.add(styleField, 2, 4);

        inputGrid.add(new Label("Type: "), 0, 6);
        typeChoice.getItems().addAll("Schuhe", "Unterteil", "Oberteil", "Kopfbedekung", "Accessories");
        inputGrid.add(typeChoice, 2, 6);

        inputGrid.add(new Label("Jahreszeit: "), 0, 10);
        seasonsBox.getChildren().addAll(winterCheckBox, springCheckBox, summerCheckBox, autumnCheckBox);
        inputGrid.add(seasonsBox, 2, 10);

        inputGrid.add(new Label("Wetter: "), 0, 12);
        weatherBox.getChildren().addAll(sunnyCheckBox, rainCheckBox, snowyCheckBox, windyCheckBox);
        inputGrid.add(weatherBox, 2, 12);

        inputGrid.add(imageButton, 0, 16, 3, 1);
        addGrid.add(inputGrid, 0, 3);

        HBox buttonBox2 = new HBox();
        buttonBox2.setAlignment(Pos.CENTER);
        buttonBox2.setSpacing(10);
        buttonBox2.getChildren().addAll(saveButton, backButton);

        addGrid.add(buttonBox2, 0, 6);
        addStage.setScene(addScene);
        addStage.show();
    }

    /**
     * Erstellt die Tabelle zur Anzeige der Kleidungsstücke.
     *
     * @return TableView mit allen Spalten
     */
    public TableView<Pieces> makeTable() {
        TableView<Pieces> table = new TableView<>();
        table.setPrefWidth(1050);
        table.setPrefHeight(700);

        TableColumn<Pieces, ImageView> column1 = new TableColumn<>("Bild");
        column1.setCellValueFactory(new PropertyValueFactory<>("imageView"));

        TableColumn<Pieces, String> column2 = new TableColumn<>("Name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Pieces, String> column3 = new TableColumn<>("Farbe");
        column3.setCellValueFactory(new PropertyValueFactory<>("color"));

        TableColumn<Pieces, String> column4 = new TableColumn<>("Stil");
        column4.setCellValueFactory(new PropertyValueFactory<>("style"));

        TableColumn<Pieces, String> column5 = new TableColumn<>("Type");
        column5.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Pieces, String> column6 = new TableColumn<>("Jahreszeit");
        column6.setCellValueFactory(data -> {
            ArrayList<String> list = data.getValue().getSeason();
            return new ReadOnlyStringWrapper(String.join(", ", list));
        });

        TableColumn<Pieces, String> column7 = new TableColumn<>("Wetter");
        column7.setCellValueFactory(data -> {
            ArrayList<String> list = data.getValue().getWeather();
            return new ReadOnlyStringWrapper(String.join(", ", list));
        });

        table.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7);
        table.setFixedCellSize(100);
        table.setPlaceholder(new Label("Keine Kleidungsstücke in deiner Liste"));
        return table;
    }

    /**
     * Zeigt die Konfigurator-Szene, um Outfits zu filtern.
     */
    public void configScene() {
        configButton.getStyleClass().add("active");
        inventoryButton.getStyleClass().remove("active");

        configGrid.getChildren().clear();
        seasonsBox.getChildren().clear();
        weatherBox.getChildren().clear();
        typeChoice.getItems().clear();

        configGrid.setAlignment(Pos.TOP_CENTER);
        configGrid.setHgap(20);
        configGrid.setVgap(20);
        configGrid.setStyle("-fx-padding: 40;");

        Label configLabel = new Label("Outfit-Konfigurator");
        configLabel.setFont(Font.font("Calibri-Light", FontWeight.BOLD, 30));
        configGrid.add(configLabel, 0, 0, 2, 1);

        configGrid.add(new Label("Style:"), 0, 1);
        configGrid.add(styleField, 1, 1);

        configGrid.add(new Label("Farbe:"), 0, 2);
        configGrid.add(colorField, 1, 2);

        configGrid.add(new Label("Jahreszeit:"), 0, 3);
        seasonsBox.getChildren().addAll(winterCheckBox, springCheckBox, summerCheckBox, autumnCheckBox);
        configGrid.add(seasonsBox, 1, 3);

        configGrid.add(new Label("Wetter:"), 0, 4);
        weatherBox.getChildren().addAll(sunnyCheckBox, rainCheckBox, snowyCheckBox, windyCheckBox);
        configGrid.add(weatherBox, 1, 4);

        Button applyButton = new Button("Anwenden");
        Button resetButton = new Button("Zurücksetzen");

        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(applyButton, resetButton);
        configGrid.add(buttonBox, 0, 5, 2, 1);

        root.setBottom(null);
        root.setCenter(configGrid);
    }

    /**
     * Menüleiste oben mit Inventar- und Konfigurator-Button.
     */
    public HBox menuBar() {
        HBox hbox = new HBox();
        hbox.getChildren().add(inventoryButton);
        hbox.getChildren().add(configButton);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(100);
        return hbox;
    }

    /**
     * Öffnet einen Dateiauswahldialog zum Hochladen von Bildern.
     */
    public void imageSelction() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            fileChooser.setDialogTitle("Bild auswählen");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                    "Bilddateien", "jpg", "jpeg", "png", "bmp", "gif"));

            JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);
            fileChooser.showOpenDialog(dialog);
            dialog.dispose();
        });
    }

    // Getter-Methoden für Zugriff von außen
    public Button getInventoryButton() {
        return inventoryButton;
    }

    public Button getConfigButton() {
        return configButton;
    }

    public ProgressBar getPbar() {
        return pbar;
    }

    public Stage getStage() {
        return stage;
    }

    public Button getAddButton() {
        return addButton;
    }

    public Button getDelButton() {
        return delButton;
    }

    public Button getBackButton() {
        return backButton;
    }

    public Stage getAddStage() {
        return addStage;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Button getImageButton() {
        return imageButton;
    }

    public CheckBox getWindyCheckBox() {
        return windyCheckBox;
    }

    public CheckBox getSnowyCheckBox() {
        return snowyCheckBox;
    }

    public CheckBox getRainCheckBox() {
        return rainCheckBox;
    }

    public CheckBox getSunnyCheckBox() {
        return sunnyCheckBox;
    }

    public HBox getWeatherBox() {
        return weatherBox;
    }

    public ChoiceBox getTypeChoice() {
        return typeChoice;
    }

    public CheckBox getAutumnCheckBox() {
        return autumnCheckBox;
    }

    public CheckBox getSummerCheckBox() {
        return summerCheckBox;
    }

    public CheckBox getSpringCheckBox() {
        return springCheckBox;
    }

    public CheckBox getWinterCheckBox() {
        return winterCheckBox;
    }

    public TextField getStyleField() {
        return styleField;
    }

    public TextField getColorField() {
        return colorField;
    }

    public TextField getNameField() {
        return nameField;
    }

    public TableView getInventoryTable() {
        return inventoryTable;
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }
}
