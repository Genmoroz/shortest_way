package com.rgr.shortest_way.window;

import com.rgr.shortest_way.algorythm.ShortestWayFinder;
import com.rgr.shortest_way.vertex.Vertex;
import com.rgr.shortest_way.vertex.VerticesFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;

import static com.rgr.shortest_way.algorythm.ShortestWayFinder.path;

public class Window extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;

    private static final int SIZE = 500;

    private List<Vertex> vertices;

    public Window() {
        super("Graph visualizer");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setVisible(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        Image buff = createImage(WIDTH, HEIGHT);
        Graphics2D g2d = (Graphics2D) buff.getGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        cleanWindow(g2d);

        List<Vertex> way = findWay();
      if (Objects.nonNull(way) && way.size() != 0) way.get(0).paintWayToParent(g2d);
        vertices.forEach(
                vertex -> vertex.paint(g2d)
        );
        g.drawImage(buff, 2, 28, WIDTH - 4, HEIGHT - 35, null);
    }

    private List<Vertex> findWay() {
        vertices = VerticesFactory.newInstance().createVertices(SIZE, WIDTH);

        Vertex start = vertices.get(0);
        start.setState(Color.GREEN);
        Vertex end = vertices.get(vertices.size() - 1);

        end.setState(Color.BLUE);

        ShortestWayFinder wayFinder = new ShortestWayFinder();
        wayFinder.find(start, end.getId());

        return path(wayFinder.getVertex());
    }

    private void cleanWindow(Graphics2D g2d) {
        g2d.setColor(new Color(236, 236, 236));
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
