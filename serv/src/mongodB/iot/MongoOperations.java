package mongodB.iot;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoOperations {

	String databaseName ="";
	
	public boolean deleteRecord(String collection) {
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
	
	@SuppressWarnings("unchecked")
	public < T > T  readRecord(String className, String collection){
		
		MongoClient mongoC = new MongoClient("localhost", 27017);
		 MongoDatabase mongoDB = mongoC.getDatabase(databaseName);
		 MongoCollection<Document> mongoCollection = mongoDB.getCollection(collection);
		 FindIterable<Document> document = mongoCollection.find();
		 System.out.println(document.first());
		 T reg = null;
		 try {
		 reg = (T) (new ObjectMapper()).
				 readValue(document.first().toJson().replace("$oid", "oid"), 
						 Class.forName(className));
		 }
		 catch(Exception e) {
			e.printStackTrace(); 
		 }
		 finally{
			 mongoC.close();
		 }
		return reg;
	}
	
	public boolean insertRecord(HashMap<String, String> map, String collection ) {

		MongoClient mongoC = new MongoClient("localhost", 27017);

		try{
		 MongoDatabase mongoDB = mongoC.getDatabase(databaseName);
		 MongoCollection<Document> mongoCollection = mongoDB.getCollection(collection);
		 Map<String, Object> map1 = new HashMap<String,Object>();
		 map1.putAll(map);
		 mongoCollection.insertOne(new Document(map1));
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
