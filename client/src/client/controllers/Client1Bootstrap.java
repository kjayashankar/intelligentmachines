package client.controllers;

import java.util.HashMap;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import jay.iot.client1.db.ClientMain;
import jay.iot.client1.db.MClient;
import jay.iot.commons.ClientRegDetails;
import jay.iot.commons.Constants;
import jay.iot.conn.Connection;

public class Client1Bootstrap {

	static String bootstrapURL = "http://localhost:7979/Bootstrap/a/acknowledgeclient/post";
	final static String collection = "register";
	final static String databaseName = "client1";
	
	public static boolean init(){
		
		setSerialID(); 
		ClientRegDetails reg = new MClient().getDBRecord("jay.iot.commons.ClientRegDetails", "register");
		RestHelper rHelp = new RestHelper();
		HashMap<String,String> hMap = rHelp.getMap(bootStrapServer(reg));
		Client1.endServerUrl = hMap.get("serverURL");
		return true;	
	}

	private static void setSerialID() {
		
		ClientMain reg = new MClient().getDBRecord("jay.iot.client1.db.ClientMain", "main");	 
		 Client1.serialID = reg.getSerialID();
		 bootstrapURL = reg.getBootstrapURL();
		
	}

	private static String bootStrapServer(ClientRegDetails reg) {

		
		HashMap<String,String> btServer = new HashMap<String,String>();
		btServer.put("serialID", reg.getSerialNumber());
		btServer.put("firmwareVersion", reg.getFirmwareVersion());
		btServer.put("soldLocation", "Canada");
		btServer.put("presentLocation", "US");
		
		Connection conn = new Connection();
		try {
		conn.acquire();
		conn.setURL(bootstrapURL);
		conn.setRequestType(Constants.POST);
		conn.setBody(btServer);
		return conn.connect();
		}
		catch(Exception e){e.printStackTrace();}
		return "";
	}
	
	
}
