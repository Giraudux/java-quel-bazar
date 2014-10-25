package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collection;

/**
 * @author Alexis Giraudet
 */
public class Parser {
    public static boolean parseLine(String fileName, Collection<String> c) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                c.add(line);
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
