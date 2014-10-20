package tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Alexis Giraudet
 */
public class BinarySearchTreeIterator<K extends Comparable<K>> implements Iterator<K> {
    private BinarySearchNode<K> __current;

    public BinarySearchTreeIterator(BinarySearchNode<K> node) {
        __current = node;
    }

    @Override
    public boolean hasNext() {
        return __current != null;
    }

    @Override
    public K next() {
        if (__current == null) {
            throw new NoSuchElementException();
        }
        K k = __current._key;
        __current = BinarySearchNode._successor(__current);
        return k;
    }

    @Override
    public void remove() {
        ;
    }
}
