

package main;
//hahaha
import engine.InputManager;
import ui.Screen;
import ui.MainMenuScreen;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Game extends JPanel implements Runnable {

    private Thread gameThread;
    private boolean running = false;

    public InputManager input;

    private Screen currentScreen;

    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    public Game() {
        input = new InputManager();
        addKeyListener(input);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setScreen(new MainMenuScreen(this,input));
        setFocusable(true);
        requestFocus();
    }

    public void start() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void setScreen(Screen newScreen) {
        if (currentScreen != null) {
            currentScreen.onExit();
        }
        currentScreen = newScreen;
        currentScreen.onEnter();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double nsPerUpdate = 1_000_000_000.0 / 60.0;

        while (running) {
            long now = System.nanoTime();
            double delta = (now - lastTime) / nsPerUpdate;
            lastTime = now;

            update(delta);
            repaint();

            try {
                Thread.sleep(16); // ~60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update(double deltaTime) {
        input.update();
        if (currentScreen != null) {
            currentScreen.update(deltaTime);
            currentScreen.handleInput(input);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, WIDTH, HEIGHT);

        if (currentScreen != null) {
            currentScreen.render(g2);
        }
    }
}

