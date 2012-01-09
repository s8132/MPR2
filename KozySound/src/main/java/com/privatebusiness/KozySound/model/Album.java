package com.privatebusiness.KozySound.model;

import java.util.HashSet;
import java.util.Set;

public class Album implements java.io.Serializable {
	private int albumId;
	private String albumName;
	private int albumYear;
	private Set<Track> tracks = new HashSet<Track>(0);
	private Track track = new Track();
	
	public Album(){
	}
	public Album(String albumName){
		this.albumName = albumName;
	}
	public Album(String albumName, int albumYear){
		this.albumName = albumName;
		this.albumYear = albumYear;
	}
	public Album(String albumName, int albumYear, Set<Track> tracks){
		this.albumName = albumName;
		this.albumYear = albumYear;
		this.tracks = tracks;
	}
	public int getAlbumId() {
		return albumId;
	}
	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public int getAlbumYear() {
		return albumYear;
	}
	public void setAlbumYear(int albumYear) {
		this.albumYear = albumYear;
	}
	public Set<Track> getTracks() {
		return tracks;
	}
	public void setTracks(Set<Track> tracks) {
		this.tracks = tracks;
	}
	public String getTrackName(){
		return track.getTrackName();
	}
}
