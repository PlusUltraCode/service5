package org.dfpl.lecture.database.assignment2.assignment01_20011634;

import java.util.*;

@SuppressWarnings("unused")
public class MyBPlusTree implements NavigableSet<Integer> {
    private MyBPlusTreeNode root;
    private LinkedList<MyBPlusTreeNode> leafList;
    private int m; // B+ Tree의 차수
    private int size; // 트리의 요소 수

    public MyBPlusTree(int m) {
        this.m = m; // 차수를 필드에 저장
        this.size = 0; // 초기 요소 수는 0

        // 루트 노드를 새로운 리프 노드로 초기화
        this.root = new MyBPlusTreeNode(m);

        // 리프 노드 리스트 초기화 및 루트 노드를 리스트에 추가
        this.leafList = new LinkedList<>();
        this.leafList.add(this.root);
    }

    @Override
    public Comparator<? super Integer> comparator() {
        // 자연 순서 사용
        return null;
    }

    @Override
    public Integer first() {
        if (root == null) {
            return null;
        }

        // 가장 왼쪽 리프 노드의 첫 번째 키를 반환
        MyBPlusTreeNode currentNode = root;
        while (!currentNode.isLeaf()) {
            currentNode = currentNode.getChildren().get(0);
        }

        return currentNode.getKeyList().get(0);
    }

    @Override
    public Integer last() {
        if (root == null) {
            return null;
        }

        // 가장 오른쪽 리프 노드의 마지막 키를 반환
        MyBPlusTreeNode currentNode = root;
        while (!currentNode.isLeaf()) {
            List<MyBPlusTreeNode> children = currentNode.getChildren();
            currentNode = children.get(children.size() - 1);
        }

        List<Integer> keys = currentNode.getKeyList();
        return keys.get(keys.size() - 1);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (!(o instanceof Integer)) {
            return false;
        }

        Integer key = (Integer) o;
        MyBPlusTreeNode node = getNode2(key);
        return node != null;
    }

    @Override
    public boolean add(Integer e) {
        if (contains(e)) {
            return false; // 중복 키는 추가하지 않음
        }

        MyBPlusTreeNode leafNode = findLeafNode(e);
        leafNode.getKeyList().add(e);
        Collections.sort(leafNode.getKeyList()); // 키를 정렬

        size++; // 요소 수 증가
        return true;
    }


    @Override
    public boolean remove(Object o) {
        if (!(o instanceof Integer)) {
            return false;
        }

        Integer key = (Integer) o;
        MyBPlusTreeNode node = getNode2(key);
        if (node == null) {
            System.out.println("> " + key + " not found");
            return false; // 키가 존재하지 않음
        }

        node.getKeyList().remove(key);
        size--; // 요소 수 감소
        return true;
    }



    public MyBPlusTreeNode getNode2(Integer key) {
        MyBPlusTreeNode currentNode = root;

        while (!currentNode.isLeaf()) {
            List<Integer> keys = currentNode.getKeyList();
            List<MyBPlusTreeNode> children = currentNode.getChildren();
            boolean found = false;

            for (int i = 0; i < keys.size(); i++) {
                if (key < keys.get(i)) {


                    currentNode = children.get(i);
                    found = true;
                    break;
                } else if (key >= keys.get(i) && i == keys.size() - 1) {


                    currentNode = children.get(i + 1);
                    found = true;
                    break;
                }
            }

            if (!found) {

                currentNode = children.get(children.size() - 1);
            }
        }

        List<Integer> leafKeys = currentNode.getKeyList();
        for (Integer leafKey : leafKeys) {
            if (leafKey.equals(key)) {


                return currentNode;
            }
        }


        return null;
    }


    @Override
    public Object[] toArray() {
        List<Integer> result = new ArrayList<>();
        inorderTraverseToArray(root, result);
        return result.toArray();
    }

    private void inorderTraverseToArray(MyBPlusTreeNode node, List<Integer> result) {
        if (node == null) return;
        if (node.isLeaf()) {
            result.addAll(node.getKeyList());
        } else {
            List<MyBPlusTreeNode> children = node.getChildren();
            for (int i = 0; i < node.getKeyList().size(); i++) {
                inorderTraverseToArray(children.get(i), result);
                result.add(node.getKeyList().get(i));
            }
            inorderTraverseToArray(children.get(children.size() - 1), result);
        }
    }

    @Override
    public <T> T[] toArray(T[] a) {
        List<Integer> result = new ArrayList<>();
        inorderTraverseToArray(root, result);
        return result.toArray(a);
    }

