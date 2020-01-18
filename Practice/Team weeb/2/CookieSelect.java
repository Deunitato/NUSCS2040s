import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;

/*
Algo
1. Build all list
2. Find missing using hashset
3. If missing is smaller, put missing
4. else put left

Algo #2 (Time limit exceed)
*1. Put values in pq for long as # is not in
2. If i see #, sort
Sorting seq:
1. Cal med from size
2. Pop the first few values before med into another pq
3. give med
4. sort and go back to begining *

Algo #3 (Wrong answer)
Insertion:
1. if low > high,
put in high only
else
put new number in high
pop high and put in low
Sort seq Improve:
1.if odd, pop low
2. else pop high


Algo #4  Reason: (Did not consider inserting smaller number that belong to the bottom)
Motive: Only change the median when needed to..

Insertion:
1. If inserted < median
	 put in small
else
	put in high
2. Get new mid
if its odd and low +1 < high, pop high, set as new mid , push to small
else if low> high +1, pop low, set as new mid, push to large

If its even and low < high,  pop high, set as new mid , push to small
else if high < low, pop low, set as new mid, push to large


Algo #5 (too many unneccesarry cases considered)
Only need to care when i add it to either low and high
care about the low exceed/ high exceed respectively
ensure the rule of med is always at low for odd numbers is always fufilled, high size can never be higher than low


*/

class CookieSelect{
	
	static PriorityQueue<Integer> high = new PriorityQueue<>(); 
	static PriorityQueue<Integer> low = new PriorityQueue<>((x, y) -> y - x); 
	static int mid=0;
	
 public static void main(String[] args){

	 String input = "l";
	   try(Kattio kattio = new Kattio(System.in)){
		 while(kattio.hasMoreTokens()){  
			 while(true){
				 input = kattio.getWord();
				 if(input.equals("#"))
					 break;
			   	// high.add(Integer.parseInt(input));
				   insert(Integer.parseInt(input));
			 }
			 //getCookie();
			 //System.out.println(high);
			 //System.out.println(low);
			    kattio.println(getMid());
			  // getMid();
			  // kattio.println(mid);
			 kattio.flush(); //so that it will be delivered to the kattis system, if not flush it will have error in output
		 
		 }
    }
   }
   
   public static void insert(int num){
	   /*if(low.size()> high.size()){
		   high.add(num);
	   }
	   else{
		   high.add(num);
		   low.add(high.poll());
	   }*/

       if(mid==0 || (low.size() + high.size()) == 0){
		   mid = num;
		   low.add(num);
	   }
	   else if(num<mid){ //add to low
		   low.add(num);
		   //check if low exceeds high
		   if(low.size() > (high.size() + 1)){ //low will always be greater or equal to high size
		   //low is too big
		     mid = low.poll();
			  high.add(mid);
		   }
		   
	   }
	   else{
		   high.add(num);
		   if(high.size() > low.size()){ //high will always be greater than low, ensure that mid is always at low position
		     mid = high.poll();
			 low.add(mid);
			
		   }
	   }
	   
	   /*
	   
	   int top = high.size();
	   int bot = low.size(); //omg
	   
	   //get new mid
        if(isEven(top+ bot)){
		   
		   if(bot < top){//high is to big
			   mid = high.poll();
			   low.add(mid);
			   
		    }
		   else if (bot > top){

			   mid = low.poll();
			   high.add(mid);
			   
		    }
		   else{
			   mid = high.peek();
		    }
	    }
	    else{
		   if(bot+1< top){
		       mid = high.poll();
			   low.add(mid);
		    }
		   else if(bot > top+1){
			   mid = low.poll();
			   high.add(mid);
		    }
           else{		   
			    mid = low.peek();
		    }
	    }
*/
   }
   
   public static int getMid(){
	   int size = low.size() + high.size();
	   if(isEven(size)){
		   return high.poll();
	   }
	   else{
		    return low.poll();
	   }
   }
   
   
   //Code beyond here is discarded
   public static void getCookie(){
	   int midIndex=0;
	   int size = high.size();
	   
	   if(isEven(size)){
		   midIndex = (size/2) +1;
	   }
	   else{
		   midIndex = (size+1) /2 ;
	   }
	   //sort
	   sort(midIndex);
   }
   
   public static void sort(int midindex){
	   if(midindex==1){
		   mid = high.poll();
		   return;
	   }
	   for(int i = 0 ; i < midindex-1; i++){
		   low.add(high.poll());
	   }
	   
	   mid = high.poll();
	   
	   for(int j = 0 ; j <=low.size() ; j++){
		   high.add(low.poll());
	   }
   }
   
      public static boolean isEven(int size){
	   return (size%2 == 0); 
   }
   

 }

