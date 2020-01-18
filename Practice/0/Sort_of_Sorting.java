import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Sort_of_Sorting{
/*
*/


   public static void main(String[] args){
	   try(Kattio kattio = new Kattio(System.in)){
			while(true){
				ArrayList<String> listNames = new ArrayList<>();
				int numNames = kattio.getInt();
				if(numNames==0){
					break; //end program
				}
				for(;numNames!=0;numNames--){
					listNames.add(kattio.getWord());
				}
				
				Collections.sort(listNames, new Comparator<String>() {
					
					public int compare(String s1, String s2) {
						int firstChar1 = (int)s1.charAt(0);
					    int secChar1 = (int)s1.charAt(1);
						int firstChar2 = (int)s2.charAt(0);
					    int secChar2 = (int)s2.charAt(1);
						if(firstChar1!= firstChar2){ //if the first letter is not the same
						 return Integer.compare(firstChar1,firstChar2);
						}
						else if(secChar1!= secChar2){
							return Integer.compare(secChar1,secChar2);
						}
						else{
							return 0;
						}
						//return v1.getEmail().compareTo(v2.getEmail());
					}
				});
				for(String name: listNames){
					kattio.println(name);
				}				
				kattio.println("");
				
				 kattio.flush(); //so that it will be delivered to the kattis system, if not flush it will have error in output
			}
		}
	   
	    
	    
    }
   	  

   


}