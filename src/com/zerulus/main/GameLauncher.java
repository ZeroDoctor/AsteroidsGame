package com.zerulus.main;



import java.io.InputStream;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.applet.Main;

public class GameLauncher extends Application {
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private Button btnPlay;
	private Button btnExit;
	
	private Label lbRez;
	ComboBox<String> cbRez;
	
	private int xOffset = 0;
	private int yOffset = 0;
	
	
	public void start(Stage ps) {
		
		Pane pane = new Pane();
		
		ImageView img = null;
		
		img = setBackground(Main.class.getResourceAsStream("/GameLauncher/Asteroids-Screen-Shot.gif"),WIDTH, HEIGHT);
		
		btnPlay = setButton("Play", 200, 200, 150, true);
		btnExit = setButton("Exit", 200, 100, 150, false);
		
		lbRez = setLabel("Resolution: ", 300, 25, Color.WHITE, Font.font(12));
		ObservableList<String> options = FXCollections.observableArrayList("640x480","800x600","1024x768","1280x960","1920x1440");
		cbRez = new ComboBox<>(options);
		
		cbRez.setLayoutX(WIDTH / 2 - 300);
		cbRez.setLayoutY(HEIGHT / 2);
		
		
		
		btnPlay.setOnAction(event -> {
			if(cbRez.getValue() != null) {
				String rez = cbRez.getValue();
				int x = Integer.parseInt(rez.substring(0, rez.indexOf("x")));
				int y = Integer.parseInt(rez.substring(rez.indexOf("x") + 1, rez.length()));
				GamePanel.WIDTH = x;
				GamePanel.HEIGHT = y;
				
				new Game();
				ps.close();
				
			} else {
				System.out.println("Please set resolution");
			}
		});
		
		btnExit.setOnAction(event -> System.exit(0));
		
		
		
		pane.getChildren().addAll(img, btnPlay, btnExit, lbRez, cbRez);
		
		Scene scene = new Scene(pane, WIDTH, HEIGHT);
		scene.getStylesheets().add("/GameLauncher/styleSheet.css");
		
		/**
		 * Mouse events still need a little work
		 * */
		scene.setOnMousePressed(event -> {
			xOffset = (int) (ps.getX() - event.getScreenX());
			yOffset = (int) (ps.getY() - event.getScreenY());
			
		});
		
		scene.setOnMouseDragged(event -> {
			ps.setX(event.getScreenX() + xOffset);
			ps.setY(event.getSceneY() + yOffset);
		});
		
		
		ps.setTitle("TheBestVersionOfAsteroidsGameEver");
		ps.setScene(scene);
		ps.initStyle(StageStyle.UNDECORATED);
		ps.show();
	}
	
	public Button setButton(String name, int x, int y, int size, boolean getOption) {
		
		Button btn = new Button(name);
		
		btn.setLayoutX(WIDTH - x);
		btn.setLayoutY(HEIGHT - y);
		btn.setMinWidth(size);
		btn.setMinHeight(25);
		
		/**
		 * Could we do something related to 
		 * events here?
		 */
		
		/*if(getOption) {
			if(cbRez.getValue() != null) {
				String rez = cbRez.getValue();
				int x = 
			}
		} else {
			btn.setOnAction(event -> System.exit(0));
		}
		*/
		return btn;
	}
	
	public Label setLabel(String name, int x, int y, Color color, Font font) {
		Label lb = new Label(name);
		lb.setTextFill(color);
		lb.setFont(font);
		lb.setLayoutX(WIDTH / 2 - x);
		lb.setLayoutY(HEIGHT / 2 - y);
		
		return lb;
	}
	
	public ImageView setBackground(InputStream path, int width, int height) {
		Image img = new Image(path);
		ImageView viewImg = new ImageView(img);
		
		viewImg.setFitHeight(height);
		viewImg.setFitWidth(width);
		
		return viewImg;
	}
	
	public static void main(String args[]) {
		launch(args);
	}
	
}
