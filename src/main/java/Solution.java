import java.util.LinkedList;

class Solution {
    public int minAddToMakeValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            }
            if (c == ')') {
                if (stack.size() != 0 && stack.getFirst() == '(') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        return stack.size();
    }
}
