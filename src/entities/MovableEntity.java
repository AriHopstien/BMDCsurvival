package entities;

import engine.Time;

import java.awt.image.BufferedImage;

public abstract class MovableEntity extends Entity {

    protected float speed;
    protected float dx, dy;

    // בנאי: מקבל רק מיקום, מהירות ותמונה. (בלי גודל!)
    public MovableEntity(float x, float y, BufferedImage sprite) {

        // קורא לבנאי של Entity שכתבת למעלה ך
        // הוא מעביר לו רק את מה שהוא צריך: x, y, sprite
        super(x, y, sprite);

        this.speed = 3;
        this.dx = 0;
        this.dy = 0;
    }

    public void move() {
        x += dx * speed* (float) Time.deltaTime;
        y += dy * speed* (float)Time.deltaTime;
    }

    // פונקציות עזר לכיוונים
    public void setDx(float dx) { this.dx = dx; }
    public void setDy(float dy) { this.dy = dy; }
    public float getSpeed() { return speed; }
}
