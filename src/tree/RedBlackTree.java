package tree;

import java.util.Collection;
import java.util.Iterator;

/**
 * Classe qui implémente l'arbre rouge et noir
 * @see Tree
 * @author Alexis Giraudet & François Hallereau
 * @version 1.0
 */
public class RedBlackTree<K extends Comparable<K>> implements Tree<K> {

    /**
     * le nombre d'élément contenu dans l'arbre
     */
    private int _size;

    /**
     * la racine de l'arbre
     */
    protected RedBlackNode<K> _root;

    /**
     * la feuille null
     */
    protected final RedBlackNode<K> _nil;

    /**
     * Constructeur de la classe RedBlackTree
     */
    public RedBlackTree() {
        _size = 0;
        _nil = new RedBlackNode<K>();
        _root = _nil;
    }

    /**
     * Méthode qui retourne la hauteur de l'arbre
     * @return height la hauteur
     */
    public int height() {
        return RedBlackNode._height(this, _root);
    }

    /**
     * Accesseur de l'attribut _size
     * @return _size le nombre d'éléments
     * @see #_size
     */
    @Override
    public int size() {
        return _size;
    }

    /**
     * Méthode qui vérifie si l'arbre est vide ou non
     * @return booléen vrai si l'arbre est vide
     */
    @Override
    public boolean isEmpty() {
        return _root == _nil;
    }

    /**
     * Méthode qui vérifie si l'objet o est dans l'arbre
     * @param o l'objet à tester
     * @return booléen vrai si o est dans l'arbre
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        return RedBlackNode._search(this, _root, (K) o) != _nil;
    }

    /**
     * Méthode qui crée un itérateur sur l'arbre
     * @see RedBlackTreeIterator
     * @return un itérateur
     */
    @Override
    public Iterator<K> iterator() {
        return new RedBlackTreeIterator<K>(this, RedBlackNode._minimum(this, _root));
    }

    /**
     * Méthode qui retourne les valeurs de l'arbre sous forme de tableau
     * @return Object[] les valeurs de l'arbre
     */
    @Override
    public Object[] toArray() {
        Object[] r = new Object[_size];
        int i = 0;
        Iterator<K> it = iterator();
        while ((it.hasNext()) && (i < size())) {
            r[i] = it.next();
            i++;
        }
        return r;
    }

    /**
     *
     * @param a
     * @param <T>
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        return null;
    }

    /**
     * Méthode qui ajoute un élément dans l'arbre
     * @param k l'élément à ajouter
     * @return booléen vrai sil'élément a été ajouté
     */
    @Override
    public boolean add(K k) {
        if (!contains(k)) {
            RedBlackNode._add(this, new RedBlackNode<K>(this, k));
            _size++;
            return true;
        }
        return false;
    }

    /**
     * Méthode qui supprime un élément de l'arbre
     * @param o l'élément à supprimer
     * @return booléen vrai si la suppression est réussie
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        RedBlackNode<K> z = RedBlackNode._search(this, _root, (K) o);
        if (z != _nil) {
            RedBlackNode._remove(this, z);
            _size--;
            return true;
        }
        return false;
    }

    /**
     * Méthode qui vérifie si tous les éléments de la collection sont dans l'arbre
     * @param c la collection
     * @return booléen vrai si tous les éléments de la collection sont dans l'arbre
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object x : c) {
            if (!contains(x)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Méthode qui ajoute tous les éléments d'une collection à l'arbe
     * @param c la collection
     * @return booléen faux si aucun élément n'a pu être ajouté
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(Collection<? extends K> c) {
        boolean r = false;
        for (Object x : c) {
            r = add((K) x) || r;
        }
        return r;
    }

    /**
     * Méthode qui supprime tous les éléments d'une collection à l'arbre
     * @param c la collection
     * @return booléen si aucun élément n'a pu être supprimer
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean r = false;
        for (Object x : c) {
            r = remove(x) || r;
        }
        return r;
    }

    /**
     * Méthode qui supprime les éléments de l'arbre si ils ne sont pas dans la collection
     * @param c la collection
     * @return booléen si aucun élément n'a pu être supprimer
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        //TODO tester cette fonction
        boolean r = false;
        for (Object x : c) {
            if (!contains(x)) {
                r = remove(x) || r;
            }
        }
        return r;
    }

    /**
     * Méthode qui vide l'arbre
     */
    @Override
    public void clear() {
        _size = 0;
        _root = _nil;
    }

    /**
     * Méthode qui affiche les éléments contenu dans l'arbre
     * @return la string à afficher
     */
    @Override
    public String toString() {
        if (!isEmpty()) {
            StringBuilder s = new StringBuilder();
            for (K k : this) {
                s.append(' ').append(k.toString()).append(',');
            }
            s.setCharAt(0, '[');
            s.setCharAt(s.length() - 1, ']');
            return s.toString();
        }
        return "[]";
    }
}
