package Controller;

import View.*;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.util.ArrayList;


public class Controller {

    private Model model;
    private View view;
    private Datenbank datenbank;

    public Controller(View view) {
        this.view = view;
        this.model = new Model();
        this.datenbank = new Datenbank();

        startDatenbank();

        loadBar();

        loadTable();

        view.getInventoryButton().setOnAction(event -> {
            view.inventoryScene();
        });

        view.getConfigButton().setOnAction(event -> {
            view.configScene();
        });

        view.getStage().setOnCloseRequest(event -> {
            datenbank.itemsSpeichern();
        });

        view.getAddButton().setOnAction(event -> {
            clearChoise();
            view.addScene();
        });

        view.getBackButton().setOnAction(event -> {
            view.getAddStage().close();
        });

        view.getSaveButton().setOnAction(event -> {
            saveNewPiece();
            view.getAddStage().close();
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

        //Das Bearbeiten der einzelnen Elemente
//        view.getInventoryTable().setOnMouseClicked(event -> {
//            Pieces selectedItem = (Pieces) view.getInventoryTable().getSelectionModel().getSelectedItem();
//        });

        view.getResetButton().setOnAction(event -> {
            clearChoise();
        });

        view.getApplyButton().setOnAction(event -> {
            String style = view.getStyleField().getText();
            String color = view.getColorField().getText();
            ArrayList<String> season = new ArrayList<>();
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

            if (view.getWindyCheckBox().isSelected()) {
                season.add("Winter");
            }
            if (view.getSpringCheckBox().isSelected()) {
                season.add("Frühling");
            }
            if (view.getSummerCheckBox().isSelected()) {
                season.add("Sommer");
            }
            if (view.getAutumnCheckBox().isSelected()) {
                season.add("Herbst");
            }
        });

    }

    public void loadBar() {
        new Thread(() -> {
            for (int i = 0; i <= 1000; i++) {
                final int progress = i;

                javafx.application.Platform.runLater(() -> {
                    view.getPbar().setProgress(progress / 1000.0);
                    view.getPbar().lookup(".bar").setStyle( //lookup überschreibt den Standart Style
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

    public void startDatenbank() {
        datenbank.itemsLaden();
        datenbank.deleteFileContent();
    }

    public void loadTable() {
        ObservableList<Pieces> observableList = FXCollections.observableArrayList(datenbank.getPieces());
        view.getInventoryTable().setItems(observableList);

    }

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
        if (view.getFileChooser().getSelectedFile() != null) {
            imageSource = view.getFileChooser().getSelectedFile().getAbsolutePath();
            System.out.println(view.getFileChooser().getSelectedFile().getAbsolutePath());
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
            season.add("Sommer");
        }
        if (view.getAutumnCheckBox().isSelected()) {
            season.add("Herbst");
        }

        Pieces piece = new Pieces(name, color, style, type, weather, season, imageSource);

        datenbank.addItem(piece);
    }

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
        view.getFileChooser().setSelectedFile(null);
    }
}
