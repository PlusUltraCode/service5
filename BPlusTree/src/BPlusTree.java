import java.util.ArrayList;
import java.util.Collections;

class BPlusTree {
    private int degree;
    private Node root;

    abstract class Node {
        ArrayList<Integer> keys;
        boolean isLeaf;

        Node(boolean isLeaf) {
            this.isLeaf = isLeaf;
            keys = new ArrayList<>();
        }
    }

    class InternalNode extends Node {
        ArrayList<Node> children;

        InternalNode() {
            super(false);
            children = new ArrayList<>();
        }
    }

    class LeafNode extends Node {
        ArrayList<Integer> values;
        LeafNode next;

        LeafNode() {
            super(true);
            values = new ArrayList<>();
            next = null;
        }
    }

    public BPlusTree(int degree) {
        this.degree = degree;
        root = new LeafNode();
    }

    public void insert(int key, int value) {
        Node r = root;
        if (r.keys.size() == 2 * degree - 1) {
            InternalNode s = new InternalNode();
            root = s;
            s.children.add(r);
            splitChild(s, 0);
            insertNonFull(s, key, value);
        } else {
            insertNonFull(r, key, value);
        }
    }

    private void splitChild(InternalNode x, int i) {
        Node y = x.children.get(i);
        Node z;
        if (y.isLeaf) {
            z = new LeafNode();
            ((LeafNode) z).next = ((LeafNode) y).next;
            ((LeafNode) y).next = (LeafNode) z;
        } else {
            z = new InternalNode();
        }

        x.children.add(i + 1, z);
        x.keys.add(i, y.keys.get(degree - 1));

        z.keys.addAll(y.keys.subList(degree, y.keys.size()));
        y.keys.subList(degree - 1, y.keys.size()).clear();

        if (!y.isLeaf) {
            ((InternalNode) z).children.addAll(((InternalNode) y).children.subList(degree, ((InternalNode) y).children.size()));
            ((InternalNode) y).children.subList(degree, ((InternalNode) y).children.size()).clear();
        } else {
            ((LeafNode) z).values.addAll(((LeafNode) y).values.subList(degree, ((LeafNode) y).values.size()));
            ((LeafNode) y).values.subList(degree, ((LeafNode) y).values.size()).clear();
        }
    }

    private void insertNonFull(Node x, int key, int value) {
        int i = x.keys.size() - 1;
        if (x.isLeaf) {
            ((LeafNode) x).values.add(value);
            x.keys.add(key);
            while (i >= 0 && x.keys.get(i) > key) {
                Collections.swap(x.keys, i, i + 1);
                Collections.swap(((LeafNode) x).values, i, i + 1);
                i--;
            }
        } else {
            while (i >= 0 && x.keys.get(i) > key) {
                i--;
            }
            i++;
            Node child = ((InternalNode) x).children.get(i);
            if (child.keys.size() == 2 * degree - 1) {
                splitChild((InternalNode) x, i);
                if (x.keys.get(i) < key) {
                    i++;
                }
            }
            insertNonFull(((InternalNode) x).children.get(i), key, value);
        }
    }

    public void delete(int key) {
        delete(root, key);
        if (root.keys.isEmpty() && !root.isLeaf) {
            root = ((InternalNode) root).children.get(0);
        }
    }

    private void delete(Node x, int key) {
        int idx = findKey(x, key);
        if (x.isLeaf) {
            if (idx < x.keys.size() && x.keys.get(idx) == key) {
                x.keys.remove(idx);
                ((LeafNode) x).values.remove(idx);
            }
        } else {
            InternalNode internalNode = (InternalNode) x;
            Node child = internalNode.children.get(idx);
            if (idx < x.keys.size() && x.keys.get(idx) == key) {
                if (child.keys.size() >= degree) {
                    int predecessorKey = getPredecessor(child);
                    x.keys.set(idx, predecessorKey);
                    delete(child, predecessorKey);
                } else {
                    Node sibling = internalNode.children.get(idx + 1);
                    if (sibling.keys.size() >= degree) {
                        int successorKey = getSuccessor(sibling);
                        x.keys.set(idx, successorKey);
                        delete(sibling, successorKey);
                    } else {
                        merge(internalNode, idx);
                        delete(child, key);
                    }
                }
            } else {
                if (child.keys.size() < degree) {
                    Node sibling = null;
                    if (idx != 0) {
                        sibling = internalNode.children.get(idx - 1);
                    }
                    if (sibling != null && sibling.keys.size() >= degree) {
                        borrowFromPrev(internalNode, idx);
                    } else {
                        sibling = null;
                        if (idx != internalNode.keys.size()) {
                            sibling = internalNode.children.get(idx + 1);
                        }
                        if (sibling != null && sibling.keys.size() >= degree) {
                            borrowFromNext(internalNode, idx);
                        } else {
                            if (idx != internalNode.keys.size()) {
                                merge(internalNode, idx);
                            } else {
                                merge(internalNode, idx - 1);
                            }
                        }
                    }
                }
                delete(internalNode.children.get(idx), key);
            }
        }
    }

