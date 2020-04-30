package main;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Polygon {
    private Point origin;
    private Point[] points;
    private Point[] displayPoints;
    public double angle;

    public Polygon(Point origin, double angle, Point[] points) {
        this.origin = origin;
        this.angle = angle;
        this.points = points;
        this.displayPoints = new Point[points.length];

        updatePoints();
    }
    
    public void paint(Graphics g) {
        updatePoints();
        for (int i = 0; i < displayPoints.length - 1; i++) {
            
            Point a = displayPoints[i];
            Point b = displayPoints[i + 1];
            
            g.drawLine((int)a.x, (int)a.y, (int)b.x, (int)b.y);
        }
        
    }
    
    public void updatePoints() {
        for (int i = 0; i < points.length; i++) {

            // raw points
            Point p = points[i];
//            System.out.println("Orig Coords: (" + a.x + ", " + a.y + ") (" + b.x + ", " + b.y + ")");

            // distance to the origin
            double dist = Math.sqrt(Math.pow(p.x, 2) + Math.pow(p.y, 2));
//            System.out.println("\taDist: " + aDist + " bDist: " + bDist);

            // original angle to origin
            double theta = Math.atan((double)p.y / p.x) - ((p.x < 0) ? Math.PI : 0);
//            System.out.println("\taAngle: " + aAngle + " bAngle: " + bAngle);

            // origin and angle adjusted
            double x = (Math.cos(theta + angle) * dist) + origin.x;
            double y = (Math.sin(theta + angle) * dist) + origin.y;
//            System.out.println("\ta: " + ax + ", " + ay + " b: " + bx + ", " + by);
            
            displayPoints[i] = new Point(x, y);
        }
    }

    public List<Point> findCollisionsWith(Polygon that) {
        List<Point> collisions = new ArrayList<>();
        
        // Iterate through all of the lines in this polygon
        for (int i = 0; i < displayPoints.length - 1; i++) {
            Point endpointA = displayPoints[i];
            Point endpointB = displayPoints[i + 1];
            
            // Iterate through all of the lines in that polygon
            for (int j = 0; j < that.displayPoints.length - 1; j++) {
                Point otherEndpointA = that.displayPoints[j];
                Point otherEndpointB = that.displayPoints[j + 1];
                
                Point intersection = findIntersection(endpointA, endpointB, otherEndpointA, otherEndpointB);
                // if there was actually an intersection, add it to the list of collisions
                if (intersection != null) {
                    collisions.add(intersection); 
                }
            }
        }
        
        return collisions;
    }
    
    private Point findIntersection(Point p1, Point p2, Point q1, Point q2) {
        
        System.out.print("p1: " + p1 + " p2: " + p2 + " q1: " + q1 + " q2: " + q2);
        
        // Calculate slopes
        double m1 = (p1.y - p2.y) / (p1.x - p2.x);
        double m2 = (q1.y - q2.y) / (q1.x - q2.x);

        // Parallel lines don't intersect, and will cause divide-by-zero
        if (m1 == m2) {
            System.out.println();
            return null;
        }
        
        double x;
        double y;
        if (m1 == Double.POSITIVE_INFINITY || m1 == Double.NEGATIVE_INFINITY) {
            // The line defined by p1 and p2 is vertical x = c
            double b2 = q1.y - (m2 * q1.x);
            
            x = p1.x;
            y = m2 * x + b2;
        } else if (m2 == Double.POSITIVE_INFINITY || m2 == Double.NEGATIVE_INFINITY) {
            // The line defined by q1 and q2 is vertical x = c
            double b1 = p1.y - (m1 * p1.x);
            
            x = q1.x;
            y = m1 * x + b1;
        } else {
            // Dealing with 2 standard lines
            
            // Calculate intercepts
            double b1 = p1.y - (m1 * p1.x);
            double b2 = q1.y - (m2 * q1.x);

            // Calculate intersection
            x = (b2 - b1) / (m1 - m2);
            y = m1 * x + b1;
        }

        // Confirm that the intersection is on both line segments (not just on the line defined by the 2 points)
        if (((p1.x < p2.x && (p1.x <= x && x <= p2.x)) || (p2.x < p1.x && (p2.x <= x && x <= p1.x)) || (p1.x == p2.x && (Math.min(p1.y, p2.y) <= y && y <= Math.max(p1.y, p2.y)))) &&
                ((q1.x < q2.x && (q1.x <= x && x <= q2.x)) || (q2.x < q1.x && (q2.x <= x && x <= q1.x)) || (q1.x == q2.x && (Math.min(q1.y, q2.y) <= y && y <= Math.max(q1.y, q2.y))))) {
            System.out.println("  hit!");
            return new Point(x, y);
        } else {
            System.out.println();
            return null;
        }
    }

}
