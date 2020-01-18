import java.lang.Math;
import java.util.HashSet;
import java.util.ArrayList;

class GreetingCard{

    static HashSet<Point> possible = new HashSet<>();
    static HashSet<Point> pointsSet = new HashSet<>();

    public static void main(String[] args) {

        ArrayList<Point> inputs = new ArrayList<>();

        Kattio kattio = new Kattio(System.in, System.out);
        int points = kattio.getInt();
        int count = 0;

        for(int i=0; i<points; i++){
            int x = kattio.getInt();
            int y = kattio.getInt();
            Point p = new Point(x, y);
            inputs.add(p);
            pointsSet.add(p);
        }
        
        generate();

        for(Point p1 : inputs){
            for(Point neighbour : possible){
                int x = p1.getX() + neighbour.getX();
                int y = p1.getY() + neighbour.getY();
                Point newP = new Point(x, y);
                if(pointsSet.contains(newP)){
                    count++;
                }
            }
        }

        kattio.println(count/2);

        kattio.flush();
    }


    //get numbers
    public static void generate(){
        possible.add(new Point(2018,0));
        possible.add(new Point(0,2018));
        possible.add(new Point(-2018,0));
        possible.add(new Point(0,-2018));

        //pytha
        possible.add(new Point(1680, 1118));
        possible.add(new Point(1680, -1118));
        possible.add(new Point(-1680, 1118));
        possible.add(new Point(-1680, -1118));
        possible.add(new Point(1118, 1680));
        possible.add(new Point(1118, -1680));
        possible.add(new Point(-1118, 1680));
        possible.add(new Point(-1118, -1680));
    }


    // //check if p1 is exactly 2018 from p2
    // static boolean isExact(Point p1, Point p2){
    //     return upAndDown(p1,p2) || (dist(p1,p2)==2018);
    // }

    // static boolean upAndDown(Point p1, Point p2){
    //     //not sure if need to handle p2-2018/ p2+2018
    //     return (p1.getX()+2018 == p2.getX()) || (p1.getX()-2018 == p2.getX())|| (p1.getY()+2018 == p2.getY())|| (p1.getY()-2018 == p2.getY());
    // }


    
    // //calculates distance between two points
    // static double dist(Point p1, Point p2){
    //     int horizDist = p2.getX() - p1.getX();
    //     int vertiDist = p2.getY()- p1.getY();
    //     double distance = Math.sqrt(Math.pow(horizDist, 2) + Math.pow(vertiDist, 2));

    //     return distance;
    // }


}