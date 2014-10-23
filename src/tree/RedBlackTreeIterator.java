package tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Alexis Giraudet
 */
public class RedBlackTreeIterator<K extends Comparable<K>> implements Iterator<K> {
    private RedBlackNode<K> __currentNode;

    public RedBlackTreeIterator(RedBlackNode<K> x) {
        __currentNode = x;
    }

    @Override
    public boolean hasNext() {
        return __currentNode._key != null;
    }

    @Override
    public K next() {
        if (__currentNode._key == null) {
            throw new NoSuchElementException();
        }
        K k = __currentNode._key;
        __currentNode = RedBlackNode._successor(__currentNode);
        return k;
    }
}
