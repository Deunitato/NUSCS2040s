class Autori{

   public static void main(String[] args){
	   
	   try(Kattio kattio = new Kattio(System.in))
   {
	     String str = kattio.getWord();
   
	   String[] substrings = str.split("[^A-Z]+");
	   str = "" ;
	   for(String s: substrings){
		   str = str + s;
	   }
	   
	   kattio.println(str);
	     kattio.flush(); //so that it will be delivered to the kattis system, if not flush it will have error in output
   }
   	  

   }


}