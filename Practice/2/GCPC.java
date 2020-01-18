import java.util.TreeMap;
import java.util.HashSet;
import java.util.Iterator;

/*
Algo #1
Data structures needed: 2 maps, one temp arr[2]

1.Store team 1 as a seprate value of solved and penalty
2.Store other teams in better or worst
if teams are better than team 1, comparing, store in better
else store in worst
3.Each time a team solved is added, if its nt team 1, check if it exist in the worst and better
if doesnt exist, create new map with team number as key and an array of [solved,penalty] as value
else, compare with one and put in correct position
4. If its team 1, update team 1, check better and compare each member

Algo #2
Store the variables of pen and solved instead of in arrays[2] we store in seperate maps with the team name as key
Store only the better teams
Compare each time it adds
insertandCompare:
we have to clone to prevent concurrent modification error from looping the hashset and insert/remove at the same time


*/

class GCPC{
	
  static TreeMap<Integer, Integer > Solved = new TreeMap<>();
  static TreeMap<Integer, Integer > Penalty = new TreeMap<>();
  static HashSet<Integer> better = new HashSet<Integer>(); 
  static int T1solved = 0;
  static int T1penalty = 0;

	
 public static void main(String[] args){
	 int teams;
	 int events;
	 
	   try(Kattio kattio = new Kattio(System.in)){
		    teams = kattio.getInt();
			events = kattio.getInt();
			
			  populate(teams);
		
			 for(int i = 0 ; i < events ; i++){
                add(kattio.getInt(), kattio.getInt());	
                kattio.println(getPosition());				
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
   
   
   //populate the list
   static void populate(int teams){

	   for(int i =  1 ; i < (teams);i++){
		  // Integer[] arr = new Integer[2];
		   int currentTeam = i +1;
		   Penalty.put(currentTeam,0);
		   Solved.put(currentTeam,0);
		   //arr[0] = 0;
		   //arr[1] = 0;
		   //low.put(currentTeam,arr);
		   
	   }
   }
   
   
   //add value
   static void add(int team , int pen){
	  // Integer[] arr;
	   int[] update = new int[2];
	   //get the arr
	   
	   if(team!= 1){
		  int newPen = Penalty.get(team) + pen;
		  int newSolve = Solved.get(team) + 1;
		  Penalty.replace(team,newPen);
		  Solved.replace(team,newSolve );
		  
		  update[1] = Penalty.get(team);
		  update[0] = Solved.get(team);
		  
		  /* if(high.containsKey(team)){
			   
			   arr = high.get(team);
			   update[0] = arr[0] +1;
		       update[1] = arr[1]+ pen;
			   high.remove(team);
		   }
		   else{
			   arr = low.get(team);
			   update[0] = arr[0] +1;
		       update[1] = arr[1]+ pen;
			   low.remove(team);
		   }*/
		   
		   
		   
		   compareAndinsert(update, team);
	   }
	   else{
		   T1solved++;
		   T1penalty += pen;
		   sortWhole();
	   }
   }
   
   
   //inserts the new updates
   static void compareAndinsert(int[] update, int team){
	   if(update[0] > T1solved){ //win team 1
			   //high.put(team,update);
			   better.add(team);
		}
	   else if(update[0] == T1solved){ //look at penalty
		   if(update[1] >=T1penalty){ //lose to team 1
			   //remove from up
			   better.remove(team);
		   }
		   else{
			   better.add(team);
		   } //we didnt handle equal penalty
	   }
	   else{ //lose to t1
		   better.remove(team);
	   }
   }
   
   
   //re-sorts the entire high and low
   static void sortWhole(){
	   
	   int[] temp = new int[2];
	   HashSet<Integer> clone = (HashSet)better.clone();
	   
	   //Iterator<Integer> value = beter.iterator(); 
 
    
        for(int team : clone) { 
		    //int team = value.next();
			temp[1] = Penalty.get(team);
		    temp[0] = Solved.get(team);
            compareAndinsert(temp, team);
        } 
	   
	   //scan through high
	   /*Integer[] temp;
	   int key = high.ceilingKey(1);
	   while(true){
		   
		   temp = high.get(key);
		   high.remove(key);
		   compareAndinsert(temp,key);
		   
		   //get new key
		   if(high.ceilingKey(key+1) == null){
			   break;
		   }
		   key = high.ceilingKey(key+1);
		   
	   }*/
	   
	   
   }
   
   static int getPosition(){
	   return better.size()+1;
   }
   
  

 }

