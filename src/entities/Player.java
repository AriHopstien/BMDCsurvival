package entities;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player extends MovableEntity {

    private String name;

    // טלפון
    private boolean phoneOpen = false;
    private List<String> currentMessages;

    public Player(float x, float y, BufferedImage sprite, String name) {
        super(x, y, sprite);
        this.name = name;
        this.currentMessages = new ArrayList<>();
    }

    // עדכון כללי של השחקן
    public void update() {
        move();  // זז לפי dx/dy
        // אפשר להוסיף כאן בדיקות פיזיקה/התנגשות בעתיד
    }

    // אינטראקציה עם העולם / אובייקטים
    public void interact(String action) {

    }

    // טלפון
    public void openPhone() {
        phoneOpen = true;

    }

    public void closePhone() {
        phoneOpen = false;

    }

    public boolean isPhoneOpen() {
        return phoneOpen;
    }

    public void addMessage(String message) {
        currentMessages.add(message);
    }

    public List<String> getMessages() {
        return currentMessages;
    }

    // getters & setters
    public String getName() {
        return name;
    }

    @Override
    public void Render(Graphics g) {
        super.Render(g);
        // אפשר להוסיף HUD קטן או סימן שמראה אם הטלפון פתוח
        if (phoneOpen) {
            g.setColor(Color.CYAN);
            g.drawRect((int)x, (int)y - 10, width, 5);
        }
    }
}

