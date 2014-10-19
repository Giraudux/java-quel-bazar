package tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Alexis Giraudet
 */
public class BinarySearchTreeIterator<T extends Comparable<T>> implements Iterator<T> {
    private BinarySearchNode<T> __current;

    public BinarySearchTreeIterator(BinarySearchNode<T> node) {
        __current = node;
    }

    @Override
    public boolean hasNext() {
        return __current != null;
    }

    @Override
    public T next() {
        if (__current == null) {
            throw new NoSuchElementException();
        }
        T x = __current._key;
        __current = BinarySearchNode._successor(__current);
        return x;
    }
}
