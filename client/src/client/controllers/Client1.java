package client.controllers;

import java.util.HashMap;

import jay.iot.commons.ClientObj;
import jay.iot.commons.Constants;
import jay.iot.conn.Connection;

public class Client1 {

	public static String serialID = "";
	
	public static String endServerUrl = "";

	public static String unregisterUrl = "";
	
	public static String communicationUrl = "";

	public static String serverID = "";
	
	public ClientObj client = null ;
	
	public static void main(String... args) {
		
		if(!Client1Bootstrap.init()) {
			System.out.println("Error in bootstrapping -"
					+ " please contact technical support");
			System.exit(0);
		}
		else 	print("BOOTSTRAP");

		new Client1().register();
		// communicate with server -- to do
		new Client1().unregister();
	}

	
	private void register() {
		
		RestHelper rHelp = new RestHelper();
		client = rHelp.getDBRecord();
		HashMap<String,String> body = rHelp.getNecessaryFields(client);
		
		Connection conn = new Connection();
		try {
			conn.acquire();
			conn.setURL(endServerUrl);
			conn.setRequestType(Constants.POST);
			conn.setBody(body);
			String response = conn.connect();
			HashMap<String, String> responseMap = rHelp.getMap(response);
			rHelp.registerServer(responseMap);
			print("REGISTRATION");
		}
		catch(Exception e){ e.printStackTrace(); }
	}
	
	private void unregister() {
		// TODO Auto-generated method stub
		RestHelper rHelper = new RestHelper();
		/*String serialID = client.getSerialNumber();*/
		HashMap<String,String> hMap = new HashMap<String, String>();
		hMap.put("serialNumber", serialID);
		Connection conn = new Connection();
		try {
			conn.acquire();
			conn.setURL(unregisterUrl);
			conn.setRequestType(Constants.POST);
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
