class Solution {
    public int longestValidParentheses(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        int maxLength = 0;
        int[] dp = new int[n + 1];
        for (int i = 1; i < n; i++) {
            int prevLength = dp[i - 1];
            if (c[i] == '(') {
                continue;
            }
            if (c[i - 1] == '(') {
                // 1, xxxx() : dp[i-2] + 2, c[i-1]=(, c[i] = )
                dp[i] = (i - 2 >= 0 ? dp[i - 2] : 0) + 2;
            } else if (prevLength > 0 && i - prevLength - 1 >= 0 && c[i - prevLength - 1] == '(') {
                // 2, yyyy(xxxx) : dp[i-1] + 2 + dp[i - dp[i-1] - 2], c[i-dp[i-1]-1]=(, c[i] = )
                // length of xxxx
                dp[i] = prevLength + 2 + ((i - prevLength - 2) >= 0 ? dp[i - prevLength - 2] : 0);
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }
}
