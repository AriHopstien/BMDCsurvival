package engine;

public class Time {
    private static long lastTime = System.nanoTime();
    public static double deltaTime = 0;

    public static void update() {
        long now = System.nanoTime();
        deltaTime = (now - lastTime) / 1_000_000_000.0;
        lastTime = now;
    }
}
