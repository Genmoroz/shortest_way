package com.rgr.shortest_way.algorythm;

import com.rgr.shortest_way.vertex.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShortestWayFinder {

    public static List<Vertex> find(Vertex vertex, int endId) {

        if (Objects.equals(vertex.getId(), endId)) return getPath(vertex);

        for (Vertex relatedVertex : vertex.getRelations()) {
            int generalDistance = vertex.getGeneralDistance() + relatedVertex.calculateDistance(vertex);
            if (relatedVertex.getGeneralDistance() == 0 || generalDistance <= relatedVertex.getGeneralDistance()) {
                relatedVertex.setGeneralDistance(generalDistance);
                relatedVertex.setParent(vertex);
                return find(relatedVertex, endId);
            }
        }
        return new ArrayList<>();
    }

    private static List<Vertex> getPath(Vertex endPoint) {
        List<Vertex> path = new ArrayList<>();
        Vertex head = endPoint;
        while (Objects.nonNull(head)) {
            path.add(head);
            head = head.getParent();
        }
        return path;
    }
}