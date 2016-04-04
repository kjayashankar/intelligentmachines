package mongodB.iot;

import reourcemodel.iot.CardDetails;
import reourcemodel.iot.PaymentOption;

public class ClientMongoImpl {

public static void main(String[] args) {
		
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
		
		
		//mOperations.deleteRecord();
		
		//mOperations.readRecord();
	}
}
