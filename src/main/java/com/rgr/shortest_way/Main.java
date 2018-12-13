package com.rgr.shortest_way;

import com.rgr.shortest_way.vertex.Vertex;
import com.rgr.shortest_way.window.Window;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Vertex> vertexes = new ArrayList<>();

        //Добавление вершин в массив
//        for (int i = 0; i < 500; i++) {
//            int x = (int) (Math.random() * 1000) % 500;
////            vertexes.add(new Vertex(i, x, y));
//        }
//
//        for (int i = 0; i < vertexes.size(); i++)
//            vertexes.get(i).addRelation(i != 0 ? vertexes.get(i - 1) : null);
//
//        //Добавление связей
//        for (int i = 0; i < 4000; i++) {
//            int id1 = (int) (Math.random() * 1000) % 500;
//            int id2 = (int) (Math.random() * 1000) % 500;
//            if (vertexes.get(id1).getRelations().stream().noneMatch(var -> var.getId() == id2)) {
//                vertexes.get(id1).addRelation(vertexes.get(id2));
//                vertexes.get(id2).addRelation(vertexes.get(id1));
//            }
//        }
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


//        List<Vertex> path = ShortestWayFinder.find(vertexes.get(0), 10);
//
//        path.forEach(System.out::println);


        Window visualizer = new Window();
//
//        ShortestWayFinder.find(vertex1, vertex3.getId()).forEach(System.out::println);

//            List<Vertex> vertices = VerticesFactory.newInstance().createVertices(10, 600);
//
//        vertices.forEach(System.out::println);
//
//            Vertex start = vertices.get((int) ((Math.random() * 1000) % vertices.size()));
//            start.setState(Vertex.State.START);
//            Vertex end = vertices.get((int) ((Math.random() * 1000) % vertices.size()));
//            while (end.getColor() == Vertex.State.START.getColor())
//                end = vertices.get((int) ((Math.random() * 1000) % vertices.size()));
//
//            end.setState(Vertex.State.FINISH);
//
//        vertices = new ArrayList<>();
//        Vertex vertex1 = new Vertex(1, 100, 100);
//        Vertex vertex2 = new Vertex(2, 200, 200);
//        Vertex vertex3 = new Vertex(3, 300, 300);
//
//        vertices.add(vertex1);
//        vertices.add(vertex2);
//        vertices.add(vertex3);
//
//        vertex1.addRelation(vertex2);
//        vertex2.addRelation(vertex3);

//            ShortestWayFinder.find(start, end.getId()).forEach(System.out::println);


    }
}