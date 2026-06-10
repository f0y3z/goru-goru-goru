package game.core;

import javafx.application.Application;//the application class under which all jfx porgrams are written 
import javafx.stage.Stage;
import javafx.scene.Scene;//to set the scene with resolution
import javafx.scene.layout.Pane;//the pane is used to hold all othe nodes(images,palyers,bg)
import javafx.scene.image.Image;//this is used to load image to memory
import javafx.scene.image.ImageView;//this is for rendering image
import javafx.geometry.Rectangle2D;//to calculate position
import javafx.scene.input.KeyCode;//to listen for keyperess

import javax.swing.ImageIcon;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
public class Effects {
    public static int coll = 0;
    public static Image img = new Image("file:game/media/explosion.png");
    public static ImageView exp = new ImageView(img);
    public static void setup() {
        exp.setFitWidth(75);
        exp.setFitHeight(69);
        Rectangle2D initialViewport = new Rectangle2D(0, 0, 1, 1);
        exp.setViewport(initialViewport);
        
        exp.setTranslateX(360);
        exp.setTranslateY(310);
    }
    public static void cycleAnimationexp() {
        coll = 0;
        Timeline timeline = new Timeline();
        
        KeyFrame keyFrame = new KeyFrame(Duration.millis(100), event -> {
            if (coll < 8) {
                Rectangle2D viewport = new Rectangle2D(coll * 75, 0, 75, 69);
                exp.setViewport(viewport);
                coll++;
            } else {
                timeline.stop();
                exp.setViewport(new Rectangle2D(0, 0, 1, 1)); 
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(9); 
        timeline.play();
    }
}