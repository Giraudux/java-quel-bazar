package tree;

/**
 * @author Alexis Giraudet
 */
class BinarySearchNode<T extends Comparable<T>> {
    protected T _key;
    protected BinarySearchNode<T> _parent;
    protected BinarySearchNode<T> _left;
    protected BinarySearchNode<T> _right;

    protected static <T extends Comparable<T>> BinarySearchNode<T> _search(BinarySearchNode<T> x, T k) {
        while ((x != null) && (!k.equals(x._key))) {
            if (k.compareTo(x._key) < 0) {
                x = x._left;
            } else {
                x = x._right;
            }
        }
        return x;
    }

    protected static <T extends Comparable<T>> BinarySearchNode<T> _minimum(BinarySearchNode<T> x) {
        while (x._left != null) {
            x = x._left;
        }
        return x;
    }

    protected static <T extends Comparable<T>> BinarySearchNode<T> _maximum(BinarySearchNode<T> x) {
        while (x._right != null) {
            x = x._right;
        }
        return x;
    }

    protected static <T extends Comparable<T>> BinarySearchNode<T> _successor(BinarySearchNode<T> x) {
        if (x._right != null) {
            return _minimum(x._right);
        }
        BinarySearchNode<T> y = x._parent;
        while ((y != null) && (x == y._right)) {
            x = y;
            y = y._parent;
        }
        return y;
    }

    protected static <T extends Comparable<T>> void _add(BinarySearchTree<T> t, BinarySearchNode<T> z) {
        BinarySearchNode<T> y = null;
        BinarySearchNode<T> x = t._root;
        while (x != null) {
            y = x;
            if (z._key.compareTo(x._key) < 0) {
                x = x._left;
            } else {
                x = x._right;
            }
        }
        z._parent = y;
        if (y == null) {
            t._root = z;
        } else if (z._key.compareTo(y._key) < 0) {
            y._left = z;
        } else {
            y._right = z;
        }
    }

    protected static <T extends Comparable<T>> void _transplant(BinarySearchTree<T> t, BinarySearchNode<T> u, BinarySearchNode<T> v) {
        if (u._parent == null) {
            t._root = v;
        } else if (u == u._parent._left) {
            u._parent._left = v;
        } else {
            u._parent._right = v;
        }
        if (v != null) {
            v._parent = u._parent;
        }
    }
}
