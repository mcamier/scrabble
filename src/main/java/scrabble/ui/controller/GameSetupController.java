package scrabble.ui.controller;

import javafx.event.ActionEvent;
import scrabble.ui.view.GameSetupView;

import java.util.ArrayList;
import java.util.HashSet;

public class GameSetupController implements Controller<GameSetupView> {

    private GameSetupView view;
    private GameController gameController;

    public GameSetupController(GameController gameController) {
        this.gameController = gameController;
        this.view = new GameSetupView();
        this.view.onStart(this::validate);
        this.view.getAddPlayerButton().setOnMouseClicked(event -> {
            String text = this.view.getNewNameField().getText();
            if(text != null && !text.isBlank()) {
                view.getPlayers().getItems().add(text);
                view.getNewNameField().setText("");
            }
        });
    }

    @Override
    public GameSetupView getView() {
        return this.view;
    }

    public void validate(ActionEvent e) {
        System.out.println(view.getLang());
        System.out.println(view.getTimeToPlay());
        System.out.println(new ArrayList<>(view.getPlayers().getItems()));
        gameController.startGame(view.getLang(), view.getTimeToPlay(), new HashSet<>(view.getPlayers().getItems()));
    }

}
