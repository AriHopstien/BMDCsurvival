package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    protected float x, y;
    protected int width, height; // הוסר ה-static
    public BufferedImage sprite;
    protected Rectangle bounds;

    public Entity(float x, float y, int width, int height, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
        this.bounds = new Rectangle(0, 0, width, height);
    }

    public void Render(Graphics g) {
        if (sprite != null) {
            g.drawImage(sprite, (int) x, (int) y, width, height, null);
        }
    }

    public Rectangle getBounds() {
        // הגדרת ה-Hitbox להיות החצי התחתון של הדמות (בשביל עומק)
        bounds.x = (int) x + 5;
        bounds.y = (int) y + (height / 2);
        bounds.width = width - 10;
        bounds.height = height / 2;
        return bounds;
    }

    // Getters and Setters
    public float getX() { return x; }
    public void setX(float x) { this.x = x; }
    public float getY() { return y; }
    public void setY(float y) { this.y = y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}