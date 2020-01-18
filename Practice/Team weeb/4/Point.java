class Point{

    private int x;
    private int y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }


    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (!(obj instanceof Point))
          return false;
        if (obj == this)
          return true;

        return this.x == ((Point)obj).getX() && this.y == ((Point)obj).getY();
    }

    @Override
    public String toString(){
        return "X:" + this.x + " Y:" + this.y;
    }

    @Override
    public int hashCode() {
        int tmp = ( y +  ((y+1)/2));
        return x +  ( tmp * tmp);
    }



}