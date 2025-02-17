package com.ganten.tethys.top150;

import java.util.ArrayList;
import java.util.List;

public class N77 {

    public static void main(String[] args) {
        N77 n77 = new N77();
        List<List<Integer>> combine = n77.combine(4, 2);
        System.out.println(combine);
    }

    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> tempList = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        dfs(n, k, 0);
        return res;
    }

    // 范围是 [1,n]
    // 取 k 个数字
    // 目前到了第 index 个 : from 0
    private void dfs(int n, int k, int index) {
        if (tempList.size() == k) {
            res.add(new ArrayList<>(tempList));
            return;
        }
        if (index == n) {
            return;
        }

        // 不取当前这个
        dfs(n, k, index + 1);

        // 取当前这个
        tempList.add(index + 1);
        dfs(n, k, index + 1);
        tempList.remove(tempList.size() - 1);
    }
}
