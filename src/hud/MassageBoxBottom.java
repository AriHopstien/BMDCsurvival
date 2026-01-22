package hud;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class MassageBoxBottom {

    private String text = "";
    private boolean visible = false;

    // מיקום וגודל
    private final int width = 720;
    private final int height = 40;
    private final int x = 40;
    private final int y = 520; // תחתית המסך (למסך 600px)

    public void show(String text) {
        this.text = text;
        this.visible = true;
    }

    public void hide() {
        this.visible = false;
    }

    public void update(double deltaTime) {
        // אין טיימר – נשאר עד שמוחבא
    }

    public void render(Graphics2D g) {
        if (!visible) return;

        // רקע
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRoundRect(x, y, width, height, 20, 20);

        // טקסט
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString(text, x + 20, y + 26);
    }

    public boolean isVisible() {
        return visible;
    }
}