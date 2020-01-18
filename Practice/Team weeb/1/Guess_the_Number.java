
import java.util.Scanner;

/*

*/

class Guess_the_Number{
 public static void main(String[] args){

       Scanner sc = new Scanner(System.in);
	   try(Kattio kattio = new Kattio(System.in)){
		 int high = 1000;
		 int low = 1;
		 int guess = 500;
		 int count = 0;
		 //System.out.println(guess);
		 while(count < 10){
			 System.out.println(guess);
			 String input = sc.nextLine();
			 if(input.equals("lower")){
				 high = guess-1;
			 }
			 else if(input.equals("higher")){
				 low = guess+1;
			 }
			 else{
				 break;
			 }
			 guess = (high+low)/2;
			 count++;
		 }
		 
	     kattio.flush(); //so that it will be delivered to the kattis system, if not flush it will have error in output
   }
	 


    }
	
	static int merger(int[] arr, int[] left, int[] right) {
       int p = 0; 
	   int q = 0;
	   int count = 0;
    while (q < right.length || p < left.length ) {
		
        if (p == left.length) {//finish with left
            arr[p+q] = right[q];
            q++;
        } else if (q == right.length) { //finish with right
            arr[p+q] = left[p];
            p++;	
        }else if (left[p] <= right[q]) {
            arr[p+q] = left[p];
            p++;                
        } else {
            arr[p+q] = right[q];
            count += left.length-p;
            q++;
        }
    }
    return count;
}

	static int counter(int[] arr) {
			if (arr.length < 2){
			return 0;
			}

			int mid = (arr.length + 1) / 2;
			
			int left[] = new int[mid];
			for(int i =0; i < mid ; i++){
				left[i] = arr[i];
			}
			
			int right[] = new int[arr.length - mid];
			int count =0;
			for(int j = mid ; j<arr.length ; j++){
				right[count] = arr[j];
				count++;
			}
			return counter(left) + counter(right) + merger(arr, left, right);
		}

 }

