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


class Background{
                //background rendering
                //background rendering (scrolling)
                Image background = new Image("file:game/media/background.png");
                public ImageView back1 = new ImageView(background);
                public ImageView back2 = new ImageView(background);
				public void setScreen(){
					back1.setFitWidth(global.width);
					back1.setFitHeight(global.height);
					back2.setFitWidth(global.width);
					back2.setFitHeight(global.height);
					back2.setTranslateX(global.width);
				}


				public void scrollBackground(int x) {
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
				public void scrollBackground(char a) {
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

class Enemy{
	//enemy rendering
	Image enm= new Image("file:game/media/enemy.png");
	public ImageView enemy = new ImageView(enm);
	public void setup() {
		enemy.setFitWidth(96);
		enemy.setFitHeight(96);
		enemy.setTranslateX(850);
		enemy.setTranslateY(340);
	}
	Random rand = new Random();
	public void spawn() {
		int randPos=rand.nextInt((global.width-global.width/2)+1)+global.width/2;
		if(enemy.getTranslateX() <-40) {
			enemy.setTranslateX(randPos);
		}
	}
}
public class Core extends Application{

	public void start(Stage primaryStage){
		//pane holds everythign together
		Pane root=new Pane();
		
		//back ground instancee
		Background background = new Background();
		background.setScreen();
		//cow palyer instance
		Cow cow = new Cow();
		ImageView player = cow.player;

		//enemy rendering
		Enemy enemyy = new Enemy();
		ImageView enemy=enemyy.enemy;
		enemyy.setup();
		
		//adding all nodes to root pane
	    root.getChildren().add(background.back1);
		root.getChildren().add(background.back2);
		root.getChildren().add(player);
		root.getChildren().add(enemy);
    
		Scene scene = new Scene(root,global.width,global.height);
    Scene[] menuSceneHolder = new Scene[1];
    int[] scoreHolder = {0};
    PauseMenu pauseMenu = new PauseMenu(primaryStage, scene, menuSceneHolder, scoreHolder);
		//controls
		scene.setOnKeyPressed(event -> {
			KeyCode key = event.getCode();
			  if (Collision.isColliding(player, enemy)) {
        System.out.println("hit!");
        enemy.setTranslateX(900);

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
				enemyy.spawn();
				enemy.setTranslateX(enemy.getTranslateX()-global.speed);
				player.setScaleX(1);//set direction
				if (!cow.running){cow.cycleAnimation(player);}
				background.scrollBackground(1);
            	}	 
			//move left
            // else if (key == KeyCode.A) {
            //     cow.row = 0; 
			// 	enemy.setTranslateX(enemy.getTranslateX()+global.speed);
			// 	player.setScaleX(-1);//set directions
            //     if (!cow.running){cow.cycleAnimation(player);}
			// 	background.scrollBackground('a');
			// 	}
			//move up
			else if (key == KeyCode.W){
				cow.row = 2;
				if(!cow.running){cow.cycleAnimation(player,1);}
				background.scrollBackground(1);
				enemyy.spawn();
				enemy.setTranslateX(enemy.getTranslateX()-global.speed);
			}
			//mvoe down
			else if(key == KeyCode.S){
				cow.row =3;
				if(!cow.running){cow.cycleAnimation(player);}
				background.scrollBackground('a');
				enemyy.spawn();
				enemy.setTranslateX(enemy.getTranslateX()+global.speed);
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
