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

	public void start(Stage primaryStage){
		//pane holds all othe nodes
		Pane root=new Pane();

		//rendering background
		Image background = new Image("file:background.png");
		ImageView back= new ImageView(background);
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



			
		//animation and movement
        	scene.setOnKeyPressed(event -> {
            		KeyCode key = event.getCode();
            		double speed = 10.0;
            		if (key == KeyCode.RIGHT) {
                		player.setTranslateX(player.getTranslateX() + speed);
                		row = 0; // row facing right
                		cycleAnimation();
                		updateViewport(player);
            		} 
            		else if (key == KeyCode.LEFT) {
                		player.setTranslateX(player.getTranslateX() - speed);
                		row = 1; // this will be changed later for multidirectional movement 
                		cycleAnimation();
                		updateViewport(player);
            		}
        	});

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//frame calculation
    	private void cycleAnimation() {
        	col = (col + 1) % totalCols; //0 1 2 3 0 1 2 3
    	}
    	//updatting viewport
    	private void updateViewport(ImageView iv) {
        	Rectangle2D viewport = new Rectangle2D(col * frameWidth, row * frameHeight, frameWidth, frameHeight);
        	iv.setViewport(viewport);
    	}			
	public static void main(String[] args){
		launch(args);
	}
}
