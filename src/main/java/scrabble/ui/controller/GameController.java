package scrabble.ui.controller;

import javafx.scene.Scene;
import scrabble.core.Game;
import scrabble.dictionnary.Languages;
import scrabble.ui.view.GameSetupView;
import scrabble.ui.view.GameView;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class GameController implements Controller<GameView> {

    private Game model;
    private GameView view;
    private Consumer<Scene> onGameStartCallback;

    @Override
    public GameView getView() {
        return view;
    }

    public void startGame(Languages lang, Integer timeToPlay, Set<String> playerNames) {
        view = new GameView();
        model = new Game(lang, playerNames);
        onGameStartCallback.accept(getView().getScene());
    }

    public void setOnGameStart(Consumer<Scene> callback) {
        onGameStartCallback = callback;
    }
}