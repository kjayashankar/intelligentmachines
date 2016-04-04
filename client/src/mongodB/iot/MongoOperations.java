package mongodB.iot;

import org.bson.Document;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


public class MongoOperations {
	
	public boolean deleteRecord(String databaseName , String collection) {
		MongoClient mongoC = new MongoClient("localhost", 27017);
		
		try{
			MongoDatabase mongoDB = mongoC.getDatabase(databaseName);
			 MongoCollection<Document> mongoCollection = mongoDB.getCollection(collection);
			 mongoCollection.drop();
		}
		catch(Exception e) { e.printStackTrace(); return false; }
		finally{ mongoC.close(); }
		return true;
	}
	
	public < T > T  readRecord(Class<T> class1, String databaseName , String collection){
		
		MongoClient mongoC = new MongoClient("localhost", 27017);
		 MongoDatabase mongoDB = mongoC.getDatabase(databaseName);
		 MongoCollection<Document> mongoCollection = mongoDB.getCollection(collection);
		 FindIterable<Document> document = mongoCollection.find();
		 System.out.println(document.first());
		 T reg = null;
		 try {
		 reg = (T) (new ObjectMapper()).
				 readValue(document.first().toJson().replace("$oid", "oid"), 
						 class1);
		 }
		 catch(Exception e) {
			e.printStackTrace(); 
		 }
		 finally{
			 mongoC.close();
		 }
		return reg;
	}
	
	public <T> boolean createRecord(T inputRecord, String dB , String collection ) {

		JSONObject jObj = new JSONObject(inputRecord);

		MongoClient mongoC = new MongoClient("localhost", 27017);

		try{
		 MongoDatabase mongoDB = mongoC.getDatabase(dB);
		 MongoCollection<Document> mongoCollection = mongoDB.getCollection(collection);
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
	
	@SuppressWarnings("resource")
	public <T> boolean updateRecord (String databaseName , String collection , String updateKey, String updateValue) {
		
		try{
			MongoClient mongoC = new MongoClient("localhost", 27017);
			MongoDatabase mongoDB = mongoC.getDatabase(databaseName);
			MongoCollection<Document> mongoCollection = mongoDB.getCollection(collection);
			FindIterable<Document> document = mongoCollection.find();
			System.out.println(document.first());
			JSONObject jSON = new JSONObject(document.first());
			System.out.println(" JSON ===="+jSON.toString());
			
			jSON.put(updateKey, updateValue);
			jSON.remove("_id");
			mongoDB.getCollection(collection).updateOne(document.first(),
			        new Document("$set", new Document(Document.parse(jSON.toString()))));
			return true;
		}
		catch(Exception e) { e.printStackTrace(); return false;}
		
	}
}
