package tree;

import java.util.Set;

/**
 * Interface Tree pour les structures de données implémentant un type d'arbe
 * @author Alexis Giraudet & François Hallereau
 * @version 1.0
 */
public interface Tree<K extends Comparable<K>> extends Set<K> {
    /**
     * Méthode retournant la hauteur de l'arbre
     * @return int hauteur
     */
    public int height();
}
