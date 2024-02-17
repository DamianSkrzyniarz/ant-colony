package pl.dskrzyniarz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ant extends Thread{

    private final List<Node> visited;
    private final List<Link> path;
    private float totalDistanceTraveled;


@Override
    public void run() {
        while(visited.size() < Node.getGlobalNodeList().size()){
            visited.add(selectNextNode());
        }
        visited.getLast().getLinks()
        .forEach(link -> {
            if(link.getStart().equals(visited.getFirst()) || link.getFinish().equals(visited.getFirst())){
                path.add(link);
                totalDistanceTraveled += link.getDistance();
            }
        });
    }

    private Node selectNextNode(){

        List<Link> links = new ArrayList<>(visited.getLast().getLinks());

        //discard links to previously visited nodes
        links.removeIf(link ->visited.contains(link.getStart()) && visited.contains(link.getFinish()));

        float totalWeight = 0;
        for(Link link: links){
            totalWeight += link.getPopularity() + 1/ link.getDistance();
//            System.out.println(link);
//            System.out.println("inv distance = " + 1/link.getDistance());
//            System.out.println("popularity = " + link.getPopularity());
        }
            //System.out.println("sum = " + totalWeight);

        Random random = new Random();
        float randomNumber = random.nextFloat(totalWeight);
//        System.out.println("random = " + randomNumber);
        for(Link link: links){
            randomNumber -= link.getPopularity() + 1/link.getDistance();
            if (randomNumber<=0){
                path.add(link);
                totalDistanceTraveled += link.getDistance();
                return (link.getStart().equals(visited.getLast()) ? link.getFinish() : link.getStart());
            }
        }
//        Link selected = links.get(random.nextInt(links.size()));
//        selected.setPopularity(selected.getPopularity()+0.1f);
//        return (selected.getStart().equals(visited.getLast()) ? selected.getFinish() : selected.getStart());
        return null;
    }
    public Ant(Node initial) {
        visited = new ArrayList<>();
        visited.add(initial);
        path = new ArrayList<>();
        totalDistanceTraveled = 0f;
    }

    public List<Link> getPath() {
        return path;
    }

    public float getTotalDistanceTraveled() {
        return totalDistanceTraveled;
    }

    @Override
    public String toString() {
        return this.visited.toString() + " " + getTotalDistanceTraveled();
    }
}
