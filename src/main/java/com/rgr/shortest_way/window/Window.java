package com.rgr.shortest_way.window;

import com.google.common.base.Stopwatch;
import com.rgr.shortest_way.algorythm.ShortestWayFinder;
import com.rgr.shortest_way.vertex.Vertex;
import com.rgr.shortest_way.vertex.VerticesFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.rgr.shortest_way.algorythm.ShortestWayFinder.path;

public class Window extends JFrame {

    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int COUNT_THREAD = 1;

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

        List<Vertex> way = null;
        try {
            way = findWay();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (Objects.nonNull(way) && way.size() != 0) way.get(0).paintWayToParent(g2d);
        vertices.forEach(
                vertex -> vertex.paint(g2d)
        );
        g.drawImage(buff, 2, 28, WIDTH - 4, HEIGHT - 35, null);
    }

    private List<Vertex> findWay() throws InterruptedException {
        vertices = VerticesFactory.newInstance().createVertices(SIZE, WIDTH);

        Vertex start = vertices.get(0);
        start.setColor(Color.GREEN);
        Vertex end = vertices.get(vertices.size() - 1);

        end.setColor(Color.BLUE);

        ShortestWayFinder wayFinder = new ShortestWayFinder();

        List<List<Vertex>> splits = splitList(vertices, COUNT_THREAD);

        Stopwatch timePoint = Stopwatch.createStarted();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < splits.size(); i++)
            threads.add(new Thread(() -> {
                wayFinder.find(start, end.getId());
            }));
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
        timePoint.stop();
        System.out.println("Thread count: " + splits.size() + " : " + timePoint.elapsed(TimeUnit.MILLISECONDS) + "ms");
        return path(wayFinder.getVertex());
    }

    private void cleanWindow(Graphics2D g2d) {
        g2d.setColor(new Color(236, 236, 236));
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    private List<List<Vertex>> splitList(List<Vertex> list, int count) {

        if (list.size() % 2 != 0)
            list.add(null);
        int step = list.size() / count;

        List<List<Vertex>> splits = new ArrayList<>();
        for (int i = 0, index = 0; i < count; i++) {
            splits.add(new ArrayList<>());
            for (int y = 0; y < step; y++, index++)
                splits.get(i).add(list.get(index));
        }
        list.remove(null);
        return splits;
    }
}
