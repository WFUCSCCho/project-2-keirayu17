/**
 * @file: BST.java
 * @description: Binary Search Tree structure. Includes basic utility methods and
 *               an iterator for the BST. The iterator is designed as an in-order
 *               traversal.
 * @author: Keira Yu
 * @date: October 24, 2024
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class BST<T extends Comparable<T>>{
    private Node<T> root;
    private int size;

    // Implement the constructor
    public BST(){
        root = null;
        size = 0;
    }

    // Implement the clear method
    public void clear(){
        root = null;
        size = 0;
    }

    // Implement the size method
    public int size(){
        return size;
    }

    // Implement the insert method
    public void insert(T element){
        root = insertHelp(root,element);
        size++;
    }

    // Helper method for inserting
    private Node<T> insertHelp(Node<T> root,T element){
        if (root == null){
            return new Node<>(element);
        }
        if (element.compareTo(root.element) < 0){
            root.left = insertHelp(root.left,element);
        }
        else if (element.compareTo(root.element) > 0){
            root.right = insertHelp(root.right,element);
        }
        return root;
    }

    // Implement the remove method
    public T remove(T element){
        T temp = searchHelp(root, element);
        if (temp != null){
            root = removeHelp(root, element);
            size--;
        }
        return temp;
    }

    // Helper method for removing
    private Node<T> removeHelp(Node<T> root,T element){
        if (root == null){
            return null;
        }
        if (root.element.compareTo(element) > 0){
            root.setLeft(removeHelp(root.left, element));
        }
        else if (root.element.compareTo(element) < 0){
            root.setRight(removeHelp(root.right, element));
        }
        else{
            if (root.left == null){
                return root.right;
            }
            else if (root.right == null){
                return root.left;
            }
            else{
                Node<T> temp = getMax(root.left);
                root.setElement(temp.element);
                root.setLeft(deleteMax(root.left));
            }
        }
        return root;
    }

    // Helper method for deleting max
    private Node<T> deleteMax(Node<T> root){
        if (root.right == null){
            return root.left;
        }
        root.setRight(deleteMax(root.right));
        return root;
    }

    // Helper method for getting max
    private Node<T> getMax(Node<T> root){
        while(root.right != null){
            root = root.right;
        }
        return root;
    }

    // Implement the search method
    public T search(T element){
        return searchHelp(root, element);
    }

    // Helper method for searching
    private T searchHelp(Node<T> root, T element){
        if (root == null){
            return null;
        }
        if (element.compareTo(root.element) < 0){
            return searchHelp(root.left,element);
        }
        else if (element.compareTo(root.element) > 0){
            return searchHelp(root.right,element);
        }
        return root.element;
    }

    // Implement the iterator method
    public Iterator<T> iterator(){
        return new BSIterator(root);
    }

    // Implement the BSTIterator class
    private class BSIterator implements Iterator<T> {
        private Stack<Node<T>> stack = new Stack<>();

        public BSIterator(Node<T> root) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<T> node = stack.pop();
            T result = node.element;

            if (node.right != null) {
                Node<T> temp = node.getRight();
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            }
            return result;
        }
    }
}

