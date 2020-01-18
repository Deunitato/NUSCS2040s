/**
 * 
 * Algorthim Planning
 * #1
 * Setup: 
 * one 2d array graph, lectureList of lecture
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
 * #3 Still rte
 * Fixes:
 * Remove the use of edge class
 * Shorten floyy code
 * Changes infinity to 0 for floydd code initial
 * Changes the conditon for if campus to /2
 * Changes initial loops to array.fill
 * 
 * #4 Wrong answer
 * changed findLectures fn
 * 

 */
import java.util.Arrays;

class NTNU{
    static Kattio kattio = new Kattio(System.in, System.out);
    static int[][] graph;
    //static Edge[] campList;
    static Lecture[] lectureList;

    public static void main(String[] args) {

        int tests = kattio.getInt();
        for(int t = 0 ; t < tests ; t++){

            int campuses = kattio.getInt();
            int lectures = kattio.getInt();
            graph = new int[campuses][campuses];
           // campList = new Edge[campuses];
            populate(campuses);
            lectureList = new Lecture[lectures];

            //input for camp
            for(int i = 0 ; i < (campuses * (campuses-1))/2; i++){
                int camp1 = kattio.getInt();
                int camp2 = kattio.getInt();
                int time = kattio.getInt();
               // campList[i] = new Edge(camp1, camp2, time);
                graph[camp1][camp2] = time;
                graph[camp2][camp1] = time;
            }
            floydWashingBoard(campuses);

            //input for lectures
            for(int i = 0 ; i < lectures; i++){
                int camp = kattio.getInt();
                int start = kattio.getInt();
                int end = kattio.getInt();
                Lecture l = new Lecture(camp,start,end,i);
                lectureList[i] = l;
            }

            Arrays.sort(lectureList);

            int attendedMax = tryStartingLecture(lectures);

            // //try lectures
            // for(int i = 0 ; i< lectures ; i++){
            //     Lecture l = lectureList.poll();
            //     int attended = tryStartingLecture(lectures);
            //     if(attended > attendedMax){
            //         attendedMax = attended;
            //     }
            // }

            kattio.println(attendedMax);
        }
        kattio.flush();

    }

    static void populate(int campuses){
        //init everything to infinity
        for(int i=0; i<campuses; i++){
            Arrays.fill(graph[i], 0);
        }
    }

    static int tryStartingLecture(int lectures){
        // int freeTime = l.getEnd();
        // int lecturesAttended = 1;
        // int myCampus = l.getCampus();

        int max = 1;
        int[] numLectArr = new int[lectures];
        for(int i = 0 ; i < lectures; i++){
            numLectArr[i] = 1;
        }

        for(int i=0; i< lectures; i++){
            Lecture curr = lectureList[i];
            for(int j=0; j<i; j++){
                int currCampus = curr.getCampus();
                int nextCampus = lectureList[j].getCampus();
                int firstStart = curr.getStart();
                int nextend = lectureList[j].getEnd();
                //if(nextStart - currEnd  >=  getCampusTravelTime(currCampus, nextCampus)){//i can visit the next campus lecture
                if(firstStart -getCampusTravelTime(currCampus, nextCampus) >= nextend ){//i can visit the next campus lecture
                    if(numLectArr[i] < numLectArr[j] +1){
                        numLectArr[i] = numLectArr[j] +1;
                    }
                }

            }

            if(numLectArr[i] > max){
                max = numLectArr[i];
            }

        }
        return max;
    }


/*
    static int tryStartingLecture(int lectures){
        // int freeTime = l.getEnd();
    
        // int myCampus = l.getCampus();

        int max = 0;
        int[] numLectArr = new int[lectures];
        Arrays.fill(numLectArr, 0);
        for(int i=lectures-1; i>=0; i--){
            Lecture curr = lectureList[i];

            for(int j=i; j<lectures; j++){

                if(j == lectures-1){ //initial
                    numLectArr[i] = 1;
                    break;
                }

                int freeTime = curr.getEnd();//first end
                int currCampus = curr.getCampus();
                int nextStart = lectureList[j+1].getStart();
                int nextCampus = lectureList[j+1].getCampus();
                // System.out.println(currCampus);
                // System.out.println(freeTime);
                // System.out.println(nextCampus);
                // System.out.println(nextStart);
                // System.out.println(getCampusTravelTime(currCampus, nextCampus));
                if(nextStart >= freeTime + getCampusTravelTime(currCampus, nextCampus)){//i can visit the next campus lecture
                    if(numLectArr[i] < numLectArr[j+1] +1){
                        numLectArr[i] = numLectArr[j+1] +1;
                        break;
                    }
                }

            }

            if(numLectArr[i] > max){
                max = numLectArr[i];
            }

        }
        return max;
    }*/

    /* static int tryStartingLecture(Lecture l){
        int freeTime = l.getEnd();
        int lecturesAttended = 1;
        int myCampus = l.getCampus();

        while(!lectureList.isEmpty()){
            Lecture next = lectureList.poll();
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

    }*/

    //return the campus time between camp1 and camp2
    static int getCampusTravelTime(int camp1, int camp2){
        return graph[camp1][camp2];
    }

    static void floydWashingBoard(int campuses){

        // for(Edge e : campList){
        //     int c1 = e.getParent();
        //     int c2 = e.getChild();
        //     int w = e.getWeight();
        //     graph[c1][c2] = w;
        //     graph[c2][c1] = w;
        // }

        for(int k = 0; k < campuses; k++){
            for(int v = 0; v < campuses; v++){
                for(int w = 0; w < campuses; w++){
                    //int min = Math.min(graph[v][w], graph[v][i]+graph[i][w]);
                    //graph[v][w] = min;

                    if (graph[v][k] + graph[k][w] < graph[v][w]) {
                        graph[v][w] = graph[v][k] + graph[k][w];
                    }
                }
            }
        }

    }


}