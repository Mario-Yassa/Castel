package BST;

import java.util.Iterator;

public class BST<K extends Comparable<K>, V> implements Iterable<V> {

    private Node<V> root;

        public void insert(K key, V value) {
            root = insert(root, key, value);
        }

        private Node<V> insert(Node<V> currentRoot, K key, V value) {
            if (currentRoot == null) {
                return new Node<V>(key, value);
            } else if (key.compareTo(currentRoot.key) < 0) {
                currentRoot.left = insert(currentRoot.left, key, value);
                return currentRoot;
            } else if (key.compareTo(currentRoot.key) > 0) {
                currentRoot.right = insert(currentRoot.right, key, value);
                return currentRoot;
            } else if (key.compareTo(currentRoot.key) == 0) {
                return currentRoot;
            }
            return currentRoot;
        }

        public V find(K key) {
            if((find(key, root)) != null)
                return (find(key, root)).value;
            return null;
        }

        private Node<V> find(K key, Node<V> currentRoot) {
            if (currentRoot == null) {
                return null;
            }
            if (key.compareTo(currentRoot.key) == 0) {
                return currentRoot;
            }
            else if (key.compareTo(currentRoot.key) < 0) {
                return find(key, currentRoot.left); // look left
            }
            else if (key.compareTo(currentRoot.key) > 0) {
                return find(key, currentRoot.right); // look right
            }
            return null;
        }

    /*
    Remove Method (you implement!)
    1) if the root is null,
        The item is not in tree – return null.
    2) Compare the value to the data at the current root.
        a) if the value is less than the data at the current root,
            Return the result of removing from the left subtree.
        b) else if the value is greater than the current root
            Return the result of removing from the right subtree.
        c) else // The value is current root's data
            i) if the current root has no children
                Set the parent of the current root to null.
            ii) else if the current root has one child
                Set the parent of the current root to reference that child.
            iii) else
                Find the in-order predecessor, p.
                Set current root's value to be p's value.
                Remove p.
     */

        public Node<V> remove(K key) {
            root = remove(key, root);
            return root;
        }
        private Node<V> remove(K key, Node<V> currentRoot) {
            if(currentRoot == null) {
                return null;
            } else {
                if(key.compareTo(currentRoot.key) < 0) {
                    currentRoot.left = remove(key, currentRoot.left);
                } else if(key.compareTo(currentRoot.key) > 0) {
                    currentRoot.right = remove(key, currentRoot.right);
                } else {
                    if(currentRoot.left == null && currentRoot.right == null) {
                        currentRoot = null;
                        return currentRoot;
                    } else if(currentRoot.left == null){
                        currentRoot = currentRoot.right;
                        return currentRoot;
                    } else if(currentRoot.right == null) {
                        currentRoot = currentRoot.left;
                        return currentRoot;
                    } else {
                        Node<V> predecessorNode = predecessor(currentRoot);
                        currentRoot.key = predecessorNode.key;
                        currentRoot.value = predecessorNode.value;
                        currentRoot.left = remove(predecessorNode.key, currentRoot.left);
                        return currentRoot;
                    }

                }
            }

            return currentRoot;
        }

        private Node<V> predecessor(Node<V> currentRoot){

            currentRoot = currentRoot.left;
            while (currentRoot.right != null) {
                currentRoot = currentRoot.right;
            }
            return currentRoot;

        }

        public String toString() {
            if (root != null) {
                return root.toString().trim();
            }
            else return "Empty tree";
        }


        public Iterator<V> iterator(){
            return new BSTIterator();
        }

        private class BSTIterator implements Iterator<V> {

            Node<V> currentRoot;
            Node<V> parent;

            public BSTIterator() {
               currentRoot = root;
               parent = currentRoot;
            }


            public boolean hasNext() {
                return currentRoot != null;
            }


            public V next() {
                while (currentRoot != null) {
                    if (currentRoot.left == null) {
                        V value = currentRoot.value;
                        currentRoot = currentRoot.right;
                        return value;
                    } else {
                        parent = currentRoot.left;
                        while (parent.right != null && parent.right != currentRoot)
                            parent = parent.right;

                        if (parent.right == null) {
                            parent.right = currentRoot;
                            currentRoot = currentRoot.left;
                        } else {
                            parent.right = null;
                            V value = currentRoot.value;
                            currentRoot = currentRoot.right;
                            return value;
                        }
                    }
                }
                return null;
            }
        }

        private class Node<V> {
            private K key;
            private V value;
            private Node<V> left, right;

            public Node(K key, V value) {
                this.key = key;
                this.value = value;
            }

            public String toString() {
                String result = "";
                if (left != null) {
                    result += left.toString();
                }
                result += value.toString() + " ";
                if (right != null) {
                    result += right.toString();
                }
                return result;
            }

        }
        public static void main(String[] args) {

            BST<Integer,Integer> bst = new BST<Integer,Integer>();
            bst.insert(20,20);
            bst.insert(10,10);
            bst.insert(5,5);
            bst.insert(15,15);
            bst.insert(40,40);
            bst.insert(30,30);
            bst.insert(100,100);
            bst.insert(12,12);

        for(Integer n : bst)
            System.out.println(n);

//        Iterator<Integer> iterator = bst.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }

//        System.out.println(bst);
//        System.out.println(bst.find("a"));
//        System.out.println(bst.find("b"));
//        System.out.println(bst.find("d"));
//        System.out.println(bst.remove(5));
//        System.out.println(bst);
//        System.out.println(bst.find(20));
//        System.out.println(bst.find(100));
//        System.out.println(bst.find(300));
        }


    }


