

import java.util.TreeMap;
import java.util.Map;

/*
Log:
Original Algo:
1. Store values in array pairs
2. Check each pair first number if its a number or string,store as string, can check using ascii / string.length
3. Change the number to correct
4. Sort
5. Print out

Algo changes
*1 
- Use dictionary instead of arrays
*2 
- Use tree map instead
*3
- Use ascii  to check number
- Store key as integer instead of string

Problems:
1. Convert to string to int
2. Length one does work, check using ascii instead



*/

class Stacking_cups{
 public static void main(String[] args){
	    Map<Integer,String> list = new TreeMap<Integer, String>(); 
		String[] temp = new String[2];
       
	   try(Kattio kattio = new Kattio(System.in)){
			 int total = kattio.getInt();
			 for(int i=0; i< total ;i++){
				 String firstString = kattio.getWord();
				 String secString = kattio.getWord();
				 temp = errorFix(firstString,secString);
				 list.put(Integer.parseInt(temp[0]),temp[1]);
			 }
			 
			 

			 for(Map.Entry m:list.entrySet()){    
                 kattio.println(m.getValue());    
               }   
			 //kattio.println(list);
	     kattio.flush(); //so that it will be delivered to the kattis system, if not flush it will have error in output
		 
        }

    }
	
	
	public static String[] errorFix(String first, String second){
		String[] temp = new String[2];
		char f = first.charAt(0);
		if(f<58 && f > 47){ // first is a number and is errored
		   
		    temp[0] = (Integer.parseInt(first))/2 + "";
			temp[1] = second;
			
		}
		else{ //second is the number
			temp[0] = second;
			temp[1] = first;
		}
		
		return temp;
		
		
	}
	
	
	
	




 }

