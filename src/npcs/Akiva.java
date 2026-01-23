package npcs;

import entities.NPC;
import entities.Player;
import hud.HUD;
import java.awt.image.BufferedImage;

public class Akiva extends NPC {

    private HUD hud;
    private boolean patrolDirection = true; // הלוך/חזור

    public Akiva(float x, float y, BufferedImage sprite, HUD hud) {
        super(x, y, sprite);
        this.hud = hud;
    }

    @Override
    public void update() {
        super.update();
        // כאן נוסיף לוגיקה שתסתובב הלוך חזור אחרי החלב
    }

    // בדיקה אם השחקן נגע בחלב מבלי להסתיר
    public boolean checkPlayerCaught(Player player, boolean carryingMilk) {
        if (carryingMilk && getBounds().intersects(player.getBounds())) {
            hud.showTopMessage("עקיבא ראה אותך! הפסדת!");
            return true;
        }
        return false;
    }

    // החלפת כיוון הליכה
    public void toggleDirection() {
        patrolDirection = !patrolDirection;
    }
}
