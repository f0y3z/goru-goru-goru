import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameMenuApp extends Application {
    @Override
    public void start(Stage stage) {
        // Buttons
        Button startBtn = new Button("Start Game");
        Button optionsBtn = new Button("Options");
        Button exitBtn = new Button("Exit");

        // Actions
        startBtn.setOnAction(e -> System.out.println("Game Started!"));
        optionsBtn.setOnAction(e -> System.out.println("Options Menu"));
        exitBtn.setOnAction(e -> stage.close());

        // Layout
        VBox menuLayout = new VBox(15, startBtn, optionsBtn, exitBtn);
        menuLayout.setStyle("-fx-alignment: center; -fx-padding: 50;");

        // Scene
        Scene scene = new Scene(menuLayout, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Game Menu");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
