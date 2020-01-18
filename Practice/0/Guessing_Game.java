class Guessing_Game{
/*
Explaination:
Orignally it failed when i allow all upperbound to be rewritten when given a number
It work once i added the condition such that upper_bound/lower_bound can only be rewritten if
the guess is smaller/bigger respectively than current upper/lower bounds

logic:
Will decrease the bounds each time the transcript is not "right on"
Check if stan is lying after "right on" is triggered]

Probs: Kattio dont read spaces

*/


   public static void main(String[] args){
	   
	   boolean zeroFlag = false;
	   try(Kattio kattio = new Kattio(System.in)){
	    int guess = 0;
		while(true){
			 int upper_bound = 10;
			 int lower_bound = 1;
			 String transcript = "";
			 while(!transcript.contains("right")){
			   guess = kattio.getInt();
			   if(guess==0){
				   zeroFlag = true;
				   break;
			   }
			   transcript = kattio.getWord() + kattio.getWord();
			   
			   
			   
			   if(transcript.contains("high")&&(guess<=upper_bound)){
				   upper_bound =  guess -1 ;
			   }
			   else if(transcript.contains("low") && (guess >= lower_bound)){
				   lower_bound = guess +1;
			   }
			   
			   
			}
			if(!zeroFlag){
			 if(upper_bound<lower_bound || !(upper_bound>=guess && lower_bound <= guess)){
				 kattio.println("Stan is dishonest");
			 }
			 else{
				 kattio.println("Stan may be honest");
			 }
			}
			else{
				break;
			}
			 kattio.flush(); //so that it will be delivered to the kattis system, if not flush it will have error in output
		}
		 
	   
	    
	    
   }
   	  

   }


}