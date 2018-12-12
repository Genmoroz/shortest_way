package com.rgr.shortest_way;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vertex {

    private Vertex parent;
    private int generalDistance;
    private int id;
    private int x;
    private int y;
    private List<Vertex> relations = new ArrayList<>();


    Vertex(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public void addRelation(Vertex vertex) {
        if (Objects.equals(vertex, this) || Objects.isNull(vertex) || relations.contains(vertex)) return;
        relations.add(vertex);
    }

    private Integer calculateDistance(Vertex vertex) {
        return (int) Point2D.distance(vertex.x, vertex.y, x, y);
    }

    public Vertex getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "parentid=" + (Objects.nonNull(parent) ? parent.id : "null") +
                ", generalDistance=" + generalDistance +
                ", id=" + id +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public static void main(String... args) {
        ArrayList<Vertex> vertexes = new ArrayList<>();

        //Добавление вершин в массив
        for (int i = 0; i < 1000; i++) {
            int x = (int) (Math.random() * 1000) % 500;
            int y = (int) (Math.random() * 1000) % 500;
            vertexes.add(new Vertex(i, x, y));
        }

        for (int i = 0; i < vertexes.size(); i++)
            vertexes.get(i).addRelation( i != 0 ? vertexes.get(i - 1) : null );

        //Добавление связей
        for (int i = 0; i < 4000; i++) {
            int id1 = (int) (Math.random() * 1000) % 999;
            int id2 = (int) (Math.random() * 1000) % 999;
            if (vertexes.get(id1).relations.stream().noneMatch(var -> var.id == id2)) {
                vertexes.get(id1).addRelation(vertexes.get(id2));
                vertexes.get(id2).addRelation(vertexes.get(id1));
            }
        }
//        Vertex vertex1 = new Vertex(1, 100, 100);
//        Vertex vertex2 = new Vertex(2, 200, 200);
//        Vertex vertex3 = new Vertex(3, 300, 300);
//
//        vertexes.add(vertex1);
//        vertexes.add(vertex2);
//        vertexes.add(vertex3);
//
//        vertex1.addRelation(vertex2);
//        vertex2.addRelation(vertex3);
//        vertex3.addRelation(vertex2);


        List<Vertex> path = findShortestWay(vertexes.get(0), 10);

        path.forEach(System.out::println);

    }

    static List<Vertex> findShortestWay(Vertex vertex, int endId) {

        if (Objects.equals(vertex.id, endId)) return getPath(vertex);

        for (Vertex relatedVertex : vertex.relations) {
            int generalDistance = vertex.generalDistance + relatedVertex.calculateDistance(vertex);
            if (relatedVertex.generalDistance == 0 || generalDistance <= relatedVertex.generalDistance) {
                relatedVertex.generalDistance = generalDistance;
                relatedVertex.parent = vertex;
                return findShortestWay(relatedVertex, endId);
            }
        }
        return new ArrayList<>();
    }

    public static List<Vertex> getPath(Vertex endPoint) {
        List<Vertex> path = new ArrayList<>();
        Vertex head = endPoint;
        while (Objects.nonNull(head)) {
            path.add(head);
            head = head.getParent();
        }
        return path;
    }
}

