package data;

import java.io.Serializable;

/**
 * X-Y coordinates.
 */

public class Coordinates implements Serializable {
    private Long x;

    private double y;

    public Coordinates(Long x, double y) {
        this.x = x;
        this.y = y;
    }
    public void setX(Long xp){
        x = xp;
    }
    public  void setY(double yp){
        y = yp;
    }
    /**
     * @return X-coordinate.
     */

    public Long getX(){return x;}

    /**
     * @return Y-coordinate.
     */

    public double getY(){return y;}

    @Override
    public String toString(){return "X: " + x + " and " + " Y: " +y;}
}
