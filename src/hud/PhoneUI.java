package hud;

import java.awt.*;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.List;

import entities.Player;
import util.Assets;

public class PhoneUI {

    private Player player;

    private boolean open = false;
    private int selectedMessageIndex = 0;

    private int unreadCount = 0;

    // מיקום וגודל (יחסי למסך)
    private final int phoneIconX = 760;
    private final int phoneIconY = 520;
    private final int phoneIconSize = 48;

    private final int phoneWindowX = 200;
    private final int phoneWindowY = 120;
    private final int phoneWindowWidth = 400;
    private final int phoneWindowHeight = 300;

    public PhoneUI(Player player) {
        this.player = player;
    }

    /* ================= לוגיקה ================= */

    public void update(double deltaTime) {
        // כרגע אין אנימציות
    }

    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_P) {
            toggle();
        }

        if (!open) return;

        if (keyCode == KeyEvent.VK_UP) {
            selectedMessageIndex--;
            if (selectedMessageIndex < 0) {
                selectedMessageIndex = 0;
            }
        }

        if (keyCode == KeyEvent.VK_DOWN) {
            selectedMessageIndex++;
            List<PhoneMessage> msgs = player.getPhoneMessages();
            if (selectedMessageIndex >= msgs.size()) {
                selectedMessageIndex = msgs.size() - 1;
            }
        }
    }

    public void keyReleased(int keyCode) {}

    private void toggle() {
        open = !open;
        player.setPhoneOpen(open);

        if (open) {
            unreadCount = 0;
        }
    }

    /* ================= הודעות ================= */

    public void addMessage(String sender, String text) {
        player.addMessage(sender, text);
        unreadCount++;
    }

    public boolean isOpen() {
        return open;
    }

    /* ================= ציור ================= */

    public void render(Graphics2D g) {
        drawPhoneIcon(g);

        if (open) {
            drawPhoneWindow(g);
        }
    }

    private void drawPhoneIcon(Graphics2D g) {
        g.drawImage(Assets.phoneIcon, phoneIconX, phoneIconY, phoneIconSize, phoneIconSize, null);

        if (unreadCount > 0) {
            g.setColor(Color.RED);
            g.fillOval(phoneIconX + phoneIconSize - 14, phoneIconY - 4, 18, 18);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 12));
            g.drawString(String.valueOf(unreadCount),
                    phoneIconX + phoneIconSize - 9,
                    phoneIconY + 10);
        }
    }

    private void drawPhoneWindow(Graphics2D g) {
        // רקע
        g.setColor(new Color(20, 20, 20, 230));
        g.fillRoundRect(phoneWindowX, phoneWindowY,
                phoneWindowWidth, phoneWindowHeight, 20, 20);

        // כותרת
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("הודעות", phoneWindowX + 20, phoneWindowY + 30);

        List<PhoneMessage> msgs = player.getPhoneMessages();
        if (!msgs.isEmpty()) {
            PhoneMessage msg = msgs.get(selectedMessageIndex);

            // שולח
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.drawString(msg.sender, phoneWindowX + 20, phoneWindowY + 70);

            // תוכן
            g.setFont(new Font("Arial", Font.PLAIN, 14));
            drawMultilineText(g, msg.text,
                    phoneWindowX + 20,
                    phoneWindowY + 95,
                    phoneWindowWidth - 40);
        } else {
            g.setFont(new Font("Arial", Font.PLAIN, 14));
            g.drawString("אין הודעות", phoneWindowX + 20, phoneWindowY + 80);
        }
    }

    private void drawMultilineText(Graphics2D g, String text, int x, int y, int maxWidth) {
        String[] words = text.split(" ");
        String line = "";
        int lineHeight = g.getFontMetrics().getHeight();

        for (String word : words) {
            String testLine = line + word + " ";
            int lineWidth = g.getFontMetrics().stringWidth(testLine);

            if (lineWidth > maxWidth) {
                g.drawString(line, x, y);
                line = word + " ";
                y += lineHeight;
            } else {
                line = testLine;
            }
        }
        g.drawString(line, x, y);
    }

}
