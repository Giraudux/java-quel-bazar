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
        _parent = this;
        _left = this;
        _right = this;
        _colour = BLACK;
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
        if (y._left != T.nil) {
            y._left._parent = x;
        }
        y._parent = x._parent;
        if (x._parent == T.nil) {
            T._root = y;
        } else if (x == x._parent._left) {
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
        if (y._right != T.nil) {
            y._right._parent = x;
        }
        y._parent = x._parent;
        if (x._parent == T.nil) {
            T._root = y;
        } else if (x == x._parent._right) {
            x._parent._right = y;
        } else {
            x._parent._left = y;
        }
        y._right = x;
        x._parent = y;
    }

    protected static <K extends Comparable<K>> void _addCorrection(RedBlackTree<K> T, RedBlackNode<K> z) {
        while (z._parent._colour == RED) {
            if (z._parent == z._parent._parent._left) {
                RedBlackNode<K> y = z._parent._parent._right;
                if (y._colour == RED) {
                    z._parent._colour = BLACK;
                    y._colour = BLACK;
                    z._parent._parent._colour = RED;
                    z = z._parent._parent;
                } else {
                    if (z == z._parent._right) {
                        z = z._parent;
                        _leftRotation(T, z);
                    }
                    z._parent._colour = BLACK;
                    z._parent._parent._colour = RED;
                    _rightRotation(T, z);
                }
            } else {
                RedBlackNode<K> y = z._parent._parent._left;
                if (y._colour == RED) {
                    z._parent._colour = BLACK;
                    y._colour = BLACK;
                    z._parent._parent._colour = RED;
                    z = z._parent._parent;
                } else {
                    if (z == z._parent._left) {
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
            if (z._key.compareTo(x._key) < 0) {
                x = x._left;
            } else {
                x = x._right;
            }
        }
        z._parent = y;
        if (y == T.nil) {
            T._root = z;
        } else if (z._key.compareTo(y._key) < 0) {
            y._left = z;
        } else {
            y._right = z;
        }
        z._left = T.nil;
        z._right = T.nil;
        z._colour = RED;
        _addCorrection(T, z);
    }

    protected static <K extends Comparable<K>> void _transplant(RedBlackTree<K> T, RedBlackNode<K> u, RedBlackNode<K> v) {
        if (u._parent == T.nil) {
            T._root = v;
        } else if (u == u._parent._left) {
            u._parent._left = v;
        } else {
            u._parent._right = v;
        }
        v._parent = u._parent;
    }

    protected static <K extends Comparable<K>> void _removeCorrection(RedBlackTree<K> T, RedBlackNode<K> x) {
        while ((x != T._root) && (x._colour == BLACK)) {
            if (x == x._parent._left) {
                RedBlackNode<K> w = x._parent._right;
                if (w._colour == RED) {
                    w._colour = BLACK;
                    x._parent._colour = RED;
                    _leftRotation(T, x._parent);
                    w = x._parent._right;
                }
                if ((w._left._colour == BLACK) && (w._right._colour == BLACK)) {
                    w._colour = RED;
                    x = x._parent;
                } else {
                    if (w._right._colour == BLACK) {
                        w._left._colour = BLACK;
                        w._colour = RED;
                        _rightRotation(T, w);
                        w = x._parent._right;
                    }
                    w._colour = x._parent._colour;
                    x._parent._colour = BLACK;
                    w._right._colour = BLACK;
                    _leftRotation(T, x._parent);
                    x = T._root;
                }
            } else {
                RedBlackNode<K> w = x._parent._left;
                if (w._colour == RED) {
                    w._colour = BLACK;
                    x._parent._colour = RED;
                    _rightRotation(T, x._parent);
                    w = x._parent._left;
                }
                if ((w._right._colour == BLACK) && (w._left._colour == BLACK)) {
                    w._colour = RED;
                    x = x._parent;
                } else {
                    if (w._left._colour == BLACK) {
                        w._right._colour = BLACK;
                        w._colour = RED;
                        _leftRotation(T, w);
                        w = x._parent._left;
                    }
                    w._colour = x._parent._colour;
                    x._parent._colour = BLACK;
                    w._left._colour = BLACK;
                    _rightRotation(T, x._parent);
                    x = T._root;
                }
            }
        }
        x._colour = BLACK;
    }

    protected static <K extends Comparable<K>> void _remove(RedBlackTree<K> T, RedBlackNode<K> z) {
        RedBlackNode<K> x;
        RedBlackNode<K> y = z;
        boolean yOriginalColour = y._colour;
        if (z._left == T.nil) {
            x = z._right;
            _transplant(T, z, z._right);
        } else if (z._right == T.nil) {
            x = z._left;
            _transplant(T, z, z._left);
        } else {
            y = _minimum(T, z._right);
            yOriginalColour = y._colour;
            x = y._right;
            if (y._parent == z) {
                x._parent = y;
            } else {
                _transplant(T, y, y._right);
                y._right = z._right;
                y._right._parent = y;
            }
            _transplant(T, z, y);
            y._left = z._left;
            y._left._parent = y;
            y._colour = z._colour;
        }
        if (yOriginalColour == BLACK) {
            _addCorrection(T, x);
        }
    }
}
