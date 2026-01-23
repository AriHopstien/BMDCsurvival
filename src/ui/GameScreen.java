package ui;

import engine.InputManager;
import main.Game;
import world.Map;

import java.awt.*;

public class GameScreen extends Screen {

    private Game game;
    private InputManager input;
    private Map map = new Map();

    // בהמשך תכניס פה:
    // GameWorld world;
    // Player player;
    // HUD hud;

    public GameScreen(Game game, InputManager input) {
        super(input);
        this.game = game;

        // initWorld();
        // initPlayer();
        // initNPCs();
    }

    @Override
    public void onEnter() {
        // קורה פעם אחת כשנכנסים למשחק
        // למשל: מוזיקה, אתחול טיימרים
    }

    @Override
    public void update(double deltaTime) {
        // world.update(deltaTime);
        // player.update(deltaTime);
        // npc.update(deltaTime);
    }



    @Override
    public void handleInput(InputManager input) {
        // player.handleInput(input);
    }

    @Override
    public void render(Graphics2D g) {

        // רקע
        //g.setColor(Color.DARK_GRAY);
        //g.fillRect(0, 0, 1280, 720);

        // ציור עולם
         this.map.draw(g);

        // ציור דמויות
        // player.render(g);
        // npc.render(g);

        // HUD
        // hud.render(g);
    }

    @Override
    public void onExit() {
        // ניקוי / עצירת סאונדים
    }
}
