package leet;

public class Pair{
    private Integer x;
    private Integer y;

    public Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Pair(int[] v){
        this.x = v[0];
        this.y = v[1];
    }


    public int hashCode(){
        return x.hashCode() + y.hashCode();
    }

    public boolean equals(Object object){
        if (object instanceof Pair) {
            return x.intValue() == ((Pair) object).getX().intValue() && y.intValue()==((Pair) object).getY().intValue();
        }
        return false;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }
}
