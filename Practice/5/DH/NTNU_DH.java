import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class NTNU {

    static Kattio io = new Kattio(System.in);

    public static void main(String[] args) {
        int cases = io.getInt();
        for (int i = 0; i < cases; i++) {
            solve();
        }
        io.close();
    }

    static void solve() {
        int nodes = io.getInt();
        int edges = nodes * (nodes - 1) / 2;
        int lectures = io.getInt();
        /*if (nodes == 1 && lectures == 1) {
            io.println(1);
            io.flush();
            System.out.println(true);
            return;
        }*/
        int[][] graph = new int[nodes][nodes];
        Node[] lecArr = new Node[lectures];
        //PriorityQueue<Node> pq = new PriorityQueue<Node>();
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j -= -1) {
                if (i == j) {
                    graph[i][j] = 0;
                } else {
                    graph[i][j] = (int) Integer.MAX_VALUE;
                }
            }
        }
        for (int i = 0; i < edges; i++) {
            int node = io.getInt();
            int neigh = io.getInt();
            int weight = io.getInt();
            graph[node][neigh] = weight;
            graph[neigh][node] = weight;
        }
        for (int i = 0; i < lectures; i++) {
            int key = io.getInt();
            int start = io.getInt();
            int end = io.getInt();
            Node lecture = new Node(key, start, end);
            lecArr[i] = lecture;
            //pq.add(lecture);
            // store the lecture in an array here
        }
        Arrays.sort(lecArr);
        //io.println(Arrays.toString(lecArr));
        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                for (int k = 0; k < nodes; k++) {
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
        /*for (int i = 0; i < nodes; i++) {
            io.println(Arrays.toString(graph[i]));
        }*/
        long[] count = new long[lecArr.length];
        for (int i = 0; i < lecArr.length; i++) {
            for (int j = i ; j < lecArr.length; j++) {
                Node from = lecArr[i];
                Node to = lecArr[j];
                if (from == to && i == 0) {
                    count[j]++;
                    //io.println(Arrays.toString(count) + i + " " + j);
                    continue;
                }
                if (from.end + graph[from.key][to.key] <= to.start) {
                    count[j] = count[i] + 1;
                } else {
                    if (count[j] < count[i]) {
                        count[j] = count[i];
                    }
                }
                //io.println(Arrays.toString(count)+ i + " " + j);
            }
        }
        long max = 0;
        for (long i : count) {
            if (i > max) max = i;
        }
        //io.println(Arrays.toString(count));
        io.println(max);
    }

    static class Node implements Comparable<Node> {

        int key;
        int start;
        int end;

        public Node(int key, int start, int end) {
            this.start = start;
            this.end = end;
            this.key = key;
        }

        public int compareTo(Node other) {
            return this.start - other.start;
        }

        public String toString() {
            return "(" + this.key + ", " + this.start + ", " + this.end + ")";
        }

    }
}