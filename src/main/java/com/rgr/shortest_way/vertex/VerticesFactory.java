package com.rgr.shortest_way.vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VerticesFactory {

    private List<Vertex> vertices;

    private VerticesFactory() {}

    public static VerticesFactory newInstance() {
        return new VerticesFactory();
    }

    public List<Vertex> createVertices(int size, int radius) {
        if (size <= 0)
            throw new IllegalArgumentException();

        vertices = new ArrayList<>();

        int scale = (int) (radius / Math.sqrt(size));
        int index = 0;
        for (int x = 0; x < radius / scale; x++)
            for (int y = 0; y < radius / scale; y++)
                vertices.add(new Vertex(index++, randomValue(scale * x, scale * x + scale), randomValue(scale * y, scale * y + scale)));

        vertices.add(new Vertex(index, randomValue(0, radius), randomValue(0, radius)));

        vertices.forEach(
                v -> findRelations(v, scale)
        );

        return vertices;
    }

    private <T extends Vertex> void findRelations(T vertex, int radius) {
        if (vertices.size() <= 1)
            throw new IllegalArgumentException("Relationships cannot be built in empty list.");
        vertex.setRadiusOfRelation((int) (radius * 1.5));
        for (Vertex v : vertices) {
            if (vertex.calculateDistance(v) <= radius * 1.5) {
                vertex.addRelation(v);
                v.addRelation(vertex);
            }
        }
    }

    private int randomValue(int start, int end) {
        Random random = new Random();
        return random.nextInt(end - start) + start;
    }

}