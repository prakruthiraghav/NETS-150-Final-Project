import java.util.Iterator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.io.FileNotFoundException;


/**
 * This FileLineIterator was taken from the 120 HW for TwitterBot
 */
public class FileLineIterator implements Iterator<String> {

    private BufferedReader br;
    private boolean hasNext;
    private String currLine;
    

    /**
     * Creates a FileLineIterator for the file located at filePath.
     * Fill out the constructor so that a user can instantiate a FileLineIterator.
     * Feel free to create and instantiate any variables that your implementation requires here.
     * See recitation and lecture notes for guidance.
     * <p>
     * If an IOException is thrown by the BufferedReader or FileReader, then hasNext should return
     * false.
     *
     * @param filePath - the path to the CSV file to be turned to an Iterator
     * @throws IllegalArgumentException if filePath is null or if the file doesn't exist
     */
    public FileLineIterator(String filePath) {
        if (filePath == null) {
            throw new IllegalArgumentException("Null file path!");
        }
        try {
            Reader r = new FileReader(filePath);
            br = new BufferedReader(r);
            currLine = br.readLine();
            hasNext = currLine != null;
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("No such file!");
        } catch (IOException e) {
            hasNext = false;
        }

    }

    /**
     * Returns true if there are lines left to read in the file, and
     * false otherwise.
     * <p>
     * If there are no more lines left, this method should close the
     * BuffereReader.
     *
     * @return a boolean indicating whether the FileLineIterator
     * can produce another line from the file
     */
    @Override
    public boolean hasNext() {
        if (br == null) {
            return false;
        }
        if (!hasNext) {
            try {
                br.close();
            } catch (IOException e) {
                return false;
            }
        }
        return hasNext;
    }

    /**
     * Returns the next line from the file, or throws a NoSuchElementException
     * if there are no more strings left to return (i.e. hasNext() is false).
     * <p>
     * This method also advances the iterator in preparation for another
     * invocation.  If an IOException is thrown during a next() call, the next time
     * next() is called, it should throw a NoSuchElementException.
     *
     * @return the next line in the file
     * @throws java.util.NoSuchElementException if there is no more data in the file
     */
    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements");
        }
        String result = currLine;
        try {
            currLine = br.readLine();
            hasNext = currLine != null;
        } catch (IOException e) {
            hasNext = false;
        }
        return result;

    }
}
