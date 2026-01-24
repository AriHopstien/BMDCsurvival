package world;

import entities.Player;
import entities.NPC;
import engine.InputManager;
import ui.GameScreen;
import util.Rect;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class GameWorld {

    private Map map;
    private Player player;
    private List<NPC> npcs;
    private final int TILE_SIZE = 64;

    public GameWorld(Player player) {
        this.map = new Map();
        this.player = player;
        this.npcs = new ArrayList<>();
    }

    public void update(double deltaTime, InputManager input, GameScreen gameScreen) {
        // 1. עדכון כיוון השחקן לפי הקלט (dx, dy)
        player.update(input,gameScreen);

        // 2. טיפול בתנועה והתנגשויות (פיצול צירים להחלקה חלקה)
        handlePlayerMovement(deltaTime);

        // 3. עדכון NPCs (אם יש)
        for (NPC npc : npcs) {
            npc.update(this);
        }
    }

    private void handlePlayerMovement(double deltaTime) {
        float speed = player.getSpeed();
        float dx = player.getDx();
        float dy = player.getDy();

        // --- בדיקת ציר X ---
        if (dx != 0) {
            float nextX = player.getX() + (dx * speed * (float) deltaTime);
            // יצירת היטבוקס עתידי לבדיקה
            Rect futureHitboxX = new Rect(nextX, player.getY(), player.getWidth(), player.getHeight());

            if (canMoveTo(futureHitboxX)) {
                player.setX(nextX);
            }
        }

        // --- בדיקת ציר Y ---
        if (dy != 0) {
            float nextY = player.getY() + (dy * speed * (float) deltaTime);
            Rect futureHitboxY = new Rect(player.getX(), nextY, player.getWidth(), player.getHeight());

            if (canMoveTo(futureHitboxY)) {
                player.setY(nextY);
            }
        }
    }

    public boolean canMoveTo(Rect hitbox) {
        // בדיקת 4 הפינות של ה-Hitbox מול ה-Tiles במפה
        // הוספנו "Padding" קטן של 5 פיקסלים כדי שההתנגשות תרגיש פחות "צפופה"
        float padding = 5;

        return isWalkable(hitbox.getLeft() + padding, hitbox.getTop() + (hitbox.size.y / 2)) && // אמצע שמאל
                isWalkable(hitbox.getRight() - padding, hitbox.getTop() + (hitbox.size.y / 2)) && // אמצע ימין
                isWalkable(hitbox.getLeft() + padding, hitbox.getBottom()) &&                   // פינה שמאלית למטה
                isWalkable(hitbox.getRight() - padding, hitbox.getBottom());                    // פינה ימנית למטה
    }

    private boolean isWalkable(float worldX, float worldY) {
        int col = (int) (worldX / TILE_SIZE);
        int row = (int) (worldY / TILE_SIZE);

        // הגנה מפני יציאה מגבולות המפה
        if (row < 0 || row >= map.layout.length || col < 0 || col >= map.layout[0].length) {
            return false;
        }

        int tileIndex = map.layout[row][col];
        return map.tiles[tileIndex].isWalkable();
    }

    public void render(Graphics2D g) {
        // 1. המפה (תמיד ראשונה)
        map.draw(g);
        // 2. דמויות (NPCs)
        for (NPC npc : npcs) {
            npc.Render(g);
        }
        // 3. השחקן (תמיד מעל הכל, אלא אם תרצה שה-HUD יהיה מעליו)
        player.Render(g);
    }

    // --- Getters & Setters ---
    public void addNPC(NPC npc) { npcs.add(npc); }
    public Map getMap() { return map; }
    public Player getPlayer() { return player; }
}