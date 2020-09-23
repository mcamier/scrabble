package scrabble.ui.view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class GameView extends View {

    private BorderPane borderPane;
    private VBox leftPane;
    private HBox bottomPane;
    GridPane board;
    Scene scene;

    public GameView() {
        borderPane = new BorderPane();

        leftPane = new VBox();
        VBox moveLogPane = new VBox();
        Label logLabel = new Label("Move history");
        ListView<String> logListView = new ListView<String>();
        moveLogPane.getChildren().addAll(logLabel, logListView);
        leftPane.getChildren().addAll(moveLogPane);

        bottomPane = new HBox();
        VBox gameButtons = new VBox();
        Button validateMove = new Button("Validate move");
        Button resetMove = new Button("Reset move");
        Button flushRack = new Button("Flush rack");
        gameButtons.getChildren().addAll(validateMove, resetMove, flushRack);
        bottomPane.getChildren().addAll(gameButtons /*, rack*/);

        board = new GridPane();
        for (int y = 0; y < 15; y++) {
            for (int x = 0; x < 15; x++) {
                Button button = new Button("" + x + y);
                button.setMaxSize(50, 50);
                button.setMinSize(40, 40);
                board.add(button, x, y, 1,1 );
            }
        }

        borderPane.setCenter(board);
        borderPane.setBottom(bottomPane);
        borderPane.setLeft(leftPane);

        scene = new Scene(borderPane, 1024, 800);
        setScene(scene);
    }

}
