

package ui;

import engine.InputManager;
import main.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class ExplanationScreen extends Screen {

    private Game game;

    private long enterDelayStartTime;

    private int screenWidth = 1280;
    private int screenHeight = 720;

    private String title;
    private String[] lines;

    public ExplanationScreen(Game game,InputManager input) {
        super(input);
        this.game = game;
    }

    @Override
    public void onEnter() {
        super.onEnter();
        enterDelayStartTime = System.currentTimeMillis();
        title = "הסבר על המשחק";

        lines = new String[] {
                "מטרה:",
                "לעבור יום בישיבה בלי להפקיד את הטלפון בבוקר.",
                "",
                "שליטה:",
                "חצים - תנועה",
                "E - אינטראקציה",
                "P - פתיחה/סגירה של הטלפון",
                "",
                "טיפים:",
                "אל תשתמש בטלפון ליד הרב מילר.",
                "משימות מסוימות משפיעות על ההמשך.",
                "",
                "ESC - חזרה לתפריט הראשי"
        };
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, screenWidth, screenHeight);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString(title, 470, 120);

        g.setFont(new Font("Arial", Font.PLAIN, 26));
        int startX = 140;
        int startY = 200;
        int lineHeight = 34;

        for (int i = 0; i < lines.length; i++) {
            g.drawString(lines[i], startX, startY + i * lineHeight);
        }

        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("ENTER - חזרה לתפריט", 560, 680);
    }

    public void handleInput(InputManager input) {
        if (System.currentTimeMillis() - enterDelayStartTime < 500) {
            return;
        }
        if (input.ENTER_key && canPressEnter()) {
            game.setScreen(new MainMenuScreen(game, input));
        }
    }

}