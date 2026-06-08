import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PauseMenu {
    private Scene pauseScene;

    public PauseMenu(Stage stage, GameMenu gameMenu) {
        VBox pauseLayout = new VBox(20);
        pauseLayout.setStyle("-fx-padding: 50; -fx-alignment: center;");

        Button resumeBtn = new Button("Resume");
        resumeBtn.setOnAction(e -> stage.setScene(gameMenu.getGameScene()));

        Button resetBtn = new Button("Reset Highscore");
        resetBtn.setOnAction(e -> {
            gameMenu.startGame(stage);
        });

        Button mainMenuBtn = new Button("Main Menu");
        mainMenuBtn.setOnAction(e -> stage.setScene(gameMenu.getMenuScene()));

        Button exitBtn = new Button("Exit");
        exitBtn.setOnAction(e -> stage.close());

        pauseLayout.getChildren().addAll(resumeBtn, resetBtn, mainMenuBtn, exitBtn);

        pauseScene = new Scene(pauseLayout, 800, 600);
        pauseScene.getStylesheets().add("file:style.css");
    }

    public Scene getScene() {
        return pauseScene;
    }
}
 