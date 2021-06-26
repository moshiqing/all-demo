package com.skin.demo.DataStructure.Set;

import com.skin.demo.DataStructure.BST.BST;

/**
 * @description:
 * @author: moshiqing
 * @time: 2020/4/24 17:11
 */
public class BSTSet<E extends Comparable<E>> implements set<E> {

    private BST<E> bst;

    BSTSet() {
        bst = new BST<>();
    }

    @Override
    public void add(E o) {
        bst.add(o);
    }

    @Override
    public void delete(E o) {

    }

    @Override
    public boolean contains(E o) {
        return bst.contains(o);
    }

    @Override
    public int getSize() {
        return bst.size();
    }
}
