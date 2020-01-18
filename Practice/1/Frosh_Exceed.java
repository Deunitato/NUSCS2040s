
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.HashSet;

/*

*/

class Frosh_Exceed{
 public static void main(String[] args){
	 ArrayList<Integer> list = new ArrayList<>();
	 
	   try(Kattio kattio = new Kattio(System.in)){
		   
         int total= kattio.getInt();
		 int[] map = new int[total+1];
		 for(int i=0; i< total;i++){
			 int get = kattio.getInt();
			 map[get] = i;
			 list.add(get);
		 }
		
		long swaps = 0;
		
		for(int i = total-1 ; i>=0 ; i--){
			int index = map[i+1];
			//int index = list.indexOf(i+1);
		   if(index!=(i+1)){
			swaps = swaps + ((i+1) - (index+1));
		 }
		}
		kattio.println(swaps);
	     kattio.flush(); //so that it will be delivered to the kattis system, if not flush it will have error in output
   }
	 


    }

 }

