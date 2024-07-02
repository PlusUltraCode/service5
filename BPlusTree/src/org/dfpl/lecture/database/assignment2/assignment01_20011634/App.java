package org.dfpl.lecture.database.assignment2.assignment01_20011634;



import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("unused")
public class App {

    public static void main(String[] args) {
        System.out.println("Assignment 4: ");

        // 평가에서는 m (>=3), c1, c2, c3를 수정하여 수행한다.
        int m = 5;
        int c1 = 7;
        int c2 = 21;
        int c3 = 22;

        MyBPlusTree bpTree = new MyBPlusTree(m);
        for (int i = 1; i <= c3; i++) {
            bpTree.add(i);
        }


        System.out.println("hell");
        MyBPlusTreeNode n1 = bpTree.getNode(c1);
        System.out.println();
        MyBPlusTreeNode n2 = bpTree.getNode(c2);
        System.out.println();
        // 학습적인 의미에서 재귀적으로 inorder traversal 하여 구현해야 함.
        bpTree.inorderTraverse();

        System.out.println("Assignment 5: ");

        ArrayList<Integer> values = new ArrayList<Integer>();
        Iterator<Integer> iterator = bpTree.iterator();
        while(iterator.hasNext()) {
            Integer value = iterator.next();
            values.add(value);
        }

        System.out.println("iterator test: " + (c3 == values.size()));
        System.out.println("iterator test: " + (c3 == bpTree.size()));

        for(Integer value: values) {
            bpTree.remove(value);
        }
        System.out.println("remove test: " + (bpTree.size() == 0));
    }
}
