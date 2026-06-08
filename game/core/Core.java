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
import game.menu.*;


class globalVar {
	public static int width = 960;
	public static int height = 540;
	public static int speed = 50;
	
}
class Background{
                //background rendering
                //background rendering (scrolling)
                Image background = new Image("file:game/media/background.png");
                public ImageView back1 = new ImageView(background);
                public ImageView back2 = new ImageView(background);
				public void setScreen(){
					back1.setFitWidth(globalVar.width);
					back1.setFitHeight(globalVar.height);
					back2.setFitWidth(globalVar.width);
					back2.setFitHeight(globalVar.height);
					back2.setTranslateX(globalVar.width);
				}


				public void scrollBackground(int x) {
					// 1. Move both background images to the left
					back1.setTranslateX(back1.getTranslateX() - globalVar.speed);
					back2.setTranslateX(back2.getTranslateX() - globalVar.speed);

					// 2. Snap them back to the right once they completely exit the screen
					if (back1.getTranslateX() <= -globalVar.width) {
						back1.setTranslateX(back2.getTranslateX() + globalVar.width	);
					}
					if (back2.getTranslateX() <= -globalVar.width) {
						back2.setTranslateX(back1.getTranslateX() + globalVar.width	);
					}
				}
				public void scrollBackground(char a) {
					// 1. Move both background images to the rigtt
					back1.setTranslateX(back1.getTranslateX() + globalVar.speed);
					back2.setTranslateX(back2.getTranslateX() + globalVar.speed);

					// 2. Snap them back to the right once they completely exit the screen
					if (back1.getTranslateX() >= globalVar.width) {
						back1.setTranslateX(back2.getTranslateX() - globalVar.width);
					}
					if (back2.getTranslateX() >= globalVar.width) {
						back2.setTranslateX(back1.getTranslateX() - globalVar.width);
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
		Image enm= new Image("file:game/media/enemy.png");
		ImageView enemy = new ImageView(enm);
		enemy.setFitWidth(96);
		enemy.setFitHeight(96);
		enemy.setTranslateX(850);
		enemy.setTranslateY(320);
		

		//adding all nodes to root pane
	    root.getChildren().add(background.back1);
		root.getChildren().add(background.back2);
		root.getChildren().add(player);
		root.getChildren().add(enemy);
    
		Scene scene = new Scene(root,globalVar.width,globalVar.height);

		//controls
		scene.setOnKeyPressed(event -> {
			if (cow.running) {return;}
			KeyCode key = event.getCode();
			//move right
            if (key == KeyCode.D) {
                cow.row = 0;
				enemy.setTranslateX(enemy.getTranslateX()-globalVar.speed);
				player.setScaleX(1);//set direction
                cow.cycleAnimation(player);
				background.scrollBackground(1);
            	}	 
			//move left
            else if (key == KeyCode.A) {
                cow.row = 0; 
				enemy.setTranslateX(enemy.getTranslateX()+globalVar.speed);
				player.setScaleX(-1);//set directions
                cow.cycleAnimation(player);
				background.scrollBackground('a');
				}
			//move up
			else if (key == KeyCode.W){
				cow.row = 2;
				cow.cycleAnimation(player,1);
				background.scrollBackground(1);
				enemy.setTranslateX(enemy.getTranslateX()-globalVar.speed-100);
			}
			//mvoe down
			else if(key == KeyCode.S){
				cow.row =3;
				cow.cycleAnimation(player);
				background.scrollBackground('a');
				enemy.setTranslateX(enemy.getTranslateX()+globalVar.speed+100);
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

		GameMenu gameMenu = new GameMenu();
		gameMenu.show(primaryStage, () -> {
			primaryStage.setScene(scene);
			primaryStage.setTitle("Game");
		});
			
	}
	
	
	public static void main(String[] args){
		launch(args);
	}
}
