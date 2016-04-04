package mongodB.iot;

import java.util.Scanner;

import resourcemodel.iot.ServerDetails;

public class ServerMongoImpl {

	public static void main(String[] args) {
		
		Scanner in = new Scanner ( System.in );
	    display_menu();
	    int index = in.nextInt();
		do{
			menu(index);
		    display_menu();
			index = in.nextInt();
			
		}while(index != 5);
		in.close();
	}
	
public static void create(){
		
		MongoOperations mOperations = new MongoOperations();

		ServerDetails serverDetails = new ServerDetails();
		
		serverDetails.setBinding("Binding_default");
		serverDetails.setLifeTime("18600");
		serverDetails.setNotification("notifications_push");
		serverDetails.setShortServer("");
		serverDetails.setMaxPeriod("86400");
		serverDetails.setRegistration("registration");
		serverDetails.setDisable("false");
		
		/**
		 * CREATE RECORD 
		 */
		mOperations.createRecord(serverDetails,"server1" ,"serverDetails" );
	}
	
	public static void read(){
		
		MongoOperations mOperations = new MongoOperations();

		/**
		 * READ RECORD		
		 */
		ServerDetails sDetails = mOperations.readRecord(ServerDetails.class,"server1","serverDetails");
		
		System.out.println("ShortServer  : "+sDetails.getShortServer());
		System.out.println("Binding : "+sDetails.getBinding());
		System.out.println("LifeTime : "+sDetails.getLifeTime());
		System.out.println("Notification : "+sDetails.getNotification());
		System.out.println("Max Period : "+sDetails.getMaxPeriod());
		System.out.println("Registration : "+sDetails.getRegistration());
		System.out.println("Disable : "+sDetails.getDisable());
		
	}
	
	public static void update(String key , String value) {

		MongoOperations mOperations = new MongoOperations();

		/**
		 * UPDATE RECORD
		 */
		mOperations.updateRecord("server1", "serverDetails", key, value);
	}
	
	public static void delete() {

		MongoOperations mOperations = new MongoOperations();

		/**
		 * DELETE RECORD
		 */
		mOperations.deleteRecord("server1","serverDetails");
	}

	private static void menu(int index) {
		
	    
	    switch ( index ) {
	    
	      case 1:
	    	create(); 
	        break;
	      case 2:
	    	read();
	        break;
	        
	      case 3:
	    	 /* Scanner b = new Scanner(System.in);
	    	  String key = b.next();
	    	  String value = b.next();
	    	  b.close();*/
			update("notification","hide_notification");
	        break;
			
	      case 4:
	    	System.out.println ( "You picked delete" );
			delete();
	        break;
	      
	      case 5:
	    	  System.exit(0);
	    	  
	      default:
	        break;
	    }
		
	}

	private static void display_menu() {
		System.out.println ( "1) CREATE \n2) READ \n3) UPDATE \n4) DELETE \n5) EXIT" );
	    System.out.print ( "Pick one: " );
	}
}
