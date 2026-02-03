package world;

import ai.MovementAI;
import ai.PatrolAI;
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

    private double currentDeltaTime;
    private Map map;
    private Player player;
    private List<NPC> npcs;
    private final int TILE_SIZE = 64;
    private Zone zone ;
    private MovementAI PatrolAI;

    public GameWorld(Player player) {
        this.map = new Map();
        this.player = player;
        this.npcs = new ArrayList<>();
        zone = new Zone("בית מדרש", 11 * 64, 26 * 64, 17 * 64, 12 * 64);
        for (int i = 0; i < 5; i++) {
            NPC npc = new NPC((20 + i) * 64, 30 * 64, 64, 64, i);
            npc.setMovementAI(new PatrolAI(zone));
            npcs.add(npc);
        }
    }

    public void update(double deltaTime, InputManager input, GameScreen gameScreen) {
        this.currentDeltaTime = deltaTime;

        // 1. קודם כל מעדכנים את ה-dx/dy של השחקן לפי המקלדת
        player.update(input, gameScreen);
        // 2. מבצעים את התנועה (היא כבר כוללת בדיקת התנגשות בתוך MovableEntity)
        player.move(deltaTime, this);

        // 3. עדכון NPCs
        for (NPC npc : npcs) {
            npc.update(this); // וודא שגם ב-NPC.update אתה קורא ל-move(deltaTime, world)
        }
    }

    public boolean canMoveTo(Rect hitbox) {
        // מניעת יציאה מגבולות המפה (הוספנו כאן את הבדיקה שהייתה ב-handle)
        int maxWorldX = map.layout[0].length * TILE_SIZE;
        int maxWorldY = map.layout.length * TILE_SIZE;

        if (hitbox.getLeft() < 0 || hitbox.getRight() > maxWorldX ||
                hitbox.getTop() < 0 || hitbox.getBottom() > maxWorldY) {
            return false;
        }

        float padding = 8;
        // הבדיקה שלך מצוינת - היא בודקת רק מהאמצע ומטה (מאפשר לראש "לצוף")
        return isWalkable(hitbox.getLeft() + padding, hitbox.getTop() + (hitbox.size.y / 2)) &&
                isWalkable(hitbox.getRight() - padding, hitbox.getTop() + (hitbox.size.y / 2)) &&
                isWalkable(hitbox.getLeft() + padding, hitbox.getBottom()) &&
                isWalkable(hitbox.getRight() - padding, hitbox.getBottom());
    }

    private boolean isWalkable(float worldX, float worldY) {
        int col = (int) (worldX / TILE_SIZE);
        int row = (int) (worldY / TILE_SIZE);

        if (row < 0 || row >= map.layout.length || col < 0 || col >= map.layout[0].length) {
            return false;
        }

        int tileIndex = map.layout[row][col];
        return map.tiles[tileIndex].isWalkable();
    }

    public void render(Graphics2D g, Camera camera) {
        map.draw(g, camera.getX(), camera.getY());
        for (NPC npc : npcs) {
            npc.Render(g);
        }
        player.Render(g);
    }

    public double getDeltaTime() { return currentDeltaTime; }
    public void addNPC(NPC npc) { npcs.add(npc); }
    public Map getMap() { return map; }
    public Player getPlayer() { return player; }
}