package world;

import engine.Camera;
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
        float dx = player.getDx();
        float dy = player.getDy();
        float speed = player.getSpeed();

        if (dx == 0 && dy == 0) return;

        // 1. חישוב גבולות המפה בפיקסלים
        // layout[0].length זה מספר העמודות, layout.length זה מספר השורות
        int maxWorldX = map.layout[0].length * 64;
        int maxWorldY = map.layout.length * 64;

        float stepX = dx * speed * (float) deltaTime;
        float stepY = dy * speed * (float) deltaTime;

        // --- ציר X ---
        if (dx != 0) {
            float nextX = player.getX() + stepX;

            // חסימה קשיחה: מונע מה-X לרדת מ-0 או לעבור את רוחב המפה
            if (nextX < 0) nextX = 0;
            if (nextX + player.getWidth() > maxWorldX) nextX = maxWorldX - player.getWidth();

            Rect futureHitboxX = new Rect(nextX, player.getY(), player.getWidth(), player.getHeight());
            if (canMoveTo(futureHitboxX)) {
                player.setX(nextX);
            }
        }

        // --- ציר Y ---
        if (dy != 0) {
            float nextY = player.getY() + stepY;

            // חסימה קשיחה: מונע מה-Y לרדת מ-0 או לעבור את גובה המפה
            if (nextY < 0) nextY = 0;
            if (nextY + player.getHeight() > maxWorldY) nextY = maxWorldY - player.getHeight();

            Rect futureHitboxY = new Rect(player.getX(), nextY, player.getWidth(), player.getHeight());
            if (canMoveTo(futureHitboxY)) {
                player.setY(nextY);
            }
        }
    }

    public boolean canMoveTo(Rect hitbox) {

        float padding = 8;

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

    public void render(Graphics2D g , Camera camera) {
        // 1. המפה (תמיד ראשונה)
        map.draw(g, camera.getX(), camera.getY());
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