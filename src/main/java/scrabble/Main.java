package scrabble;

import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.Stage;
import scrabble.ui.controller.GameController;
import scrabble.ui.controller.GameSetupController;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    Stage window;

    GameSetupController controller;
    GameController gameController;

    @Override
    public void start(Stage stage) throws Exception {

        window = stage;
        window.setTitle("Scrabble");

        gameController = new GameController();
        gameController.setOnGameStart(scene -> window.setScene(scene));

        controller = new GameSetupController(gameController);
        window.setScene(controller.getView().getScene());
        window.show();
    }


}
