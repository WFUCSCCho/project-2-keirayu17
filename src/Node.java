/**
 * @file: Node.java
 * @description: A node class for BST. Includes constructors and fundamental
 *               utility methods for a tree node.
 * @author: Keira Yu
 * @date: October 24, 2024
 */
public class Node<T extends Comparable<T>> {
    T element;
    Node<T> left;
    Node<T> right;
    // Implement the constructor
    public Node(T element) {
        this.element = element;
        left = right = null;
    }

    // Implement the setElement method
    public void setElement(T element) {
        this.element = element;
    }

    // Implement the setLeft method
    public void setLeft(Node<T> left) {
        this.left = left;
    }

    // Implement the setRight method
    public void setRight(Node<T> right) {
        this.right = right;
    }

    // Implement the getLeft method
    public Node<T> getLeft() {
        return this.left;
    }

    // Implement the getRight method
    public Node<T> getRight() {
        return this.right;
    }

    // Implement the getElement method
    public T getElement() {
        return this.element;
    }

    // Implement the isLeaf method
    public boolean isLeaf(){
        return (this.left == null && this.right == null);
    }
}




