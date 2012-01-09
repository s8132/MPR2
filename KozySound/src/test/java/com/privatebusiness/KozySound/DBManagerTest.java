package com.privatebusiness.KozySound;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.privatebusiness.KozySound.lib.DBManager;
import com.privatebusiness.KozySound.model.Album;
import com.privatebusiness.KozySound.model.Artist;
import com.privatebusiness.KozySound.model.Track;


public class DBManagerTest {

	DBManager dbManager = DBManager.getIstance();
	
	//Artist
	private final static String nameArtist = "Scorpions";
	
	//Album
	private final static String nameAlbum = "Comeblack";
	private final static int year = 2001;
	
	//Track
	private final static String title = "The Zoo";
	private final static String type = "Hard Rock";
	private final static int number = 3;
	
	@Test
	public void checkConnection(){
		assertNotNull(dbManager.getConnection());
	}
	
	@Test
	public void checkAddingTrack(){
		
		//Track track = new Track(title, number, type);
		
		//assertEquals(1, dbManager.addTrack(track));
		
		List<Track> tracks = dbManager.getAllTracks();
		Track trackRetrieved = tracks.get(0);
		
		//assertEquals(title, trackRetrieved.getTitle());
		//assertEquals(type, trackRetrieved.getType());
		//assertEquals(number, trackRetrieved.getNumber());
	}
	
	@Test
	public void checkAddingAlbum(){
		
		Album album = new Album(nameAlbum, year);
		
		assertEquals(1, dbManager.addAlbum(album));
		
		List<Album> albums = dbManager.getAllAlbum();
		Album albumRetrieved = albums.get(0);
		
		//assertEquals(nameAlbum, albumRetrieved.getName());
		//assertEquals(year, albumRetrieved.getYear());
	}
	
	@Test
	public void checkAddingArtist(){
		
		Artist artist = new Artist(nameArtist);
		
		assertEquals(1, dbManager.addArtist(artist));
		
		List<Artist> artists = dbManager.getAllArtist();
		Artist artistRetrived = artists.get(0);
		
		assertEquals(nameArtist, artistRetrived.getName());
		
	}
}
