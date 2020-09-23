package scrabble.ui.view;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import scrabble.dictionnary.Languages;

import java.io.InputStream;
import java.util.function.Consumer;

public class GameSetupView extends View {
    private ToggleGroup toggleGroupLang;
    private RadioButton buttonFrench;
    private RadioButton buttonEnglish;
    private TextField timeToPlay;
    private TextField newNameField;
    private Button addPlayerButton;
    private Button startButton;
    private Button cancelButton;
    private ListView<String> players;

    public GameSetupView() {
        BorderPane bp = new BorderPane();

        GridPane gp = new GridPane();
        gp.setVgap(15);
        gp.setHgap(5);
        toggleGroupLang = new ToggleGroup();
        buttonFrench = new RadioButton("French");
        buttonFrench.setUserData(Languages.FRENCH);
        buttonFrench.setToggleGroup(toggleGroupLang);
        buttonFrench.setSelected(true);
        buttonEnglish = new RadioButton("English");
        buttonEnglish.setToggleGroup(toggleGroupLang);
        buttonEnglish.setUserData(Languages.ENGLISH);

        Label langLabel = new Label("Language : ");
        GridPane.setConstraints(langLabel, 0, 0);
        VBox v5 = new VBox();
        v5.getChildren().addAll(buttonFrench, buttonEnglish);
        GridPane.setConstraints(v5, 1, 0);

        Label timeToPlayLabel = new Label("Time in play (minutes) : ");
        GridPane.setConstraints(timeToPlayLabel, 0, 1);
        timeToPlay = new TextField("5");
        GridPane.setConstraints(timeToPlay, 1, 1);

        HBox v4 = new HBox(10);
        newNameField = new TextField();
        newNameField.setPromptText("New player name");
        addPlayerButton = new Button("Add");
        v4.getChildren().addAll(newNameField, addPlayerButton);

        GridPane.setConstraints(v4, 0, 2, 2, 1);

        HBox v3 = new HBox(10);
        startButton = new Button();
        startButton.setText("Play");

        cancelButton = new Button();
        cancelButton.setText("Cancel");

        v3.getChildren().addAll(startButton, cancelButton);

        players = new ListView<>();
        GridPane.setConstraints(players, 0, 3, 2, 1);

        gp.getChildren().addAll(langLabel,v5,timeToPlayLabel,timeToPlay, v4, players);
        gp.setOpaqueInsets(new Insets(10));
        bp.setCenter(gp);

        InputStream asStream = ClassLoader.getSystemResourceAsStream("scrabble.jpg");
        Image image = new Image(asStream);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(300);
        bp.setTop(imageView);

        HBox h2 = new HBox();
        h2.getChildren().add(v3);
        h2.setAlignment(Pos.BOTTOM_RIGHT);
        bp.setBottom(h2);

        Scene scene = new Scene(bp, 300, 500);
        setScene(scene);
    }

    public Integer getTimeToPlay() {
        return Integer.valueOf(this.timeToPlay.getText());
    }

    public Languages getLang() {
        return (Languages) toggleGroupLang.getSelectedToggle().getUserData();
    }

    public ListView<String> getPlayers() {
        return players;
    }

    public Button getAddPlayerButton() {
        return addPlayerButton;
    }

    public TextField getNewNameField() {
        return newNameField;
    }

    public void onStart(Consumer<ActionEvent> callback) {
        startButton.setOnAction(callback::accept);
    }


}
