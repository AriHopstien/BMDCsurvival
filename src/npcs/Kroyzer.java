//package npcs;
//
//import entities.NPC;
//import entities.Player;
//import hud.HUD;
//import world.GameWorld;
//
//import java.awt.image.BufferedImage;
//
//public class Kroyzer extends NPC {
//
//    private HUD hud;
//    private boolean chasing = false;
//
//    public Kroyzer(float x, float y, BufferedImage sprite, HUD hud) {
//        super(x, y, sprite);
//        this.hud = hud;
//    }
//
//    public void update(GameWorld world) {
//        super.update(world);
//    }
//
//    // התחלת רדיפה אחרי השחקן
//    public void startChase(Player player) {
//        chasing = true;
//    }
//
//    // בדיקת רדיפה מול השחקן
//    public void chasePlayer(Player player) {
//        if (!chasing) return;
//
//        float dx = player.getX() - x;
//        float dy = player.getY() - y;
//        float length = (float)Math.sqrt(dx*dx + dy*dy);
//
//        if (length != 0) {
//            setDx(dx / length);
//            setDy(dy / length);
//        }
//    }
//
//    // עצירת רדיפה
//    public void stopChase() {
//        chasing = false;
//        setDx(0);
//        setDy(0);
//    }
//}