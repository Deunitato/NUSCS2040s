/**
 *
 * Algorthim Planning
 * #1
 * 
 * Algo

 */

import java.util.Stack;

class EvenUp{

    static Stack<Integer> myStack = new Stack<>();
    static Kattio kattio = new Kattio(System.in, System.out);

    public static void main(String[] args) {

            int cards = kattio.getInt();
            for(int i = 0 ; i < cards ; i++){
                int value = kattio.getInt();
                if(!myStack.isEmpty() && isEven(value, myStack.peek())){
                    myStack.pop();//remove the 2 cards tgt
                } else {
                    myStack.add(value);
                }
            }
            

            // int counter = 0;


            // for(int i = 0 ; i <cards ; i++){
                
               
            //     int polled = myStack.pop();
            //     /*if(myStack.size() == 1){
            //         counter++;
            //     }*/
            //     if(myStack.isEmpty()){
            //         break;
            //     }
            //     if(isEven(polled, myStack.peek())){ //if the first two is even
            //         myStack.pop();
            //         continue;
            //     }
            //     else{ //not even, remove one, check next
            //         counter++;
            //     }
            // }

            System.out.println(myStack.size());
            kattio.flush();
     }

    //returns true when impossible
    static boolean isEven(int first, int second){
        return (first+second)%2 ==0;
    }

    

}