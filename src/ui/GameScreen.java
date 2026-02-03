package ui;

import engine.InputManager;
import main.Game;
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

    // נשאיר אותם כמשתנים פשוטים, נאתחל אותם בבנאי
    private int worldWidth;
    private int worldHeight;

    private final int screenWidth = 1280;
    private final int screenHeight = 720;

    public GameScreen(Game game, InputManager input) {
        super(input);

        // 1. קודם כל יוצרים את השחקן והעולם
        this.player = new Player(1000, 2000);
        this.world = new GameWorld(player);
        this.hud = new HUD(player);
        this.camera = new Camera(screenWidth, screenHeight);
        // (השתמשתי בשיטה שהצעתי קודם - גטרים מתוך world)
        this.worldWidth = world.getMap().layout[0].length * 64;
        this.worldHeight = world.getMap().layout.length * 64;
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);

        // עדכון עולם
        world.update(deltaTime, input, this);

        // עדכון מצלמה עם הערכים שחושבו פעם אחת בבנאי
        camera.update(player, worldWidth, worldHeight);

        hud.handleInput(input);
        hud.update(deltaTime);
    }

    @Override
    public void render(Graphics2D g) {
        AffineTransform oldTransform = g.getTransform();

        // תרגום המצלמה
        g.translate(-camera.getX(), -camera.getY());
        world.render(g, camera);

        g.setTransform(oldTransform);

        // HUD סטטי
        hud.render(g);
    }

    @Override
    public void handleInput(InputManager input) {
        // לוגיקה נוספת אם צריך
    }
}