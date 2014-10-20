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
    }

    protected static <K extends Comparable<K>> RedBlackNode<K> _search(RedBlackTree<K> T, RedBlackNode<K> x, K k) {
        while ((x != T.nil) && (!k.equals(x._key))) {
            if (k.compareTo(x._key) < 0) {
                x = x._left;
            } else {
                x = x._right;
            }
        }
        return x;
    }

    protected static <K extends Comparable<K>> RedBlackNode<K> _minimum(RedBlackTree<K> T, RedBlackNode<K> x) {
        while (x._left != T.nil) {
            x = x._left;
        }
        return x;
    }

    protected static <K extends Comparable<K>> RedBlackNode<K> _maximum(RedBlackTree<K> T, RedBlackNode<K> x) {
        while (x._right != T.nil) {
            x = x._right;
        }
        return x;
    }

    protected static <K extends Comparable<K>> RedBlackNode<K> _successor(RedBlackTree<K> T, RedBlackNode<K> x) {
        if (x._right != T.nil) {
            return _minimum(T, x._right);
        }
        RedBlackNode<K> y = x._parent;
        while ((y != T.nil) && (x == y._right)) {
            x = y;
            y = y._parent;
        }
        return y;
    }

    protected static <K extends Comparable<K>> int _size(RedBlackTree<K> T, RedBlackNode<K> x) {
        if (x == T.nil) {
            return 0;
        }
        return 1 + _size(T, x._left) + _size(T, x._right);
    }

    protected static <K extends Comparable<K>> void _leftRotation(RedBlackTree<K> T, RedBlackNode<K> x) {
        RedBlackNode<K> y = x._right;
        x._right = y._left;
        if(y._left != T.nil) {
            y._left._parent = x;
        }
        y._parent = x._parent;
        if(x._parent == T.nil) {
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
        if(y._right != T.nil) {
            y._right._parent = x;
        }
        y._parent = x._parent;
        if(x._parent == T.nil) {
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
        RedBlackNode<K> y = T.nil;
        RedBlackNode<K> x = T._root;
        while (x != T.nil) {
            y = x;
            if(z._key.compareTo(x._key) < 0) {
                x = x._left;
            } else {
                x = x._right;
            }
        }
        z._parent = y;
        if(y == T.nil) {
            T._root = z;
        } else if(z._key.compareTo(y._key) < 0) {
            y._left = z;
        } else {
            y._right = z;
        }
        _addCorrection(T, z);
    }
}
