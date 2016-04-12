package client.controllers;

import java.util.HashMap;

import mongodB.iot.MongoOperations;
import reourcemodel.iot.ClientDetails;
import reourcemodel.iot.ClientMain;

public class Client1Bootstrap {

	static String bootstrapURL = "http://localhost:7979/Bootstrap/a/acknowledgeclient/post";
	final static String collection = "register";
	final static String databaseName = "client1";
	
	public static boolean init(){
		
		setSerialID(); 
		ClientDetails reg = new MongoOperations().readRecord(
				ClientDetails.class,"client1", "register");
		RestHelper rHelp = new RestHelper();
		HashMap<String,String> hMap = rHelp.getMap(bootStrapServer(reg));
		Client1.endServerUrl = hMap.get("serverURL");
		return true;	
	}

	private static void setSerialID() {
		
		ClientMain reg = new MongoOperations().readRecord(ClientMain.class,"client1", "main");	 
		 Client1.serialID = reg.getSerialID();
		 bootstrapURL = reg.getBootstrapURL();
		
	}

	private static String bootStrapServer(ClientDetails reg) {

		
		HashMap<String,String> btServer = new HashMap<String,String>();
		btServer.put("serialID", reg.getSerialNo());
		btServer.put("firmwareVersion", reg.getFirmware());
		btServer.put("soldLocation", "Canada");
		btServer.put("presentLocation", "US");
		
		Connection conn = new Connection();
		try {
		conn.acquire();
		conn.setURL(bootstrapURL);
		conn.setRequestType("POST");
		conn.setBody(btServer);
		return conn.connect();
		}
		catch(Exception e){e.printStackTrace();}
		return "";
	}
	
	
}
