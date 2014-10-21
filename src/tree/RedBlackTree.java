package tree;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Alexis Giraudet
 */
public class RedBlackTree<K extends Comparable<K>> implements Tree<K> {
    public final RedBlackNode<K> nil;
    protected RedBlackNode<K> _root;

    public RedBlackTree() {
        nil = new RedBlackNode<K>(null);
        nil._colour = RedBlackNode.BLACK;
        _root = nil;
    }

    @Override
    public int size() {
        return RedBlackNode._size(this, _root);
    }

    @Override
    public boolean isEmpty() {
        return _root == null;
    }

    @Override
    public boolean contains(Object o) {
        return RedBlackNode._search(this, _root, (K) o) != null;
    }

    @Override
    public Iterator<K> iterator() {
        return new RedBlackTreeIterator<K>(this, RedBlackNode._minimum(this, _root));
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(K k) {
        RedBlackNode._add(this, new RedBlackNode<K>(k));
        return false;
    }

    @Override
    public boolean remove(Object o) {
        RedBlackNode._remove(this, RedBlackNode._search(this, _root, (K) o));
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object x : c) {
            if (!contains(x)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends K> c) {
        for (Object x : c) {
            add((K) x);
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object x : c) {
            remove((K) x);
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        _root = null;
    }
}
