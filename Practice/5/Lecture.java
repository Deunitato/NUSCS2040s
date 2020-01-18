public class Lecture implements Comparable<Lecture>{

    private int campus = 0;
    private int startTime = 0;
    private int endTime = 0;
    private int id=0;
    public Lecture(int campus, int startTime, int endTime,int id){
        this.campus = campus;
        this.startTime = startTime;
        this.endTime = endTime;
        this.id = id;
    }

    public int getCampus(){
        return this.campus;
    }
    
    public int getId(){
        return this.id;
    }


    public int getStart(){
        return this.startTime;
    }

    public int getEnd(){
        return this.endTime;
    }

    public int getDuration(){
        return this.startTime - this.endTime;
    }

    public int compareTo(Lecture anotherEdge) {
		//ascending order
		return this.startTime - anotherEdge.getStart();
	}


}