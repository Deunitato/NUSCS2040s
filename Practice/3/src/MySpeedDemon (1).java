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
	static Queue<String> myList = new LinkedList<>(); 
	static HashMap<String, Integer> myMap = new HashMap<>(); 
	static int count = 0;
  
    // Your code
    public static int speedMatch(String dbfilename) {
		
		try {
         // Code for accessing the file goes here
			 FileReader dataFile = new FileReader(dbfilename);
			 BufferedReader bufferedDataFile = new BufferedReader(dataFile);
			 String line = bufferedDataFile.readLine();
			 int counter = Integer.parseInt(line);
			

			//String lines[] = line.split("\n");
			//System.out.println(lines.length);

			//populate
			for(int i=0; i<counter; i++){
				String s = bufferedDataFile.readLine();
				add(sortString(s));
				myList.add(s);
			}
			bufferedDataFile.close();
			count();

        } catch (Exception e) {
           System.out.println(e);
        }
		
        // Your code
        return count;
    }
	
	public static String sortString(String s){
        char myArray[] = s.toCharArray(); 
        Arrays.sort(myArray); 

        // return new sorted string 
		return new String(myArray);
	}
	
	
	public static void count(){
		while(!myList.isEmpty()){
			String key = sortString(myList.poll());
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
	
	
	
	public static void add(String s){
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
