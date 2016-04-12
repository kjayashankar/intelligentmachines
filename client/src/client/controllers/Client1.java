package client.controllers;

import java.util.HashMap;

import reourcemodel.iot.ClientDetails;

public class Client1 {

	public static String serialID = "";
	
	public static String endServerUrl = "";

	public static String unregisterUrl = "";
	
	public static String communicationUrl = "";

	public static String serverID = "";
	
	public ClientDetails client = null ;
	
	public static void main(String... args) {
		
		
	}

	
	public void register() {
		
		RestHelper rHelp = new RestHelper();
		client = rHelp.getDBRecord();
		
		Connection conn = new Connection();
		try {
			conn.acquire();
			conn.setURL(endServerUrl);
			conn.setRequestType("POST");
// TO DO			conn.setBody(client);
			String response = conn.connect();
			HashMap<String, String> responseMap = rHelp.getMap(response);
			rHelp.registerServer(responseMap);
			print("REGISTRATION");
		}
		catch(Exception e){ e.printStackTrace(); }
	}
	
	public void unregister() {
		// TODO Auto-generated method stub
		RestHelper rHelper = new RestHelper();
		/*String serialID = client.getSerialNumber();*/
		HashMap<String,String> hMap = new HashMap<String, String>();
		hMap.put("serialNumber", serialID);
		Connection conn = new Connection();
		try {
			conn.acquire();
			conn.setURL(unregisterUrl);
			conn.setRequestType("post");
			conn.setBody(hMap);
			String response = conn.connect();
			HashMap<String, String> responseMap = rHelper.getMap(response);
			System.out.println(responseMap);
			if("Success".equalsIgnoreCase(responseMap.get("Message"))) {
				if(rHelper.unregisterServer(serverID)) {
					System.out.println("Succesfull detached -- Device"
							+ " is shutting down");
					print("DEVICE DETACHMENT");
				}
			}
		}
		catch(Exception e){ e.printStackTrace(); }
	}

	private static void print(String string) {
		// TODO Auto-generated method stub
		System.out.println("-------------------------"+string+" COMPLETED-----------------------");
	}

	
}
