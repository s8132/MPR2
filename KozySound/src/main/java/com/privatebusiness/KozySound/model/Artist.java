package com.privatebusiness.KozySound.model;

public class Artist {
	
	private String name;
	private Album album = new Album();
	
	public Artist(){
		this.album = new Album();
	}
	
	public Artist(String name){
		this.name = name;
	}
	
	//SET
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setAlbum(Album album){
		this.album = album;
	}
	//GET
	
	public String getName(){
		return this.name;
	}
	
	public Album getAlbum(){
		return this.album;
	}

}
