package tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Alexis Giraudet
 */
public class RedBlackTreeIterator<K extends Comparable<K>> implements Iterator<K> {
    private RedBlackTree<K> __currentTree;
    private RedBlackNode<K> __currentNode;

    public RedBlackTreeIterator(RedBlackTree<K> T, RedBlackNode<K> x) {
        __currentTree = T;
        __currentNode = x;
    }

    @Override
    public boolean hasNext() {
        return __currentNode != __currentTree.nil;
    }

    @Override
    public K next() {
        if (__currentNode == __currentTree.nil) {
            throw new NoSuchElementException();
        }
        K k = __currentNode._key;
        __currentNode = RedBlackNode._successor(__currentTree, __currentNode);
        return k;
    }
}
