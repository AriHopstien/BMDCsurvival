package hud;

public class MassageBoxTop {
    package ui.hud;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;



        private String text = "";
        private boolean visible = false;

        private double timer = 0;
        private final double DISPLAY_TIME = 3.0; // שניות

        // מיקום וגודל
        private final int x = 40;
        private final int y = 20;
        private final int width = 720;
        private final int height = 50;

        public void show(String text) {
            this.text = text;
            this.visible = true;
            this.timer = DISPLAY_TIME;
        }

        public void update(double deltaTime) {
            if (!visible) return;

            timer -= deltaTime;
            if (timer <= 0) {
                visible = false;
            }
        }

        public void render(Graphics2D g) {
            if (!visible) return;

            // רקע
            g.setColor(new Color(0, 0, 0, 180));
            g.fillRoundRect(x, y, width, height, 20, 20);

            // טקסט
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString(text, x + 20, y + 32);
        }

        public boolean isVisible() {
            return visible;
        }
}


