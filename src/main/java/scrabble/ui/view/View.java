package scrabble.ui.view;

import javafx.scene.Scene;

public abstract class View {

    protected Scene scene;

    public Scene getScene() {
        return scene;
    }

    protected void setScene(Scene scene) {
        this.scene = scene;
    }
}
