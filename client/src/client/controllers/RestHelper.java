package client.controllers;

import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import mongodB.iot.MongoOperations;
import reourcemodel.iot.ClientDetails;
import reourcemodel.iot.ServerDetails;

public class RestHelper {

	public static String databaseName = "client1";
	
	public static String collection = "personal";
	
	@SuppressWarnings("unchecked")
	public HashMap<String,String> getMap(String badString) {

		HashMap<String, String> hm = new HashMap<String, String>();
		try {
			hm = new ObjectMapper().readValue(badString,hm.getClass());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hm;
		
	}

	public ClientDetails getDBRecord() {
		
		return new MongoOperations().readRecord(ClientDetails.class,"client1", "personal");
	}

	public HashMap<String, String> getNecessaryFields(ClientDetails client) {
		// TODO Auto-generated method stub
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("manufacturer", client.getManufacturer());
		
		
		return hMap;
		
	}

	public void registerServer(HashMap<String, String> responseMap) {
		Client1.unregisterUrl = fetchValues(responseMap, "unregisterUrl");
		Client1.communicationUrl = fetchValues(responseMap, "communicationurl");
		Client1.serverID = fetchValues(responseMap, "shortserverid");
		new MongoOperations().createRecord(ServerDetails.class,"server", Client1.serverID);
	}
	
	public String fetchValues(HashMap<String, String> hM , String key) {
		if(hM == null)
			return "";
		if(hM.containsKey(key))
			return hM.get(key);
		return "";
	}

	public boolean unregisterServer(String serverID) {
		
		return new MongoOperations().deleteRecord("client1",serverID);
		
	}
}