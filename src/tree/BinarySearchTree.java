package tree;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Alexis Giraudet
 */
public class BinarySearchTree<K extends Comparable<K>> implements Collection<K> {
    protected BinarySearchNode<K> _root;

    public BinarySearchTree() {
        _root = null;
    }

    @Override
    public int size() {
        return BinarySearchNode._size(_root);
    }

    @Override
    public boolean isEmpty() {
        return _root == null;
    }

    @Override
    public boolean contains(Object o) {
        return BinarySearchNode._search(BinarySearchNode._minimum(_root), (K) o) != null;
    }

    @Override
    public Iterator<K> iterator() {
        return new BinarySearchTreeIterator<K>(_root);
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <K1> K1[] toArray(K1[] a) {
        return null;
    }

    @Override
    public boolean add(K k) {
        BinarySearchNode._add(this, new BinarySearchNode<K>(k));
        return false;
    }

    @Override
    public boolean remove(Object o) {
        BinarySearchNode._remove(this, new BinarySearchNode<K>((K) o));
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
            remove(x);
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