    private MyBPlusTreeNode findLeafNode(Integer key) {
        MyBPlusTreeNode currentNode = root;

        while (!currentNode.isLeaf()) {
            List<Integer> keys = currentNode.getKeyList();
            List<MyBPlusTreeNode> children = currentNode.getChildren();
            boolean found = false;

            for (int i = 0; i < keys.size(); i++) {
                if (key < keys.get(i)) {
                    currentNode = children.get(i);
                    found = true;
                    break;
                } else if (i == keys.size() - 1) {
                    currentNode = children.get(i + 1);
                    found = true;
                    break;
                }
            }

            if (!found) {
                currentNode = children.get(children.size() - 1);
            }
        }
        return currentNode;
    }
    public MyBPlusTreeNode getNode(Integer key) {
        MyBPlusTreeNode currentNode = root;
    if(currentNode.isLeaf()){
        System.out.println("말단노드");
    }
        while (!currentNode.isLeaf()) {
            List<Integer> keys = currentNode.getKeyList();
            List<MyBPlusTreeNode> children = currentNode.getChildren();
            boolean found = false;

            for (int i = 0; i < keys.size(); i++) {
                if (key < keys.get(i)) {
                    System.out.println("> less than " + keys.get(i));
                    currentNode = children.get(i);
                    found = true;
                    break;
                } else if (key >= keys.get(i) && i == keys.size() - 1) {
                    System.out.println("> larger than or equal to " + keys.get(i));
                    currentNode = children.get(i + 1);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("> larger than or equal to " + keys.get(keys.size() - 1));
                currentNode = children.get(children.size() - 1);
            }
        }

        List<Integer> leafKeys = currentNode.getKeyList();
        for (Integer leafKey : leafKeys) {
            if (leafKey.equals(key)) {
                System.out.println("> " + key + " found");
                return currentNode;
            }
        }

        System.out.println("> " + key + " not found");
        return null;
    }



    public void inorderTraverse() {
        if (root != null) {
            inorderTraversal(root);
        }
    }

    private void inorderTraversal(MyBPlusTreeNode node) {
        if (node.isLeaf()) {
            for (Integer key : node.getKeyList()) {
                System.out.println(key);
            }
        } else {
            List<MyBPlusTreeNode> children = node.getChildren();
            for (int i = 0; i < node.getKeyList().size(); i++) {
                inorderTraversal(children.get(i));
                System.out.println(node.getKeyList().get(i));
            }
            inorderTraversal(children.get(children.size() - 1));
        }
    }



    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        boolean modified = false;
        for (Integer e : c) {
            if (add(e)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Iterator<Integer> iterator = iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (!c.contains(element)) {
                iterator.remove();
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o : c) {
            if (remove(o)) {
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        root = new MyBPlusTreeNode(m);
        leafList.clear();
        leafList.add(root);
        size = 0;
    }

    @Override
    public Integer lower(Integer e) {
        Integer result = null;
        MyBPlusTreeNode currentNode = root;

        while (!currentNode.isLeaf()) {
            List<Integer> keys = currentNode.getKeyList();
            List<MyBPlusTreeNode> children = currentNode.getChildren();
            boolean found = false;

            for (int i = 0; i < keys.size(); i++) {
                if (e <= keys.get(i)) {
                    currentNode = children.get(i);
                    found = true;
                    break;
                }
            }

            if (!found) {
                currentNode = children.get(children.size() - 1);
            }
        }

        List<Integer> leafKeys = currentNode.getKeyList();
        for (Integer key : leafKeys) {
            if (key < e) {
                result = key;
            } else {
                break;
            }
        }

        return result;
    }

    @Override
    public Integer floor(Integer e) {
        Integer result = null;
        MyBPlusTreeNode currentNode = root;

        while (!currentNode.isLeaf()) {
            List<Integer> keys = currentNode.getKeyList();
            List<MyBPlusTreeNode> children = currentNode.getChildren();
            boolean found = false;

            for (int i = 0; i < keys.size(); i++) {
                if (e < keys.get(i)) {
                    currentNode = children.get(i);
                    found = true;
                    break;
                }
            }

            if (!found) {
                currentNode = children.get(children.size() - 1);
            }
        }

        List<Integer> leafKeys = currentNode.getKeyList();
        for (Integer key : leafKeys) {
            if (key <= e) {
                result = key;
            } else {
                break;
            }
        }

        return result;
    }

    @Override
    public Integer ceiling(Integer e) {
        Integer result = null;
        MyBPlusTreeNode currentNode = root;

        while (!currentNode.isLeaf()) {
            List<Integer> keys = currentNode.getKeyList();
            List<MyBPlusTreeNode> children = currentNode.getChildren();
            boolean found = false;

            for (int i = 0; i < keys.size(); i++) {
                if (e <= keys.get(i)) {
                    currentNode = children.get(i);
                    found = true;
                    break;
                }
            }

            if (!found) {
                currentNode = children.get(children.size() - 1);
            }
        }

        List<Integer> leafKeys = currentNode.getKeyList();
        for (Integer key : leafKeys) {
            if (key >= e) {
                result = key;
                break;
            }
        }

        return result;
    }



    @Override
    public Integer higher(Integer e) {
        Integer result = null;
        MyBPlusTreeNode currentNode = root;

        while (!currentNode.isLeaf()) {
            List<Integer> keys = currentNode.getKeyList();
            List<MyBPlusTreeNode> children = currentNode.getChildren();
            boolean found = false;

            for (int i = 0; i < keys.size(); i++) {
                if (e < keys.get(i)) {
                    currentNode = children.get(i);
                    found = true;
                    break;
                }
            }

            if (!found) {
                currentNode = children.get(children.size() - 1);
            }
        }

        List<Integer> leafKeys = currentNode.getKeyList();
        for (Integer key : leafKeys) {
            if (key > e) {
                result = key;
                break;
            }
        }

        return result;
    }

    @Override
    public Integer pollFirst() {
        if (root == null || size == 0) {
            return null;
        }

        MyBPlusTreeNode currentNode = root;
        while (!currentNode.isLeaf()) {
            currentNode = currentNode.getChildren().get(0);
        }

        Integer firstElement = currentNode.getKeyList().remove(0);
        size--;

        if (currentNode.getKeyList().isEmpty() && currentNode == root) {
            root = null;
        }

        return firstElement;
    }

    @Override
    public Integer pollLast() {
        if (root == null || size == 0) {
            return null;
        }

        MyBPlusTreeNode currentNode = root;
        while (!currentNode.isLeaf()) {
            List<MyBPlusTreeNode> children = currentNode.getChildren();
            currentNode = children.get(children.size() - 1);
        }

        List<Integer> keys = currentNode.getKeyList();
        Integer lastElement = keys.remove(keys.size() - 1);
        size--;

        if (keys.isEmpty() && currentNode == root) {
            root = null;
        }

        return lastElement;
    }

    @Override
    public Iterator<Integer> iterator() {
        List<Integer> elements = new ArrayList<>();
        inorderTraverseToList(root, elements);
        return elements.iterator();
    }

    private void inorderTraverseToList(MyBPlusTreeNode node, List<Integer> result) {
        if (node == null) return;
        if (node.isLeaf()) {
            result.addAll(node.getKeyList());
        } else {
            List<MyBPlusTreeNode> children = node.getChildren();
            for (int i = 0; i < node.getKeyList().size(); i++) {
                inorderTraverseToList(children.get(i), result);
                result.add(node.getKeyList().get(i));
            }
            inorderTraverseToList(children.get(children.size() - 1), result);
        }
    }


    @Override
    public NavigableSet<Integer> descendingSet() {
        TreeSet<Integer> descendingSet = new TreeSet<>(Collections.reverseOrder());
        descendingSet.addAll(this);
        return descendingSet;
    }

    @Override
    public Iterator<Integer> descendingIterator() {
        List<Integer> elements = new ArrayList<>();
        inorderTraverseToList(root, elements);
        Collections.reverse(elements);
        return elements.iterator();
    }

    @Override
    public NavigableSet<Integer> subSet(Integer fromElement, boolean fromInclusive, Integer toElement, boolean toInclusive) {
        TreeSet<Integer> subSet = new TreeSet<>();
        Iterator<Integer> iterator = iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if ((fromInclusive ? element >= fromElement : element > fromElement) &&
                    (toInclusive ? element <= toElement : element < toElement)) {
                subSet.add(element);
            }
        }
        return subSet;
    }

    @Override
    public NavigableSet<Integer> headSet(Integer toElement, boolean inclusive) {
        TreeSet<Integer> headSet = new TreeSet<>();
        Iterator<Integer> iterator = iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (inclusive ? element <= toElement : element < toElement) {
                headSet.add(element);
            } else {
                break;
            }
        }
        return headSet;
    }

    @Override
    public NavigableSet<Integer> tailSet(Integer fromElement, boolean inclusive) {
        TreeSet<Integer> tailSet = new TreeSet<>();
        Iterator<Integer> iterator = iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (inclusive ? element >= fromElement : element > fromElement) {
                tailSet.add(element);
            }
        }
        return tailSet;
    }
    @Override
    public SortedSet<Integer> subSet(Integer fromElement, Integer toElement) {
        return subSet(fromElement, true, toElement, false);
    }

    @Override
    public SortedSet<Integer> headSet(Integer toElement) {
        return headSet(toElement, false);
    }

    @Override
    public SortedSet<Integer> tailSet(Integer fromElement) {
        return tailSet(fromElement, true);
    }

}