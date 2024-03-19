package GeeksForGeeksChallenges.Medium;

/*
        Given two numbers as strings s1 and s2. Calculate their Product.

    Note: The numbers can be negative, and you are not allowed to use any built-in function or convert the strings to integers.
    There can be zeros at the beginning of the numbers.

    Example 1:
    Input:
    s1 = "0033"
    s2 = "2"
    Output:
    66

    Example 2:
    Input:
    s1 = "11"
    s2 = "23"
    Output:
    253

    Your Task: You don't need to read input or print anything. Your task is to complete the function multiplyStrings()
    which takes two strings s1 and s2 as input and returns their product as a string.

    Expected Time Complexity: O(n1* n2)
    Expected Auxiliary Space: O(n1 + n2); where n1 and n2 are sizes of strings s1 and s2 respectively.

    Constraints:
    1 ≤ length of s1 and s2 ≤ 103
*/
public class MultiplyTwoStrings {

    public static void main(String[] args) {

        String s1 = "342857466747623190253535915582654079729535249666495366204751947309612861759399743340838318159436477709808";
        String s2 = "4471335218251938463464417392810911901096513109096223883606949011331588570391782354624160500218170491853613331964401401436877119247304542334868677958787620083249";

        System.out.println("Input: ");
        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
        System.out.println("Output: ");
        FindMultiplyStrings g = new FindMultiplyStrings();
        System.out.println(g.multiplyStrings(s1, s2));
    }
}

class FindMultiplyStrings
{
    private int[] smaller, larger;

    private boolean smallerSgn, largerSgn, resultSgn;

    private void setSgns(String s1, String s2) {

        if (s1.length() <= s2.length()) {
            this.smallerSgn = ((int) s1.charAt(0)) >= 48;
            this.largerSgn = ((int) s2.charAt(0)) >= 48;
        } else {
            this.smallerSgn = ((int) s2.charAt(0)) >= 48;
            this.largerSgn = ((int) s1.charAt(0)) >= 48;
        }
        this.resultSgn = (smallerSgn && largerSgn) || (!smallerSgn && !largerSgn);
    }

    private boolean Ispositive(String s) {
        return ((int) s.charAt(0)) >= 48;
    }

    private int howManyZerosAtBegining(String s) {

        int i = 0;
        if(!Ispositive(s)) {
            i = 1;
        }

        while ((int) s.charAt(i) == 48) {
            i++;
        }
        return i;
    }

    private int[] convertStringToDigits(String s) {

        int i = 0;

        int zeros = howManyZerosAtBegining(s);
        if(zeros != 0) {
            i += zeros;
        }

        int decLen = s.length() - i;
        int[] digits = new int[decLen];

        for (int j = 0; j < decLen; j++) {
            digits[j] = Integer.parseInt(s.substring(i , i + 1));
            i++;
        }

        return digits;
    }

    private void setArrays(String s1, String s2) {
        if (s1.length() <= s2.length()) {
            this.smaller = convertStringToDigits(s1);
            this.larger = convertStringToDigits(s2);
        } else {
            this.smaller = convertStringToDigits(s2);
            this.larger = convertStringToDigits(s1);
        }
    }

    private String multiply(String s1, String s2) {

        setSgns(s1, s2);
        setArrays(s1, s2);

        int smallestLen = smaller.length, longestLen = larger.length;
        int[][] arrToBeAdded = new int[smallestLen][longestLen];
        int index = 0;
        for (int i = smallestLen - 1; i >= 0; i--) {
            for (int j = longestLen - 1; j >= 0; j--) {
                arrToBeAdded[index][j] = this.larger[j] * this.smaller[i];
            }
            index++;
        }

        int len = longestLen + smallestLen;
        int[][] arr = new int[smallestLen][len];
        for (int i = 0; i < arrToBeAdded.length; i++) {
            int base = 0, carry = 0, prod = 0;
            int length = len - i;
            length--;
            for (int j = longestLen - 1; j >= 0; j--) {
                if (j != 0) {
                    prod = arrToBeAdded[i][j] + carry;
                    base = (prod) % 10;
                    carry = prod / 10;
                    arr[i][length] = base;
                } else if (carry !=0){
                    arr[i][length] = carry;
                } else {
                    arr[i][length] = arrToBeAdded[i][j];
                }
                length--;
            }
        }

        int[] result = new int[len];
        int length = len, subCarry = 0;
        for (int i = len - 1; i >= 0; i--) {
            length--;
            int subBase = 0;
            for (int j = 0; j < smallestLen; j++) {
                subBase += arr[j][i] + subCarry;
                subCarry = 0;
            }
            if (subBase < 9) {
                result[length] += subBase;
            } else {
                subCarry = subBase / 10;
                subBase %= 10;
                result[length] += subBase;
            }
        }

        int[] result2 = new int[len - 1];
        if (result[0] == 0) {
            System.arraycopy(result, 1, result2, 0, len - 1);
        }

        String finalResult = "";
        if (!resultSgn) {
            finalResult += "-";
        }
        for (int j : result[0] == 0 ? result2 : result) {
            finalResult += String.valueOf(j);
        }

        return finalResult;
    }

    public String multiplyStrings(String s1, String s2)
    {
        int decLen1 = s1.length();
        int decLen2 = s2.length();

        if (decLen1 > decLen2) {
            int[] subRes = new int[decLen1];
        } else {
            int[] subRes = new int[decLen2];
        }

        if (decLen1 + decLen2 < 10) {
            int num1 = Integer.parseInt(s1);
            int num2 = Integer.parseInt(s2);;
            return String.valueOf(num1 * num2);
        }

        return multiply(s1, s2);
    }
}