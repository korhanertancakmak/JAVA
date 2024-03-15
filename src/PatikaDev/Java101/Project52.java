/* Pratice52 - Palindromic Words

We are writing a program in Java that detects whether the word entered by the user is "Palindromic" or not.
If reading a word backwards gives the same word, that word is a "Palindromic Word".

Example: abba, civic, level, madam, mom, refer, etc.

*/

public class Project52 {
    
    static boolean isPalindrome(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    static boolean isPalindrome2(String str) {
        String reverse = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            reverse += str.charAt(i);
        }

        if (str.equals(reverse))
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("abba"));
        System.out.println(isPalindrome("civic"));
        System.out.println(isPalindrome("madam"));
        System.out.println(isPalindrome("refer"));
        System.out.println(isPalindrome("reference"));
    }
}