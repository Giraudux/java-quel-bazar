package tree;

import java.util.Set;

/**
 * @author Alexis Giraudet
 */
public interface Tree<K extends Comparable<K>> extends Set<K> {
    public int height();
}
