/**
 * 
 * Algorthim Planning
 * #1
 * Setup: 
 * one 2d array graph, pq of lecture
 * Input:
 * Algo
 * 1. Run Floyd for 2d Graph to get apsp
 * 2. Try every lecture as starting
 * 3. Get max number of lecture that can be attended for each lecture
 * 
 * #2 Run time error - probabily infinite loop
 * Fixes: Change entire algo
 * Algo
 * 1. Floyd 
 * Label all lectures with ids
 * 2. Start from the back lecture to front
 * 3. use memorisation to store prev possible lectures 
 * 4. Each iteration, check if that lecture can be place infront of previous
 * 5. Stop when reach the first lect timing
 * 

 */
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Collections;
import java.util.Arrays;

class NTNU_sucks{
    static Kattio kattio = new Kattio(System.in, System.out);
    static int[][] graph;
    static Edge[] campList;
	static Lecture[] lectureList;

    public static void main(String[] args) {

        int tests = kattio.getInt();
        for(int t = 0 ; t < tests ; t++){

            int campuses = kattio.getInt();
            int lectures = kattio.getInt();
            graph = new int[campuses][campuses];
            campList = new Edge[campuses];
            populate(campuses);
            
            lectureList = new Lecture[lectures];

            //input for camp
            for(int i = 0 ; i < (0.5 * campuses * (campuses-1)); i++){
                int camp1 = kattio.getInt();
                int camp2 = kattio.getInt();
                int time = kattio.getInt();
                campList[i] = new Edge(camp1, camp2, time);
            }
            floydWashingBoard(campuses);

            //input for lectures
            for(int i = 0 ; i < lectures; i++){
                int camp = kattio.getInt();
                int start = kattio.getInt();
                int end = kattio.getInt();
                Lecture l = new Lecture(camp,start,end,i);
                lectureList[i]=(l);
 
            }


             int attendedMax = 0;

            //try lectures
             for(int i = 0 ; i< lectures ; i++){
                 Lecture l = lectureList[i];
                int attended = tryStartingLecture(l,i);
                if(attended > attendedMax){
                     attendedMax = attended;
                }
            }

            kattio.println(attendedMax);
            kattio.flush();
        }

    }

    static void populate(int campuses){

        //init everything to infinity
        for(int i=0; i<campuses; i++){
            for(int j=0; j<campuses; j++){
                graph[i][j] = 1000000000;
            }
        }

        //path to myself is 0
        for(int k = 0 ; k < campuses; k++){
            graph[k][k] = 0;
        }
    }




     static int tryStartingLecture(Lecture l, int starting){
        int freeTime = l.getEnd();
        int lecturesAttended = 1;
        int myCampus = l.getCampus();

        for(int i = starting ; i < lectureList.length; i++){
            Lecture next = lectureList[i];
            if(myCampus == next.getCampus()){

                if(freeTime < next.getStart()){ //i can do this lecture
                    freeTime = next.getEnd();
                    lecturesAttended++;
                    myCampus = next.getCampus();
                }
                else{
                    continue;
                }
            }
            else{//have to travel to next campus
                if(freeTime < next.getStart() +getCampusTravelTime(myCampus,next.getCampus())){//i can visit the next campus lecture
                    freeTime = next.getEnd();
                    lecturesAttended++;
                    myCampus = next.getCampus();
                }

            }

        }

        return lecturesAttended;

    }

    //return the campus time between camp1 and camp2
    static int getCampusTravelTime(int camp1, int camp2){
        return graph[camp1][camp2];
    }

    static void floydWashingBoard(int campuses){

        for(Edge e : campList){
            int c1 = e.getParent();
            int c2 = e.getChild();
            int w = e.getWeight();
            graph[c1][c2] = w;
            graph[c2][c1] = w;
        }

        for(int i = 0; i < campuses; i++){
            for(int v = 0; v < campuses; v++){
                for(int w = 0; w < campuses; w++){
                    graph[v][w] = Math.min(graph[v][w], graph[v][i]+graph[i][w]);
                }
            }
        }

    }


}