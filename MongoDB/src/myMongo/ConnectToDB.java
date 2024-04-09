package myMongo;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javax.swing.text.Document;

public class ConnectToDB {

    public static void main(String[] args) {
        // Creating a Mongo Client
        MongoClient mongo = new MongoClient("localhost",27017);

        // Creating Credential
        MongoCredential credential;
        credential = MongoCredential.createCredential("mycollection","testdb","password".toCharArray());
        System.out.println("Connected to database successfully");

        // Accessing the database
        MongoDatabase database = mongo.getDatabase("testdb");
        System.out.println("Credential :: "+credential);

        MongoCollection<org.bson.Document> collection = database.getCollection("mycollection");
        System.out.println("Collection sample Collection selected Successfully ");
       /* Document document = new Document("title", "MongoDB");
        document.append("DDD", "ddd");
        document.append("EEE", "eee");
        document.append("FFF", "fff");
        document.append("GGG", "ggg");*/

        // Inserting document into the collection




    }
}
