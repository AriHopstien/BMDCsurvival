//package npcs;
//
//import entities.NPC;
//import entities.Player;
//import world.GameWorld;
//
//import javax.imageio.ImageIO;
//import java.awt.Graphics;
//import java.awt.image.BufferedImage;
//
//public class Miller extends NPC {
//
//    // ================= תמונות מקור (SpriteSheets) =================
//    private BufferedImage sideSheet;
//    private BufferedImage frontSheet;
//    private BufferedImage backSheet;
//
//    // ================= אנימציה =================
//    private BufferedImage currentFrame;
//    private int frameIndex = 0;              // 0 או 1 (2 פריימים)
//    private double animTimer = 0;
//    private double animSpeed = 0.25;         // כל כמה שניות להחליף פריים
//
//    // ================= חיתוך =================
//    private final int FRAME_W = width;
//    private final int FRAME_H = height;
//
//    public Miller(float x, float y) {
//        super(x, y, null);
//
//        loadImages();
//
//        // ברירת מחדל: עומד קדימה פריים ראשון
//        currentFrame = cutFrame(frontSheet, 0);
//        this.sprite = currentFrame;
//
//
//    }
//
//    private void loadImages() {
//        try {
//            // התמונות בתוך הפקטה npcs
//            sideSheet  = ImageIO.read(getClass().getResource("/npcs/מילר.צדדים.png"));
//            frontSheet = ImageIO.read(getClass().getResource("/npcs/מילר.קדימה.png"));
//            backSheet  = ImageIO.read(getClass().getResource("/npcs/מילר.אחורה.png"));
//        } catch (Exception e) {
//            System.out.println("Failed to load Miller sprites!");
//            e.printStackTrace();
//        }
//    }
//
//    // חותך פריים מהשיט: פריים 0 = החתיכה הראשונה משמאל, פריים 1 = השנייה
//    private BufferedImage cutFrame(BufferedImage sheet, int index) {
//        if (sheet == null) return null;
//        return sheet.getSubimage(index * FRAME_W, 0, FRAME_W, FRAME_H);
//    }
//
//    @Override
//    public void update(GameWorld world) {
//        super.update(world);
//
//        // קובע איזה כיוון לפי dx/dy (שהוגדר מבחוץ)
//        BufferedImage activeSheet = frontSheet;
//
//        if (Math.abs(dx) > Math.abs(dy)) {
//            // הולך לצדדים (ימין/שמאל)
//            activeSheet = sideSheet;
//        } else {
//            if (dy > 0) {
//                // למטה (קדימה)
//                activeSheet = frontSheet;
//            } else if (dy < 0) {
//                // למעלה (אחורה)
//                activeSheet = backSheet;
//            } else {
//                // עומד במקום -> תשאיר את מה שהיה
//                activeSheet = null;
//            }
//        }
//
//        // אנימציה רק אם הוא זז
//        boolean isMoving = (dx != 0 || dy != 0);
//
//        if (isMoving) {
//            animTimer += 1.0 / 60.0; // אם אתה לא שולח deltaTime אז נניח 60FPS
//
//            if (animTimer >= animSpeed) {
//                animTimer = 0;
//                frameIndex = 1 - frameIndex; // 0<->1
//            }
//        } else {
//            // אם עומד במקום נחזיר לפריים הראשון
//            frameIndex = 0;
//        }
//
//        // עדכון הפריים הנוכחי
//        if (activeSheet != null) {
//            currentFrame = cutFrame(activeSheet, frameIndex);
//            if (currentFrame != null) {
//                this.sprite = currentFrame;
//            }
//        }
//    }
//
//    // בדיקה אם השחקן במרחק ראיה בעת שימוש בטלפון
//    public boolean seePlayerOnPhone(Player player) {
//        if (player.isPhoneOpen() && canSee(player)) {
//            return true;
//        }
//        return false;
//    }
//
//    @Override
//    public void Render(Graphics g) {
//        super.Render(g);
//    }
//}