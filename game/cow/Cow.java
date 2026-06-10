package game.cow;

import javafx.application.Application;//the application class under which all jfx porgrams are written 
import javafx.stage.Stage;
import javafx.scene.Scene;//to set the scene with resolution
import javafx.scene.layout.Pane;//the pane is used to hold all othe nodes(images,palyers,bg)
import javafx.scene.image.Image;//this is used to load image to memory
import javafx.scene.image.ImageView;//this is for rendering image
import javafx.geometry.Rectangle2D;//to calculate position
import javafx.scene.input.KeyCode;//to listen for keyperess

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import game.core.*;


public class Cow {
	//encapsulated animation frame variabels
	private int frameHeight = 216;
	private int frameWidth = 216;
	private int totalCols = 4;
	private int col = 0;


	//control variables with access
	public int row = 0;
	public boolean running = false;
	public ImageView player;

	//loading player resoruces and details
	public Cow() {
		Image spriteSheet = new Image("file:game/media/horse.png");
		player = new ImageView(spriteSheet);
		player.setFitWidth(216);
		player.setFitHeight(216);
		player.setSmooth(false);
		Rectangle2D viewport = new Rectangle2D(col * frameWidth, row * frameHeight, frameWidth, frameHeight);
		player.setViewport(viewport);
		player.setTranslateX(200);
		player.setTranslateY(200);
	}
    
    //Sprite display
	public void cycleAnimation(ImageView iv){
		col =0;
		running=true;//this stops registering keystrokes while animation is still palyeing
		Timeline timeline = new Timeline(
			new KeyFrame(Duration.millis(100), event ->{
				Rectangle2D viewport = new Rectangle2D(col*frameWidth, row*frameHeight, frameWidth, frameHeight);
				iv.setViewport(viewport);
				col++;
				})
			);
		timeline.setCycleCount(4);
		timeline.setOnFinished(event -> running =false);
		timeline.play();
	}
	//spirte display (fixes defective sprite)
	public void cycleAnimation(ImageView iv,int x){
                col =0;
                running=true;//this stops registering keystrokes while animation is still palyeing
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.millis(100), event ->{
                                Rectangle2D viewport = new Rectangle2D(col*frameWidth, row*frameHeight, frameWidth, frameHeight);
								for(int i=0;i<15;i++){
									gameBackground.scrollBackground(1);
									Enemy.move();
									IICT.move();
								}
                                iv.setViewport(viewport);
                                col++;
                                })
                        );
		//fixiing slop sprite defect with code
            timeline.setCycleCount(4);
			timeline.setOnFinished(event -> {
			Rectangle2D firstframe = new Rectangle2D(0,row*frameHeight, frameWidth,frameHeight);
			iv.setViewport(firstframe);
			running=false;
		});
        timeline.play();
	}
}
