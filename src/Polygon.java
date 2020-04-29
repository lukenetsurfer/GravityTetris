import java.awt.*;

public class Polygon {
    private Point origin;
    private Point[] points;

    public Polygon(Point origin, Point[] points) {
        this.origin = origin;
        this.points = points;
    }
    
    public void paint(Graphics g) {
        for (int i = 0; i < points.length - 1; i++) {
            Point a = points[i];
            Point b = points[i + 1];
            g.drawLine(
                    origin.x + a.x, 
                    origin.y + a.y, 
                    origin.x + b.x, 
                    origin.y + b.y
            );
            
        }
        
    }
}
