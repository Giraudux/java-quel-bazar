package parser;

import bazar.Page;
import tree.RedBlackTree;
import tree.Tree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

/**
 * @author Alexis Giraudet
 */
public class Parser {
    public static Tree<String> parseDico(String fileName) throws IOException {
        Tree<String> t = new RedBlackTree<String>();
        StreamTokenizer streamTokenizer = new StreamTokenizer(new BufferedReader(new FileReader(fileName)));
        /*streamTokenizer.quoteChar('.');
        streamTokenizer.quoteChar(',');
        streamTokenizer.quoteChar(';');
        streamTokenizer.quoteChar(':');
        streamTokenizer.quoteChar('!');
        streamTokenizer.quoteChar('?');
        streamTokenizer.quoteChar('"');
        streamTokenizer.quoteChar('(');
        streamTokenizer.quoteChar(')');
        streamTokenizer.quoteChar('[');
        streamTokenizer.quoteChar(']');
        streamTokenizer.quoteChar('{');
        streamTokenizer.quoteChar('}');
        streamTokenizer.quoteChar('«');
        streamTokenizer.quoteChar('»');*/
        while (streamTokenizer.nextToken() != StreamTokenizer.TT_EOF) {
            if (streamTokenizer.ttype == StreamTokenizer.TT_WORD) {
                t.add(streamTokenizer.sval.toLowerCase());
            }
        }
        return t;
    }

    public static Page parsePage(String fileName) throws IOException {
        Page p;
        Tree<String> words;
        StreamTokenizer streamTokenizer = new StreamTokenizer(new BufferedReader(new FileReader(fileName)));
        streamTokenizer.quoteChar('.');
        streamTokenizer.quoteChar(',');
        streamTokenizer.quoteChar(';');
        streamTokenizer.quoteChar(':');
        streamTokenizer.quoteChar('!');
        streamTokenizer.quoteChar('?');
        /*streamTokenizer.quoteChar('"');
        streamTokenizer.quoteChar('(');
        streamTokenizer.quoteChar(')');
        streamTokenizer.quoteChar('[');
        streamTokenizer.quoteChar(']');
        streamTokenizer.quoteChar('{');
        streamTokenizer.quoteChar('}');
        streamTokenizer.quoteChar('«');
        streamTokenizer.quoteChar('»');*/
        if (streamTokenizer.nextToken() != StreamTokenizer.TT_NUMBER) {
            throw new IOException();
        }
        p = new Page((int) streamTokenizer.nval);
        words = p.getWords();
        while (streamTokenizer.nextToken() != StreamTokenizer.TT_EOF) {
            if (streamTokenizer.ttype == StreamTokenizer.TT_WORD) {
                words.add(streamTokenizer.sval.toLowerCase());
            }
        }
        return p;
    }
}
