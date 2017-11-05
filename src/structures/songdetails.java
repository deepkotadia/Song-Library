package structures;

/*
 * Authors: 
 * Chinmoyi Bhushan
 * Deep Kotadia
 */

public class songdetails {
	
	private String name;
	private String artist;
	private String album;
	private String year;

	
//constructor
	public songdetails(String name, String artist, String album, String year) {
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}

	
//getter methods
	public String getname() {
		return this.name;
	}
	
	public String getartist() {
		return this.artist;
	}
	
	public String getalbum() {
		return this.album;
	}
	
	public String getyear() {
		return this.year;
	}
	
	
//setter methods
	public void setname(String name) {
		this.name = name;
	}
	
	public void setartist(String artist) {
		this.artist = artist;
	}
	
	public void setalbum(String album) {
		this.album = album;
	}
	
	public void setyear(String year) {
		this.year = year;
	}
	
}

