package com.privatebusiness.KozySound.configuration;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class DBConfiguration {

	private static final String PROPERTIES_FILE_NAME = "dbconfiguration.properties";
	private PropertiesConfiguration dbconfig;
	
	public DBConfiguration() throws ConfigurationException{
		dbconfig = new PropertiesConfiguration(PROPERTIES_FILE_NAME);
	}
	
	//SET
	
	public void setHost(String host) throws ConfigurationException{
		dbconfig.setProperty("DB.Host", host);
		dbconfig.save();
	}
	
	public void setUser(String user) throws ConfigurationException{
		dbconfig.setProperty("DB.User", user);
		dbconfig.save();
	}
	
	public void setPassword(String password) throws ConfigurationException{
		dbconfig.setProperty("DB.Password", password);
		dbconfig.save();
	}
	
	public void setDatabaseName(String DatabaseName) throws ConfigurationException{
		dbconfig.setProperty("DB.Name", DatabaseName);
		dbconfig.save();
	}
	
	public void setPort(String Port) throws ConfigurationException{
		dbconfig.setProperty("DB.Port", Port);
		dbconfig.save();
	}
	//GET
	
	public String getHost(){
		String dbhost = dbconfig.getString("DB.Host");
		return dbhost;
	}
	
	public String getUser(){
		String dbuser = dbconfig.getString("DB.User");
		return dbuser;
	}
	
	public String getPassword(){
		String dbpassword = dbconfig.getString("DB.Password");
		return dbpassword;
	}
	
	public String getDatabaseName(){
		String dbname = dbconfig.getString("DB.Name");
		return dbname;
	}
	
	public String getPort(){
		String port = dbconfig.getString("DB.Port");
		return port;
	}
}
