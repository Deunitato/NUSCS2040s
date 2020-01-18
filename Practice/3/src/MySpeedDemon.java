/**
 * CS2040S Problem Set 3 - Speed Demon
 * 
 * Include the names of any collaborators here, and how they helped you.
 * Example: Tan Ah Kow - Discussed what Hash functions to use for the Problem
 *
 * 
 */
 import java.util.HashMap;
 import java.util.LinkedList;
 import java.util.Arrays;
 import java.util.Queue;
 import java.io.BufferedReader;
 import java.io.FileReader;
 import java.util.Scanner;
 
 /*
 Algorithm
 #1
 Setup
 1. Have a hashmap in a hashset
 2. First hashmap sort by length of string
 3. 2nd hashmap sort by acci code sum
 Insertion:
 1. Count length of string
 2. Count sum of ascii
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
 
 
 Fixes: Hash set spoil, out of bound, overshot space limit
 
 
 #3 Change Hashing
 Insertion:
 1. Map every letter in ascii to prime number
 2. Hash it by multiplying the primes together and mod it with a large number
 3. use it as the second hash set key
 
 */

public class MySpeedDemon {
	static Queue<String> myList = new LinkedList<>(); 
	
	static HashMap<Integer, HashMap<Integer, Integer>> myMap = new HashMap<>(); 
	//static HashMap<Integer, Integer> myMap = new HashMap<>(); 
	static int count = 0;
	static int[] primeArray = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719};
    // Your code
    public static int speedMatch(String dbfilename) {
		try {
         // Code for accessing the file goes here
		 
		 FileReader dataFile = new FileReader(dbfilename);
		 Scanner scanner = new Scanner(dataFile);
		// BufferedReader bufferedDataFile = new BufferedReader(dataFile);
		 String line = scanner.nextLine();
		 int counter = Integer.parseInt(line);
		 char c ='v';
		 
		   // Your code
		  //populate
		   //for(int i = 0 ; i < counter ; i++ ){
			/*c = (char)bufferedDataFile.read();
			while(c != '\n' && c!= -1)  {  
			   s = s+c;
			   c = (char)bufferedDataFile.read();
			   //add(s.length(), hashString(s));
			} */
			
            while (scanner.hasNextLine()) {
				String s = scanner.nextLine();
				add(s.length(), hashString(s));
			    myList.add(s);
   // process the line
            }
		
			//bufferedDataFile.close();
		
		count();
		//System.out.println(count);
        } catch (Exception e) {
           System.out.println(e);
        }
		
		
        return count;
	}


	public static int hashString(String s){

		int hashKey =1;
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i); 
			hashKey = hashKey * primeArray[(int)c];
		}
        // return new sorted string 
		return hashKey;// % (int) Math.pow(128,s.length());
	}
	
	public static void count(){
		HashMap<Integer, Integer> aMap;
		
		while(!myList.isEmpty()){
			String s = myList.poll();
			int length = (s).length();
			int key = hashString(s);
			if(myMap.containsKey(length)){//check existence of length
			   aMap = myMap.get(length);
			   if(aMap.containsKey(key)){
				   int temp = (aMap.get(key))-1;
				   //remove when reaches 0
				   if(temp==0){
					   aMap.remove(key);
					   myMap.replace(length, aMap); //replace original value
					   continue;
				   }
				   aMap.replace(key, temp);
				   myMap.replace(length, aMap); //replace original value
				   count = count+ temp;
			   }
			   else{
				   continue;
			   }
			}
			else{
				continue; //move on
			}
		}
			
		
	}

	/*
	public static void count(){
		while(!myList.isEmpty()){
			int key = hashString(myList.poll());
			   if(myMap.containsKey(key)){
				   int temp = (myMap.get(key))-1;
				   //remove when reaches 0
				   if(temp==0){
					   myMap.remove(key);
					   continue;
				   }
				   myMap.replace(key, temp); //replace original value
				   count = count+ temp;
			    }
				else{
					continue; //move on
				}
		}
	}
	*/
	
/*	
	public static void add(int key){
		if(myMap.containsKey(key)){ //if have
		//just check length
		    int temp = myMap.get(key)+1;
			myMap.replace(key, temp); //update original value
		} else { //create new
			myMap.put(key, 1);
		}
	}
	*/
	

	public static void add(int length, int key){
		HashMap<Integer, Integer> aMap;
		if(myMap.containsKey(length)){ //if have
		//just check length
			aMap = myMap.get(length);
			
			if(aMap.containsKey(key)){ //it's string exist
			    int temp = aMap.get(key)+1;
				
				aMap.replace(key, temp); //change the value
				myMap.replace(length, aMap); //replace original value
			}
			else{
			    aMap.put(key, 1);
			    myMap.replace(length, aMap); //replace original value
				
			}
			
		}
		else{ //create new
			aMap = new HashMap<>();
			aMap.put(key, 1);
			myMap.put(length,aMap);
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
