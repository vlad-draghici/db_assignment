package exercise1;

import java.util.*;

public class BinaryTree {

    private Node root;

    private static class Node {
        int val;
        Node left;
        Node right;

        private Node(int val) {
            this.val = val;
        }
    }

    public void addElements(int[] vals) {
        Arrays.stream(vals).forEach(this::addElement);
    }

    public void addElement(int val) {
        if (root == null) {
            root = new Node(val);
        } else {
            Queue<Node> queue = new ArrayDeque<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                Node node = queue.poll();

                if (node.left != null) {
                    queue.add(node.left);
                } else {
                    node.left = new Node(val);
                    return;
                }

                if (node.right != null) {
                    queue.add(node.right);
                } else {
                    node.right = new Node(val);
                    return;
                }
            }
        }
    }

    public int getDepth() {
        return getDepth(0, root);
    }

    private int getDepth(int depth, Node node) {
        if (node == null) { return depth; }

        ++depth;
        return Math.max(getDepth(depth, node.left),
                        getDepth(depth, node.right));
    }

    public int getMaxValue() {
        int max;
        if (root == null) {
            throw new NoSuchElementException("Tree is empty, cannot retrieve max value!");
        } else {
            Queue<Node> queue = new ArrayDeque<>();
            queue.add(root);
            max = root.val;
            while (!queue.isEmpty()) {
                Node node = queue.poll();

                if (node.left != null) {
                    queue.add(node.left);
                    max = Math.max(max, node.left.val);
                }

                if (node.right != null) {
                    queue.add(node.right);
                    max = Math.max(max, node.right.val);
                }
            }
        }
        return max;
    }
}
