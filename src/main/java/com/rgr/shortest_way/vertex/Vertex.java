package com.rgr.shortest_way.vertex;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class Vertex {

    @Setter
    private Vertex parent;
    @Setter
    private int generalDistance;
    private int id;
    private int x;
    private int y;

    private List<Vertex> relations = new ArrayList<>();

    public Vertex(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics2D g2d) {
        g2d.drawOval(x, y, 1, 1);
    }

    public void addRelation(Vertex vertex) {
        if (Objects.equals(vertex, this) || Objects.isNull(vertex) || relations.contains(vertex)) return;
        relations.add(vertex);
    }

    public Integer calculateDistance(Vertex vertex) {
        return (int) Point2D.distance(vertex.x, vertex.y, x, y);
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "parentId=" + (Objects.nonNull(parent) ? parent.id : "null") +
                ", generalDistance=" + generalDistance +
                ", id=" + id +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}

