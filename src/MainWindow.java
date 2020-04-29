

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class MainWindow extends JPanel{

    // Create a constructor method
    public MainWindow(){
        super();
    }

    public void paintComponent(Graphics g){

        polygons.forEach((p) -> {
            p.paint(g);
        });
    }


    List<Polygon> polygons = new ArrayList<>() {{
        this.add(new Polygon(
                new Point(10, 10),
                new Point[] {
                        new Point(50, 50),
                        new Point(70, 60),
                        new Point(100, 200),
                        new Point(100, 200),
                        new Point(50,80)
                }));

        this.add(new Polygon(
                new Point(15, 15),
                new Point[] {
                        new Point(50, 50),
                        new Point(70, 60),
                        new Point(100, 200),
                        new Point(100, 200),
                        new Point(50,80)
                }));
    }};
    

    public static void main(String arg[]){
        JFrame frame = new JFrame("BasicPanel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLocation(800, 400);

        MainWindow panel = new MainWindow();
        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}