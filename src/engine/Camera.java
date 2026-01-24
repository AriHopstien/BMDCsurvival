package engine;

import entities.Entity;

public class Camera {
    private float x, y;
    private int screenWidth, screenHeight;

    public Camera(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.x = 0;
        this.y = 0;
    }

    public void update(Entity target) {
        // חישוב המיקום כך שהשחקן (target) יהיה בדיוק באמצע המסך
        // אנחנו לוקחים את מיקום השחקן ומחסירים חצי מהמסך
        this.x = target.getX() - (screenWidth / 2f) + (target.getWidth() / 2f);
        this.y = target.getY() - (screenHeight / 2f) + (target.getHeight() / 2f);

        // כאן אפשר להוסיף חסימה (Boundary Check) כדי שהמצלמה לא תראה "חלל שחור"
        // מחוץ למפה:
        // if (x < 0) x = 0;
        // if (y < 0) y = 0;
    }

    public float getX() { return x; }
    public float getY() { return y; }

    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
}