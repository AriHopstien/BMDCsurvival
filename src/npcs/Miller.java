package npcs;

import entities.NPC;
import entities.Player;
import hud.HUD;
import java.awt.image.BufferedImage;

public class Miller extends NPC {

    private HUD hud;

    public Miller(float x, float y, BufferedImage sprite, HUD hud) {
        super(x, y, sprite);
        this.hud = hud;
    }

    @Override
    public void update() {
        super.update();
        // ניתן להוסיף כאן אנימציה או תזוזה איטית בתוך הבית מדרש
    }

    // בדיקה אם השחקן במרחק ראיה בעת שימוש בטלפון
    public boolean seePlayerOnPhone(Player player) {
        if (player.isPhoneOpen() && canSee(player)) {
            hud.showTopMessage("הרב מילר ראה אותך משתמש בטלפון!");
            return true;
        }
        return false;
    }
}

