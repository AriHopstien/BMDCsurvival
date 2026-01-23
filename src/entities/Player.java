package entities;

import hud.PhoneMessage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player extends MovableEntity {

    private String name;

    // טלפון
    private boolean phoneOpen = false;
    private List<PhoneMessage> phoneMessages;

    public Player(float x, float y, BufferedImage sprite, String name) {
        super(x, y, sprite);
        this.name = name;
        this.phoneMessages = new ArrayList<>();
    }

    // עדכון כללי של השחקן
    public void update() {
        move();  // זז לפי dx/dy
    }

    // אינטראקציה עם העולם / אובייקטים
    public void interact(String action) {
        // אפשר למלא בעתיד
    }

    // טלפון
    public void openPhone() {
        phoneOpen = true;
    }

    public void closePhone() {
        phoneOpen = false;
    }

    public void setPhoneOpen(boolean open) {
        this.phoneOpen = open;
    }

    public boolean isPhoneOpen() {
        return phoneOpen;
    }

    // הודעות
    public void addMessage(String sender, String text) {
        phoneMessages.add(new PhoneMessage(sender, text));
    }

    public List<PhoneMessage> getPhoneMessages() {
        return phoneMessages;
    }

    // getters & setters
    public String getName() {
        return name;
    }

    @Override
    public void Render(Graphics g) {
        super.Render(g);
        // סימן קטן אם הטלפון פתוח
        if (phoneOpen) {
            g.setColor(Color.CYAN);
            g.drawRect((int)x, (int)y - 10, width, 5);
        }
    }
}
