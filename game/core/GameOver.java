package game.core;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameOver {

    public static void show(Stage stage, Scene[] menuSceneHolder, Runnable onReset) {
        VBox layout = new VBox(20);
        layout.setStyle("-fx-alignment: center; -fx-background-color: black; -fx-padding: 50;");

        Label gameOverLabel = new Label("GAME OVER");
        gameOverLabel.setStyle("-fx-font-size: 64px; -fx-text-fill: red; -fx-font-weight: bold;");

        Label killedLabel = new Label("You have been killed by Aurther Rakib Morgan");
        killedLabel.setStyle("-fx-font-size: 22px; -fx-text-fill: white;");

        layout.getChildren().addAll(gameOverLabel, killedLabel);

        Scene gameOverScene = new Scene(layout, global.width, global.height);
        stage.setScene(gameOverScene);

        Timeline wait = new Timeline(
            new KeyFrame(Duration.seconds(5), e -> {
                if (menuSceneHolder[0] != null) {
                    onReset.run();
                    stage.setScene(menuSceneHolder[0]);
                    stage.setTitle("Game Menu");
                }
            })
        );
        wait.play();
    }
}
