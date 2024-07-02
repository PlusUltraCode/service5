package org.dfpl.lecture.database.assignment2.assignment01_20011634;


import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class MyBPlusTreeNode {

    private MyBPlusTreeNode parent;
    private List<Integer> keyList;
    private List<MyBPlusTreeNode> children;


    public MyBPlusTreeNode(int m) {
        this.keyList = new ArrayList<>(m - 1); // 최대 m-1개의 키를 가질 수 있음

        this.children = new ArrayList<>(m); // 최대 m개의 자식을 가질 수 있음


    }


    public List<Integer> getKeyList() {
        return keyList;
    }

    public List<MyBPlusTreeNode> getChildren() {
        return children;
    }

    public boolean isLeaf() {
        return children.isEmpty();
    }
    public void inorderTraversal() {
        if (isLeaf()) {
            for (Integer key : keyList) {
                System.out.println(key);
            }
        } else {
            for (int i = 0; i < keyList.size(); i++) {
                children.get(i).inorderTraversal();
                System.out.println(keyList.get(i));
            }
            children.get(keyList.size()).inorderTraversal(); // 마지막 자식 순회
        }
    }


    // 기타 메서드들...
}