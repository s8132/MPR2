package com.privatebusiness.KozySound.model;

import java.util.HashSet;
import java.util.Set;

public class Track implements java.io.Serializable {
	private int trackId;
	private String trackName;
	private String trackType;
	private int trackNumber;
	private Set<Album> albums = new HashSet<Album>();
	
	public Track(){
		
	}
	public Track(String trackName){
		this.trackName = trackName;
	}
	public Track(String trackName, String trackType){
		this.trackName = trackName;
		this.trackType = trackType;
	}
	public Track(String trackName, String trackType, int trackNumber){
		this.trackName = trackName;
		this.trackType = trackType;
		this.trackNumber = trackNumber;
	}
	public int getTrackId() {
		return trackId;
	}
	public void setTrackId(int trackId) {
		this.trackId = trackId;
	}
	public String getTrackName() {
		return trackName;
	}
	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}
	public String getTrackType() {
		return trackType;
	}
	public void setTrackType(String trackType) {
		this.trackType = trackType;
	}
	public int getTrackNumber() {
		return trackNumber;
	}
	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}
	
	
}
