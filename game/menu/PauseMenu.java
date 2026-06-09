package game.menu;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
// import game.core.Background;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import game.core.*;

public class PauseMenu {
    private Scene pauseScene;

    public PauseMenu(Stage stage, Scene gameScene, Scene[] menuSceneHolder, int[] scoreHolder){
       // background
        BackgroundImage bg = new BackgroundImage(
            new Image("file:game/media/bg.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, true)
        );

        VBox pauseLayout = new VBox(20);
        pauseLayout.setStyle("-fx-padding: 50; -fx-alignment: center-left;");
        pauseLayout.setBackground(new Background(bg));

        // score label
        Label scoreLabel = new Label("Score: " + scoreHolder[0]);
        scoreLabel.getStyleClass().add("pause-button");

        Button resumeBtn = new Button("Resume");
        resumeBtn.getStyleClass().add("pause-button");
        resumeBtn.setOnAction(e -> stage.setScene(gameScene));
        Button mainMenuBtn = new Button("Main Menu");
        mainMenuBtn.getStyleClass().add("pause-button");
        mainMenuBtn.setOnAction(e -> {
            // menuSceneHolder[0] is set when Start was clicked in GameMenu
            if (menuSceneHolder[0] != null) {
                stage.setScene(menuSceneHolder[0]);
                stage.setTitle("Game Menu");
            }
        });
        Button exitBtn = new Button("Exit");
        exitBtn.getStyleClass().add("pause-button");
        exitBtn.setOnAction(e -> stage.close());

          pauseLayout.getChildren().addAll(scoreLabel, resumeBtn, mainMenuBtn, exitBtn);
          pauseScene = new Scene(pauseLayout, global.width, global.height);

          // css
          pauseScene.getStylesheets().add("file:game/menu/style.css");

          // responsive buttons
          resumeBtn.prefWidthProperty().bind(pauseScene.widthProperty().multiply(0.3));
          resumeBtn.prefHeightProperty().bind(pauseScene.heightProperty().multiply(0.1));

          mainMenuBtn.prefWidthProperty().bind(pauseScene.widthProperty().multiply(0.3));
          mainMenuBtn.prefHeightProperty().bind(pauseScene.heightProperty().multiply(0.1));

          exitBtn.prefWidthProperty().bind(pauseScene.widthProperty().multiply(0.3));
          exitBtn.prefHeightProperty().bind(pauseScene.heightProperty().multiply(0.1));
    }

    public Scene getScene() {
        return pauseScene;
    }
}
