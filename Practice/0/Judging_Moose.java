class Judging_Moose{

   public static void main(String[] args){
	   
	   try(Kattio kattio = new Kattio(System.in))
   {
	     int left = kattio.getInt();
		 int right = kattio.getInt();
		 int big;
		 if(left>right)
        {
            big = left*2;
			kattio.println("Odd "+ big);
        }
        else if(right > left)
        {
            big = right*2;
			kattio.println("Odd "+ big);
        }
		else if(right == 0 && left ==0){
			kattio.println("Not a moose");
		}
		else{
			kattio.println("Even "+ (left*2));
		}
		
		 
	   
	    
	     kattio.flush(); //so that it will be delivered to the kattis system, if not flush it will have error in output
   }
   	  

   }


}