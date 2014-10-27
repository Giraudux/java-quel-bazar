package tree;

import java.util.Collection;

/**
 * @author Alexis Giraudet
 */
public interface Tree<K extends Comparable<K>> extends Collection<K> {
    public int height();
}
