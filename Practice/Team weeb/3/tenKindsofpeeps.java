/**
 * CS2040S Practice Team Weebs
 *    10 kinds of people -kattis
 *  https://open.kattis.com/contests/os7ty8/problems/10kindsofpeople
 */

/* 
    TODO: 
*/

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Scanner;
import java.util.Queue;
import java.util.HashSet;
/*
Algorithm
#1


*/

public class tenKindsofpeeps {
    static boolean[][] myMap;
    static boolean[][] visited;
    //static HashSet<Coordinate> visited = new HashSet<>(); 
    public static final int[][] NEIGHBOURSDIR = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    
    public static void main(String args[]) {
        Kattio scan = new Kattio(System.in);

        int row = scan.getInt();
        int column = scan.getInt();
        myMap = new boolean[row][column];
        visited = new boolean[row][column];
        buildMap(scan, row, column);    
        
        int n = scan.getInt();
        for(int i = 0; i< n ; i++){
            visited = new boolean[row][column];
            int r1 = scan.getInt()-1;
            int c1 = scan.getInt()-1;
            int r2 = scan.getInt()-1;
            int c2 = scan.getInt()-1;

            Coordinate start = new Coordinate(r1, c1);
            Coordinate end = new Coordinate(r2, c2);
            
            System.out.println(query(start, end));
        }
        
        
    }


    public static void buildMap(Kattio sc, int row, int col){
        //int k=0;
        for(int i = 0 ; i < row ; i++){
            String s = sc.getWord();
            for(int j = 0; j < col; j++){
                myMap[i][j] = toBool(s.charAt(j));
                visited[i][j] = false;
                //k++;
            }
        }
    }

    public static boolean toBool(char c){
        if(c == '1'){
            return true;
        } else {
            return false;
        }
    }

    //return true if can go to the end
    //return false if cannot
    public static boolean map(boolean type, Coordinate start, Coordinate end){

        //dfs
        Queue<Coordinate> path = new LinkedList<>();

        Coordinate curr;
        int currR = start.getRow();
        int currC = start.getCol();
        int endR = end.getRow();
        int endC = end.getCol();
        int neighbourR = 0;
        int neighbourC = 0;
        if(myMap[endR][endC]!= type){
            return false;
        } //check if my final destination is in my path
        visit(start);
        path.add(start);
        while(!path.isEmpty()){
            curr = path.remove();
            currR = curr.getRow();
            currC = curr.getCol();
            
            if(currR == endR && currC == endC){
                return true;
            } else {
                for(int[] arr : NEIGHBOURSDIR){
                    neighbourR = currR + arr[0];
                    neighbourC = currC + arr[1];
                    Coordinate neighbour = new Coordinate(neighbourR, neighbourC);
                    if(neighbourC >= myMap[0].length || neighbourR >= myMap.length || neighbourC < 0 || neighbourR < 0 || myMap[neighbourR][neighbourC] != type){   //dead end
                        continue;
                    }
                   // if(!visited.contains(neighbour)){
                    if(!visited[neighbourR][neighbourC]){    
                        visit(neighbour);
                        path.add(neighbour);
                    } else {
                        continue;
                    }
                    
                }
            }
        }

        return false;

    }

    public static void visit(Coordinate c){
        //visited.add(c);
        visited[c.getRow()][c.getCol()] = true;
    }

    public static String query(Coordinate start, Coordinate end){


        int r1 = start.getRow();
        int c1 = start.getCol();
        boolean isBin = myMap[r1][c1];
        boolean mapResult = map(isBin, start, end);
        if(!isBin&& mapResult ){
            return "binary";
        }
        else if(isBin && mapResult){

            return "decimal";
        }
        else{
            return "neither";
        }
    }
}

