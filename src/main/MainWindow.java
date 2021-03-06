package main;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class MainWindow extends JPanel{

    // Create a constructor method
    public MainWindow(){
        super();
    }

    @Override
    public void paintComponent(Graphics g){

        polygons.forEach((p) -> {
            p.paint(g);
        });
        
//        polygons.get(0).angle += 0.1;
    }


    List<Polygon> polygons = new ArrayList<>() {{
        this.add(new Polygon(
                new Point(200, 200),
                Math.PI / 4d,
                new Point[] {
                        new Point(-20, -20),
                        new Point(-20, 20),
                        new Point(20, 20),
                        new Point(20, -20),
                        new Point(-20, -20)
                }));

//        this.add(new main.Polygon(
//                new main.Point(15, 15),
//                new main.Point[] {
//                        new main.Point(50, 50),
//                        new main.Point(70, 60),
//                        new main.Point(100, 200),
//                        new main.Point(100, 200),
//                        new main.Point(50,80)
//                }));
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