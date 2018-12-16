package com.rgr.shortest_way.algorythm;

import com.rgr.shortest_way.vertex.Vertex;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShortestWayFinder {

    @Getter
    private volatile Vertex vertex = null;

    public synchronized void find(Vertex vertex, int endId) {

        if (Objects.equals(vertex.getId(), endId)) {
            this.vertex = vertex;
            return;
        }

        for (Vertex relatedVertex : vertex.getRelations()) {
            int generalDistance = vertex.getGeneralDistance() + relatedVertex.calculateDistance(vertex);
            if (relatedVertex.getGeneralDistance() == 0 || generalDistance < relatedVertex.getGeneralDistance()) {
                if (path(vertex.getParent()).contains(relatedVertex))
                    continue;
                relatedVertex.setGeneralDistance(generalDistance);
                relatedVertex.setParent(vertex);
                find(relatedVertex, endId);
            }
        }
    }

    public synchronized static List<Vertex> path(Vertex vertex) {
        List<Vertex> path = new ArrayList<>();
        Vertex head = vertex;
        while (Objects.nonNull(head)) {
            path.add(head);
            head = head.getParent();
        }
        return path;
    }
}