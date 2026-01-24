package entities;

import engine.Time;
import java.awt.image.BufferedImage;

public abstract class MovableEntity extends Entity {

    protected float speed;
    protected float dx, dy;


    public MovableEntity(float x, float y, BufferedImage sprite) {
        super(x, y, sprite);
        this.speed = 4.0f;
        this.dx = 0;
        this.dy = 0;
    }

    public void move(double deltaTime) {
        // יצירת משתני עזר לנרמול כדי לא להרוס את הערכים המקוריים של dx/dy
        float moveX = dx;
        float moveY = dy;
        // בדיקה: האם יש תנועה אלכסונית?
        if (moveX != 0 && moveY != 0) {
            // חישוב אורך הווקטור (לפי משפט פיתגורס)
            float length = (float) Math.sqrt(moveX * moveX + moveY * moveY);

            // חילוק הכיוון באורך שלו כדי לקבל ווקטור באורך 1
            moveX /= length;
            moveY /= length;
        }
        // ביצוע התנועה הסופית
        x += moveX * speed * (float) Time.deltaTime;
        y += moveY * speed * (float) Time.deltaTime;
    }

    // Getters & Setters
    public void setDx(float dx) { this.dx = dx; }
    public void setDy(float dy) { this.dy = dy; }
    public float getDx() { return dx; }
    public float getDy() { return dy; }

    public float getSpeed() { return speed; }

    // אנחנו צריכים גם אפשרות לעדכן את המיקום ישירות מה-World
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
}