import java.awt.*;

public class Polygon {
    private Point origin;
    private Point[] points;
    public double angle;

    public Polygon(Point origin, double angle, Point[] points) {
        this.origin = origin;
        this.angle = angle;
        this.points = points;
        
    }
    
    public void paint(Graphics g) {
        System.out.println("painting");
        for (int i = 0; i < points.length - 1; i++) {
            
            // raw points
            Point a = points[i];
            Point b = points[i + 1];
            
            System.out.println("Orig Coords: (" + a.x + ", " + a.y + ") (" + b.x + ", " + b.y + ")");
            // distance to the origin
            double aDist = Math.sqrt(Math.pow(a.x, 2) + Math.pow(a.y, 2));
            double bDist = Math.sqrt(Math.pow(b.x, 2) + Math.pow(b.y, 2));
            System.out.println("\taDist: " + aDist + " bDist: " + bDist);
            // original angle to origin
            double aAngle = Math.atan((double)a.y / a.x) - ((a.x < 0) ? Math.PI : 0);
            double bAngle = Math.atan((double)b.y / b.x) - ((b.x < 0) ? Math.PI : 0);
            System.out.println("\taAngle: " + aAngle + " bAngle: " + bAngle);
            
            // origin and angle adjusted
            int ax = (int)((Math.cos(aAngle + angle) * aDist) + origin.x);
            int ay = (int)((Math.sin(aAngle + angle) * aDist) + origin.y);
            int bx = (int)((Math.cos(bAngle + angle) * bDist) + origin.x);
            int by = (int)((Math.sin(bAngle + angle) * bDist) + origin.y);

            System.out.println("\ta: " + ax + ", " + ay + " b: " + bx + ", " + by);
            
            g.drawLine(ax, ay, bx, by);
            
        }
        
    }
    
//    public void setAngle(double angle) {
//        this.angle = angle % 360;
//    }
//    
//    public double getAngle() {
//        return angle;
//    }
    
    private double signedCosine(double angle) {
        return ((Math.PI / 2d <= angle && angle < 3 * Math.PI / 2d) ? -1 : 1) * Math.cos(angle);
    }
    private double signedSine(double angle) {
        return ((Math.PI <= angle && angle < 2 * Math.PI) ? -1 : 1) * Math.sin(angle);
    }
}
