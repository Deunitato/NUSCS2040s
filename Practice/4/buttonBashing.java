/*
Algorithm
#1
setup
Datastructure -> array / treemap
Index/key - > time in seconds
values => moves needed to go there

1. Set all values as infinity (array)
2. Init Array of buttons

BFS:
1. Search through every frontier from any starting
2. Each time add the sum of buttons for every neighbour including myself
3. Store the number of moves needed in the ds
4. Check ds if there exist a number > = wanted
5. if false, continue next frontier
else
(if true)   
take smallest of the indexes that is > = wanted
6. return moves and key- wanted

Optimisation:
Change treemap to array
 */

import java.util.LinkedList;
import java.util.Queue;

public class buttonBashing {
    
    static int[] buttonList;
    static int[] myList;

	public static void main(String args[]) {
        int finalSum;
    	try(Kattio kattio = new Kattio(System.in)){
			int testCases = kattio.getInt();
			for(int i=0; i<testCases; i++){
                myList = new int[3601];
                populate();
				int numOfButtons = kattio.getInt();
                int wantedTime = kattio.getInt();
                buttonList = new int[numOfButtons];
                for(int k = 0 ; k<numOfButtons; k ++){
                    buttonList[k] = kattio.getInt();
                }
                finalSum = BFS(wantedTime);
                kattio.println(myList[finalSum] + " " +(finalSum - wantedTime));
                kattio.flush(); //so that it will be delivered to the kattis system, if not flush it will have error in output
            }
		}
    }

    public static void populate(){
        for(int i = 0 ; i < 3601 ; i++){
            myList[i] = Integer.MAX_VALUE;
        }
    }
    

//This BFS returns the final calculated value closes to the time we are searching
    public static int BFS(int searching){

        Queue<Integer> sumOfSeconds = new LinkedList<>();
        sumOfSeconds.add(0); //start from 0
        myList[0] = 0;

        while(!sumOfSeconds.isEmpty()){
            int current = sumOfSeconds.poll();

            for(int button : buttonList){ //does one move each time
                int newTime = button + current;
                int currentMoves = myList[current];
               /* if((update(newTime, currentMoves))){ //change the moves if possible
                    sumOfSeconds.add(newTime); //update the queue
                }*/
                /*if(newTime<0 ){
                    newTime = 0;
                } else if (newTime > 3600){
                    newTime = 3600;
                }*/
                newTime = Math.max(0, Math.min(3600,newTime));

                if((currentMoves +1) < myList[newTime]){
                    myList[newTime] = currentMoves+1;
                    sumOfSeconds.add(newTime); //update the queue
                }
        	}
        }

        for(int i = searching ; i < 3601 ; i++){
            if(myList[i] != Integer.MAX_VALUE){
                return i;
            }
        }

        return 0;
    }
    /*

    /**
     * 
     * Updates the sum of time when a button is press
     * @param button
     * @param sum
     * @return updated true if updates, returns false when fails
     */

   /* public static boolean update(int newTime, int currentMoves){
        if(newTime<0 ){
            newTime = 0;
        }
        else if (newTime > 3600){
            newTime = 3600;
        }

        if((currentMoves +1) > myList[newTime]){
            //if the previous number of moves to get to same time is lesser, do not update
                return false;
        }
        else{
            myList[newTime] = ++currentMoves;
        }
        //myList.put(newTime, currentMoves +1);
        return true;    
    

    }
    */
}