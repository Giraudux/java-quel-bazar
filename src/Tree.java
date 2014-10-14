/**
 * @author Alexis Giraudet
 */
public class Tree<T> {
    protected Comparable<T> _label;
    protected Tree<T> _parent;
    protected Tree<T> _left;
    protected Tree<T> _right;

    public Tree(Comparable<T> label) {
        _label = label;
        _parent = null;
        _left = null;
        _right = null;
    }

    public void setParent(Tree<T> parent) {
        _parent = parent;
    }

    public void setLeft(Tree<T> left) {
        _left = left;
    }

    public void setRight(Tree<T> right) {
        _right = right;
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
