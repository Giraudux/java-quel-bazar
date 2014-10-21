package makeset;

/**
 * @author Alexis Giraudet
 */
public class MakeSet<K> {
    protected final K _key;
    protected MakeSet<K> _parent;
    protected long _rank;

    public MakeSet(K k) {
        _key = k;
        _parent = null;
        _rank = 0;
    }

    public static <K> void union(MakeSet<K> x, MakeSet<K> y) {
        MakeSet<K> xRoot = find(x);
        MakeSet<K> yRoot = find(y);
        if (xRoot != yRoot) {
            if (xRoot._rank < yRoot._rank) {
                xRoot._parent = yRoot;
            } else if (xRoot._rank > yRoot._rank) {
                yRoot._parent = xRoot;
            } else {
                yRoot._parent = xRoot;
                xRoot._rank++;
            }
        }
    }

    public static <K> MakeSet<K> find(MakeSet<K> x) {
        if (x._parent != x) {
            x._parent = find(x._parent);
        }
        return x._parent;
    }
}
