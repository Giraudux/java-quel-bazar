package bazar;

import tree.RedBlackTree;
import tree.Tree;

/**
 * @author Alexis Giraudet
 */
public class Page implements Comparable<Page> {
    private final Integer __id;
    private Tree<String> __words;

    public Page(Integer id) {
        __id = id;
        __words = new RedBlackTree<String>();
    }

    public Integer getId() {
        return __id;
    }

    public Tree<String> getWords() {
        return __words;
    }

    public boolean containsWords(Page p, int k) {
        int i = 0;
        for (String s : p.getWords()) {
            if (__words.contains(s)) {
                i++;
                if (i >= k) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public int compareTo(Page p) {
        return __id.compareTo(p.getId());
    }
}
