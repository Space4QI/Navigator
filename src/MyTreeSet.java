//import java.util.Comparator;
//import java.util.Iterator;
//import java.util.NoSuchElementException;
//
//public class MyTreeSet<T> implements Iterable<T> {
//
//    private Node root;
//
//    private Comparator<? super T> comparator;
//
//    public MyTreeSet(Node root, Comparator<? super T> comparator) {
//        this.root = root;
//        this.comparator = comparator;
//    }
//
//    private int compare(T a, T b) {
//        if (comparator != null) {
//            return comparator.compare(a, b);
//        } else {
//            return ((Comparable<? super T>) a).compareTo(b);
//        }
//    }
//
//    private boolean contains(Node<T> node, T value) {
//        if (node == null) {
//            return false;
//        }
//
//        int temp = compare(value, node.value);
//
//        if (temp < 0) {
//            return contains(node.left, value);
//        } else if (temp > 0) {
//            return contains(node.right, value);
//        } else {
//            return true;
//        }
//    }
//
//    private Node<T> add(Node<T> node, T value) {
//        if (node == null) {
//            return new Node<>(value);
//        }
//
//        int temp = compare(value, node.value);
//
//        if (temp == 0) {
//            return node;
//        } else if (temp < 0) {
//            node.left = add(node.left, value);
//        } else {
//            node.right = add(node.right, value);
//        }
//
//        node.height = 1 + Math.max(height(node.left), height(node.right));
//
//        int balance = getBalance(node);
//
//        if (balance > 1) {
//            if (getBalance(node.left) < 0) {
//                node.left = rotateLeft(node.left);
//            }
//            return rotateRight(node);
//
//        } else if (balance < -1) {
//            if (getBalance(node.right) > 0) {
//                node.right = rotateRight(node.right);
//            }
//            return rotateLeft(node);
//        }
//
//        return node;
//    }
//
//    private Node<T> delete(Node<T> node, T value) {
//        if (node == null) {
//            return null;
//        }
//
//        int temp = compare(value, node.value);
//
//        if (temp == 0) {
//            if (node.left == null) {
//                return node.right;
//            } else if (node.right == null) {
//                return node.left;
//            }
//
//            Node<T> successor = findMin(node.right);
//            node.value = successor.value;
//            node.right = delete(node.right, successor.value);
//
//        } else if (temp < 0) {
//            node.left = delete(node.left, value);
//        } else {
//            node.right = delete(node.right, value);
//        }
//
//        if (node != null) {
//            node.height = 1 + Math.max(height(node.left), height(node.right));
//
//            int balance = getBalance(node);
//
//            if (balance > 1) {
//                if (node.left != null && getBalance(node.left) < 0) {
//                    node.left = rotateLeft(node.left);
//                }
//                return rotateRight(node);
//
//            } else if (balance < -1) {
//                if (node.right != null && getBalance(node.right) > 0) {
//                    node.right = rotateRight(node.right);
//                }
//                return rotateLeft(node);
//            }
//        }
//
//        return node;
//    }
//
//    private int height(Node<T> node) {
//        if (node == null) {
//            return 0;
//        } else {
//            return node.height;
//        }
//    }
//
//    private int getBalance(Node<T> node) {
//        if (node == null) {
//            return 0;
//        } else {
//            return height(node.left) - height(node.right);
//        }
//    }
//
//    private Node<T> rotateRight(Node<T> node) {
//        Node<T> newRoot = node.left;
//        node.left = newRoot.right;
//        newRoot.right = node;
//
//        node.height = 1 + Math.max(height(node.left), height((node.right)));
//        newRoot.height = 1 + Math.max(height(newRoot.left), height(newRoot.right));
//
//        return newRoot;
//    }
//
//    private Node<T> rotateLeft(Node<T> node) {
//        Node<T> newRoot = node.right;
//        node.right = newRoot.left;
//        newRoot.left = node;
//
//        node.height = 1 + Math.max(height(node.left), height((node.right)));
//        newRoot.height = 1 + Math.max(height(newRoot.left), height(newRoot.right));
//
//        return newRoot;
//    }
//
//    @Override
//    public Iterator<T> iterator() {
//        return new LinkedListIterator();
//    }
//
//    private class LinkedListIterator implements Iterator<T> {
//        private Node<T> currentNode = findMin(root);
//
//        @Override
//        public boolean hasNext() {
//            return currentNode != null;
//        }
//
//        @Override
//        public T next() {
//            if (!hasNext()) {
//                throw new NoSuchElementException();
//            }
//            T value = currentNode.value;
//            currentNode = successor(currentNode);
//            return value;
//        }
//    }
//
//    private Node<T> successor(Node<T> node) {
//        if (node == null) {
//            return null;
//        }
//
//        if (node.right != null) {
//            return findMin(node.right);
//        }
//
//        Node<T> successor = null;
//        Node<T> current = root;
//
//        while (current != null) {
//            int cmp = compare(node.value, current.value);
//
//            if (cmp < 0) {
//                successor = current;
//                current = current.left;
//            } else if (cmp > 0) {
//                current = current.right;
//            } else {
//                break;
//            }
//        }
//
//        return successor;
//    }
//
//    private Node<T> findMin(Node<T> node) {
//        if (node == null) {
//            return null;
//        }
//        while (node.left != null) {
//            node = node.left;
//        }
//        return node;
//    }
//}