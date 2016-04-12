package mongodB.iot;

import java.util.Scanner;

import reourcemodel.iot.CardDetails;
import reourcemodel.iot.PaymentOption;

public class ClientMongoImpl {

	public static Scanner in = new Scanner ( System.in );

	public static void main(String[] args) {

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

		PaymentOption paymentOp = new PaymentOption();
		
		paymentOp.setAmount("345");
		paymentOp.setPlan("basic");
		paymentOp.setBillDate("05/04/2016");
		
		CardDetails cardDetails = new CardDetails("cardNumber","cvv","expiryDate");
		cardDetails.setEmail("kjayashankar@yahoo.com");
		cardDetails.setContactNumber("669-292-6025");
		
		paymentOp.setCardDetails(cardDetails);
		
		/**
		 * CREATE RECORD 
		 */
		mOperations.createRecord(paymentOp,"client1" ,"paymentOption" );
	}
	
	public static void read(){
		
		MongoOperations mOperations = new MongoOperations();

		/**
		 * READ RECORD		
		 */
		PaymentOption readPay = mOperations.readRecord(PaymentOption.class,"client1","paymentOption");
		
		System.out.println("Bill Date : "+readPay.getBillDate());
		System.out.println("Plan : "+readPay.getPlan());
		System.out.println("Amount : "+readPay.getAmount());
		System.out.println("Card Number : "+readPay.getCardDetails().getCardNumber());
		System.out.println("CVV : "+readPay.getCardDetails().getCvv());
		System.out.println("Expiry Date : "+readPay.getCardDetails().getExpiryDate());
		System.out.println("Contact number : "+readPay.getCardDetails().getContactNumber());
		System.out.println("Email : "+readPay.getCardDetails().getEmail());
		
	}
	
	public static void update(String key , String value) {

		MongoOperations mOperations = new MongoOperations();

		/**
		 * UPDATE RECORD
		 */
		mOperations.updateRecord("client1", "paymentOption", key, value);
	}
	
	public static void delete() {

		MongoOperations mOperations = new MongoOperations();

		/**
		 * DELETE RECORD
		 */
		mOperations.deleteRecord("client1","paymentOption");
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
	    	 System.out.println("Enter key and value");
	    	  String key = in.next();
	    	  String value = in.next();
	    	  update(key,value);
			//update("amount","900");
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
