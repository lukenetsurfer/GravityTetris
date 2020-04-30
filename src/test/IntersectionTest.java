package test;

import main.Point;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.List;

public class IntersectionTest {
    
    @Test
    public void testIntersection() {


        main.Polygon p1 = new main.Polygon(
                new main.Point(200, 200),
//                Math.PI / 4d,
                0,
                new main.Point[] {
                        new main.Point(-20, -20),
                        new main.Point(-20, 20),
                        new main.Point(20, 20),
                        new main.Point(20, -20),
                        new Point(-20, -20)
                });
        main.Polygon p2 = new main.Polygon(
                new main.Point(230, 210),
                0,
                new main.Point[] {
                        new main.Point(-20, -20),
                        new main.Point(-20, 20),
                        new main.Point(20, 20),
                        new main.Point(20, -20),
                        new Point(-20, -20)
                });
//        main.Polygon p3 = new main.Polygon(
//                new main.Point(200, 200),
//                0.1,
//                new main.Point[] {
//                        new main.Point(-20, -20),
//                        new main.Point(-20, 20),
//                        new main.Point(20, 20),
//                        new main.Point(20, -20),
//                        new Point(-20, -20)
//                });
        
        makeWindow((g) -> {
            p1.paint(g);
            p2.paint(g);
            
            
//            p2.angle += 0.01;
            
            
            System.out.println("Calculating intersections: "); 
            List<Point> collisions = p1.findCollisionsWith(p2);
            System.out.println("Intersections: ");
            collisions.forEach((p) -> {
                System.out.println("\t" + p);
                drawCenteredCircle((int)p.x, (int)p.y, 2, g);
            });
            
        });
        
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    private void makeWindow(Consumer<Graphics> c) {
        JFrame frame = new JFrame("BasicPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLocation(800, 400);

        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                c.accept(g);
            }
        };
        
        frame.setContentPane(panel);
        frame.setVisible(true);
    }
    
    private void drawCenteredCircle(int x, int y, int r, Graphics g) {
        Color oldColor = g.getColor();
        g.setColor(Color.RED);
        g.drawOval(x - r, y - r, 2 * r, 2 * r);
        g.setColor(oldColor);
    }
}
