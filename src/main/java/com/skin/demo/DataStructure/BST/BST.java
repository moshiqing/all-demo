package com.skin.demo.DataStructure.BST;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * 二分搜索树
 *
 * @param <E>
 */
public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    {
        System.out.printf("执行代码块");
    }

    public BST() {
        System.out.printf("执行构造函数");
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
//        if (root == null) {
//            root=new Node(e);
//            size++;
//        }else{
//            //从根节点开始判断插入元素
//            add(root,e);
//        }
        root = add(root, e);
    }

    //递归插入
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    //查询是否重复元素
    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    /**
     * 前序    中 左 右
     */
    public void pervOrder() {
        pervOrder(root);
    }

    private void pervOrder(Node node) {
        if (node != null) {
            System.out.println(node.e);
            pervOrder(node.left);
            pervOrder(node.right);
        }
    }

    /**
     * 中序   左 中 右
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node != null) {
            pervOrder(node.left);
            System.out.println(node.e);
            pervOrder(node.right);
        }
    }

    /**
     * 后序   左 右 中
     */
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.e);
        }
    }

    @Override
    public String toString() {
        return "BST{" +
                "root=" + root +
                ", size=" + size +
                '}';
    }

    /**
     * 树中最小值
     *
     * @return
     */
    public E MinNode() {
        return MinNode(root).e;
    }

    private Node MinNode(Node node) {
        if (node.left == null) {
            return node;
        }
        return MinNode(node.left);
    }

    /**
     * 树中最大值
     *
     * @return
     */
    public E MaxNode() {
        return MaxNode(root).e;
    }

    private Node MaxNode(Node node) {
        if (node.right == null) {
            return node;
        }
        return MaxNode(node.right);
    }

    /**
     * 移除最小节点
     *
     * @return
     */
    public E removeMin() {
        E ret = MinNode();
        root = removeMin(root);
        return ret;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            //这里是最小的一个节点
            //保存当前节点的右子树
            Node nodeRight = node.right;
            //当前要删除的节点如果存在右子树 那么需要把它移除 直接设置为null，
            node.right = null;
            size--;
            return nodeRight;
        }
        //如果存在右子树 那么需要将右子树变为上一个节点的左子树 node代表的是要删除的上一个节点 node.left是上一个节点的子节点
        //这个节点为新的搜索的二叉树的根  可以为null 也可以为树
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 移除最大的节点
     *
     * @return
     */
    public E removeMax() {
        E ret = MaxNode();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            //这里是最小的一个节点
            Node nodeLeft = node.left;
            node.left = null;
            size--;
            return nodeLeft;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 移除任意的元素
     *
     * @param e
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {

        if (node == null) {
            //节点为空
            return null;
        }

        if (e.compareTo(node.e) < 0) {
            //要删除节点 < 当前节点
            // 继续查找node的下一个左子节点
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            //要删除节点 > 当前节点
            // 继续查找node的下一个左子节点
            node.right = remove(node.right, e);
            return node;
        } else {
            //要删除的e 进行删除的情况
            if (node.left == null) {
                //左边树为空 直接删除
                Node noderight = node.right;
                node.right = null;
                size--;
                return noderight;
            }

            if (node.right == null) {
                //左边树为空 直接删除
                Node nodeleft = node.left;
                node.left = null;
                size--;
                return nodeleft;
            }
            //左右子树都不为空
            //找到该删除节点中右子节点的最小数 这个节点用于替代当前删除的节点位置
            Node temp = MinNode(node.right);
            //将右子节点的最小数删除
            temp.right = removeMin(node.right);
            //替代节点的左子树等于原节点的左子树
            temp.left = node.left;
            //移除树的关联
            node.left = node.right = null;
            return temp;
        }
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 2, 15, 4};
        for (int num : nums) {
            bst.add(num);
        }
//        Random random=new Random();
//        for (int i = 0; i < 100; i++) {
//            bst.add(random.nextInt(100000));
//        }
//        ArrayList<Integer> integers = new ArrayList<>();
//        while (!bst.isEmpty()){
//            integers.add(bst.removeMin());
//        }
//        System.out.println(integers);
        bst.removeMin();
        System.out.println("///");
//
//        bst.pervOrder();
//
//        System.out.println("///");
//
//        bst.inOrder();
//
//        System.out.println("///");
//
//        bst.postOrder();
    }
}
