package ru.ifmo.se.lab1;

import org.junit.Assert;
import org.junit.Test;

public class TestAVLTree {
    /**
     * AVL Tree scenarios check
     */
    @Test(expected = RuntimeException.class)
    public void insertDuplicateKeyError() {
        AVLTree tree = new AVLTree();
        tree.insert(1);
        tree.insert(1);
    }

    @Test
    public void rotateLeftWorksCorrectly_withThreeNodesInsert() {
        AVLTree tree = new AVLTree();
        tree.insert(1);
        tree.insert(2);
        Assert.assertEquals(1, tree.height());
        Assert.assertEquals(1, tree.getRoot().key);
        Assert.assertEquals(2, tree.getRoot().right.key);

        tree.insert(3);
        Assert.assertEquals(1, tree.height());
        Assert.assertEquals(0, tree.getBalance(tree.getRoot()));
        Assert.assertEquals(2, tree.getRoot().key);
        Assert.assertEquals(3, tree.getRoot().right.key);
        Assert.assertEquals(1, tree.getRoot().left.key);
    }

    @Test
    public void rotateLeftWorksCorrectly_withFiveNodesInsert() {
        AVLTree tree = new AVLTree();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        Assert.assertEquals(2, tree.height());
        Assert.assertEquals(1, tree.getBalance(tree.getRoot()));

        Assert.assertEquals(2, tree.getRoot().key);
        Assert.assertEquals(3, tree.getRoot().right.key);
        Assert.assertEquals(4, tree.getRoot().right.right.key);

        tree.insert(5);
        Assert.assertEquals(2, tree.height());
        Assert.assertEquals(1, tree.getBalance(tree.getRoot()));

        Assert.assertEquals(2, tree.getRoot().key);
        Assert.assertEquals(4, tree.getRoot().right.key);
        Assert.assertEquals(5, tree.getRoot().right.right.key);
        Assert.assertEquals(3, tree.getRoot().right.left.key);
    }

    @Test
    public void deleteWorksCorrectly_withTwoNodesDeleted() {
        AVLTree tree = new AVLTree();
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);
        tree.insert(9);
        tree.delete(9);
        Assert.assertEquals(2, tree.height());
        Assert.assertEquals(1, tree.getBalance(tree.getRoot()));

        Assert.assertEquals(6, tree.getRoot().key);
        Assert.assertEquals(8, tree.getRoot().right.key);
        Assert.assertEquals(7, tree.getRoot().right.left.key);

        tree.delete(8);

        Assert.assertEquals(1, tree.height());
        Assert.assertEquals(0, tree.getBalance(tree.getRoot()));

        Assert.assertEquals(6, tree.getRoot().key);
        Assert.assertEquals(7, tree.getRoot().right.key);
    }

    @Test
    public void deleteWorksCorrectly_withOneNodeLeft() {
        AVLTree tree = new AVLTree();
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);
        tree.insert(9);
        tree.delete(9);
        tree.delete(8);
        tree.delete(7);
        tree.delete(6);

        Assert.assertEquals(0, tree.height());
        Assert.assertEquals(5, tree.getRoot().key);
    }

    @Test
    public void rotateRightWorksCorrectly() {
        AVLTree tree = new AVLTree();
        tree.insert(5);
        tree.insert(4);
        Assert.assertEquals(1, tree.height());

        Assert.assertEquals(5, tree.getRoot().key);
        Assert.assertEquals(4, tree.getRoot().left.key);

        tree.insert(3);
        Assert.assertEquals(1, tree.height());
        Assert.assertEquals(0, tree.getBalance(tree.getRoot()));

        Assert.assertEquals(4, tree.getRoot().key);
        Assert.assertEquals(3, tree.getRoot().left.key);
        Assert.assertEquals(5, tree.getRoot().right.key);
    }


    /**
     * Base class method tests
     */
    @Test
    public void givenEmptyTree_whenHeightCalled_shouldReturnMinus1() {
        AVLTree tree = new AVLTree();
        Assert.assertEquals(-1, tree.height());
    }

    @Test
    public void givenEmptyTree_whenInsertCalled_heightShouldBeZero() {
        AVLTree tree = new AVLTree();
        tree.insert(1);
        Assert.assertEquals(0, tree.height());
    }

    @Test
    public void givenEmptyTree_whenInsertCalled_treeShouldBeAvl() {
        AVLTree tree = new AVLTree();
        tree.insert(1);
        Assert.assertTrue(isAVL(tree));
    }

    @Test
    public void givenSampleTree_whenInsertCalled_treeShouldBeAvl() {
        AVLTree tree = getSampleAVLTree();
        int newKey = 11;
        tree.insert(newKey);
        Assert.assertTrue(isAVL(tree));
    }

    @Test
    public void givenSampleTree_whenFindExistingKeyCalled_shouldReturnMatchedNode() {
        AVLTree tree = getSampleAVLTree();
        int existingKey = 2;
        AVLTree.Node result = tree.find(existingKey);
        Assert.assertEquals(result.key, existingKey);
    }

    @Test
    public void givenSampleTree_whenFindNotExistingKeyCalled_shouldReturnNull() {
        AVLTree tree = getSampleAVLTree();
        int notExistingKey = 11;
        AVLTree.Node result = tree.find(notExistingKey);
        Assert.assertNull(result);
    }

    @Test
    public void givenEmptyTree_whenDeleteCalled_treeShouldBeAvl() {
        AVLTree tree = new AVLTree();
        tree.delete(1);
        Assert.assertTrue(isAVL(tree));
    }

    @Test
    public void givenSampleTree_whenDeleteCalled_treeShouldBeAvl() {
        AVLTree tree = getSampleAVLTree();
        tree.delete(1);
        Assert.assertTrue(isAVL(tree, tree.getRoot()));
    }

    private boolean isAVL(AVLTree tree) {
        return isAVL(tree, tree.getRoot());
    }

    private boolean isAVL(AVLTree tree, AVLTree.Node node) {
        if ( node == null )
            return true;
        int balance = tree.getBalance(node);
        return (balance <= 1 && balance >= -1) && isAVL(tree, node.left) && isAVL(tree, node.right);
    }

    private AVLTree getSampleAVLTree() {
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < 10; i++)
            avlTree.insert(i);
        return avlTree;
    }
}
