package com.ganten.tethys.top150;

import java.util.ArrayList;
import java.util.List;

public class N22 {
    public List<String> generateParenthesis(int n) {
        char[] array = new char[n * 2];
        List<String> list = new ArrayList<>();
        this.helper(list, array, 0, n, n);
        return list;
    }

    public void helper(List<String> list, char[] array, int index, int left, int right) {
        if (index == array.length && left == 0 && right == 0) {
            list.add(new String(array));
        }
        if (left > 0) {
            array[index] = '(';
            this.helper(list, array, index + 1, left - 1, right);
        }
        if (right > left) {
            array[index] = ')';
            this.helper(list, array, index + 1, left, right - 1);
        }
    }

    public static void main(String[] args) {
        N22 n22 = new N22();
        n22.generateParenthesis(3);
    }
}
