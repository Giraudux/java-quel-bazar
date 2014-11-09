package parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.Collection;

/**
 * @author Alexis Giraudet
 */
public class Parser {

    public static void parseLines(String fileName, Collection<String> c) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            c.add(line.toLowerCase());
        }
    }

    public static void parseTokens(String fileName, Collection<String> c) throws IOException {
        StreamTokenizer streamTokenizer = new StreamTokenizer(new BufferedReader(new FileReader(fileName)));
        streamTokenizer.quoteChar('.');
        streamTokenizer.quoteChar(',');
        streamTokenizer.quoteChar(';');
        streamTokenizer.quoteChar(':');
        streamTokenizer.quoteChar('!');
        streamTokenizer.quoteChar(',');
        streamTokenizer.quoteChar('"');
        streamTokenizer.quoteChar('(');
        streamTokenizer.quoteChar(')');
        streamTokenizer.quoteChar('[');
        streamTokenizer.quoteChar(']');
        streamTokenizer.quoteChar('{');
        streamTokenizer.quoteChar('}');
        streamTokenizer.quoteChar('«');
        streamTokenizer.quoteChar('»');
        while(streamTokenizer.nextToken() != StreamTokenizer.TT_EOF) {
            if(streamTokenizer.ttype == StreamTokenizer.TT_WORD) {
                c.add(streamTokenizer.sval.toLowerCase());
            }
        }
    }
}
