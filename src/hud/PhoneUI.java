package hud;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.List;

import engine.InputManager;
import entities.Player;

public class PhoneUI {

    private Player player;
    private BufferedImage phoneIcon;
    private int selectedMessageIndex = 0;

    // מיקום וגודל
    private final int phoneIconX = 1175;
    private final int phoneIconY = 590;
    private final int phoneIconSize = 128;

    private final int phoneWindowX = 1000;
    private final int phoneWindowY = 590;
    private final int phoneWindowWidth = 150;
    private final int phoneWindowHeight = 100;

    public PhoneUI(Player player) {
        this.player = player;
        try {
            phoneIcon = ImageIO.read(getClass().getResource("/hud/phon.png"));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void update(double deltaTime) {
        // לוגיקת עדכון אם תהיה בעתיד
    }

    public void handleInput(InputManager input) {
        // אם הטלפון לא פתוח בשחקן, אין מה לטפל בקלט פה
        if (!player.isPhoneOpen()) return;

        List<PhoneMessage> messages = player.getPhoneMessages();
        if (messages.isEmpty()) return;

        // מעבר בין הודעות בעזרת ה-InputManager החדש (מונע דפדוף מהיר מדי)
        if (input.W_key) {
            selectedMessageIndex--;
            if (selectedMessageIndex < 0) selectedMessageIndex = 0;
        }

        if (input.S_Key) {
            selectedMessageIndex++;
            if (selectedMessageIndex >= messages.size()) {
                selectedMessageIndex = messages.size() - 1;
            }
        }
    }

    public void render(Graphics2D g) {
        drawPhoneIcon(g);

        if (player.isPhoneOpen()) {
            drawPhoneWindow(g);
        }
    }

    private void drawPhoneIcon(Graphics2D g) {
        if (phoneIcon != null) {
            g.drawImage(phoneIcon, phoneIconX, phoneIconY, phoneIconSize, phoneIconSize, null);
        }

        int unread = player.getUnreadCount();
        if (unread > 0) {
            g.setColor(Color.RED);
            g.fillOval(phoneIconX + phoneIconSize - 14, phoneIconY - 4, 18, 18);

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 12));
            g.drawString(String.valueOf(unread), phoneIconX + phoneIconSize - 9, phoneIconY + 10);
        }
    }

    private void drawPhoneWindow(Graphics2D g) {
        List<PhoneMessage> messages = player.getPhoneMessages();

        // רקע חלון הטלפון
        g.setColor(new Color(20, 20, 20, 230));
        g.fillRoundRect(phoneWindowX, phoneWindowY, phoneWindowWidth, phoneWindowHeight, 20, 20);

        // כותרת
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("הודעות", phoneWindowX + 20, phoneWindowY + 30);

        if (!messages.isEmpty()) {
            // מוודא שהאינדקס לא חורג (למשל אם נמחקה הודעה)
            if (selectedMessageIndex >= messages.size()) selectedMessageIndex = messages.size() - 1;

            PhoneMessage msg = messages.get(selectedMessageIndex);

            // שולח
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.setColor(Color.CYAN);
            g.drawString(msg.sender, phoneWindowX + 20, phoneWindowY + 70);

            // תוכן
            g.setFont(new Font("Arial", Font.PLAIN, 14));
            g.setColor(Color.WHITE);
            drawMultilineText(g, msg.text, phoneWindowX + 20, phoneWindowY + 95, phoneWindowWidth - 40);

            // חיווי על מיקום ברשימה
            g.setFont(new Font("Arial", Font.ITALIC, 12));
            g.drawString((selectedMessageIndex + 1) + " / " + messages.size(), phoneWindowX + phoneWindowWidth - 60, phoneWindowY + 30);
        } else {
            g.setFont(new Font("Arial", Font.PLAIN, 14));
            g.drawString("אין הודעות חדשות", phoneWindowX + 20, phoneWindowY + 80);
        }
    }

    private void drawMultilineText(Graphics2D g, String text, int x, int y, int maxWidth) {
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        int lineHeight = g.getFontMetrics().getHeight();

        for (String word : words) {
            String testLine = line + word + " ";
            int lineWidth = g.getFontMetrics().stringWidth(testLine);

            if (lineWidth > maxWidth) {
                g.drawString(line.toString(), x, y);
                line = new StringBuilder(word + " ");
                y += lineHeight;
            } else {
                line.append(word).append(" ");
            }
        }
        g.drawString(line.toString(), x, y);
    }
}