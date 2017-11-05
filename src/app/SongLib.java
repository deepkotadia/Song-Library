package app;

/*
 * Authors: 
 * Chinmoyi Bhushan
 * Deep Kotadia
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import structures.songdetails;
import view.PlayListController;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import java.util.*;



public class SongLib extends Application {
	
	@Override
	public void start(Stage primaryStage) 
		throws Exception {
			FXMLLoader loader= new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/PlaylistView.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			
			PlayListController listController = loader.getController();
		    listController.start(primaryStage);
			
			Scene scene = new Scene(root,700,500);
			primaryStage.setScene(scene);
			primaryStage.show();
			
			/*write to json file once application is exited, for persistence*/
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>(){
				public void handle(WindowEvent we) {
					listController.write_to_jsonfile();
					System.out.println("Stage is closing");
				}
			});
		}
	
	public static void main(String[] args) {
		
		PlayListController.read_from_jsonfile();
		PlayListController.sort_and_transfer_to_subarray();
		
		launch(args);
		
		//launch(args);
	}
}
