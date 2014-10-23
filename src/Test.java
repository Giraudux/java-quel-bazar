import tree.RedBlackTree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Alexis Giraudet
 */
public class Test {
    public static void main(String[] args) {
        int size = 1000;
        ArrayList<Integer> a = new ArrayList<Integer>(size);
        RedBlackTree<Integer> t = new RedBlackTree<Integer>();

        for(int i=0; i<size; i++) {
            a.add(i, i);
        }
        Collections.shuffle(a);
        t.addAll(a);

        System.out.println("height = "+t.height());
        System.out.println("size = "+t.size());
        System.out.println(t);
    }
}
