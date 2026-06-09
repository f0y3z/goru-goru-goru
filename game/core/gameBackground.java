package game.core;
import game.menu.*;
import game.collision.*;
import javafx.application.Application;//the application class under which all jfx porgrams are written 
import javafx.stage.Stage;
import javafx.scene.Scene;//to set the scene with resolution
import javafx.scene.layout.Pane;//the pane is used to hold all othe nodes(images,palyers,bg)
import javafx.scene.image.Image;//this is used to load image to memory
import javafx.scene.image.ImageView;//this is for rendering image
import javafx.geometry.Rectangle2D;//to calculate position
import javafx.scene.input.KeyCode;//to listen for keyperess
import javafx.animation.KeyFrame;//animation libns
import javafx.animation.Timeline;
import javafx.util.Duration;
import game.cow.*;//cow package
import java.util.Random;
public class gameBackground{
                //background rendering
                //background rendering (scrolling)
                public static Image background = new Image("file:game/media/background.png");
                public static ImageView back1 = new ImageView(background);
                public static ImageView back2 = new ImageView(background);
				public static void setScreen(){
					back1.setFitWidth(global.width);
					back1.setFitHeight(global.height);
					back2.setFitWidth(global.width);
					back2.setFitHeight(global.height);
					back2.setTranslateX(global.width);
				}


				public static void scrollBackground(int x) {
					// 1. Move both background images to the left
					back1.setTranslateX(back1.getTranslateX() - global.speed);
					back2.setTranslateX(back2.getTranslateX() - global.speed);

					// 2. Snap them back to the right once they completely exit the screen
					if (back1.getTranslateX() <= -global.width) {
						back1.setTranslateX(back2.getTranslateX() + global.width	);
					}
					if (back2.getTranslateX() <= -global.width) {
						back2.setTranslateX(back1.getTranslateX() + global.width	);
					}
				}
				public static void scrollBackground(char a) {
					// 1. Move both background images to the rigtt
					back1.setTranslateX(back1.getTranslateX() + global.speed);
					back2.setTranslateX(back2.getTranslateX() + global.speed);

					// 2. Snap them back to the right once they completely exit the screen
					if (back1.getTranslateX() >= global.width) {
						back1.setTranslateX(back2.getTranslateX() - global.width);
					}
					if (back2.getTranslateX() >= global.width) {
						back2.setTranslateX(back1.getTranslateX() - global.width);
					}
				}
}