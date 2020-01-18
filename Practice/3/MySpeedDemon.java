/*
* CS2040S Problem Set 3 - Speed Demon
* Joshua Wee - Discuss improvement to calculation of permutation
*/


import java.util.HashMap;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;

 /*


 Algorithm
 #1
 Setup
 1. Have a hashmap in a hashset
 2. First hashmap sort by length of string
 3. 2nd hashmap sort by acci code sum
 Insertion:
 1. Count length of string
 2. Count sum of asci
 3. Store accordingly in hashset
 4. Store in PQ
 Counting:
 1. pop pq
 2. Check pq value's length and acci sum
 3. Access hashset set
 4. Decrease number by 1
 5. add to sum and continue 1. till pq is empty
 
 
 
 
 #2 Reason: Multiple collision could be encountered with summing ascii
 Setup:
 3. Sort each string according to ascii
 4. Store the sorted string as keys of the second hashmap
 Insertion:
 2. Sort
 3. Store in hashmap
 4. PQ add
 Counting:
 1. pop pq
 2. Check length and String
 3. Access hashset set
 4. Decrease number by 1
 5. add to sum and continue 1. till pq is empty
 
 
 Fixes:
 
 
 #3
 
 */

public class MySpeedDemon {
	static HashMap<Long, Integer> myMap = new HashMap<>();
	static int count = 0;
	static int[] primeArray = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719};
	public static int speedMatch(String dbfilename) {

		try {
			FileReader dataFile = new FileReader(dbfilename);
			BufferedReader bufferedDataFile = new BufferedReader(dataFile);
			String line = bufferedDataFile.readLine();
			int counter = Integer.parseInt(line);
		   //populate
			for(int i=0; i<counter; i++){
				String s = bufferedDataFile.readLine();
				add(hashString(s));
			}
			for (long key : myMap.keySet()) {
				count(key);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

	   // Your code
		return count;
	}


	public static long hashString(String s){

		long hashKey = 1;
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i); 
			hashKey = hashKey * primeArray[(int)c];
		}
        // return new sorted string 
		return hashKey ;
	}
	
	public static int combination(int n, int r){
		return (n* (n -1))/2;
	}
	public static void count(Long sortedString){
		int i = myMap.get(sortedString);
		count = count + combination(i, 2);
	}

   public static void add(Long s){
	   if(myMap.containsKey(s)){ //if have
	   //just check length
		   int temp = myMap.get(s)+1;
		   myMap.replace(s, temp); //update original value
	   } else { //create new
		   myMap.put(s, 1);
	   }
   }

   public static void main(String args[]) {
	   if (args != null && args.length > 0) {
		   int result = MySpeedDemon.speedMatch(args[0]);
		   System.out.println(result);
	   } else {
		   throw new IllegalArgumentException();
	   }
   }
}
