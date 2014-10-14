import java.lang.Math;

/**
 * @author Alexis Giraudet
 */
public class Tree<T> {
    protected Comparable<T> _label;
    protected long _balance;
    protected Tree<T> _parent;
    protected Tree<T> _left;
    protected Tree<T> _right;

    public Tree(Comparable<T> label) {
        _label = label;
        _balance = 0;
        _parent = null;
        _left = null;
        _right = null;
    }

    public Tree(Tree<T> tree) {
        _label = tree._label;
        _balance = tree._balance;
        _parent = tree._parent;
        _left = tree._left;
        _right = tree._right;
    }

    public static <T> long height(Tree<T> tree) {
        if(tree == null) {
            return -1;
        }
        return Math.max(height(tree._left), height((tree._right)))+1;
    }

    public static <T> long balance(Tree<T> tree) {
        return height(tree._right) - height(tree._left);
    }

    public static <T> Tree<T> leftRotation(Tree<T> treeA){
        long a, b;
        Tree<T> treeB = new Tree<T>(treeA._right);
        a = treeA._balance;
        b = treeB._balance;
        treeA._right = treeB._left;
        treeB._left = treeA;
        treeA._balance = a-Math.max(b,0)-1;
        treeB._balance = Math.min(a-2,Math.min(a+b-2,b-1));
        return treeB;
    }

    /*public void setParent(Tree<T> parent) {
        _parent = parent;
    }*/

    public void setLeft(Tree<T> left) {
        _left = left;
        _left._parent = this;
    }

    public void setRight(Tree<T> right) {
        _right = right;
        _right._parent = this;
    }

    public Comparable<T> getLabel() {
        return _label;
    }

    public Tree<T> getParent() {
        return _parent;
    }

    public Tree<T> getLeft() {
        return _left;
    }

    public Tree<T> getRight() {
        return _right;
    }
}
