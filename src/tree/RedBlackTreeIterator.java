package tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Classe qui implémente un itérateur pour l'arbre rouge et noir
 * @see RedBlackTree
 * @see java.util.Iterator
 * @author Alexis Giraudet &amp; Francois Hallereau
 * @version 1.0
 */
public class RedBlackTreeIterator<K extends Comparable<K>> implements Iterator<K> {
    /**
     * l'arbre sur lequel on applique l'itération
     */
    private RedBlackTree<K> __currentTree;

    /**
     * le noeud courant
     */
    private RedBlackNode<K> __currentNode;

    /**
     * le noeud à supprimer lors de l'appel à remove()
     */
    private RedBlackNode<K> __removeNode;

    /**
     * Constructeur de RedBlackTreeIterator
     * @param T l'arbre à itérer
     * @param x le noeud de départ pour l'itération
     */
    public RedBlackTreeIterator(RedBlackTree<K> T, RedBlackNode<K> x) {
        __currentTree = T;
        __currentNode = x;
        __removeNode = T._nil;
    }

    /**
     * Redéfinition de la méthode hasNext()
     * @see java.util.Iterator#hasNext()
     * @return booléen vrai si __currentNode n'est pas une feuille
     */
    @Override
    public boolean hasNext() {
        return __currentNode != __currentTree._nil;
    }

    /**
     * Redéfinition de la méthode next()
     * @see java.util.Iterator#next()
     * @return K _key du noeud courant
     */
    @Override
    public K next() {
        if (__currentNode == __currentTree._nil) {
            throw new NoSuchElementException();
        }
        __removeNode = __currentNode;
        K k = __currentNode._key;
        __currentNode = RedBlackNode._successor(__currentTree, __currentNode);
        return k;
    }

    /**
     * Redéfinition de la méthode remove()
     * @see java.util.Iterator#remove()
     */
    @Override
    public void remove() {
        if(__removeNode == __currentTree._nil) {
            throw new IllegalStateException();
        }
        __currentTree.remove(__removeNode._key);
        __removeNode = __currentTree._nil;
    }
}
