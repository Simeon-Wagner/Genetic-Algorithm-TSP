package calculate;

public class City {

    private int id;
    private int x;
    private int y;

    public City(String id, String x, String y) {
        this.id = Integer.valueOf(id);
        this.x = Integer.valueOf(x);
        this.y = Integer.valueOf(y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "calculate.City{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
