package pl.dskrzyniarz;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class Frame extends JFrame {

    private final List<Node> nodes;
    Frame(List<Node> nodes) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1024,768);
        this.setVisible(true);
        this.nodes = nodes;
        this.setBackground(Color.black);
    }

    public void paint(Graphics g){

        Graphics2D g2d = (Graphics2D) g;


        for(Node node: nodes){

            g2d.setPaint(Color.white);

            for(Link link: node.getLinks()){
                g2d.setStroke(new BasicStroke(0.5f + link.getPopularity()/20));

                int startX = (int)(link.getStart().getWidth()*100);
                int startY = 768-(int)(link.getStart().getHeight()*100);
                int finishX = (int)(link.getFinish().getWidth()*100);
                int finishY = 768-(int)(link.getFinish().getHeight()*100);
                g2d.drawLine(startX, startY, finishX, finishY);
            }

            g2d.setPaint(Color.red);

            int nodeX = (int)(node.getWidth()*100)-10;
            int nodeY = 758-(int)(node.getHeight()*100);
            g2d.fillOval(nodeX, nodeY, 20, 20);

        }

    }


}
