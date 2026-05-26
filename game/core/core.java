import javafx.application.Application;//the application class under which all jfx porgrams are written 
import javafx.stage.Stage;
import javafx.scene.Scene;//to set the scene with resolution
import javafx.scene.layout.Pane;//the pane is used to hold all othe nodes(images,palyers,bg)
import javafx.scene.image.Image;//this is used to load image to memory
import javafx.scene.image.ImageView;//this is for rendering image
import javafx.geometry.Rectangle2D;//to calculate position
import javafx.scene.input.KeyCode;//to listen for keyperess




public class core extends Application{
	//variables to control the viewport of the player sprite
	private	int frameHeight=256;
	private	int frameWidth=256;
	private int totalCols=4; 	
	private	int col=0;
	private	int row =0;
        //frame calculation
        private void cycleAnimation(ImageView iv){
                col = (col + 1) % totalCols; //0 1 2 3 0 1 2 3
                Rectangle2D viewport = new Rectangle2D(col * frameWidth, row * frameHeight, frameWidth, frameHeight);
                iv.setViewport(viewport);
        }



	public void start(Stage primaryStage){
		//pane holds all othe nodes
		Pane root=new Pane();
	
		//rendering background
		Image background = new Image("file:background.png");
		ImageView back= new ImageView(background);
		back.setFitWidth(1900);
		back.setFitHeight(1000);
		back.setSmooth(false);
	
		//rendering the horse sprite
		Image spriteSheet = new Image("file:horse.png");
		ImageView player= new ImageView(spriteSheet);
		Rectangle2D viewport = new Rectangle2D(col * frameWidth,row* frameHeight,frameWidth,frameHeight);	
		player.setViewport(viewport);
		player.setFitWidth(94);
		player.setFitHeight(94);
		player.setTranslateX(200);
		player.setTranslateY(200);

                //adding all nodes to root pane
                root.getChildren().add(back);
                root.getChildren().add(player);
                //creates scene with the pane
                Scene scene = new Scene(root,1900,1000);
		

		//animation and movement
        	scene.setOnKeyPressed(event -> {
            		KeyCode key = event.getCode();
            		double speed = 4.0;
            		switch (key){
				case KeyCode.UP:
					row =1;
					player.setTranslateY(player.getTranslateY()-speed);
					cycleAnimation(player);
					break;
				case KeyCode.DOWN:
					row = 3;
					player.setTranslateY(player.getTranslateY() + speed);
					cycleAnimation(player);
					break;
				case KeyCode.LEFT:
					row =2;
					player.setTranslateX(player.getTranslateX() - speed);
					cycleAnimation(player);
					break;
				case KeyCode.RIGHT:
					row =0;
					player.setTranslateX(player.getTranslateX() + speed);
					cycleAnimation(player);
					break;

			}
        	});

		primaryStage.setScene(scene);
		primaryStage.show();
	}	
	public static void main(String[] args){
		launch(args);
	}
}
