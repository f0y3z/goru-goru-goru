package game.core;
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

public class Core extends Application{
	private int row = 0;

	public void start(Stage primaryStage){
		//pane holds everythign together
		Pane root=new Pane();
		

		//background rendering
		//background rendering (scrolling)
		Image background = new Image("file:game/media/green.jpg");
		ImageView back1 = new ImageView(background);
		ImageView back2 = new ImageView(background);

		back1.setFitWidth(960);
		back1.setFitHeight(540);
		back2.setFitWidth(960);
		back2.setFitHeight(540);

		// 2nd image comes right after the first one
		back2.setTranslateX(960);

		//add both to root
		root.getChildren().addAll(back1, back2);

		//scrolling animation
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), e -> {
    	back1.setTranslateX(back1.getTranslateX() - 2);
    	back2.setTranslateX(back2.getTranslateX() - 2);

    	//reset position when off-screen
    	if (back1.getTranslateX() <= -960) {
        	back1.setTranslateX(back2.getTranslateX() + 960);
    	}
    	if (back2.getTranslateX() <= -960) {
        	back2.setTranslateX(back1.getTranslateX() + 960);
    	}
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();


		//cow palyer instance
		Cow cow = new Cow();
		ImageView player = cow.player;

		//enemy rendering
		Image enm= new Image("file:game/media/enemy.png");
		ImageView enemy = new ImageView(enm);
		enemy.setFitWidth(96);
		enemy.setFitHeight(96);
		enemy.setTranslateX(850);
		enemy.setTranslateY(320);


		//adding all nodes to root pane
		root.getChildren().add(player);
		root.getChildren().add(enemy);
        Scene scene = new Scene(root,960,540);

		//controls
		scene.setOnKeyPressed(event -> {
			if (cow.running) {return;}
			
			KeyCode key = event.getCode();
			//move right
            if (key == KeyCode.D) {
                cow.row = 0;
				enemy.setTranslateX(enemy.getTranslateX()-40);
				player.setScaleX(1);//set direction
                cow.cycleAnimation(player);
            	} 
			//move left
            else if (key == KeyCode.A) {
                cow.row = 0; 
				enemy.setTranslateX(enemy.getTranslateX()+40);
				player.setScaleX(-1);//set directions
                cow.cycleAnimation(player);
            	}
			//move up
			else if (key == KeyCode.W){
				cow.row = 2;
				cow.cycleAnimation(player,1);
			}
			//mvoe down
			else if(key == KeyCode.S){
				cow.row =3;
				cow.cycleAnimation(player);
			}
			//fall back
			else if(key == KeyCode.SPACE){
				cow.row =3;
				cow.cycleAnimation(player);
				player.setTranslateX(player.getTranslateX()-60);
			}
			//attack
			else if(key == KeyCode.F){
                                cow.row =2;
                                cow.cycleAnimation(player,1);
                                player.setTranslateX(player.getTranslateX()+60);
                        }

        	});

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	public static void main(String[] args){
		launch(args);
	}
}
