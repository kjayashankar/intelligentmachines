package tester;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import mongodB.iot.MongoOperations;
import resourcemodel.iot.ServerDetails;

public class Tester {

	public static void main(String [] rags){
		
		ServerDetails sDetails = new ServerDetails();
		sDetails.setBinding("blah");
		sDetails.setDisable("blah");
		sDetails.setNotification("blah");
		sDetails.setShortServer("blah");

		MongoOperations mnp = new MongoOperations();
		mnp.updateRecord("tester","tester","notification","finalee!!");
	}
	
	public <T> boolean createRecord(T inputRecord, String collection ) {

		JSONObject jObj = new JSONObject(inputRecord);

		MongoClient mongoC = new MongoClient("localhost", 27017);

		try{
		 MongoDatabase mongoDB = mongoC.getDatabase("tester");
		 MongoCollection<Document> mongoCollection = mongoDB.getCollection("tester");
		 mongoCollection.insertOne(Document.parse(jObj.toString()));
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		finally{
			mongoC.close();
		}
		return true;
	}
}
