public class Coordinate{

    private int row=0;
    private int col = 0;

    Coordinate(int r, int c){
        this.row = r;
        this.col = c;
    }

    public int getRow(){
        return this.row;
    }

    public int getCol(){
        return this.col;

    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (!(obj instanceof Coordinate))
          return false;
        if (obj == this)
          return true;
        return this.row == ((Coordinate)obj).getRow() && this.col == ((Coordinate)obj).getCol();
    }

    @Override
    public String toString(){
        return "Col:" + this.col + " Row:" + this.row;
    }

    @Override
    public int hashCode() {
        int tmp = ( col +  ((col+1)/2));
        return row +  ( tmp * tmp);
    }


}