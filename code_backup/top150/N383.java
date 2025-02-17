package com.ganten.tethys.top150;

public class N383 {
    public static void main(String[] args) {
        N383 n383 = new N383();
        n383.canConstruct("xxaa", "xsaxqa");
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() == 0 && magazine.length() >= 0) {
            return true;
        } else if (!ransomNote.isEmpty() && magazine.length() == 0) {
            return false;
        }
        int[] ransomTimes = this.countTimes(ransomNote);
        int[] magenizeTimes = this.countTimes(magazine);
        for (int i = 0; i < 26; i++) {
            if (ransomTimes[i] > magenizeTimes[i]) {
                return false;
            }
        }
        return true;
    }

    private int[] countTimes(String string) {
        int[] reArray = new int[26];
        for (int i = 0; i < string.length(); i++) reArray[string.charAt(i) - 'a']++;
        return reArray;
    }
}
