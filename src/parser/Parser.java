package parser;

import bazar.Page;
import tree.RedBlackTree;
import tree.Tree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

/**
 * Classe implémentant le Parser
 * @author Alexis Giraudet &amp; François Hallereau
 * @version 1.0
 */
public class Parser {

    /**
     * Méthode qui parse un fichier pour en faire un dictionnaire
     * @param fileName le fichier servant de dictionnaire
     * @return un arbre représentant un dictionnaire
     * @throws IOException erreur d'entrée/sortie
     */
    public static Tree<String> parseDico(String fileName) throws IOException {
        Tree<String> t = new RedBlackTree<String>();
        StreamTokenizer streamTokenizer = new StreamTokenizer(new BufferedReader(new FileReader(fileName)));
        streamTokenizer.whitespaceChars(' ', '/');
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

    /**
     * Méthode qui parse un fichier pour en faire une page
     * @param fileName le fichier servant de page
     * @return une page
     * @throws IOException erreur d'entrée/sortie
     */
    public static Page parsePage(String fileName) throws IOException {
        Page p;
        Tree<String> words;
        StreamTokenizer streamTokenizer = new StreamTokenizer(new BufferedReader(new FileReader(fileName)));
        streamTokenizer.whitespaceChars(' ', '/');
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
        if (streamTokenizer.nextToken() != StreamTokenizer.TT_NUMBER) {
            throw new IOException();
        }
        p = new Page((int) streamTokenizer.nval);
        words = p.getWords();
        streamTokenizer.whitespaceChars(' ', '@');
        while (streamTokenizer.nextToken() != StreamTokenizer.TT_EOF) {
            if (streamTokenizer.ttype == StreamTokenizer.TT_WORD) {
                words.add(streamTokenizer.sval.toLowerCase());
            }
        }
        return p;
    }
}
