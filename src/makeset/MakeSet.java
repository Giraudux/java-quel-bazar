package makeset;

/**
 * Classe qui implémente la structure Classe-Union
 * version statique
 * @see unionfind.MakeSetTree
 * @author Alexis Giraudet & François Hallereau
 * @version 1.0
 */
public class MakeSet<K> {

    /**
     * _key représente la valeur à stocker
     */
    protected final K _key;

    /**
     * _parent fait référence au père de l'objet
     */
    protected MakeSet<K> _parent;

    /**
     * _rank est le rang de la classe
     */
    protected long _rank;

    /**
     * Constructeur de la classe MakeSet
     * @param k la valeur attribué à _key @see #_key
     */
    public MakeSet(K k) {
        _key = k;
        _parent = null;
        _rank = 0;
    }

    /**
     * La méthode union fusionne deux classes en mettant à jour
     * le parent et le rang
     * @param x la classe à fusionner
     * @param y l'autre classe à fusionner
     */
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

    /**
     * La méthode retourne le père de la classe
     * @param x la classe
     * @return le père de la classe
     */
    public static <K> MakeSet<K> find(MakeSet<K> x) {
        if (x._parent != x) {
            x._parent = find(x._parent);
        }
        return x._parent;
    }
}
