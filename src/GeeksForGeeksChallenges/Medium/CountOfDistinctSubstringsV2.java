package GeeksForGeeksChallenges.Medium;
/*
        Given a string of length N of lowercase alphabet characters. The task is to complete the function countDistinctSubstring(),
    which returns the count of total number of distinct substrings of this string.

    Input: The first line of input contains an integer T, denoting the number of test cases. Then T test cases follow.
    Each test case contains a string str.

    Output: For each test case in a new line, output will be an integer denoting count of total number of distinct substrings of
    this string.

    User Task: Since this is a functional problem you don't have to worry about input, you just have to complete the function
    countDistinctSubstring().

    Constraints:
    1 ≤ T ≤ 10
    1 ≤ N ≤ 1000

    Example (To be used only for expected output):
    Input:
    2
    ab
    ababa

    Output:
    4
    10

    Exaplanation:
    Testcase 1: For the given string "ab" the total distinct substrings are: "", "a", "b", "ab".
    Testcase 1: For the given string "ab" the total distinct substrings are: "", "a", "b", "ab", "ba", "aba", "bab", "abab",
    "baba", "ababa".
*/

import java.util.*;

public class CountOfDistinctSubstringsV2 {

    public static void main (String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter no of test cases");
        int t = sc.nextInt();

        while(t-- > 0) {
            System.out.println("Enter the string");
            String st = sc.next();
            System.out.println(FindCountOfDistinctSubstringsV2.countDistinctSubstring(st));
        }
    }
}

class Trie {
    Trie[] Map = new Trie[26];                                      // Her harfi temsil etmek için 26 çocuk Trie düğümü
}

class FindCountOfDistinctSubstringsV2 {
    public static Trie newTrie() {
        return new Trie();
    }

    public static void findDuplicateStrings(Trie root, String str, int stringIndex, int[] numberOfDuplicateStrings) {
        if (stringIndex >= str.length())
            return;
        if (root.Map[str.charAt(stringIndex) - 'a'] == null) {
            root.Map[str.charAt(stringIndex) - 'a'] = newTrie();    // Yeni bir Trie düğümü oluştur
        } else {
            numberOfDuplicateStrings[0]++;                          // Eğer bu alt dize daha önce görüldüyse, yineleme sayısını artır
        }
        root = root.Map[str.charAt(stringIndex) - 'a'];             // Trie ağacını ilerlet
        findDuplicateStrings(root, str, stringIndex + 1, numberOfDuplicateStrings);     // Rekürsif olarak devam et
    }

    public static int countDistinctSubstring(String s) {
        int length = s.length();
        Set<String> differentStrings = new HashSet<>();             // Farklı alt dizeleri saklamak için küme kullan
        int[] numberOfDuplicateStrings = {0};
        Trie root = newTrie();
        for (int i = 0; i < length; ++i) {
            findDuplicateStrings(root, s, i, numberOfDuplicateStrings);     // Her karakter için farklı alt dizeleri bul
        }

        // Tüm farklı alt dizelerin sayısını hesapla ve sonucu döndür
        return (length * (length + 1)) / 2 - numberOfDuplicateStrings[0] + 1;
    }
}



//Problem successfully Solved
//Test Cases Passed:
//30 /30
//Total Points Scored:
//4 /4
//Your Total Score:
//86
//Total Time Taken:
//0.77
//Your Accuracy:
//11%
//Attempts No.:
//9