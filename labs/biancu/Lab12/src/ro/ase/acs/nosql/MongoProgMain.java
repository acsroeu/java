package ro.ase.acs.nosql;

import java.util.Date;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class MongoProgMain {

	public static void main(String[] args) {
		MongoClient mongoClient = 
				new MongoClient("localhost", 27017);
		MongoDatabase db = mongoClient.getDatabase("test");
		
		if(db.getCollection("employees") != null) {
			db.getCollection("employees").drop();
		}
		
		db.createCollection("employees");
		Document document = 
				new Document().append("name", "Gigel Ionescu").
				append("birthdate", new Date()).
				append("address", "Stefan cel Mare nr 20").
				append("salary", 2000);
		MongoCollection<Document> collection =
				db.getCollection("employees");
		collection.insertOne(document);
		
		Document document2 = 
				new Document().append("name", "Ionel Popescu").
				append("address", "Mihai Bravu 20").
				append("salary", 4000);
		collection.insertOne(document2);
		
		collection = db.getCollection("employees");
		FindIterable<Document> it = collection.find();
		MongoCursor<Document> cursor = it.iterator();
		
		while(cursor.hasNext()) {
			System.out.println(cursor.next());
		}
		
		mongoClient.close();
	}
}
