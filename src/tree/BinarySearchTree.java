package tree;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Alexis Giraudet
 */
public class BinarySearchTree<T extends Comparable<T>> implements Collection<T> {
    protected BinarySearchNode<T> _root;

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
        return BinarySearchNode._search(BinarySearchNode._minimum(_root), (T) o) != null;
    }

    @Override
    public Iterator<T> iterator() {
        return new BinarySearchTreeIterator<T>(_root);
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        BinarySearchNode._add(this, new BinarySearchNode<T>(t));
        return false;
    }

    @Override
    public boolean remove(Object o) {
        BinarySearchNode._remove(this, new BinarySearchNode<T>((T) o));
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
    public boolean addAll(Collection<? extends T> c) {
        for (Object x : c) {
            add((T) x);
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
