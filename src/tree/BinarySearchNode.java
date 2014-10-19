package tree;

/**
 * @author Alexis Giraudet
 */
class BinarySearchNode<T extends Comparable<T>> {
    protected T _key;
    protected BinarySearchTree<T> _parent;
    protected BinarySearchTree<T> _left;
    protected BinarySearchTree<T> _right;
}
