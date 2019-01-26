package geometry;

public class Rectangle {
    public double x;
    public double y;
    public double width;
    public double height;

    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean intersects(Rectangle r) {
        if(x < r.x) {
            if(x + width > r.x) {
                if(y < r.y) {
                    if(y + height > r.y) {
                        return true;
                    } else return false;
                } else if(y > r.y) {
                    if(r.y + r.height > y) {
                        return true;
                    }
                    else return false;
                } else {
                    return true;
                }
            } else return false;
        } else if(x > r.x) {
            if(x + width < r.x) {
                if(y < r.y) {
                    if(y + height > r.y) {
                        return true;
                    } else return false;
                } else if(y > r.y) {
                    if(r.y + r.height > y) {
                        return true;
                    }
                    else return false;
                } else {
                    return true;
                }
            } else return false;

        } else {

        }
    }
}
