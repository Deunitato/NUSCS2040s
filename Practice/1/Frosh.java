
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.HashSet;

/*

*/

class Frosh{
	
	static long counter = 0;
 public static void main(String[] args){

       
	   try(Kattio kattio = new Kattio(System.in)){
         int total= kattio.getInt();
		 int[] list = new int[total];
		 for(int i=0; i< total;i++){
			 int get = kattio.getInt();
			 list[i] = get;
		 }
		
		//int swaps = counter(list);
		 merge_sort(list,0,total-1);
		/*for(int i = total-1 ; i>=0 ; i--){
			int index = list.indexOf(i+1);
		   if(index!=(i+1)){
			swaps = swaps + ((i+1) - (index+1));
		 }
		}*/
		/*for(int i: list){
		kattio.println(i);	
		}
		*/
		kattio.println(counter);
	     kattio.flush(); //so that it will be delivered to the kattis system, if not flush it will have error in output
   }
	 


    }
	
	static void merge_sort(int[] arr,int p1, int p2 ){
		
		int mid=0;
		if(p1<p2){ //if my lower pointer is lower than my higher pointer
			mid = p1+((p2 - p1)/2);
			merge_sort(arr,p1,mid);
		    merge_sort(arr, mid+1,p2);
			merge(arr, p1, mid, p2);
		}
	}
	
	static void merge(int[] arr, int p1, int mid, int p2){
		
		int left = mid - p1 +1;
		int right = p2 - mid;
		
		int leftarr[] = new int[left];
		int rightarr[] = new int[right];
		//copy
		for (int i=0; i<left; i++) {
         leftarr[i] = arr[p1 + i];  
		}
  
        for (int j=0; j<right; j++) {
         rightarr[j] = arr[mid + 1+ j];  
		}
		
		int i =0;
		int j=0;
		int k= p1;
		while(i<left&& j<right){
			if(leftarr[i] < rightarr[j]){
				arr[k] = leftarr[i];
				i++;
			}
			else{
				arr[k] = rightarr[j];
				//counter+= mid- i +1; 
				counter+= left - i;
				j++;
			}
			k++;
		}
		
		while(i< left){
			arr[k] = leftarr[i];
			i++;
			k++;
		}
		
		while(j<right){
			arr[k] = rightarr[j];
			j++;
			k++;
		}
		
		
	/*	int[] buffer = new int[arr.length];
		for(int i= 0 ; i< arr.length; i++){
			buffer[i] = arr[i];
		}
		
		int low1 = p1;
		int m = mid +1;
		int low2 = p1;
		while(low1<=mid && m <= p2){
			
			if(buffer[low1] <= buffer[m]){
				arr[low2] = buffer[low1];
				low1++;
			}
			else{
				arr[low2] = buffer[m];
				counter = counter + (m-low1);
				m++;
			}
			low2++;	
		}
		
		while(low1<= mid){
			arr[low2] = buffer[low1];
		    low1++;
		    low2++;
		}*/
		
		
		
	}
	/*
	static void merger(int[] arr, int[] left, int[] right) {
       int p = 0; 
	   int q = 0;
	   int count = 0;
    while (q < right.length || p < left.length ) {
		
        if (p == left.length) {//finish with left
          //  arr[p+q] = right[q];
            q++;
        } else if (q == right.length) { //finish with right
            //arr[p+q] = left[p];
            p++;	
        }else if (left[p] <= right[q]) {
            //arr[p+q] = left[p];
            p++;                
        } else {
            //arr[p+q] = right[q];
            count += left.length-p;
            q++;
        }
    }
    return count;
}

	static void counter(int[] arr) {
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
*/
 }

