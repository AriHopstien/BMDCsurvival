package ui;

import engine.InputManager;
import main.Game;
import ui.Screen;
import entities.Player;
import hud.HUD;
import world.GameWorld;
import engine.Camera;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class GameScreen extends Screen {

    private GameWorld world;
    private Player player;
    private HUD hud;
    private Camera camera;

    private final int screenWidth = 1280;
    private final int screenHeight = 720;

    public GameScreen(Game game, InputManager input) {
        super(input); // קריאה לבנאי של Screen
        this.player = new Player(500,500);
        this.world = new GameWorld(player);
        this.hud = new HUD(player);
        this.camera = new Camera(screenWidth, screenHeight);
    }

    @Override
    public void onEnter() {
        super.onEnter(); // מפעיל את הטימר לחסימת מקשים בכניסה
        // כאן אפשר להוסיף מוזיקת רקע או אתחול ספציפי לשלב
    }

    // בתוך GameScreen
    @Override
    public void update(double deltaTime) {
        super.update(deltaTime); // מעדכן את ה-enterLockTimer

        // מעבירים את ה-this (המסך) כדי שהשחקן יוכל לבדוק canPressEnter()
        world.update(deltaTime, input, this);
        camera.update(player);
        hud.handleInput(input);
        hud.update(deltaTime);
    }

    @Override
    public void render(Graphics2D g) {
        // שמירת המצב המקורי של הגרפיקה (לפני הזזת המצלמה)
        AffineTransform oldTransform = g.getTransform();

        // --- שכבת העולם (מושפעת מהמצלמה) ---
        // הזזת נקודת הציור לפי מיקום המצלמה
        g.translate(-camera.getX(), -camera.getY());

        world.render(g);

        // החזרת הגרפיקה למצב "סטטי" כדי שה-UI לא יזוז עם המצלמה
        g.setTransform(oldTransform);

        // --- שכבת ה-HUD (קבועה על המסך) ---
        hud.render(g);
    }

    @Override
    public void handleInput(InputManager input) {
        if (input.ENTER_key) {}
    }
}