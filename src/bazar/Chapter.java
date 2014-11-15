package bazar;

import tree.RedBlackTree;
import tree.Tree;

/**
 * Classe qui implémente les chapitres
 * @author Alexis Giraudet & françois Hallereau
 * @version 1.0
 */
public class Chapter implements Comparable<Chapter> {

    /**
     * le numéro du chapitre
     */
    private final Integer __id;

    /**
     * les mots clés du chapitre
     */
    private Tree<String> __keyWords;

    /**
     * les pages du chapitre
     * @see bazar.Page
     */
    private Tree<Page> __pages;

    /**
     * Constructeur de la classe
     * @param id le numéro du chapitre
     */
    public Chapter(Integer id) {
        __id = id;
        __keyWords = new RedBlackTree<String>();
        __pages = new RedBlackTree<Page>();
    }

    /**
     * Accesseur de l'attribut __id
     * @see bazar.Chapter#id
     * @return __id
     */
    public Integer getId() {
        return __id;
    }

    /**
     * Accesseur de l'attribut __keyWords
     * @see bazar.Chapter#__keyWords
     * @return __keyWords
     */
    public Tree<String> getKeyWords() {
        return __keyWords;
    }

    /**
     * Accesseur de l'attribut __pages
     * @see bazar.Chapter#__pages
     * @return __pages
     */
    public Tree<Page> getPages() {
        return __pages;
    }

    /**
     * Ajoute une page au chapitre
     * @param p la page
     * @return true
     */
    boolean addPage(Page p) {
        __pages.add(p);
        __keyWords.addAll(p.getWords());
        return true;
    }

    /**
     * Ajoute une page au chapitre si elle contient au moins k mot du dictionnaire
     * @param p la page
     * @param k la constante
     * @return true si la page a été ajouté
     */
    boolean addPage(Page p, int k) {
        for (Page pp : __pages) {
            if (pp.containsWords(p, k)) {
                addPage(p);
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode de comparaison de chapitre en fonction de l'id
     * @param c le chapitre
     * @return un entier
     * @see java.lang.Comparable#compareTo(T o)
     */
    @Override
    public int compareTo(Chapter c) {
        return __id.compareTo(c.getId());
    }

    /**
     * Affiche un chapitre
     * @return la string à afficher
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Chapter ").append(__id).append(":\npages=");
        for (Page p : __pages) {
            s.append(" ").append(p.getId());
        }
        s.append("\nkeywords=");
        for (String w : __keyWords) {
            s.append(" ").append(w);
        }
        s.append("\n");
        return s.toString();
    }
}
