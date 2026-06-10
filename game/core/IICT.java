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
import javafx.scene.control.Label;


public class IICT {
    	public static Image iictt= new Image("file:game/media/iict-building.png");
	public static ImageView iict = new ImageView(iictt);
	public static void setup(){
		iict.setTranslateX(140);
		iict.setTranslateY(172);
	}
	public static void spawn() {
		if(iict.getTranslateX() <-450) {
			iict.setTranslateX(global.width+500);
		}
	}
    public static void move(){
        iict.setTranslateX(iict.getTranslateX()-global.speed);
    }
}
