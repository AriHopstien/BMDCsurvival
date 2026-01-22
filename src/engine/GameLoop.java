package engine;

public class GameLoop implements Runnable {

    private boolean running = true;
    private int targetFPS;
    private double frameTime;

    public GameLoop(int targetFPS) {
        setTargetFPS(targetFPS);
    }

    public void setTargetFPS(int fps) {
        this.targetFPS = fps;
        this.frameTime = fps > 0 ? 1.0 / fps : 0;
    }

    @Override
    public void run() {
        while (running) {
            long start = System.nanoTime();

            Time.update();
            update();
            render();

            if (targetFPS > 0) {
                long sleepTime =
                        (long)((frameTime - Time.deltaTime) * 1_000_000_000);

                if (sleepTime > 0) {
                    try {
                        Thread.sleep(sleepTime / 1_000_000);
                    } catch (InterruptedException ignored) {}
                }
            }
        }
    }

    private void update() {
        // game logic
    }

    private void render() {
        // repaint / buffer swap
    }
}
