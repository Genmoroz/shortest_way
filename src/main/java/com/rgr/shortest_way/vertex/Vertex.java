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

    private Vertex parent;
    @Setter
    private int generalDistance;
    @Setter
    private int radiusOfRelation;
    private int id;
    private int x;
    private int y;

    @Setter
    private Color color = Color.BLACK;

    private List<Vertex> relations = new ArrayList<>();

    public Vertex(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics2D g2d) {

        g2d.setStroke(new BasicStroke(0.1f));
        g2d.setColor(new Color(47, 40, 40));
        for (Vertex vertex : relations) {
            g2d.drawLine(x, y, vertex.x, vertex.y);
        }
        g2d.setColor(color);
        g2d.fillOval(x - 3, y - 3, 6, 6);
    }

    public void paintWayToParent(Graphics2D g2d) {
        if (Objects.isNull(parent))
            return;
        g2d.setStroke(new BasicStroke(2.1f));
        g2d.setColor(Color.RED);
        g2d.drawLine(x, y, parent.x, parent.y);
        parent.paintWayToParent(g2d);
    }

    public void addRelation(Vertex vertex) {
        if (Objects.equals(vertex, this) || Objects.isNull(vertex)) return;
        relations.add(vertex);
    }

    public Integer calculateDistance(Vertex vertex) {
        return (int) Point2D.distance(vertex.x, vertex.y, x, y);
    }

    public void setParent(Vertex parent) {
        if (Objects.equals(this, parent) || Objects.equals(this.parent, parent))
            return;
        this.parent = parent;
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

