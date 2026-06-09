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



public class Enemy {
	//enemy rendering
	public static Image enm= new Image("file:game/media/enemy.png");
	public static ImageView enemy = new ImageView(enm);
	public static void setup() {
		enemy.setFitWidth(96);
		enemy.setFitHeight(96);
		enemy.setTranslateX(850);
		enemy.setTranslateY(340);
	}
	public static Random rand = new Random();
	public static void spawn() {
		int randPos=rand.nextInt((global.width-global.width/2)+1)+global.width/2;
		if(enemy.getTranslateX() <-40) {
			enemy.setTranslateX(randPos);
		}
	}
    public static void move(){
        enemy.setTranslateX(enemy.getTranslateX()-global.speed);
    }
}
