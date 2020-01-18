class Different_Prob{

public static void main(String[] args){
   try(Kattio kattio = new Kattio(System.in))
   {
	   while(kattio.hasMoreTokens()){
		   
		   long first = kattio.getLong();
		   long second = kattio.getLong();
		   kattio.println(Math.abs(first-second));
		   kattio.flush(); //so that it will be delivered to the kattis system, if not flush it will have error in output
	   }
   }
   

}

}