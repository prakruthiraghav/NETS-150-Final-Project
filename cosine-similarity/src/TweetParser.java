import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This TweetParser class was modified from the 120 HW TweetParser
 */
public class TweetParser {
    
    public static final int TWEET_COLUMN = 10;
    public static final int DATE_COLUMN = 9;


    /**
     * Given a String that represents a line extracted from a CSV file and an int
     * that represents the column of the CSV file that we want to extract from,
     * return the contents of that column from the String. Columns in the CSV file
     * are zero indexed.
     * <p>
     * You may find the String.split() method useful here. Your solution should be
     * relatively short.
     * <p>
     * You may assume that the column contents themselves don't have any commas.
     *
     * @param csvLine   - a line extracted from a CSV file
     * @param csvColumn - the column of the line whose contents ought to be returned
     * @return the portion of csvLine corresponding to the column of csvColumn. If
     * the csvLine is null or has no appropriate csvColumn, return null
     */
    static String extractColumn(String csvLine, int csvColumn) {
        if (csvLine == null || csvColumn < 0) {
            return null;
        }
        String[] cols = csvLine.split(",");
        System.out.println(cols.length);
        if (cols.length <= csvColumn) {
            return null;
        }
        return cols[csvColumn];
    }

    /**
     * Given the argument pathToCSVFile and the column that the tweets are in, use the
     * extractColumn and a FileLineIterator to extract every tweet from the CSV.
     * (Recall that extractColumn returns null if there is no data at that column.)
     * You should skip lines in the CSV for which the tweetColumn is out of bounds.
     *
     * @param pathToCSVFile - a String representing a path to a CSV file containing
     *                      tweets
     * @param tweetColumn   - the number of the column in the CSV file that contains
     *                      the tweet
     * @throws IOException 
     * @throws IllegalArgumentException if pathToCSVFile is null or if the file
     *                                  doesn't exist
     */
    static void csvFileToTweets(String pathToCSVFile) throws IOException {
        FileLineIterator it = new FileLineIterator(pathToCSVFile);
        FileWriter dec = new FileWriter("dec.txt");
        FileWriter jan = new FileWriter("jan.txt");
        FileWriter feb = new FileWriter("feb.txt");
        FileWriter mar = new FileWriter("mar.txt");
        FileWriter apr = new FileWriter("apr.txt");
        
        BufferedWriter bDec = new BufferedWriter(dec);
        BufferedWriter bJan = new BufferedWriter(jan);
        BufferedWriter bFeb = new BufferedWriter(feb);
        BufferedWriter bMar = new BufferedWriter(mar);
        BufferedWriter bApr = new BufferedWriter(apr);
        
        it.next(); // get rid of col names row
        while (it.hasNext()) {
            String line = it.next();
            int comInd = line.indexOf(",");
            String date = line.substring(0, comInd);
            String tweet = line.substring(comInd+1, line.length() - 3);
            if (date.startsWith("12")) {
                bDec.write(tweet);
                bDec.newLine();
            } else if (date.startsWith("1")) {
                bJan.write(tweet);
                bJan.newLine();
            } else if (date.startsWith("2")) {
                bFeb.write(tweet);
                bFeb.newLine();
            } else if (date.startsWith("3")) {
                bMar.write(tweet);
                bMar.newLine();
            } else if (date.startsWith("4")) {
                bApr.write(tweet);
                bApr.newLine();
            }
        }
        bDec.close();
        bJan.close();
        bFeb.close();
        bMar.close();
        bApr.close();
    }

}
