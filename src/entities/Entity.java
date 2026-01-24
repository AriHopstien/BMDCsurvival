package entities;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    protected float x, y;
    protected static  int width = 64;
    protected static  int height = 64;
    public BufferedImage sprite;
    Rectangle bounds;
    public Entity(float x, float y, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        bounds = new Rectangle(0,0 ,width, height);
    }
    public void Render(Graphics G) {
        if (sprite != null) {
            G.drawImage(sprite, (int) x, (int) y, width, height, null);
        }
    }
    public Rectangle getBounds (){
        bounds.x = (int) x + 5;
        bounds.y = (int) y + (height / 2);
        bounds.width = width - 10;
        bounds.height = height / 2;

        return bounds;
    }
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }



}
