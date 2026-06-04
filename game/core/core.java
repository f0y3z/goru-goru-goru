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


public class core extends Application{
	//variables to control the viewport of the player sprite
	private	int frameHeight=216;
	private	int frameWidth=216;
	private int totalCols=4; 	
	private	int col=0;
	private	int row =0;
	private boolean running= false;
	public void start(Stage primaryStage){
		//pane holds all othe nodes
		Pane root=new Pane();

		//rendering background
		Image background = new Image("file:background.png");
		ImageView back= new ImageView(background);
		ImageView back2= new ImageView(background);
		back.setFitWidth(960);
		back.setFitHeight(540);
		back.setSmooth(false);

		//rendering the horse sprite
		Image spriteSheet = new Image("file:horse.png");
		ImageView player= new ImageView(spriteSheet);
		Rectangle2D viewport = new Rectangle2D(col * frameWidth,row* frameHeight,frameWidth,frameHeight);	
		player.setViewport(viewport);
		player.setTranslateX(200);
		player.setTranslateY(200);

		//adding all nodes to root pane
		root.getChildren().add(back);
		root.getChildren().add(player);
		//creates scene with the pane
                Scene scene = new Scene(root,960,540);



			
		//Controls
        	scene.setOnKeyPressed(event -> {
			if (running) {return;}//if animaiton is still running dont register keypress.

            		KeyCode key = event.getCode();
            		if (key == KeyCode.RIGHT) {
                		row = 0;
				player.setScaleX(1);
                		cycleAnimation(player);
            		} 
            		else if (key == KeyCode.LEFT) {
                		row = 0; 
				player.setScaleX(-1);
                		cycleAnimation(player);
            		}
			else if (key == KeyCode.UP){
				row = 2;
				cycleAnimation2(player);
			}
			else if(key == KeyCode.DOWN){
				row =3;
				cycleAnimation(player);
			}
        	});

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//Sprite display
	private void cycleAnimation(ImageView iv){
		col =0;
		running=true;//this stops registering keystrokes while animation is still palyeing
		Timeline timeline = new Timeline(
			new KeyFrame(Duration.millis(150), event ->{
				Rectangle2D viewport = new Rectangle2D(col*frameWidth, row*frameHeight, frameWidth, frameHeight);
				iv.setViewport(viewport);
				col++;
				})
			);
		timeline.setCycleCount(4);
		timeline.setOnFinished(event -> running =false);
		timeline.play();
	}//spirte display fix
	private void cycleAnimation2(ImageView iv){
                col =0;
                running=true;//this stops registering keystrokes while animation is still palyeing
                Timeline timeline = new Timeline(
                        new KeyFrame(Duration.millis(150), event ->{
                                Rectangle2D viewport = new Rectangle2D(col*frameWidth, row*frameHeight, frameWidth, frameHeight);
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
	
	public static void main(String[] args){
		launch(args);
	}
}
