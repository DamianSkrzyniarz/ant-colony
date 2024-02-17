package pl.dskrzyniarz;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private float width;
    private float height;
    private char id;

    private static List<Node> globalNodeList;
    private final List<Link> linkList;

    public Node(char id, float width, float height) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.linkList = new ArrayList<>();
        if(globalNodeList == null){ //initialize node list on first node creation
            globalNodeList = new ArrayList<>();
        }
        for(Node node : globalNodeList){ //link to all existing nodes
            new Link(node, this);
        }
        globalNodeList.add(this); //add to node list
    }

    public static List<Node> getGlobalNodeList() {
        return globalNodeList;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public char getId() {
        return id;
    }

    public void setId(char id) {
        this.id = id;
    }

    public List<Link> getLinks() {
        return linkList;
    }

    public void addLink(Link link){
        linkList.add(link);
    }

    @Override
    public String toString() {
        return id + ": x=" + width + " y=" + height;
    }
}