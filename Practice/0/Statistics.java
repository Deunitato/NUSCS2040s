import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Statistics{
/*
*/


   public static void main(String[] args){
	   
	   try(Kattio kattio = new Kattio(System.in)){
		   int count = 1;
		   
		   
			while(kattio.hasMoreTokens()){
				ArrayList<Integer> myList = new ArrayList<>();
				int input = kattio.getInt();
				for(int i=0;i<input; i++){
					myList.add(kattio.getInt());
				}
				Collections.sort(myList);
				int min = myList.get(0);
				int max = myList.get(myList.size()-1);
				int range = max-min;
				
				kattio.println("Case "+ count+": " + min + " "+max + " "+range);
				count++;
				kattio.flush(); //so that it will be delivered to the kattis system, if not flush it will have error in output
			}
			
		
			
			
			 
		}
	   
	    
	    
    }
   	  

   


}