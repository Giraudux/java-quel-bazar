package tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Alexis Giraudet
 */
public class RedBlackTreeIterator<K extends Comparable<K>> implements Iterator<K> {
    private RedBlackNode<K> __current;

    public RedBlackTreeIterator(RedBlackNode<K> x) {
        __current = x;
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
        __current = RedBlackNode._successor(__current);
        return k;
    }
}
