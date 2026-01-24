package entities;

import ai.MovementAI;
import world.GameWorld;
import java.awt.*;
import java.awt.image.BufferedImage;
import static engine.Time.deltaTime;

public abstract class NPC extends MovableEntity {

    protected float visionRange = 200f;  // טווח ראייה כללי
    protected boolean alert = false;     // להראות סימן אזהרה מעל הראש
    protected MovementAI movementAI;

    public NPC(float x, float y, BufferedImage sprite) {
        super(x, y, sprite);
    }

    public void setMovementAI(MovementAI ai) {
        this.movementAI = ai;
    }

    public void update(GameWorld world) {
        if (movementAI != null) {
            movementAI.update(this, world);
        }
        move(deltaTime);
    }

    public void moveTowards(float dx, float dy) {
        float len = (float) Math.sqrt(dx * dx + dy * dy);
        if (len == 0) {
            stop();
            return;
        }
        setDx(dx / len);
        setDy(dy / len);
    }

    public void stop() {
        setDx(0);
        setDy(0);
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
