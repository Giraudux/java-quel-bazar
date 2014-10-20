package tree;

/**
 * @author Alexis Giraudet
 */
class BinarySearchNode<K extends Comparable<K>> {
    protected final K _key;
    protected BinarySearchNode<K> _parent;
    protected BinarySearchNode<K> _left;
    protected BinarySearchNode<K> _right;

    protected BinarySearchNode(K k) {
        _key = k;
    }

    protected static <K extends Comparable<K>> BinarySearchNode<K> _search(BinarySearchNode<K> x, K k) {
        while ((x != null) && (!k.equals(x._key))) {
            if (k.compareTo(x._key) < 0) {
                x = x._left;
            } else {
                x = x._right;
            }
        }
        return x;
    }

    protected static <K extends Comparable<K>> BinarySearchNode<K> _minimum(BinarySearchNode<K> x) {
        while (x._left != null) {
            x = x._left;
        }
        return x;
    }

    protected static <K extends Comparable<K>> BinarySearchNode<K> _maximum(BinarySearchNode<K> x) {
        while (x._right != null) {
            x = x._right;
        }
        return x;
    }

    protected static <K extends Comparable<K>> BinarySearchNode<K> _successor(BinarySearchNode<K> x) {
        if (x._right != null) {
            return _minimum(x._right);
        }
        BinarySearchNode<K> y = x._parent;
        while ((y != null) && (x == y._right)) {
            x = y;
            y = y._parent;
        }
        return y;
    }

    protected static <K extends Comparable<K>> void _add(BinarySearchTree<K> T, BinarySearchNode<K> z) {
        BinarySearchNode<K> y = null;
        BinarySearchNode<K> x = T._root;
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
            T._root = z;
        } else if (z._key.compareTo(y._key) < 0) {
            y._left = z;
        } else {
            y._right = z;
        }
    }

    protected static <K extends Comparable<K>> void _transplant(BinarySearchTree<K> T, BinarySearchNode<K> u, BinarySearchNode<K> v) {
        if (u._parent == null) {
            T._root = v;
        } else if (u == u._parent._left) {
            u._parent._left = v;
        } else {
            u._parent._right = v;
        }
        if (v != null) {
            v._parent = u._parent;
        }
    }

    protected static <K extends Comparable<K>> void _remove(BinarySearchTree<K> T, BinarySearchNode<K> z) {
        if (z._left == null) {
            _transplant(T, z, z._right);
        } else if (z._right == null) {
            _transplant(T, z, z._left);
        } else {
            BinarySearchNode<K> y = _minimum(z._right);
            if (y._parent != z) {
                _transplant(T, y, y._right);
                y._right = z._right;
                y._right._parent = y;
            }
            _transplant(T, z, y);
            y._left = z._left;
            y._left._parent = y;
        }
    }

    protected static <K extends Comparable<K>> int _size(BinarySearchNode<K> x) {
        if (x == null) {
            return 0;
        }
        return 1 + _size(x._left) + _size(x._right);
    }
}
