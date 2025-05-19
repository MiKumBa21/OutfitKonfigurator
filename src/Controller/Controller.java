package Controller;

import View.*;
import Model.*;


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

        view.getInventoryButton().setOnAction(event -> {
            view.inventoryScene();
        });

        view.getConfigButton().setOnAction(event -> {
            view.configScene();
        });

        view.getStage().setOnCloseRequest(event -> {
            datenbank.itemsSpeichern();
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

    public void test() {
        datenbank.addItem(new Pieces("Bären Shirt", "Schwarz", "Streetware", "Sommer", "www.beispiel.at"));

        for (int i = 0; i < datenbank.getItems().size(); i++) {
            System.out.println(datenbank.getItems().get(i).toString());
        }
    }

}
