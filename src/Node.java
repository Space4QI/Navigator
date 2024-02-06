public class Node<T> {
    T value;
    Node left;
    Node right;
    int height;

    Node(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}