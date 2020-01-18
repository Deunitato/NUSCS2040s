
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.HashSet;

/*
1. Build all list
2. Find missing using hashset
3. If missing is smaller, put missing
4. else put left


*/

class Gnomes{
 public static void main(String[] args){
	 Queue<Integer> left_List = new ArrayDeque<>();
	 HashSet <Integer> missing_map = new HashSet<>();
	 ArrayList<Integer> answer = new ArrayList<>();
	 ArrayList<Integer> miss_List = new ArrayList<>();
	   try(Kattio kattio = new Kattio(System.in)){
         int total= kattio.getInt();
		 int left= kattio.getInt();
		 for(int i=0; i< left;i++){
			 int get = kattio.getInt();
			 missing_map.add(get);
			 left_List.add(get);
		 }
		 // Retrieved missing list
		 for(int i=1;i<=total;i++){
			 if(!missing_map.contains(i)){
				 miss_List.add(i);
			 }
		 }
		 
		 //Build list
		 int counter = 0;
		 for(int j =0 ; j < total ; j++){
			 if(left_List.size()==0){ 
				 answer.add(miss_List.get(counter));
				 counter++;
				 continue;
			 }
			 
			 if(counter>= miss_List.size()){
				 answer.add(left_List.remove());
				 continue;
			 }
			 
			 if(left_List.peek() < miss_List.get(counter)){
				 answer.add(left_List.remove());
			 }
			 else{
				 answer.add(miss_List.get(counter));
				 counter++;
			 }
		 }
		 
		 for(int a : answer){
			 kattio.println(a);
		 }
	     kattio.flush(); //so that it will be delivered to the kattis system, if not flush it will have error in output
   }
	 


    }

 }

