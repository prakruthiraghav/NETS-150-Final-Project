

import java.io.IOException;
import java.util.ArrayList;

/**
 * the tester class.
 * @author swapneel
 */
public class VectorSpaceModelTester {

	public static void main(String[] args) {
	    
	    try {
            TweetParser.csvFileToTweets("vaccination_all_tweets_clean2.csv");
            // the file vaccination_all_tweets.csv is the raw data set that we downloaded
            // the file vaccination_all_tweets_clean2.csv is the data set cleaned so that it
            // only has the data for month and text of tweet and so the text does not contain
            // any line breaks
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    
	    Document dec = new Document("dec.txt");
	    Document jan = new Document("jan.txt");
	    Document feb = new Document("feb.txt");
	    Document mar = new Document("mar.txt");
	    Document apr = new Document("apr.txt");
		
		ArrayList<Document> documents = new ArrayList<Document>();
		documents.add(dec);
		documents.add(jan);
		documents.add(feb);
		documents.add(mar);
		documents.add(apr);
		
		Corpus corpus = new Corpus(documents);
		
		VectorSpaceModel vectorSpace = new VectorSpaceModel(corpus);
		
		for (int i = 0; i < documents.size(); i++) {
			for (int j = i + 1; j < documents.size(); j++) {
				Document doc1 = documents.get(i);
				Document doc2 = documents.get(j);
				System.out.println("\nComparing " + doc1 + " and " + doc2);
				System.out.println(vectorSpace.cosineSimilarity(doc1, doc2));
			}
		}
		
		
	}

}
