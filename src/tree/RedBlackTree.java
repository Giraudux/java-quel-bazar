package tree;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Alexis Giraudet
 */
public class RedBlackTree<K extends Comparable<K>> implements Tree<K> {
    private int _size;
    protected RedBlackNode<K> _root;
    protected final RedBlackNode<K> _nil;

    public RedBlackTree() {
        _size = 0;
        _nil = new RedBlackNode<K>();
        _root = _nil;
    }

    public int height() {
        return RedBlackNode._height(this, _root);
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public boolean isEmpty() {
        return _root == _nil;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean contains(Object o) {
        return RedBlackNode._search(this, _root, (K) o) != _nil;
    }

    @Override
    public Iterator<K> iterator() {
        return new RedBlackTreeIterator<K>(this, RedBlackNode._minimum(this, _root));
    }

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

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(K k) {
        if (!contains(k)) {
            RedBlackNode._add(this, new RedBlackNode<K>(this, k));
            _size++;
            return true;
        }
        return false;
    }

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
    @SuppressWarnings("unchecked")
    public boolean addAll(Collection<? extends K> c) {
        boolean r = false;
        for (Object x : c) {
            r = add((K) x) || r;
        }
        return r;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean r = false;
        for (Object x : c) {
            r = remove(x) || r;
        }
        return r;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean r = false;
        for (Object x : c) {
            if (!contains(x)) {
                r = remove(x) || r;
            }
        }
        return r;
    }

    @Override
    public void clear() {
        _size = 0;
        _root = _nil;
    }

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
