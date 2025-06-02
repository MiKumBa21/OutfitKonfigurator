package View;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class View {

    private Stage stage = new Stage();


    private ProgressBar pbar = new ProgressBar();

    private BorderPane root = new BorderPane();
    private Scene mainScene = new Scene(root, 700, 500);

    private GridPane startGrid = new GridPane();

    private Button inventoryButton = new Button("Inventory");
    private Button configButton = new Button("Konfigurator");

    private MenuBar menuBar = new MenuBar();

    private GridPane inventoryGrid = new GridPane();

    private Button addButton = new Button("Hinzufügen");
    private Button delButton = new Button("Löschen");

//    private Menu stylesMenu = new Menu("Stil");
//    private MenuItem streetwearItem = new MenuItem("Streetwear");
//    private MenuItem oldMoneyItem = new MenuItem("Old-Money");
//    private MenuItem y2kItem = new MenuItem("Y2K");
//    private MenuItem grungeItem = new MenuItem("Grunge");
//    private MenuItem vintageItem = new MenuItem("Vintage");
//    private MenuItem gothicItem = new MenuItem("Gothic");
//    private MenuItem downTownItem = new MenuItem("Down Town Style");
//    private MenuItem indieItem = new MenuItem("Indie");
//    private MenuItem altItem = new MenuItem("Alt");
//    private MenuItem TalahoonItem = new MenuItem("Talahoon");
//    private MenuItem MetalheadItem = new MenuItem("Metal Head");
//    private MenuItem HippieItem = new MenuItem("Hippie");
//    private MenuItem CottagecoreItem = new MenuItem("Cottagecore");
//    private MenuItem CasualItem = new MenuItem("Casual");

    private Stage addStage = new Stage();
    private GridPane addGrid = new GridPane();
    private Scene addScene = new Scene(addGrid,500,500);
    private TextField nameField = new TextField();
    private TextField colorField = new TextField();
    private TextField styleField = new TextField();
    private HBox seasonsBox = new HBox();
    private CheckBox winterCheckBox = new CheckBox("Winter");
    private CheckBox springCheckBox = new CheckBox("Frühling");
    private CheckBox summerCheckBox = new CheckBox("Sommer");
    private CheckBox autumnCheckBox = new CheckBox("Herbst");
    private ChoiceBox typeChoice = new ChoiceBox();
    private HBox weatherBox = new HBox();
    private CheckBox sunnyCheckBox = new CheckBox("Sonnig");
    private CheckBox rainCheckBox = new CheckBox("Regen");
    private CheckBox snowyCheckBox = new CheckBox("Schnee");
    private CheckBox windyCheckBox = new CheckBox("Windig");
    private Button imageButton = new Button("Bild hinzufügen");
    private Button saveButton = new Button("Speichern");
    private  Button backButton = new Button("Zurück");


    private GridPane configGrid = new GridPane();

    public void start(Stage primaryStage) {
        stage = primaryStage;

        stage.setTitle("Outfit-Konfigurator");
        stage.getIcons().add(new Image("file:./images/icon.png"));
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
        //root.setTop(menuBar());
        root.setCenter(startGrid);


    }

    public void inventoryScene() {
        inventoryGrid.getChildren().clear();
        seasonsBox.getChildren().clear();

        Label titel = new Label("Inventar");
        titel.setTextAlignment(TextAlignment.CENTER);
        titel.setFont(Font.font("Calibri-Light", FontWeight.BOLD, 25));
        inventoryGrid.add(titel, 0, 0);

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

    public void addScrene(){
        addGrid.getChildren().clear();

        addStage.setTitle("Kleidungsstück hinzufügen");
        addGrid.setAlignment(Pos.CENTER);
        addGrid.setHgap(10);
        addGrid.setVgap(10);
        addStage.getIcons().add(new Image("file:./images/icon.png"));

        Label addLabel = new Label("Kleidungsstück hinzufügen");
        addLabel.setTextAlignment(TextAlignment.CENTER);
        addLabel.setAlignment(Pos.CENTER);
        addLabel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        addLabel.setFont(Font.font("Calibri-Light", FontWeight.BOLD, 25));
        addGrid.add(addLabel, 0, 0);

        GridPane inputGrid = new GridPane();
        inputGrid.setAlignment(Pos.CENTER);
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);

        Label nameLabel = new Label("Name: ");
        inputGrid.add(nameLabel, 0, 0);
        inputGrid.add(nameField, 2, 0);

        Label colorLabel = new Label("Farbe: ");
        inputGrid.add(colorLabel, 0, 2);
        inputGrid.add(colorField, 2, 2);

        Label styleLabel = new Label("Stil: ");
        inputGrid.add(styleLabel, 0, 4);
        inputGrid.add(styleField, 2, 4);

        Label typeLabel = new Label("Type: ");
        typeChoice.getItems().addAll("Schuhe","Unterteil", "Oberteil","Kopfbedekung","Accessories");
        inputGrid.add(typeLabel, 0, 6);
        inputGrid.add(typeChoice, 2, 6);

        Label seasonLabel = new Label("Jahreszeit: ");
        inputGrid.add(seasonLabel, 0, 10);
        seasonsBox.getChildren().addAll(winterCheckBox, springCheckBox, summerCheckBox, autumnCheckBox);
        seasonsBox.setSpacing(5);
        inputGrid.add(seasonsBox, 2, 10);

        Label weatherLabel = new Label("Wetter: ");
        inputGrid.add(weatherLabel, 0, 12);
        weatherBox.getChildren().addAll(sunnyCheckBox, rainCheckBox, snowyCheckBox, windyCheckBox);
        weatherBox.setSpacing(5);
        inputGrid.add(weatherBox, 2, 12);

        inputGrid.add(imageButton, 0, 16,3,1);
        imageButton.setAlignment(Pos.CENTER);
        imageButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        addGrid.add(inputGrid, 0, 3);

        HBox buttonBox2 = new HBox();
        buttonBox2.setAlignment(Pos.CENTER);
        buttonBox2.setSpacing(10);
        buttonBox2.getChildren().addAll(saveButton, backButton);

        addGrid.add(buttonBox2, 0, 6);

        addStage.setScene(addScene);
        addStage.initOwner(stage);
        addStage.show();
    }

    public TableView makeTable() {
        TableView table = new TableView();
        table.setPrefWidth(650);
        table.setPrefHeight(400);

        TableColumn column1 = new TableColumn("Bild");
        column1.setMinWidth(108.33333333333333333333333333333);
        TableColumn column2 = new TableColumn("Name");
        column2.setMinWidth(108.33333333333333333333333333333);
        TableColumn column3 = new TableColumn("Farbe");
        column3.setMinWidth(108.33333333333333333333333333333);
        TableColumn column4 = new TableColumn("Stil");
        column4.setMinWidth(108.33333333333333333333333333333);
        TableColumn column5 = new TableColumn("Jahreszeit");
        column5.setMinWidth(108.33333333333333333333333333333);
        TableColumn column6 = new TableColumn("Wetter");
        column6.setMinWidth(108.33333333333333333333333333333);


        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.getColumns().add(column3);
        table.getColumns().add(column4);
        table.getColumns().add(column5);
        table.getColumns().add(column6);



        return table;
    }

    public void configScene() {
        configGrid.getChildren().clear();

        root.setBottom(null);
        root.setCenter(configGrid);
    }

    public HBox menuBar() {

        HBox hbox = new HBox();

        hbox.getChildren().add(inventoryButton);
        hbox.getChildren().add(configButton);
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
}