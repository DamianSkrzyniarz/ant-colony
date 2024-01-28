package pl.dskrzyniarz;


public class Link {
    private Node start;
    private Node finish;
    private float distance;

    public Link(Node start, Node finish) {
        this.start = start;
        this.finish = finish;
        float widthDifference = Math.abs(start.getWidth() - finish.getWidth());
        float heightDifference = Math.abs(start.getHeight() - finish.getHeight());
        this.distance = (float)Math.sqrt(heightDifference*heightDifference + widthDifference*widthDifference);
        start.addLink(this);
        finish.addLink(this);
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getFinish() {
        return finish;
    }

    public void setFinish(Node finish) {
        this.finish = finish;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return start.getId() + "-" + finish.getId() + ": " + distance;
    }
}