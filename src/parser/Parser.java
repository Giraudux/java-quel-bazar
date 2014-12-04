package parser;

import bazar.Page;
import tree.RedBlackTree;
import tree.Tree;

import java.io.*;

/**
 * Classe implémentant le Parser
 * @author Alexis Giraudet &amp; François Hallereau
 * @version 1.0
 */
public class Parser {

    /**
     * Méthode qui parse un fichier pour en faire un dictionnaire
     * @param file le fichier servant de dictionnaire
     * @return un arbre représentant un dictionnaire
     * @throws IOException erreur d'entrée/sortie
     */
    public static Tree<String> parseDico(File file) throws IOException {
        Tree<String> t = new RedBlackTree<String>();
        StreamTokenizer streamTokenizer = new StreamTokenizer(new FileReader(file));
        streamTokenizer.whitespaceChars(' ', '/');
        while (streamTokenizer.nextToken() != StreamTokenizer.TT_EOF) {
            if (streamTokenizer.ttype == StreamTokenizer.TT_WORD) {
                t.add(streamTokenizer.sval.toLowerCase());
            }
        }
        return t;
    }

    /**
     * Méthode qui parse un fichier pour en faire une page
     * @param file le fichier servant de page
     * @return une page
     * @throws IOException erreur d'entrée/sortie
     */
    public static Page parsePage(File file) throws IOException {
        Page p;
        Tree<String> words;
        StreamTokenizer streamTokenizer = new StreamTokenizer(new FileReader(file));
        streamTokenizer.whitespaceChars(' ', '/');
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
