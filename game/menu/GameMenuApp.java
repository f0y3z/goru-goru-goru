import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GameMenuApp extends Application {
    @Override
    public void start(Stage stage) {
        // Buttons
        Button startBtn = new Button("Start Game");
        Button optionsBtn = new Button("Options");
        Button exitBtn = new Button("Exit");

        // Layout
        VBox menuLayout = new VBox(15, startBtn, optionsBtn, exitBtn);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        // Background Image (full window)
        Image bgImage = new Image("file:/home/zian/Downloads/iict.jpg");
        BackgroundImage bg = new BackgroundImage(
                bgImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, false) //for full width & height
        );
        menuLayout.setBackground(new Background(bg));

        // Scene
        Scene scene = new Scene(menuLayout, 800, 600);

        // Responsive Button Styles (font size auto adjust by width & height)
        startBtn.styleProperty().bind(
            Bindings.concat("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: ",
                scene.widthProperty().divide(40).asString(), "; -fx-padding: 10 20;")
        );
        optionsBtn.styleProperty().bind(
            Bindings.concat("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: ",
                scene.widthProperty().divide(40).asString(), "; -fx-padding: 10 20;")
        );
        exitBtn.styleProperty().bind(
            Bindings.concat("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-size: ",
                scene.widthProperty().divide(40).asString(), "; -fx-padding: 10 20;")
        );

        // Actions
        startBtn.setOnAction(e -> {
            VBox gameLayout = new VBox(new Label("🎮 Game Started!"));
            gameLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");
            Scene gameScene = new Scene(gameLayout, 600, 400);
            stage.setScene(gameScene);
        });

        optionsBtn.setOnAction(e -> System.out.println("Options Menu"));
        exitBtn.setOnAction(e -> stage.close());

        stage.setScene(scene);
        stage.setTitle("Game Menu");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
