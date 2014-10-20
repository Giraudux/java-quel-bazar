package tree;

/**
 * @author Alexis Giraudet
 */
class RedBlackNode<K extends Comparable<K>> {
    protected static final boolean RED = false, BLACK = true;
    protected final K _key;
    protected RedBlackNode<K> _parent, _left, _right;
    protected boolean _colour;

    protected RedBlackNode(K k) {
        _key = k;
        _parent = null;
        _left = null;
        _right = null;
        _colour = RED;
    }

    protected static <K extends Comparable<K>> RedBlackNode<K> _search(RedBlackNode<K> x, K k) {
        while ((x != null) && (!k.equals(x._key))) {
            if (k.compareTo(x._key) < 0) {
                x = x._left;
            } else {
                x = x._right;
            }
        }
        return x;
    }

    protected static <K extends Comparable<K>> RedBlackNode<K> _minimum(RedBlackNode<K> x) {
        while (x._left != null) {
            x = x._left;
        }
        return x;
    }

    protected static <K extends Comparable<K>> RedBlackNode<K> _maximum(RedBlackNode<K> x) {
        while (x._right != null) {
            x = x._right;
        }
        return x;
    }

    protected static <K extends Comparable<K>> RedBlackNode<K> _successor(RedBlackNode<K> x) {
        if (x._right != null) {
            return _minimum(x._right);
        }
        RedBlackNode<K> y = x._parent;
        while ((y != null) && (x == y._right)) {
            x = y;
            y = y._parent;
        }
        return y;
    }

    protected static <K extends Comparable<K>> int _size(RedBlackNode<K> x) {
        if (x == null) {
            return 0;
        }
        return 1 + _size(x._left) + _size(x._right);
    }

    protected static <K extends Comparable<K>> void _leftRotation(RedBlackTree<K> T, RedBlackNode<K> x) {
        RedBlackNode<K> y = x._right;
        x._right = y._left;
        if(y._left != null) {
            y._left._parent = x;
        }
        y._parent = x._parent;
        if(x._parent == null) {
            T._root = y;
        } else if(x == x._parent._left) {
            x._parent._left = y;
        } else {
            x._parent._right = y;
        }
        y._left = x;
        x._parent = y;
    }

    protected static <K extends Comparable<K>> void _rightRotation(RedBlackTree<K> T, RedBlackNode<K> x) {
        RedBlackNode<K> y = x._left;
        x._left = y._right;
        if(y._right != null) {
            y._right._parent = x;
        }
        y._parent = x._parent;
        if(x._parent == null) {
            T._root = y;
        } else if(x == x._parent._right) {
            x._parent._right = y;
        } else {
            x._parent._left = y;
        }
        y._right = x;
        x._parent = y;
    }

    protected static <K extends Comparable<K>> void _addCorrection(RedBlackTree<K> T, RedBlackNode<K> z) {
        while (z._parent._colour == RED) {
            if(z._parent == z._parent._parent._left) {
                RedBlackNode<K> y = z._parent._parent._right;
                if(y._colour == RED) {
                    z._parent._colour = BLACK;
                    y._colour = BLACK;
                    z._parent._parent._colour = RED;
                    z = z._parent._parent;
                } else {
                    if(z == z._parent._right) {
                        z = z._parent;
                        _leftRotation(T, z);
                    }
                    z._parent._colour = BLACK;
                    z._parent._parent._colour = RED;
                    _rightRotation(T, z);
                }
            } else {
                RedBlackNode<K> y = z._parent._parent._left;
                if(y._colour == RED) {
                    z._parent._colour = BLACK;
                    y._colour = BLACK;
                    z._parent._parent._colour = RED;
                    z = z._parent._parent;
                } else {
                    if(z == z._parent._left) {
                        z = z._parent;
                        _rightRotation(T, z);
                    }
                    z._parent._colour = BLACK;
                    z._parent._parent._colour = RED;
                    _leftRotation(T, z);
                }
            }
        }
        T._root._colour = BLACK;
    }

    protected static <K extends Comparable<K>> void _add(RedBlackTree<K> T, RedBlackNode<K> z) {
        RedBlackNode<K> y = null;
        RedBlackNode<K> x = T._root;
        while (x != null) {
            y = x;
            if(z._key.compareTo(x._key) < 0) {
                x = x._left;
            } else {
                x = x._right;
            }
        }
        z._parent = y;
        if(y == null) {
            T._root = z;
        } else if(z._key.compareTo(y._key) < 0) {
            y._left = z;
        } else {
            y._right = z;
        }
        _addCorrection(T, z);
    }
}
