package com.privatebusiness.KozySound;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.configuration.ConfigurationException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.privatebusiness.KozySound.configuration.DBConfiguration;
import com.privatebusiness.KozySound.controller.MainController;
import com.privatebusiness.KozySound.lib.DBManager;
import com.privatebusiness.KozySound.lib.HibernateUtil;
import com.privatebusiness.KozySound.model.Album;
import com.privatebusiness.KozySound.model.Artist;
import com.privatebusiness.KozySound.model.MainModel;
import com.privatebusiness.KozySound.model.Track;
import com.privatebusiness.KozySound.view.MainView;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			DBConfiguration test = new DBConfiguration();
			test.setHost("localhost");
			test.setUser("root");
			test.setPort("3306");
			test.setDatabaseName("mpr");
			
			System.out.println("Host: " + test.getHost());
			System.out.println("User: " + test.getUser());
			System.out.println("Password: " + test.getPassword());
			System.out.println("Port: " + test.getPort());
			System.out.println("DBNAME: " + test.getDatabaseName());
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		DBManager test = DBManager.getIstance();
		if(test.getConnection() == null){
			System.out.println("fail");
		}else{
			System.out.println("work");
		}
		
		Album al = new Album("test moj album");
		Artist ar = new Artist("Ja jestem artysta");
		
		test.insertArtistAlbum(al, ar);
		
		test.getArtistAlbum();
		
		for(Artist cos: test.getArtistAlbum()){
			System.out.println("Artysta: " + cos.getName());
			System.out.println("Album: " + cos.getAlbum().getAlbumName());
		
		}
	
		
		
		/*
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		
		try{
			transaction = session.beginTransaction();
			
			Set<Track> tracks = new HashSet<Track>();
			tracks.add(new Track("Tytul 1"));
			tracks.add(new Track("Nowy Tytu³"));
			tracks.add(new Track("fajna piosenka"));
			tracks.add(new Track("ulubione"));
			
			Album album1 = new Album("Album 1", 1990, tracks);
			Album album2 = new Album("Fajny album", 2020, tracks);
			session.save(album1);
			session.save(album2);
			
			transaction.commit();
			
			
		}catch(HibernateException e){
			transaction.rollback();
			e.printStackTrace();
		}finally{
			session.close();
		}*/
	
		//MainModel model = new MainModel();
		//MainView view = new MainView(model);
		//MainController controller = new MainController(model, view);
		
		//view.setVisible(true);

	}

}
