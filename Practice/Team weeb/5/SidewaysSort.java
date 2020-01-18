/**
 *
 * Algorthim Planning
 * #1
 * 
 * Algo

 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;


class SidewaysSort{

    static Kattio kattio = new Kattio(System.in, System.out);
    static ArrayList<String> array;

    public static void main(String[] args) {

            while(true){
                int lines = kattio.getInt();
                int chars = kattio.getInt();

                if(lines == 0 && chars == 0){break;}
                
                array = new ArrayList<>();

                for(int i=0; i<lines; i++){
                    String s = kattio.getWord();
                    char[] charArr = s.toCharArray();
                    Comparator charSort = new Comparator<Character>(){
                        public int compare(Character a, Character b ){
                            int compA = (int)a;
                            int compB = (int)b;
                            if(a<61){
                                compA = a+ 32;
                            }
                            if(b<61){
                                compB = b+ 32;
                            }
                            return Integer.compare(compA,compB);
                        }
                    };

                    Arrays.sort(charArr,charSort);

                    String newS = new String(charArr);
                    System.out.println(newS);
                    array.add(newS);
                }
                for(String s : array){
                    System.out.println(s);
                }
                System.out.println("");
            }
            kattio.flush();
     }    

}