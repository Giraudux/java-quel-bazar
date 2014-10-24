package tree;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Alexis Giraudet
 */
public class RedBlackTree<K extends Comparable<K>> implements Tree<K> {
    protected RedBlackNode<K> _root;
    protected RedBlackNode<K> _nil;

    public RedBlackTree() {
        _nil = new RedBlackNode<K>();
        _root = _nil;
    }

    public int height() {
        return RedBlackNode._height(this, _root);
    }

    @Override
    public int size() {
        return RedBlackNode._size(this, _root);
    }

    @Override
    public boolean isEmpty() {
        return _root == _nil;
    }

    @Override
    public boolean contains(Object o) {
        return RedBlackNode._search(this, _root, (K) o) != _nil;
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
        RedBlackNode._add(this, new RedBlackNode<K>(this, k));
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
        _root = _nil;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(K k : this) {
            s.append(' ').append(k.toString()).append(',');
        }
        s.setCharAt(0, '[');
        s.setCharAt(s.length()-1, ']');
        return s.toString();
    }
}
