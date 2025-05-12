import Controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import View.View;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    //View & Controller erstellen
    private View view;
    private Controller controller;

    //Start-Methode: view, ein controller, Methode mit primaryStage & .show()
    @Override
    public void start(Stage primaryStage) {
        view = new View();
        view.start(primaryStage);
        controller = new Controller(view);
        System.out.println("Penis Petra");
        primaryStage.show();

    }
}
