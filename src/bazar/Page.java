package bazar;

import tree.RedBlackTree;
import tree.Tree;

/**
 * Classe qui implémente les pages
 * @author Alexis Giraudet &amp; François Hallereau
 * version 1.0
 */
public class Page implements Comparable<Page> {

    /**
     * le numéro de page
     */
    private final Integer __id;

    /**
     * les mots de la pages
     */
    private Tree<String> __words;

    /**
     * Constructeur de la classe
     * @param id le numéro de page
     */
    public Page(Integer id) {
        __id = id;
        __words = new RedBlackTree<String>();
    }

    /**
     * Accesseur de l'attribut __id
     * @return __id
     * @see bazar.Page#__id
     */
    public Integer getId() {
        return __id;
    }

    /**
     * Accesseur de l'attribut __words
     * @return __words
     * @see bazar.Page#__words
     */
    public Tree<String> getWords() {
        return __words;
    }

    /**
     * Teste si la page a au moins k mots en commun avec la page p
     * @param p la page à tester
     * @param k la constante
     * @return true si les deux pages ont au moins k mots en commun
     */
    public boolean containsWords(Page p, int k) {
        int i = 0;
        for (String s : p.getWords()) {
            if (__words.contains(s)) {
                i++;
                if (i >= k) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Compare 2 pages en fonction de leur numéro de page
     * @param p la page
     * @return un entier
     * @see java.lang.Comparable
     */
    @Override
    public int compareTo(Page p) {
        return __id.compareTo(p.getId());
    }
}
