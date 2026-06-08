package game.background;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Background {
    private ImageView back1;
    private ImageView back2;
    private Timeline timeline;

    public Background(Pane root) {
        // Load your background image (green.jpg)
        Image bg = new Image("file:game/media/green.jpg");

        back1 = new ImageView(bg);
        back2 = new ImageView(bg);

        back1.setFitWidth(960);
        back1.setFitHeight(540);

        back2.setFitWidth(960);
        back2.setFitHeight(540);

        // Place second image right after the first one
        back2.setTranslateX(960);

        // Add both to root
        root.getChildren().addAll(back1, back2);

        // Create timeline for scrolling
        timeline = new Timeline(new KeyFrame(Duration.millis(20), e -> moveBackground()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void moveBackground() {
        // Move both images left
        back1.setTranslateX(back1.getTranslateX() - 2);
        back2.setTranslateX(back2.getTranslateX() - 2);

        // Reset position when off-screen
        if (back1.getTranslateX() <= -960) {
            back1.setTranslateX(back2.getTranslateX() + 960);
        }
        if (back2.getTranslateX() <= -960) {
            back2.setTranslateX(back1.getTranslateX() + 960);
        }
    }
}
