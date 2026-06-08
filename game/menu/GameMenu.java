package game.menu;
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

    private Scene menuScene, optionScene, gameScene, pauseScene;
    private int currentScore = 0;

    private void loadHighscore() {
        try (BufferedReader br = new BufferedReader(new FileReader(SCORE_FILE))) {
            highscore = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            highscore = 0;
        }
    }

    private void saveHighscore() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(SCORE_FILE))) {
            pw.println(highscore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void resetHighscore() {
        highscore = 0;
        saveHighscore();
    }

    private int getHighscore() {
        return highscore;
    }

    @Override
    public void start(Stage stage) {
        loadHighscore();

        BackgroundImage bg = new BackgroundImage(
                new Image("file:bg.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, false)
        );

        VBox menu = new VBox(20);
        menu.setStyle("-fx-padding: 50; -fx-alignment: center-left;");
        menu.setBackground(new Background(bg));

        Button startBtn = new Button("Start");
        Button optionBtn = new Button("Option");
        Button exitBtn = new Button("Exit");

        menu.getChildren().addAll(startBtn, optionBtn, exitBtn);
        menuScene = new Scene(menu, 800, 600);
        menuScene.getStylesheets().add("file:style.css");

        VBox optionMenu = new VBox(20);
        optionMenu.setStyle("-fx-padding: 50; -fx-alignment: center-left;");
        optionMenu.setBackground(new Background(bg));

        Label highscoreLabel = new Label("Highscore: " + getHighscore());
        highscoreLabel.getStyleClass().add("highscore");

        Button resetBtn = new Button("Reset Highscore");
        resetBtn.setOnAction(ev -> {
            resetHighscore();
            highscoreLabel.setText("Highscore: " + getHighscore());
        });

        Button newOptionBtn = new Button("For New Option");
        Button backBtn = new Button("Back");

        optionMenu.getChildren().addAll(highscoreLabel, resetBtn, newOptionBtn, backBtn);
        optionScene = new Scene(optionMenu, 800, 600);
        optionScene.getStylesheets().add("file:style.css");

        gameScene = createGameScene(stage);

        PauseMenu pauseMenu = new PauseMenu(stage, this);
        pauseScene = pauseMenu.getScene();

        startBtn.setOnAction(e -> startGame(stage));
        optionBtn.setOnAction(e -> stage.setScene(optionScene));
        backBtn.setOnAction(e -> stage.setScene(menuScene));
        exitBtn.setOnAction(e -> stage.close());

        stage.setScene(menuScene);
        stage.setTitle("Game Menu");
        stage.show();
    }

    public void startGame(Stage stage) {
        currentScore = 0;
        gameScene = createGameScene(stage);
        stage.setScene(gameScene);
    }

    private Scene createGameScene(Stage stage) {
        VBox gameRoot = new VBox(20);
        gameRoot.setStyle("-fx-padding: 50; -fx-alignment: center;");
        gameRoot.setBackground(new Background(new BackgroundImage(
                new Image("file:bg.png"),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, false)
        )));

        Button pauseBtn = new Button("Pause");
        gameRoot.getChildren().add(pauseBtn);

        Scene scene = new Scene(gameRoot, 800, 600);
        scene.getStylesheets().add("file:style.css");

        pauseBtn.setOnAction(e -> stage.setScene(pauseScene));

        return scene;
    }

    public Scene getMenuScene() { return menuScene; }
    public Scene getGameScene() { return gameScene; }
    public int getCurrentScore() { return currentScore; }

    public static void main(String[] args) {
        launch();
    }
}
