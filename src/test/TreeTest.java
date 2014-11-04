package test;

import tree.RedBlackTree;
import tree.Tree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Alexis Giraudet
 */
//TODO: use junit
public class TreeTest {
    public static void Test0() {
        int size = 1000;
        ArrayList<Integer> a = new ArrayList<Integer>(size);
        RedBlackTree<Integer> t = new RedBlackTree<Integer>();
        for(int i=0; i<size; i++) {
            a.add(i, i);
        }
        Collections.shuffle(a);

        System.out.println("#addAll = "+t.addAll(a));
        printTree(t);

        System.out.println("#containsAll = "+t.containsAll(a));
        printTree(t);

        System.out.println("#removeAll = "+t.removeAll(a));
        printTree(t);
    }

    protected static <K extends Comparable<K>> void printTree(Tree<K> t) {
        System.out.println("height = "+t.height());
        System.out.println("size = "+t.size());
        System.out.println("isEmpty = "+t.isEmpty());
        System.out.println("content = "+t.toString());
    }
}
