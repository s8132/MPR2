package com.privatebusiness.KozySound.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.privatebusiness.KozySound.model.*;

import org.apache.commons.configuration.ConfigurationException;

import com.privatebusiness.KozySound.configuration.DBConfiguration;

public class DBManager {

	private static volatile DBManager instance = null;
	
	private DBConfiguration config; 
	private String dbhost;
	private String dbuser;
	private String dbpassword;
	private String dbname;
	private String dbport;
	
	private Connection connection;
	private PreparedStatement getAllTracksStmt;
	private PreparedStatement getAllAlubmsStmt;
	private PreparedStatement getAllArtistStmt;
	private PreparedStatement addTrackStmt;
	private PreparedStatement addAlbumStmt;
	private PreparedStatement addArtistStmt;
	private PreparedStatement getArtistAlbums;
	
	private DBManager(){
		
		try {
			config = new DBConfiguration();
		} catch (ConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.dbhost = config.getHost();
		this.dbuser = config.getUser();
		this.dbpassword = config.getPassword();
		this.dbname = config.getDatabaseName();
		this.dbport = config.getPort();
		
		try{
			//"jdbc:mysql://host:port/databaseName", "user", "password"
			//String adres = "jdbc:mysql://localhost:3306/mpr";
			String url="jdbc:mysql://" + this.dbhost + ":" + this.dbport + "/" + this.dbname;
			connection = DriverManager.getConnection(url, "root", "");
		
			getAllTracksStmt = connection.prepareStatement("SELECT * FROM track");
			getAllAlubmsStmt = connection.prepareStatement("SELECT * FROM albums");
			getAllArtistStmt = connection.prepareStatement("SELECT * FROM artists");
			
			addTrackStmt = connection.prepareStatement("INSERT INTO track (title, type, number_of_album) VALUES(?, ?, ?)");
			addAlbumStmt = connection.prepareStatement("INSERT INTO albums (name, year) VALUES(?, ?)");
			addArtistStmt = connection.prepareStatement("INSERT INTO artists (name) VALUES(?)");
			
			getArtistAlbums = connection.prepareStatement(
					"SELECT artists.name, albums.name, albums.year FROM artists_albums" +
					" LEFT JOIN artists ON artists_albums.id_artists=artists.id" +
					" LEFT JOIN albums ON artists_albums.id_albums=albums.id");
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static DBManager getIstance(){
		if(instance == null){
			synchronized(DBManager.class){
				if(instance == null){
					instance = new DBManager();
				}
			}
		}
		return instance;
	}
	
	public Connection getConnection(){
		return connection;
	}
	
	/*
	 *	POBIERANIE Z BAZY!
	 */
	public List<Track> getAllTracks(){
		List<Track> tracks = new ArrayList<Track>();
		try{
			ResultSet rs = getAllTracksStmt.executeQuery();
			
			while(rs.next()){
				Track t = new Track();
				t.setTrackName(rs.getString("title"));
				t.setTrackType(rs.getString("type"));
				t.setTrackNumber(rs.getInt("number_of_album"));
				tracks.add(t);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return tracks;
	}
	
	public List<Album> getAllAlbum(){
		List<Album> albums = new ArrayList<Album>();
		
		try {
			ResultSet rs = getAllAlubmsStmt.executeQuery();
			while(rs.next()){
				Album a = new Album();
				a.setAlbumName(rs.getString("name"));
				a.setAlbumYear(rs.getInt("year"));
				albums.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return albums;
	}
	
	public List<Artist> getAllArtist(){
		List<Artist> artist = new ArrayList<Artist>();
		
		try {
			ResultSet rs = getAllArtistStmt.executeQuery();
			while(rs.next()){
				Artist a = new Artist();
				a.setName(rs.getString("name"));
				artist.add(a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return artist;
	}
	
	public void insertArtistAlbum(Album album, Artist artist){
		//this.addAlbum(album);
		//this.addArtist(artist);
		
		int albumid=0, artistid=0;
		
		try {			
			PreparedStatement stm = connection.prepareStatement("SELECT * FROM albums order by id desc limit 1");
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				albumid = rs.getInt("id");
				//System.out.println("Album id" + albumid);
			}
			
			PreparedStatement stm2 = connection.prepareStatement("SELECT * FROM artists order by id desc limit 1");
			ResultSet rs2 = stm2.executeQuery();
			while(rs2.next()){
				artistid = rs2.getInt("id");
				//System.out.println("Artist id" + artistid);
			}
			
			PreparedStatement stm3 = connection.prepareStatement("INSERT INTO artists_albums (id_artists, id_albums) VALUES(?, ?)");
			stm3.setInt(1, artistid);
			stm3.setInt(2, albumid);
			stm3.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<Artist> getArtistAlbum(){		
		//List<Album> album = new ArrayList<Album>();
		List<Artist> artist = new ArrayList<Artist>();
		
		try {
			ResultSet rs = getArtistAlbums.executeQuery();
			while(rs.next()){
				Album a = new Album(rs.getString("albums.name"));
				Artist art = new Artist();
				art.setName(rs.getString("name"));
				art.setAlbum(a);
				artist.add(art);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return artist;
	}
	
	/*
	 *	WSTAWIANIE DO BAZY
	 */
	
	public int addTrack(Track track){
		int count = 0;
		
		try {
			addTrackStmt.setString(1, track.getTrackName());
			addTrackStmt.setString(2, track.getTrackType());
			addTrackStmt.setInt(3, track.getTrackNumber());
			
			count = addTrackStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public int addAlbum(Album album){
		int count = 0;
		try {
			addAlbumStmt.setString(1, album.getAlbumName());
			addAlbumStmt.setInt(2, album.getAlbumYear());
			
			count = addAlbumStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public int addArtist(Artist artist){
		int count = 0;
		
		try {
			addArtistStmt.setString(1, artist.getName());
			
			count = addArtistStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	/*
	 * USUWANIE Z BAZY
	 */
	
	public int deleteTrack(String title){
		int count = 0;
		
		String sql = "DELETE FROM track WHERE title = 'title'";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			count = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return count;
	}
	
	public int deleteAlbum(String name){
		int count = 0;
		
		String sql = "DELETE FROM albums WHERE name = 'name'";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			count = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	public int deleteArtist(String name){
		int count = 0;
		
		String sql = "DELETE FROM artists WHERE name = 'name'";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			count = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	public void saveTrackInAlbum(Album album){
		try {
			connection.setAutoCommit(false);
			
			PreparedStatement stm = connection.prepareStatement("");
			stm.executeUpdate();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveAlbumInTrack(Track track){
		
	}
}
