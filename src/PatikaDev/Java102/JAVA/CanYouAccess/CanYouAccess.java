package JAVA.CanYouAccess;

import java.io.*;

/** 
You are given an inner class Inner as Private.  
The main method of class Codes.CanYouAccess.CanYouAccess takes an integer as input.
The powerof2 in class Inner.
Private checks whether a number is a power of. 
You have to call the method powerof2 of the class Inner.
Private from the main method of the class Codes.CanYouAccess.CanYouAccess.

Constraints
1 <= num <= 2^30

Sample Input
8

Sample Output
8 is power of 2
An instance of class: Codes.CanYouAccess.CanYouAccess.Inner.Private has been created
**/


public class CanYouAccess {

	public static void main(String[] args) throws Exception {
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int num = Integer.parseInt(br.readLine().trim());
			Inner.Private o;// Must be used to hold the reference of the instance of the class Codes.CanYouAccess.CanYouAccess.Inner.Private

			//Write your code here
			Inner inner = new CanYouAccess.Inner();
            o = new Inner.Private();
            System.out.println(num + " is " + o.powerof2(num));

		System.out.println("An instance of class: " + o.getClass().getCanonicalName() + " has been created");
		
		} catch (IOException e) {
			System.out.println("Unsuccessful Termination!!");
		}
	}
	//end of main

	static class Inner{
		private static class Private{
			private String powerof2(int num){
				return ((num&num-1)==0)?"power of 2":"not a power of 2";
			}
		}
	}
	//end of Inner
}
