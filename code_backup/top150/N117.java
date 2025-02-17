package com.ganten.tethys.top150;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class N117 {

//    public Node connect(Node root) {
//        if (root == null) return null;
//        level.add(root);
//        this.helper();
//        return root;
//    }
//
//    List<Node> level = new ArrayList<>();
//
//    private void helper() {
//        while (!level.isEmpty()) {
//            List<Node> tempList = new ArrayList<>();
//            Node pre = null;
//            for (Node node : level) {
//                if (pre == null) {
//                    pre = node;
//                } else {
//                    pre.next = node;
//                    pre = pre.next;
//                }
//                if (Objects.nonNull(node.left)) tempList.add(node.left);
//                if (Objects.nonNull(node.right)) tempList.add(node.right);
//            }
//            level.clear();
//            level.addAll(tempList);
//        }
//    }
}

//class Node {
//    public int val;
//    public Node left;
//    public Node right;
//    public Node next;
//
//    public Node() {
//    }
//
//    public Node(int _val) {
//        val = _val;
//    }
//
//    public Node(int _val, Node _left, Node _right, Node _next) {
//        val = _val;
//        left = _left;
//        right = _right;
//        next = _next;
//    }
//};