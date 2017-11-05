package view;

/*
 * Authors: 
 * Chinmoyi Bhushan
 * Deep Kotadia
 */

import java.io.*;
//import java.text.ParseException;
import java.util.*;
import structures.songdetails;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import java.util.Optional;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;
//import javafx.scene.control.ListView;
//import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class PlayListController {
	
	@FXML         
	   ListView<String> listView;      
	   
	@FXML
	   TextArea detailBox;
	
	@FXML
		Label songName,artist,album,year;
	
	@FXML
		Button ok,cancel,edit,delete;
	
	//@FXML
	//public static Button edit,delete;

	@FXML
		TextField nameText,artistText,albumText,yearText;

	   private ObservableList<String> obsList; 
	   public static ArrayList<songdetails> songs = new ArrayList<songdetails>();
	   public static ArrayList<String> nameandartist = new ArrayList<String>();
	   
	   public static int addoredit = 0; //1 for add, 2 for edit
	   
	   ListProperty<String> listProperty = new SimpleListProperty<>();
	  
	   public void start(Stage mainStage) {                
	      // create an ObservableList 
	      // from an ArrayList              
	      obsList = FXCollections.observableArrayList(nameandartist);               
	      listView.setItems(obsList);  
	      
	      // select the first song and display its details
	      if(songs.size() > 0) {
	    	  	  listView.getSelectionModel().select(0);
			  detailBox.setText("Song Name: " + songs.get(0).getname() + "\n" + "Song Artist: " + songs.get(0).getartist() + "\n" + "Song Album: " + songs.get(0).getalbum() + "\n" + "Song Year: " + songs.get(0).getyear());
	    	  	  edit.setVisible(true);
			  delete.setVisible(true);
	      }


	   }
	   
	   private void showItem(Stage mainStage) {                
		   Alert alert = 
				   new Alert(AlertType.INFORMATION);
		   alert.initOwner(mainStage);
		   alert.setTitle("List Item");
		   alert.setHeaderText(
				   "Selected list item properties");

		   String content = "Index: " + 
				   listView.getSelectionModel()
		   .getSelectedIndex() + 
		   "\nValue: " + 
		   listView.getSelectionModel()
		   .getSelectedItem();

		   alert.setContentText(content);
		   alert.showAndWait();
	   }
	   
	   @FXML
	   public void displayDetail(MouseEvent arg0 ) {
		   songdetails song = songs.get(listView.getSelectionModel().getSelectedIndex());
		   detailBox.setText("Song Name: " + song.getname() + "\n" + "Song Artist: " + song.getartist() + "\n" + "Song Album: " + song.getalbum() + "\n" + "Song Year: " + song.getyear());
		   
	   }
	   
	   @FXML
	   private void addDisplay(MouseEvent event) {
		   songName.setVisible(true);
		   artist.setVisible(true);
		   album.setVisible(true);
		   year.setVisible(true);
		   ok.setVisible(true);
		   cancel.setVisible(true);
		   nameText.setVisible(true);
		   artistText.setVisible(true);
		   albumText.setVisible(true);
		   yearText.setVisible(true);
		   nameText.setText("");
		   artistText.setText("");
		   albumText.setText("");
		   yearText.setText("");
		   
		   addoredit = 1;   
	   }
	   
	   @FXML
	   private void editDisplay(MouseEvent event) {
		   int selectedsongindex = listView.getSelectionModel().getSelectedIndex();
		   songdetails selectedsong = songs.get(selectedsongindex);
		   songName.setVisible(true);
		   artist.setVisible(true);
		   album.setVisible(true);
		   year.setVisible(true);
		   ok.setVisible(true);
		   cancel.setVisible(true);
		   nameText.setVisible(true);
		   artistText.setVisible(true);
		   albumText.setVisible(true);
		   yearText.setVisible(true);
		   nameText.setText(selectedsong.getname());
		   artistText.setText(selectedsong.getartist());
		   albumText.setText(selectedsong.getalbum());
		   yearText.setText(selectedsong.getyear());
		   
		   addoredit = 2;
	   }
	   
	   @FXML
	   private void deleteDisplay(MouseEvent event) {
		   songName.setVisible(false);
		   artist.setVisible(false);
		   album.setVisible(false);
		   year.setVisible(false);
		   if(handleDeleteFunction() == 1) {
			   return;
		   }
		   obsList=FXCollections.observableArrayList(nameandartist);
		   listView.setItems(obsList);
		   //listView.getItems().remove(listView.getSelectionModel().getSelectedIndex());
		   //listView.refresh();
	   }
	
	   @FXML
	   private void cancelFunction(MouseEvent event) {
		   
		   songName.setVisible(false);
		   artist.setVisible(false);
		   album.setVisible(false);
		   year.setVisible(false);
		   nameText.setVisible(false);
		   artistText.setVisible(false);
		   albumText.setVisible(false);
		   yearText.setVisible(false);
		   ok.setVisible(false);
		   cancel.setVisible(false);
		   
		   addoredit = 0;
	   }
	   
	   @FXML
	   private void okFunction(MouseEvent event) {
		   listView.refresh();
		   //String currname, currartist, curralbum, curryear;
		   
		   if(addoredit == 1) { //call coming from add function
			   //while(true) {
				   if(handleAddFunction() == 1) {
					   return;
				   }
			   //}
		   }
		   else if(addoredit == 2) { //call coming from edit function
			   //while(true) {
				   if(handleEditFunction(listView.getSelectionModel().getSelectedIndex()) == 1) {
					   return;
				   }
			   //}
		   }
		   
		   songName.setVisible(false);
		   artist.setVisible(false);
		   album.setVisible(false);
		   year.setVisible(false);
		   nameText.setVisible(false);
		   artistText.setVisible(false);
		   albumText.setVisible(false);
		   yearText.setVisible(false);
		   ok.setVisible(false);
		   cancel.setVisible(false);
		   
		   addoredit = 0;
		   listView.refresh();
		   obsList=FXCollections.observableArrayList(nameandartist);
		   listView.setItems(obsList);
		   
		   
	   }
	   
	   
	   /*
	    * checks for songs uniqueness and field emptiness
	    * and if all cases are passed, successfully adds 
	    * new song to arraylist
	    */
	   public int handleAddFunction() {
		   
		   String newsongname;
		   String newsongartist;
		   String newsongalbum, newsongyear;
		   
		   if(nameText.getText() == null || nameText.getText().trim().isEmpty()) {
			   Alert alert = new Alert(AlertType.ERROR);
			   alert.setTitle("Error Dialog");
			   alert.setHeaderText("Song Name field cannot be empty!");
			   alert.setContentText("Enter a Song Name");

			   //alert.showAndWait();
			   Optional<ButtonType> buttonClicked=alert.showAndWait();
			   if (buttonClicked.get()==ButtonType.OK) {
				   alert.close();
			   }
			   return 1; //function ended in error state, so needs to be rerun 
		   }
		   else {
			   newsongname = nameText.getText();
		   }
		   
		   if(artistText.getText() == null || artistText.getText().trim().isEmpty()) {
			   Alert alert = new Alert(AlertType.ERROR);
			   alert.setTitle("Error Dialog");
			   alert.setHeaderText("Song Artist field cannot be empty!");
			   alert.setContentText("Enter a Artist Name");

			   Optional<ButtonType> buttonClicked=alert.showAndWait();
			   if (buttonClicked.get()==ButtonType.OK) {
				   alert.close();
			   }
			   return 1; //function ended in error state, so needs to be rerun 
		   }
		   else {
			   newsongartist = artistText.getText();
		   }
		   
		   if(albumText.getText() == null || albumText.getText().trim().isEmpty()) {
			   newsongalbum = "";
		   }
		   else {
			   newsongalbum = albumText.getText();
		   }
		   
		   if(yearText.getText() == null || yearText.getText().trim().isEmpty()) {
			   newsongyear = "";
		   }
		   else {
			   newsongyear = yearText.getText();
		   }
		   
		   /*check for song uniqueness*/
		   for(int i = 0; i < songs.size(); i++) {
			   if(songs.get(i).getname().equals(newsongname) && songs.get(i).getartist().equals(newsongartist)) {
				   Alert alert = new Alert(AlertType.ERROR);
				   alert.setTitle("Error Dialog");
				   alert.setHeaderText("Song is not Unique!");
				   alert.setContentText("Enter a unique combination of song name and artist");

				   alert.showAndWait();
				   return 1; //function ended in error state, so needs to be rerun 
			   }
		   }
		   
		   /*This song is unique, so add it in main arraylist*/
		   songdetails newsong = new songdetails(newsongname, newsongartist, newsongalbum, newsongyear);
		   songs.add(newsong);
		   sort_and_transfer_to_subarray();
		   
		   if(songs.size() > 0) {
				edit.setVisible(true);
				delete.setVisible(true);
	       }
		   /*select and display details of newly added song*/
		   detailBox.setText("Song Name: " + newsong.getname() + "\n" + "Song Artist: " + newsong.getartist() + "\n" + "Song Album: " + newsong.getalbum() + "\n" + "Song Year: " + newsong.getyear());
		   listView.getSelectionModel().select(songs.indexOf(newsong));
		   addoredit = 0;
		   return 0; //function ended successfully
	   }
	   
	   
	   public int handleEditFunction(int songindex) {
		   
		   String editedsongname;
		   String editedsongartist;
		   String editedsongalbum, editedsongyear;
		   
		   if(nameText.getText() == null || nameText.getText().trim().isEmpty()) {
			   Alert alert = new Alert(AlertType.ERROR);
			   alert.setTitle("Error Dialog");
			   alert.setHeaderText("Song Name field cannot be empty!");
			   alert.setContentText("Enter a Song Name");

			   alert.showAndWait();
			   return 1; //function ended in error state, so needs to be rerun 
		   }
		   else {
			   editedsongname = nameText.getText();
		   }
		   
		   if(artistText.getText() == null || artistText.getText().trim().isEmpty()) {
			   Alert alert = new Alert(AlertType.ERROR);
			   alert.setTitle("Error Dialog");
			   alert.setHeaderText("Song Artist field cannot be empty!");
			   alert.setContentText("Enter a Artist Name");

			   alert.showAndWait();
			   return 1; //function ended in error state, so needs to be rerun 
		   }
		   else {
			   editedsongartist = artistText.getText();
		   }
		   
		   if(albumText.getText() == null || albumText.getText().trim().isEmpty()) {
			   editedsongalbum = "";
		   }
		   else {
			   editedsongalbum = albumText.getText();
		   }
		   
		   if(yearText.getText() == null || yearText.getText().trim().isEmpty()) {
			   editedsongyear = "";
		   }
		   else {
			   editedsongyear = yearText.getText();
		   }
		   
		   /*check for song uniqueness*/
		   for(int i = 0; i < songs.size(); i++) {
			   if(songs.get(i).getname().equals(editedsongname) && songs.get(i).getartist().equals(editedsongartist)) {
				   Alert alert = new Alert(AlertType.ERROR);
				   alert.setTitle("Error Dialog");
				   alert.setHeaderText("Song is not Unique!");
				   alert.setContentText("Enter a unique combination of song name and artist");

				   alert.showAndWait();
				   return 1; //function ended in error state, so needs to be rerun 
			   }
		   }
		   
		   songdetails editedsong = new songdetails(editedsongname, editedsongartist, editedsongalbum, editedsongyear);
		   songs.set(songindex, editedsong);
		   sort_and_transfer_to_subarray();
		   detailBox.setText("Song Name: " + editedsong.getname() + "\n" + "Song Artist: " + editedsong.getartist() + "\n" + "Song Album: " + editedsong.getalbum() + "\n" + "Song Year: " + editedsong.getyear());
		   listView.getSelectionModel().select(songs.indexOf(editedsong));
		   addoredit = 0;
		   return 0; //function ended successfully
		   
	   }
	   
	   
	   public int handleDeleteFunction() {
		   int songindex = listView.getSelectionModel().getSelectedIndex();
		   
		   Alert alert = new Alert(AlertType.CONFIRMATION);
		   alert.setTitle("Confirm Delete");
		   alert.setHeaderText(null);
		   alert.setContentText("Are you sure you want to delete this song?");

		   Optional<ButtonType> result = alert.showAndWait();
		   if (result.get() == ButtonType.OK) { // ... user chose OK
			   songs.remove(songindex);
			   sort_and_transfer_to_subarray();
			   
			   if(songs.size() == 0) {
					edit.setVisible(false);
					delete.setVisible(false);
					detailBox.clear();
		       }
			   else {
				   int lastsongindex = songs.size();
				   if(songs.size() == 1) { //only one song remaining in list, so select it and display its details
					   listView.getSelectionModel().select(0);
					   detailBox.setText("Song Name: " + songs.get(0).getname() + "\n" + "Song Artist: " + songs.get(0).getartist() + "\n" + "Song Album: " + songs.get(0).getalbum() + "\n" + "Song Year: " + songs.get(0).getyear());
				   }
				   else if(songindex == lastsongindex) { //deleted song was last song in the list, so select previous song, previous song is now last song
					   listView.getSelectionModel().select(lastsongindex-1);
					   detailBox.setText("Song Name: " + songs.get(lastsongindex-1).getname() + "\n" + "Song Artist: " + songs.get(lastsongindex-1).getartist() + "\n" + "Song Album: " + songs.get(lastsongindex-1).getalbum() + "\n" + "Song Year: " + songs.get(lastsongindex-1).getyear());
				   }
				   else { //not the last song, so select next song
					   listView.getSelectionModel().select(songindex);
					   detailBox.setText("Song Name: " + songs.get(songindex).getname() + "\n" + "Song Artist: " + songs.get(songindex).getartist() + "\n" + "Song Album: " + songs.get(songindex).getalbum() + "\n" + "Song Year: " + songs.get(songindex).getyear());
				   }
			   }
			   
			   
			   //listView.refresh();
		   } else { // ... user chose CANCEL or closed the dialog
			   return 1;
		   }
		   return 0;
	   }
	  
	
	
	public static void read_from_jsonfile() {
		
		JSONParser parser = new JSONParser();
		
		try {
		JSONArray jsonarr = (JSONArray) parser.parse(new FileReader("allsongs.json"));

		  for (Object o : jsonarr)
		  {
			
		    JSONObject onesongobj = (JSONObject) o;

		    String name = (String) onesongobj.get("name");
		    String artist = (String) onesongobj.get("artist");		    
		    String album = (String) onesongobj.get("album");
		    String year = (String) onesongobj.get("year");
		    
		    songdetails onesong = new songdetails(name, artist, album, year);
		    songs.add(onesong);

		  }
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}	

	}
	
	
	@SuppressWarnings("unchecked")
	public void write_to_jsonfile() {
		
		JSONArray allsongslist = new JSONArray();		
		
		for(int i = 0; i < songs.size(); i++) {
			
			JSONObject singlesongobj = new JSONObject();
			
			singlesongobj.put("name", songs.get(i).getname());
			singlesongobj.put("artist", songs.get(i).getartist());
			singlesongobj.put("album", songs.get(i).getalbum());
			singlesongobj.put("year", songs.get(i).getyear());
			
			allsongslist.add(singlesongobj);
			
		}
		
		try(FileWriter file = new FileWriter("allsongs.json")){
			file.write(allsongslist.toString());
			file.flush();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Parses through main Arraylist- songs, sorts it by name
	 * and transfers name and artist of each song
	 * as a String to songsandartist array
	 */
	public static void sort_and_transfer_to_subarray() {
		
		/*sort the arraylist based on song name/artist*/
		Collections.sort(songs, new Comparator<songdetails>() {
		    public int compare(songdetails song1, songdetails song2) {
		    		if(song1.getname().equalsIgnoreCase(song2.getname())) {
		    			return song1.getartist().compareToIgnoreCase(song2.getartist());
		    		}
		    		return song1.getname().compareToIgnoreCase(song2.getname());
		    }
		});
		
		nameandartist.clear();
		
		/*transfer name and artist to sub-arraylist*/
		for(int i = 0; i < songs.size(); i++) {
			nameandartist.add(songs.get(i).getname() + ", " + songs.get(i).getartist());
		}
		
	}
	
}
