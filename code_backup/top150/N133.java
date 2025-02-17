package com.ganten.tethys.top150;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class N133 {
    private HashMap<Integer, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;
        int val = node.val;
        Node n = map.get(val);
        if (n != null) return n;
        n = new Node(val);
        map.put(val, n);
        List<Node> nodes = new ArrayList<>();
        for (Node neighbor : node.neighbors) nodes.add(this.cloneGraph(neighbor));
        n.neighbors = nodes;
        return n;
    }
}

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}