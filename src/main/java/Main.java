import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] xx = new int[] {2, 0, 1};
        solution.sortColors(xx);
        System.out.println(Arrays.toString(xx));
    }
}
