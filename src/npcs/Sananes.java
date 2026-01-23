package npcs;

import entities.NPC;
import entities.Player;
//import hud.HUD;
import java.awt.image.BufferedImage;

public class Sananes extends NPC {

    //private HUD hud;

    public Sananes(float x, float y, BufferedImage sprite) {
        super(x, y, sprite);
        //this.hud = hud;
    }

    // בדיקה אם השחקן נכנס לתפילה אסורה
    public boolean checkPlayerInForbiddenPrayer(Player player, boolean inPrayer) {
        if (inPrayer && canSee(player)) {
            //hud.showTopMessage("שגיאה: אסור להיכנס לתפילה עם סננס!");
            return true; // השחקן נפסל
        }
        return false;
    }
}
