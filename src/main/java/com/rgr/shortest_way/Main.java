package com.rgr.shortest_way;

import com.rgr.shortest_way.algorythm.ShortestWayFinder;
import com.rgr.shortest_way.vertex.Vertex;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ArrayList<Vertex> vertexes = new ArrayList<>();

        //Добавление вершин в массив
        for (int i = 0; i < 1000; i++) {
            int x = (int) (Math.random() * 1000) % 500;
            int y = (int) (Math.random() * 1000) % 500;
            vertexes.add(new Vertex(i, x, y));
        }

        for (int i = 0; i < vertexes.size(); i++)
            vertexes.get(i).addRelation(i != 0 ? vertexes.get(i - 1) : null);

        //Добавление связей
        for (int i = 0; i < 4000; i++) {
            int id1 = (int) (Math.random() * 1000) % 999;
            int id2 = (int) (Math.random() * 1000) % 999;
            if (vertexes.get(id1).getRelations().stream().noneMatch(var -> var.getId() == id2)) {
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


        List<Vertex> path = ShortestWayFinder.find(vertexes.get(0), 10);

        path.forEach(System.out::println);


    }
}
