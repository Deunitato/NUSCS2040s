import java.util.ArrayList;
import java.util.Collections;
class Unicorn_Prize{

/*Algo Explaination:
1. Sort big
2. For each big number. compare with the next by addition
3. If addition excceeds X, remove that number and go to the next number in the list, update list size
4. else if reach end of list without exceeding, break out of the entire calculation, return size of list
*/

public static void main(String[] args){
	
	ArrayList<Integer> list_Of_Prices = new ArrayList<>();
   try(Kattio kattio = new Kattio(System.in))
   {
	   int num_Items = kattio.getInt();
	   int min_Cost = kattio.getInt();
	   
	   while(kattio.hasMoreTokens()){
		   list_Of_Prices.add(kattio.getInt());
	   }
	   Collections.sort(list_Of_Prices, Collections.reverseOrder()); //sort decendiing
	   int list_size = list_Of_Prices.size();
	   //calculate here
	   
	   boolean reachend = false;
         

	   
	   for(int i= 0 ; i< list_size; i++){
		   int currentPrice = list_Of_Prices.get(i);
		 if(reachend){
			 break;
		 }
		   for(int k = i + 1; k< list_size ; k++){
			  //System.out.println(currentPrice+": "+ k + " " + i);
			   if((currentPrice+ list_Of_Prices.get(k))> min_Cost){// exceeds
				   list_Of_Prices.remove(i);
				   list_size = list_Of_Prices.size();
				   i--;
				  // System.out.println(list_size);
				   break; 		
			   }
			   if((k+1) == list_size){
				   reachend = true;
				   break;
			   }
			  
		    }
		   
		   
	   }
	   
	    kattio.println(list_Of_Prices.size());
	    kattio.flush(); //so that it will be delivered to the kattis system, if not flush it will have error in output
   }
   

}

}