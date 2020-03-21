package leet;

class Pair{
    private Integer x;
    private Integer y;

    public Pair(int x, int y){
        this.x = x;
        this.y = y;
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
