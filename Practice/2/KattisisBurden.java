import java.util.TreeMap;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;

/*
Algo #1
1. We will store the quest which are arrays that contain energy and gold into pq
2. Pq will compare and ensure that each quest is sorted by largest energy first, if equal, sort by largest gold first

Query:
1. While not yet reach the end of pq,
2. poll the first value and compare if energyleft - questenergy  < 0
If yes -> put it into another list
no -> deduct the energy and remove the quest from the pq, increase gold counter
3. Swap the list with list2

Algo #2 (Data stucture grouping)
Instead of using arrays of arrays, we use tree map of pq
We will sort the data according to energy first then in each energy, a pq of gold

*/

class KattisisBurden{
	
	/*
	
	static PriorityQueue<Integer[]> list = new PriorityQueue<>(new Comparator<Integer[]>() {
	public int compare(Integer[] lhs, Integer[] rhs) {
		//System.out.println("ca" + lhs + "\n" + rhs);
		if(rhs[0] == lhs[0]){
			//return rhs[1] - lhs[1];
			return Integer.compare(rhs[1],lhs[1]);
		}
		else if (rhs[0] > lhs[0]){
			return 1;
		}
		else{
			return -1;
		}

    }
});
    static PriorityQueue<Integer[]> tempList = new PriorityQueue<>(new Comparator<Integer[]>() {
	public int compare(Integer[] lhs, Integer[] rhs) {
		//System.out.println("ca" + lhs + "\n" + rhs);
		if(rhs[0] == lhs[0]){
			//return rhs[1] - lhs[1];
			return Integer.compare(rhs[1],lhs[1]);
		}
		else if (rhs[0] > lhs[0]){
			return 1;
		}
		else{
			return -1;
		}

    }
});
    

    static PriorityQueue<Integer[]> list2 = new PriorityQueue<>(new Comparator<Integer[]>() {
	public int compare(Integer[] lhs, Integer[] rhs) {
		//System.out.println("ca" + lhs + "\n" + rhs);
		if(rhs[0] == lhs[0]){
			//return rhs[1] - lhs[1];
			return Integer.compare(rhs[1],lhs[1]);
		}
		else if (rhs[0] > lhs[0]){
			return 1;
		}
		else{
			return -1;
		}
    }
});
*/

    
	static TreeMap<Integer, PriorityQueue<Integer> > energyList = new TreeMap<>();

	
 public static void main(String[] args){
	 int tries;
	 String input;
	 
	   try(Kattio kattio = new Kattio(System.in)){
		    tries = kattio.getInt();
		
			 for(int i = 0 ; i < tries ; i++){
				 input = kattio.getWord();
				 if(input.equals("query")){ // do query
					 kattio.println(query(kattio.getInt()));
				 }
				 else{ //do add
					 add(kattio.getInt() , kattio.getInt());
				 }
				 
				 
				 
			 }
			 //getCookie();
			 /*int o = list.size();
			 for(int i = 0 ; i < o ; i++){
				 Integer[] k = list.poll();
				 System.out.println(k[0] + " " + k[1]);
			 }
			 */
			 kattio.flush(); //so that it will be delivered to the kattis system, if not flush it will have error in output
		 
		 
        }
   }
   
   
   
   //query
   public static long query(int X){
	   long money = 0;
	   int energyleft = X;
	   //Integer temp[] = new Integer[1];
	   int key=0;
      while(energyleft > 0 || energyList.size() > 0){
		  if(energyList.floorKey(energyleft)==null){
			  break;
		  }
		  key = energyList.floorKey(energyleft); // get the energy that is lesser than what we have or equal
		  
		  money += energyList.get(key).poll();
		  energyleft -= key; //remove energy
		  if((energyList.get(key)).isEmpty()){ //if goldlist is empty
			  energyList.remove(key);
		  }
		  
	  }
     

	 
     /*while(energyleft > 0 && list.size()> 0){
		   
		   temp = list.poll();
		   if((energyleft - temp[0])< 0 ){
			   list2.add(temp); //save for later
		   }
		   else{
			   energyleft -= temp[0];
			   money += temp[1];
		   }
		   
	   }
	   
	   while(list.size()!= 0 ){
		   list2.add(list.poll());
	   }
	   */
	   
	  /* int size = list.size();
	   for(int i = 0 ; i < size ; i++){
		   
		   temp = list.poll();
		   if(temp[0]> energyleft){
			   list2.add(temp);
			   continue;
		   }
		   else{
			   if((energyleft- temp[0])< 0){
				   list2.add(temp); // the quest can't do so save for later
			   }
			   else{
				   energyleft = energyleft - temp[0];
				   money = money + temp[1];
			   }
		   }
	   }
	   
	   //swap
	   tempList = list;
	   list = list2;
	   list2 = tempList;*/
	   return money;
	   
   }
   
   //add 
   public static void add(int E , int G){
	   /*Integer[] arr = new Integer[2];
	   arr[0] = E;
	   arr[1] = G;
	   list.add(arr);*/
	   PriorityQueue<Integer> goldList = new PriorityQueue<>((x, y) -> y - x); 
	   if(energyList.containsKey(E)){
		   goldList = energyList.get(E); //get the prev
		   energyList.remove(E); //remove current mapping
	   }	   
	   
	   goldList.add(G); //does it affect?
	   energyList.put(E,goldList);
	   
	   
   }
  

 }

