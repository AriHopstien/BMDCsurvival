package entities;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class NPC extends MovableEntity {

    protected float visionRange = 200f;  // טווח ראייה כללי
    protected boolean alert = false;     // להראות סימן אזהרה מעל הראש

    public NPC(float x, float y, BufferedImage sprite) {
        super(x, y, sprite);
    }

    // עדכון NPC – NPC עצמו לא מחליט על התנועה, move() מבוסס על dx/dy שנקבעו חיצונית
    public void update() {
        move();
    }

    // בדיקה אם NPC "רואה" יעד כללי
    public boolean canSee(Entity target) {
        float dx = target.getX() - x;
        float dy = target.getY() - y;
        return (dx * dx + dy * dy) <= visionRange * visionRange;
    }

    // להפעיל או לכבות סימן אזהרה
    public void setAlert(boolean value) {
        alert = value;
    }

    @Override
    public void Render(Graphics g) {
        super.Render(g);
        if (alert) {
            g.setColor(Color.RED);
            g.drawString("!", (int) x + width / 2, (int) y - 8);
        }
    }
}
