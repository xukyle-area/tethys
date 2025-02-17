package com.ganten.tethys.top150;


/**
 public class Node {
 public int val;
 public Node next;
 public Node random;

 public Node(int val) {
 this.val = val;
 this.next = null;
 this.random = null;
 }
 }
 */
public class N138 {

//    public Node copyRandomList(Node head) {
//        if (head == null) return null;
//        Node cur = head;
//        int i = 0;
//        Map<Integer, Node> indexToNode = new HashMap<>();
//        Map<Node, Integer> nodeToIndex = new HashMap<>();
//        while (cur != null) {
//            indexToNode.put(i, cur);
//            nodeToIndex.put(cur, i);
//            cur = cur.next;
//            i++;
//        }
//        int totalSize = indexToNode.size();
//        Node[] nodes = new Node[totalSize];
//        // 构建顺序
//        for (int j = 0; j < totalSize; j++) {
//            nodes[j] = new Node(indexToNode.get(j).val);
//        }
//        // 构建随机
//        for (int j = 0; j < totalSize; j++) {
//            Node random = indexToNode.get(j).random;
//            if (random != null) {
//                int index = nodeToIndex.get(random);
//                nodes[j].random = nodes[index];
//            }
//        }
//        // 关联起来
//        for (int j = 0; j < totalSize - 1; j++) {
//            nodes[j].next = nodes[j + 1];
//        }
//        return nodes[0];
//    }
}


