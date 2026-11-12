package org.example;

public class StringUtils {
    public boolean isPalindrome(String input) {
        if(input == null) {
            return false;
        }

        input = input.toLowerCase();

        for(int i = 0, j = input.length() - 1; i < j; i++, j--) {
            if(input.charAt(i) != input.charAt(j)) {
                return false;
            }
        }

        return true;
    }

    public int countVowels(String input) {
        if(input == null) {
            return 0;
        }

        String vowels = "eyuioa";
        int count = 0;

        for (char c : input.toLowerCase().toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }
}
