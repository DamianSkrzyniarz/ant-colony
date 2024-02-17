package pl.dskrzyniarz;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Ilość punktów: ");
        int points = scanner.nextInt();

        for(int i=0; i< points; i++){
            System.out.println("Punkt " + (char)(i+65));
            System.out.print("X: ");
            float x = scanner.nextFloat();
            System.out.print("Y: ");
            float y = scanner.nextFloat();
            new Node((char)(i+65), x, y);
        }

        Frame frame = new Frame(Node.getGlobalNodeList());

        for(int i = 0; i<1000; i++){
            List<Ant> ants = new ArrayList<>();
            for (int j = 0; j <= 30; j++) {
                ants.add(new Ant(Node.getGlobalNodeList().getFirst()));
            }
            ants.forEach(Thread::start);
            ants.forEach(ant -> {
                try {
                    ant.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            Ant bestAnt = ants.getFirst();
            for (Ant ant : ants) {
                if (ant.getTotalDistanceTraveled() < bestAnt.getTotalDistanceTraveled()) {
                    bestAnt = ant;
                }
            }
            bestAnt.getPath().forEach(link -> link.increasePopularity(0.1f));
            frame.repaint();
            System.out.println(bestAnt);
        }
    }
}