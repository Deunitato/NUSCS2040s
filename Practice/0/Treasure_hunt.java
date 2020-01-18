import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

class Treasure_hunt{
/*
Explaination:
I  have a seperate iterator that will record the rows and cols
Each move will put the ID of the row and col iterator into mem hashset
Hashset is used to record the prev moves so that when reach again,it knows it has been here before
catches for exception if run out of bounds

*/


   public static void main(String[] args){
	   
	   try(Kattio kattio = new Kattio(System.in)){
		   String steps = "null";
		   
				ArrayList<String> myList = new ArrayList<>();
				HashSet<String> mem = new HashSet<String>();
				int row = kattio.getInt();
				int col = kattio.getInt();
				
				for(int i = 0 ; i < row ; i ++){
					myList.add(kattio.getWord());
				}
				char iterator =(myList.get(0)).charAt(0);
				String pointer = "0 0";
				int rowP= 0;
				int colP = 0;
				while(true){
					/*
					N
					indicates a move to the previous row,
					S
					indicates a move to the next row,
					W
					indicates a move to the previous column,
					E
					indicates a move to the next column, and
					T
					indicates the location of the treasure.
					*/
					try{
					switch(iterator){
						case 'N':
						rowP--;
						break;
						case 'S':
						rowP++;
						break;
						case 'W':
						colP--;
						break;
						case 'E':
						colP++;
						break;
						case 'T':
						kattio.println(mem.size());
						//System.out.println(mem);
						return;
					}
					   iterator = (myList.get(rowP)).charAt(colP);
					   if(mem.contains(rowP+" "+colP)){ //returns to same spot
					    kattio.println("Lost");
						break;
					    }
						mem.add(rowP+" "+colP);
					
					}
					catch (Exception e){
						kattio.println("Out");
						kattio.flush(); //so that it will be delivered to the kattis system, if not flush it will have error in output
						return;
					}
					
					
					
				}
				//System.out.println(mem);
				//kattio.flush(); //so that it will be delivered to the kattis system, if not flush it will have error in output
				
			
			
		
			
			
			 
		}
    }
   	  

   


}