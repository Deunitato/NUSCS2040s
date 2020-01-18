import java.util.*;

public class ntnuorienteering {

    public int testCases;
    public int campuses;
    public int lectures;

    public int startCampus;
    public int endCampus;
    public int timeTaken;

    public int campusIndex;
    public int startTime;
    public int endTime;

    public Graph graph;

    public void run() {
        Kattio sc = new Kattio(System.in, System.out);

        testCases = sc.getInt();

        for (int t = 0; t < testCases; t++) {
            campuses = sc.getInt();
            lectures = sc.getInt();

            graph = new Graph(campuses, lectures);

            int limit = campuses * (campuses - 1) / 2;

            for (int c = 0; c < limit; c++) {
                startCampus = sc.getInt();
                endCampus = sc.getInt();
                timeTaken = sc.getInt();

                graph.addEdge(startCampus, endCampus, timeTaken);
            }

            for (int l = 0; l < lectures; l++) {
                campusIndex = sc.getInt();
                startTime = sc.getInt();
                endTime = sc.getInt();

                graph.addLecture(l, campusIndex, startTime, endTime);
            }

            graph.floydWarshall();
            graph.sortLectures();
            int maxVisited = graph.getVisited();
            sc.println(maxVisited);
        }

        sc.close();

    }

    public static void main(String[] args) {
        ntnuorienteering problem = new ntnuorienteering();
        problem.run();
    }
}

class Graph {
    public int numVertices;
    public int numLectures;
    public int[][] adjMatrix;

    public Lecture[] lectures;
    public int[] visited;

    public Graph(int numVertices, int lecture) {
        this.numVertices = numVertices;
        adjMatrix = new int[numVertices][numVertices];

        for (int v = 0; v < numVertices; v++) {
            Arrays.fill(adjMatrix[v], 0);
        }

        this.numLectures = lecture;

        this.lectures = new Lecture[numLectures];
        this.visited = new int[numLectures];

        Arrays.fill(visited, 1);
    }

    public void addEdge(int start, int end, int weight) {
        adjMatrix[start][end] = weight;
        adjMatrix[end][start] = weight;
    }

    public void addLecture(int pos, int index, int start, int end) {
        lectures[pos] = new Lecture(index, start, end);
    }

    public void floydWarshall() {
        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if (adjMatrix[i][k] + adjMatrix[k][j] < adjMatrix[i][j]) {
                        adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
                    }
                }
            }
        }
    }

    public void sortLectures() {
        Arrays.sort(lectures);
    }

    public int getVisited() {
        int maxVisited = 1;

        for (int i = 0; i < numLectures; i++) {
            for (int j = 0; j < i; j++) {

                Lecture firstLecture = lectures[i];
                Lecture secondLecture = lectures[j];

                int firstLectureStart = firstLecture.start;
                int secondLectureEnd = secondLecture.end;

                int timeTaken = adjMatrix[firstLecture.index][secondLecture.index];

                int firstVisited = visited[i];
                int secondVisited = visited[j];

                if (firstLectureStart - secondLectureEnd >= timeTaken) {
                    if (firstVisited < 1 + secondVisited) {
                        visited[i] = 1 + visited[j];
                    }
                }
            }

            if (visited[i] > maxVisited) {
                maxVisited = visited[i];
            }
        } 

        return maxVisited;
    }


}

class Lecture implements Comparable<Lecture> {
    public int index;
    public int start;
    public int end;

    public Lecture(int index, int start, int end) {
        this.index = index;
        this.start = start;
        this.end = end;
    }

    public int compareTo(Lecture other) {
        return this.start - other.start;
    }
}
