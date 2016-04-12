package client.controllers;

import java.io.IOException;
import java.util.HashMap;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import jay.iot.client1.db.MClient;
import jay.iot.commons.ClientObj;

public class RestHelper {

	public static String databaseName = "client1";
	
	public static String collection = "personal";
	
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

	public ClientObj getDBRecord() {
		
		return new MClient().getDBRecord("jay.iot.commons.ClientObj", "personal");
	}

	public HashMap<String, String> getNecessaryFields(ClientObj client) {
		// TODO Auto-generated method stub
		HashMap<String, String> hMap = new HashMap<String, String>();
		hMap.put("manufacturer", client.getManufacturer());
		hMap.put("modelNumber", client.getModelNumber());
		hMap.put("serialNumber", client.getSerialNumber());
		hMap.put("firmwareVersion", client.getFirmwareVersion());
		hMap.put("Binding", client.getBinding());
		hMap.put("UTCOffset", client.getUTCOffset());
		
		return hMap;
		
	}

	public void registerServer(HashMap<String, String> responseMap) {
		Client1.unregisterUrl = fetchValues(responseMap, "unregisterUrl");
		Client1.communicationUrl = fetchValues(responseMap, "communicationurl");
		Client1.serverID = fetchValues(responseMap, "shortserverid");
		new MClient().insertRecord(responseMap, Client1.serverID);
	}
	
	public String fetchValues(HashMap<String, String> hM , String key) {
		if(hM == null)
			return "";
		if(hM.containsKey(key))
			return hM.get(key);
		return "";
	}

	public boolean unregisterServer(String serverID) {
		
		return new MClient().deleteRecord(serverID);
		
	}
}