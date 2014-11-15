package unionfind;

/**
 * Classe qui implémente la structure Classe-Union
 * @author Alexis Giraudet & François Hallereau
 * @version 1.0
 */
public class MakeSetTree<K extends Comparable<K>>{

    /**
     * _parent fait référence au père de l'objet
     */
    protected MakeSetTree<K> _parent;

    /**
     * _key représente la valeur à stocker
     */
    protected final K _key;

    /**
     * _rank est le rang de la classe
     */
    protected int _rank;

    /**
     * Constructeur de la classe MakeSetTree
     * @param ka la valeur attribué à _key @see #_key
     */
    public MakeSetTree(K ka){
        _key = ka;
        _parent = null;
        _rank = 0;
    }

    /**
     * La méthode union fusionne deux classes en mettant à jour
     * le parent et le rang
     * @see #_parent
     * @see #_rank
     * @param set la classe à fusionner
     */
    public void union(MakeSetTree<K> set) {
        MakeSetTree<K> xRep = this.find();
        MakeSetTree<K> yRep = set.find();

        if(xRep != yRep){
            if(xRep._rank < yRep._rank){
                xRep._parent = yRep;
            }
            else if(xRep._rank > yRep._rank){
                yRep._parent = xRep;
            }
            else{
                yRep._parent = xRep;
                xRep._rank++;
            }
        }

    }

    /**
     * La méthode retourne le père de la classe
     * @return le parent de la classe
     */
    public MakeSetTree<K> find() {
        if(this._parent != null)
            this._parent = this._parent.find();
        return this._parent;
    }
}
