package View;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class View {

    private Stage stage = new Stage();

    private ProgressBar pbar = new ProgressBar();

    private BorderPane root = new BorderPane();
    private Scene mainScene = new Scene(root, 1000, 800);

    private GridPane startGrid = new GridPane();

    private Button inventoryButton = new Button("Inventory");
    private Button configButton = new Button("Konfigurator");

    private MenuBar menuBar = new MenuBar();

    private GridPane inventoryGrid = new GridPane();

    private Menu seasonsMenu = new Menu("Jahreszeit");
    private MenuItem winterItem = new MenuItem("Winter");
    private MenuItem springItem = new MenuItem("Frühling");
    private MenuItem summerItem = new MenuItem("Sommer");
    private MenuItem autumnItem = new MenuItem("Herbst");

    private Menu weatherMenu = new Menu("Wetter");
    private MenuItem rainItem = new MenuItem("Regen");
    private MenuItem snowItem = new MenuItem("Schnee");
    private MenuItem sunnyItem = new MenuItem("Sonnig");
    private MenuItem cloudyItem = new MenuItem("Bewölkt");

    private Menu stylesMenu = new Menu("Stil");
    private MenuItem streetwearItem = new MenuItem("Streetwear");
    private MenuItem oldMoneyItem = new MenuItem("Old-Money");
    private MenuItem y2kItem = new MenuItem("Y2K");
    private MenuItem grungeItem = new MenuItem("Grunge");
    private MenuItem vintageItem = new MenuItem("Vintage");
    private MenuItem gothicItem = new MenuItem("Gothic");
    private MenuItem downTownItem = new MenuItem("Down Town Style");
    private MenuItem indieItem = new MenuItem("Indie");
    private MenuItem altItem = new MenuItem("Alt");
    private MenuItem TalahoonItem = new MenuItem("Talahoon");
    private MenuItem MetalheadItem = new MenuItem("Metal Head");
    private MenuItem HippieItem = new MenuItem("Hippie");
    private MenuItem CottagecoreItem = new MenuItem("Cottagecore");
    private MenuItem CasualItem = new MenuItem("Casual");

    Button addButton = new Button("Hinzufügen");
    Button delButton = new Button("Löschen");

    private GridPane configGrid = new GridPane();

    public void start(Stage primaryStage) {
        stage = primaryStage;

        stage.setTitle("Outfit-Konfigurator");
        stage.getIcons().add(new Image("file:///C:/Users/matth/Documents/Schule/2024.25/SEW/UE26/Outfit%20Konfigurator/images/icon.png"));
        startScene();
        stage.setScene(mainScene);
        stage.setResizable(false);
        stage.show();
    }

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
    }

    public void inventoryScene() {
        inventoryGrid.getChildren().clear();
        seasonsMenu.getItems().clear();

        Label titel = new Label("Inventar");
        titel.setTextAlignment(TextAlignment.CENTER);
        titel.setFont(Font.font("Calibri-Light", FontWeight.BOLD, 25));
        inventoryGrid.add(titel, 0, 0);

        seasonsMenu.getItems().addAll(winterItem, springItem, summerItem, autumnItem);
        menuBar.getMenus().addAll(seasonsMenu);

        HBox topMenu = new HBox(menuBar);
        inventoryGrid.setAlignment(Pos.CENTER);

        root.setTop(menuBar());
        root.setCenter(inventoryGrid);

        TableView table = makeTable();
        inventoryGrid.add(table, 0, 0);

        // Buttons unter der Tabelle rechts
        HBox buttonBox = new HBox(10); // spacing
        buttonBox.getChildren().addAll(addButton, delButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        buttonBox.setStyle("-fx-padding: 10 20 10 10;");

        BorderPane bottomPane = new BorderPane();
        bottomPane.setRight(buttonBox);
        root.setBottom(bottomPane);
    }

    public TableView makeTable() {
        TableView table = new TableView();
        table.setPrefWidth(800);
        table.setPrefHeight(500);

        TableColumn column1 = new TableColumn("");
        column1.setPrefWidth(800); // volle Breite
        table.getColumns().add(column1);

        // Header-Styling
        table.skinProperty().addListener((obs, oldSkin, newSkin) -> {
            Platform.runLater(() -> {
                Node headerBackground = table.lookup(".column-header-background");
                if (headerBackground != null) {
                    headerBackground.setStyle("-fx-background-color: white;");
                }

                Node headerRow = table.lookup(".column-header");
                if (headerRow != null) {
                    headerRow.setStyle("-fx-background-color: white;");
                }

                Node label = table.lookup(".column-header .label");
                if (label != null) {
                    label.setStyle("-fx-text-fill: black;");
                }
            });
        });

        return table;
    }

    public void configScene() {
        configGrid.getChildren().clear();
        root.setCenter(configGrid);
    }

    public HBox menuBar() {
        HBox hbox = new HBox();
        hbox.getChildren().addAll(inventoryButton, configButton);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(100);
        return hbox;
    }

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
}
