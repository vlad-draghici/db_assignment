package exercise1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BinaryTreeTest {

    @Test
    @DisplayName("Should compute tree depth correctly")
    void testDepth() {
        BinaryTree tree = new BinaryTree();
        tree.addElements(new int[] {5, 3, 2, 10, 11});

        assertEquals(3, tree.getDepth());
    }

    @Test
    @DisplayName("Should compute max value correctly")
    void testMaxValue() {
        BinaryTree tree = new BinaryTree();
        tree.addElements(new int[] {5, 3, 2, 10, 11});

        assertEquals(11, tree.getMaxValue());
    }

    @Test
    @DisplayName("Should throw NoSuchElementException when trying to get max value of empty tree")
    void testMaxValueEmptyTree() {
        BinaryTree tree = new BinaryTree();

        assertThrows(NoSuchElementException.class, tree::getMaxValue);
    }

    @Test
    @DisplayName("Should return depth of 0 from empty tree")
    void testDepthEmptyTree() {
        BinaryTree tree = new BinaryTree();

        assertEquals(0, tree.getDepth());
    }
}
