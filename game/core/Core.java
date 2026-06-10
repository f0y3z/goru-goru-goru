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


public class Core extends Application{

	public void start(Stage primaryStage){
		//pane holds everythign together
		Pane root=new Pane();
		
		//back ground instancee
		gameBackground.setScreen();
		//cow palyer instance
		Cow cow = new Cow();
		ImageView player = cow.player;


		Label breath = new Label("!!! Take a Deep Breath before JUMPING!!!");
		Label tute = new Label("                          1.press 'S' to attract the cowboy\n2.Once the cow boy is close enough press 'W' to jump over him");
		tute.getStyleClass().add("custom-label");
		tute.setStyle(
		    "-fx-background-color: transparent; " +
			"-fx-text-fill: #17533c; " +
			"-fx-font-weight: bold; " +  
			"-fx-font-size: 18px;"
		);
		breath.setStyle(
		    "-fx-background-color: transparent; " +
			"-fx-text-fill: #ff0000; " +
			"-fx-font-weight: bold; " +  
			"-fx-font-size: 18px;"
		);
		// breath.setVisible(false);
		tute.setTranslateX((global.width)/2 -250);
		breath.setTranslateX((global.width)/2 - (200));
		breath.setTranslateY((global.height)/2 - (100));
		//enemy rendering
		Enemy.setup();
		Effects.setup();
		IICT.setup();
		
		//adding all nodes to root pane
	    root.getChildren().add(gameBackground.back1);
		root.getChildren().add(gameBackground.back2);
		root.getChildren().add(IICT.iict);
		root.getChildren().add(player);
		root.getChildren().add(Enemy.enemy);
		root.getChildren().add(tute);
		root.getChildren().add(Effects.exp);
		root.getChildren().add(breath);

		Scene scene = new Scene(root,global.width,global.height);
    	Scene[] menuSceneHolder = new Scene[1];
    	int[] scoreHolder = {0};
    	PauseMenu pauseMenu = new PauseMenu(primaryStage, scene, menuSceneHolder, scoreHolder);
		
		//controls
		scene.setOnKeyPressed(event -> {
			KeyCode key = event.getCode();
			  if (Collision.isColliding(player, Enemy.enemy)) {
        		System.out.println("hit!");
				Effects.cycleAnimationexp();
        		Enemy.enemy.setTranslateX(900);
			}
			//PauseMenu 
            if (key == KeyCode.ESCAPE) {
                primaryStage.setScene(pauseMenu.getScene());
                return;
            }
			// if (cow.running) {return;}
			//move right
            if (key == KeyCode.D) {
                cow.row = 0;
				Enemy.spawn();
				Enemy.move();
				IICT.spawn();
				IICT.move();
				player.setScaleX(1);//set direction
				if (!cow.running){cow.cycleAnimation(player);}
				gameBackground.scrollBackground(1);
            	}	 
			//move left
            // else if (key == KeyCode.A) {
            //     cow.row = 0; 
			// 	Enemy.move();
			// 	player.setScaleX(-1);//set directions
            //     if (!cow.running){cow.cycleAnimation(player);}
			// 	background.scrollBackground('a');
			// 	}
			//move up
			else if (key == KeyCode.W){
				cow.row = 2;
				if(!cow.running){cow.cycleAnimation(player,1);}
				gameBackground.scrollBackground(1);
				Enemy.spawn();
				Enemy.move();
				// IICT.spawn();
				// IICT.move();
			}
			//mvoe down
			else if(key == KeyCode.S){
				cow.row =3;
				if(!cow.running){cow.cycleAnimation(player);}
				gameBackground.scrollBackground('a');
				Enemy.spawn();
				Enemy.move();
				IICT.spawn();
				IICT.move();
			}


			// //fall back
			// else if(key == KeyCode.SPACE){
			// 	cow.row =3;
			// 	if(!cow.running){cow.cycleAnimation(player);}
			// 	// player.setTranslateX(player.getTranslateX()-60);
			//     }
			// //attack
			// else if(key == KeyCode.F){
			// 	cow.row =2;
			// 	if(!cow.running){cow.cycleAnimation(player,1);}
			// 	// player.setTranslateX(player.getTranslateX()+60);
            //     }
			//alert thing
			int x =(int) (Enemy.enemy.getTranslateX()-player.getTranslateX());
			if(x<350){breath.setVisible(true);}
			else{breath.setVisible(false);}
        	});

		GameMenu gameMenu = new GameMenu();
		gameMenu.show(primaryStage, () -> {
      menuSceneHolder[0] = primaryStage.getScene();
      primaryStage.setScene(scene);
			primaryStage.setTitle("Game");
		});
			
	}
	
	
	public static void main(String[] args){
		launch(args);
	}
}
