
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.HashSet;

/*

*/

class Svada{
 public static void main(String[] args){

       
	   try(Kattio kattio = new Kattio(System.in)){
         int Ttime= kattio.getInt();
		 int nMonkeys = kattio.getInt();
		 
		 int[] A = new int[nMonkeys];
		 int[] B = new int[nMonkeys];
		
		 for(int i=0; i< nMonkeys;i++){
			  A[i] = kattio.getInt();
			  B[i] = kattio.getInt();
		 }
		 int mMonkeys = kattio.getInt();
		 int[] C = new int[mMonkeys];
		 int[] D = new int[mMonkeys];
		
		for(int i=0; i< mMonkeys;i++){
			  C[i] = kattio.getInt();
			  D[i] = kattio.getInt();
		 }
		 
		 kattio.println(binary_Search(Ttime,0,Ttime,A,B,C,D));
		 
		 
		 
		 
	     kattio.flush(); //so that it will be delivered to the kattis system, if not flush it will have error in output
   }
 }
   
   static int binary_Search(int Ttime,int low, int high, int[] A, int[] B, int[] C, int[] D){
	   int mid = (high + low)/2;
	   int coconut_found = calmMonkeys(mid, A, B);
	   int coconut_broke = angryMonkeys(Ttime-mid, C, D);
	   if(coconut_found > coconut_broke && high>=low){ //calm monkey too much time
		   return binary_Search(Ttime, low, mid-1, A, B, C, D);
	   }
	   else if(coconut_found < coconut_broke && high>=low){
		   return binary_Search(Ttime, mid+1, high, A, B, C, D);
	   }
	   else{
		   return mid;
	   }
   }
	 


    
	
	static int calmMonkeys(int time,int[] A, int[] B){//monkey that pic and number of coconuts break
     // int count = 0;
	     int coco = 0;
	   for(int i=0;i<A.length ; i++){
		   int temp = time - A[i];
		   if(temp<0){
		   continue;
		   }
		   coco +=  temp/B[i] +1;
	   }
	   return coco;
	}
	
	static int angryMonkeys(int time,int[] C, int[] D){//monkey that pic and number of coconuts break
     // int count = 0;
	  int coco = 0;
	   for(int i=0;i<C.length ; i++){
		   int temp = time - C[i];
		   
		   if(temp<0){
		   continue;
		   }
		   coco +=  temp/D[i] +1;
	   }
	   return coco;
	}

 }