    private int findKey(Node x, int key) {
        int idx = 0;
        while (idx < x.keys.size() && x.keys.get(idx) < key) {
            idx++;
        }
        return idx;
    }

    private int getPredecessor(Node x) {
        while (!x.isLeaf) {
            x = ((InternalNode) x).children.get(x.keys.size());
        }
        return x.keys.get(x.keys.size() - 1);
    }

    private int getSuccessor(Node x) {
        while (!x.isLeaf) {
            x = ((InternalNode) x).children.get(0);
        }
        return x.keys.get(0);
    }

    private void merge(InternalNode x, int idx) {
        Node child = x.children.get(idx);
        Node sibling = x.children.get(idx + 1);

        if (!child.isLeaf) {
            child.keys.add(x.keys.get(idx));
            child.keys.addAll(sibling.keys);
            ((InternalNode) child).children.addAll(((InternalNode) sibling).children);
        } else {
            child.keys.addAll(sibling.keys);
            ((LeafNode) child).values.addAll(((LeafNode) sibling).values);
            ((LeafNode) child).next = ((LeafNode) sibling).next;
        }

        x.keys.remove(idx);
        x.children.remove(idx + 1);
    }

    private void borrowFromPrev(InternalNode x, int idx) {
        Node child = x.children.get(idx);
        Node sibling = x.children.get(idx - 1);

        if (!child.isLeaf) {
            child.keys.add(0, x.keys.get(idx - 1));
            x.keys.set(idx - 1, sibling.keys.remove(sibling.keys.size() - 1));
            ((InternalNode) child).children.add(0, ((InternalNode) sibling).children.remove(sibling.keys.size() + 1));
        } else {
            child.keys.add(0, sibling.keys.remove(sibling.keys.size() - 1));
            ((LeafNode) child).values.add(0, ((LeafNode) sibling).values.remove(sibling.keys.size()));
        }
    }

    private void borrowFromNext(InternalNode x, int idx) {
        Node child = x.children.get(idx);
        Node sibling = x.children.get(idx + 1);

        if (!child.isLeaf) {
            child.keys.add(x.keys.get(idx));
            x.keys.set(idx, sibling.keys.remove(0));
            ((InternalNode) child).children.add(((InternalNode) sibling).children.remove(0));
        } else {
            child.keys.add(sibling.keys.remove(0));
            ((LeafNode) child).values.add(((LeafNode) sibling).values.remove(0));
        }
    }

    public Integer search(int key) {
        return search(root, key);
    }

    private Integer search(Node x, int key) {
        int i = 0;
        while (i < x.keys.size() && key > x.keys.get(i)) {
            i++;
        }
        if (i < x.keys.size() && key == x.keys.get(i)) {
            if (x.isLeaf) {
                return ((LeafNode) x).values.get(i);
            } else {
                return search(((InternalNode) x).children.get(i + 1), key);
            }
        } else if (x.isLeaf) {
            return null;
        } else {
            return search(((InternalNode) x).children.get(i), key);
        }
    }

    public static void main(String[] args) {
        BPlusTree bpt = new BPlusTree(3);
        bpt.insert(10, 100);
        bpt.insert(20, 200);
        bpt.insert(5, 50);
        bpt.insert(6, 60);
        bpt.insert(12, 120);

        System.out.println(bpt.search(10)); // Output: 100
        System.out.println(bpt.search(20)); // Output: 200
        System.out.println(bpt.search(5));  // Output: 50
        System.out.println(bpt.search(6));  // Output: 60
        System.out.println(bpt.search(12)); // Output: 120
        System.out.println(bpt.search(15)); // Output: null

        bpt.delete(6);
        System.out.println(bpt.search(6));  // Output: null

        bpt.delete(5);
        System.out.println(bpt.search(5));  // Output: null

        bpt.delete(12);
        System.out.println(bpt.search(12)); // Output: null
    }
}
