package ui;

import engine.InputManager;
import main.Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class GameOverScreen extends Screen {

    private Game game;
    private int lossType;      // סוג ההפסד
    private String message;    // הטקסט שיוצג

    private int screenWidth = 1280;
    private int screenHeight = 720;

    private long enterDelayStartTime;

    public GameOverScreen(Game game, InputManager input, int lossType) {
        super(input);
        this.game = game;
        this.lossType = lossType;

        // הגדרת הטקסט לפי סוג ההפסד
        switch (lossType) {
            case 0:
                message = "פסלת במשימה! אל תדאג, נסה שוב.";
                break;
            case 1:
                message = "התקרית הסתיימה בכישלון. חזור והיה זהיר יותר.";
                break;
            case 2:
                message = "הפסדת את המשחק! למדו מהטעויות שלך.";
                break;
        }
    }

    @Override
    public void onEnter() {
        enterDelayStartTime = System.currentTimeMillis();
    }

    @Override
    public void update(double deltaTime) {
        // אפשר להוסיף עדכונים כמו טיימר או אנימציות
    }

    @Override
    public void render(Graphics2D g) {
        // רקע שחור
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, screenWidth, screenHeight);

        // טקסט ההפסד
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        g.drawString("Game Over", 450, 200);

        // ההודעה הייחודית
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 28));
        g.drawString(message, 200, 300);

        // הוראות חזרה לתפריט
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("לחץ על ENTER כדי לחזור לתפריט הראשי", 400, 500);
    }

    @Override
    public void handleInput(InputManager input) {
        if (System.currentTimeMillis() - enterDelayStartTime < 500) {
            return;
        }
        if (input.ENTER_key) {
            game.setScreen(new MainMenuScreen(game, input));
        }
    }
}
