package unionfind;

/**
 * @author Alexis Giraudet
 */
public class MakeSetTree<K extends Comparable<K>>{

    protected MakeSetTree<K> _parent;
    protected final K _key;
    protected int _rank;

    public MakeSetTree(K ka){
        _key = ka;
        _parent = null;
        _rank = 0;
    }


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

    public MakeSetTree<K> find() {
        if(this._parent != null)
            this._parent = this._parent.find();
        return this._parent;
    }
}
