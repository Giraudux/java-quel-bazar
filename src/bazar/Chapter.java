package bazar;

import tree.RedBlackTree;
import tree.Tree;

/**
 * @author Alexis Giraudet
 */
public class Chapter implements Comparable {
    private final Integer __id;
    private Tree<String> __keyWords;
    private Tree<Page> __pages;

    public Chapter(Integer id) {
        __id = id;
        __keyWords = new RedBlackTree<String>();
        __pages = new RedBlackTree<Page>();
    }

    public Integer getId() {
        return __id;
    }

    public Tree<String> getKeyWords() {
        return __keyWords;
    }

    public Tree<Page> getPages() {
        return __pages;
    }

    boolean addPage(Page p) {
        __pages.add(p);
        __keyWords.addAll(p.getWords());
        return true;
    }

    boolean addPage(Page p, int k) {
        for (Page pp : __pages) {
            if (pp.containsWords(p, k)) {
                addPage(p);
                return true;
            }
        }
        return false;
    }

    @Override
    public int compareTo(Object o) {
        return __id.compareTo(((Chapter) o).getId());
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Chapter ").append(__id).append(":\npages=");
        for (Page p : __pages) {
            s.append(" ").append(p.getId());
        }
        s.append("\nkeywords=");
        for (String w : __keyWords) {
            s.append(" ").append(w);
        }
        s.append("\n");
        return s.toString();
    }
}
