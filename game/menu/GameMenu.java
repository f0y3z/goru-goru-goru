import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;

public class GameMenu extends Application {
    private int highscore = 0;
    private final String SCORE_FILE = "highscore.txt";

    // Load highscore from file
    private void loadHighscore() {
        try (BufferedReader br = new BufferedReader(new FileReader(SCORE_FILE))) {
            highscore = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            highscore = 0; // default
        }
    }

    // Save highscore to file
    private void saveHighscore() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(SCORE_FILE))) {
            pw.println(highscore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Reset highscore
    private void resetHighscore() {
        highscore = 0;
        saveHighscore();
    }

    // Update highscore if new score is higher
    private void updateHighscore(int newScore) {
        if (newScore > highscore) {
            highscore = newScore;
            saveHighscore();
        }
    }

    private int getHighscore() {
        return highscore;
    }

    @Override
    public void start(Stage stage) {
        loadHighscore();

        // Background image
        BackgroundImage bg = new BackgroundImage(
                new Image("file:/home/zian/Downloads/bg.jpg"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, false)
        );

        // ===== Menu Scene =====
        VBox menu = new VBox(20);
        menu.setStyle("-fx-padding: 50; -fx-alignment: center-left;");
        menu.setBackground(new Background(bg));

        Button startBtn = new Button("Start");
        Button optionBtn = new Button("Option");
        Button exitBtn = new Button("Exit");

        menu.getChildren().addAll(startBtn, optionBtn, exitBtn);
        Scene menuScene = new Scene(menu, 800, 600);

        // ===== Option Scene =====
        VBox optionMenu = new VBox(20);
        optionMenu.setStyle("-fx-padding: 50; -fx-alignment: center-left;");
        optionMenu.setBackground(new Background(bg));

        Label highscoreLabel = new Label("Highscore: " + getHighscore());
        highscoreLabel.getStyleClass().add("highscore"); // CSS class

        Button resetBtn = new Button("Reset Highscore");
        resetBtn.setOnAction(ev -> {
            resetHighscore();
            highscoreLabel.setText("Highscore: " + getHighscore());
        });

        Button newOptionBtn = new Button("For New Option");
        Button backBtn = new Button("Back");

        optionMenu.getChildren().addAll(highscoreLabel, resetBtn, newOptionBtn, backBtn);
        Scene optionScene = new Scene(optionMenu, 800, 600);

        // ===== Apply CSS =====
        menuScene.getStylesheets().add("file:/home/zian/Study/TLOU-Vater-Hotel/game/menu/style.css");
        optionScene.getStylesheets().add("file:/home/zian/Study/TLOU-Vater-Hotel/game/menu/style.css");

        // ===== Responsive Binding =====
        startBtn.prefWidthProperty().bind(menuScene.widthProperty().multiply(0.3));
        startBtn.prefHeightProperty().bind(menuScene.heightProperty().multiply(0.1));

        optionBtn.prefWidthProperty().bind(menuScene.widthProperty().multiply(0.3));
        optionBtn.prefHeightProperty().bind(menuScene.heightProperty().multiply(0.1));

        exitBtn.prefWidthProperty().bind(menuScene.widthProperty().multiply(0.3));
        exitBtn.prefHeightProperty().bind(menuScene.heightProperty().multiply(0.1));

        resetBtn.prefWidthProperty().bind(optionScene.widthProperty().multiply(0.4));
        resetBtn.prefHeightProperty().bind(optionScene.heightProperty().multiply(0.1));

        newOptionBtn.prefWidthProperty().bind(optionScene.widthProperty().multiply(0.4));
        newOptionBtn.prefHeightProperty().bind(optionScene.heightProperty().multiply(0.1));

        backBtn.prefWidthProperty().bind(optionScene.widthProperty().multiply(0.4));
        backBtn.prefHeightProperty().bind(optionScene.heightProperty().multiply(0.1));

        // ===== Actions =====
        optionBtn.setOnAction(e -> stage.setScene(optionScene));
        backBtn.setOnAction(e -> stage.setScene(menuScene));
        exitBtn.setOnAction(e -> stage.close());

        // ===== Show Stage =====
        stage.setScene(menuScene);
        stage.setTitle("Game Menu");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
