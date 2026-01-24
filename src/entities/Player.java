package entities;

import engine.InputManager;
import hud.PhoneMessage;
import ui.Screen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import static engine.Time.deltaTime;

public class Player extends MovableEntity {

    private boolean phoneOpen = false;
    private List<PhoneMessage> phoneMessages;
    private int unreadCount = 0;

    // --- מערכת אנימציה ---
    private BufferedImage[] walkUp, walkDown, walkLeft, walkRight;
    private String direction = "DOWN"; // כיוון ברירת מחדל
    private int animationTick = 0;
    private int animationFrame = 0;
    private final int ANIMATION_SPEED = 10; // ככל שהמספר גבוה יותר, האנימציה איטית יותר

    public Player(float x, float y) {
        super(x, y, null); // שולחים null כי נטען את ה-Sprite בתוך loadAnimations
        this.phoneMessages = new ArrayList<>();
        this.speed = 250.0f;
        this.width = 64;
        this.height = 64;
        loadAnimations();
    }

    private void loadAnimations() {
        try {
            // טעינת הקבצים
            BufferedImage frontSheet = ImageIO.read(getClass().getResourceAsStream("/entities/player-front.png"));
            BufferedImage backSheet  = ImageIO.read(getClass().getResourceAsStream("/entities/player-back.png"));
            BufferedImage sideSheet  = ImageIO.read(getClass().getResourceAsStream("/entities/player-side.png"));

            // חיתוך פריימים (לפי המבנה של התמונות שהעלית)
            // הערה: הערכים 64 ו-48 הם הערכה, ייתכן ותצטרך לדייק אותם לפי הפיקסלים במדויק

            walkDown = new BufferedImage[] {
                    frontSheet.getSubimage(0, 64, 64, 64), // פריים 2 (הליכה 1)
                    frontSheet.getSubimage(0, 128,64, 64) // פריים 3 (הליכה 2)
            };

            walkUp = new BufferedImage[] {
                    backSheet.getSubimage(0, 0, 64, 64),
                    backSheet.getSubimage(0, 64, 64, 64)
            };

            walkRight = new BufferedImage[] {
                    sideSheet.getSubimage(0, 0, 64, 64),
                    sideSheet.getSubimage(64, 0, 64, 64)
            };

            walkLeft = new BufferedImage[] {
                    sideSheet.getSubimage(0, 64, 64, 64),
                    sideSheet.getSubimage(64,64 , 64, 64)
            };

            this.sprite = walkDown[0]; // תמונה התחלתית

        } catch (Exception e) {
            System.out.println("Error loading player sprites: " + e.getMessage());
        }
    }

    // בתוך מחלקת Player
    public void update(InputManager input,Screen currentScreen) { // הוספנו את המסך כפרמטר
        if (!phoneOpen) {
            handleMovement(input);
            move(deltaTime);
            updateAnimation();
        }
        //} else {
        //            this.dx = 0;
        //            this.dy = 0;
        //        }

        // בדיקה האם ENTER לחוץ וגם האם עבר מספיק זמן מאז הלחיצה האחרונה
        if (input.ENTER_key && currentScreen.canPressEnter()) {
            togglePhone();
            currentScreen.resetEnterTimer(); // מאפס את הטימר כדי למנוע לחיצות נוספות
        }
    }

    private void updateAnimation() {
        // קביעת הכיוון הלוגי לצורך בחירת מערך התמונות
        if (dx > 0) direction = "RIGHT";
        else if (dx < 0) direction = "LEFT";
        else if (dy > 0) direction = "DOWN";
        else if (dy < 0) direction = "UP";

        // אם השחקן זז, נקדם את הטיקר של האנימציה
        if (dx != 0 || dy != 0) {
            animationTick++;
            if (animationTick >= ANIMATION_SPEED) {
                animationTick = 0;
                animationFrame = (animationFrame + 1) % 2; // החלפה בין פריים 0 ל-1
            }

            // עדכון ה-Sprite הנוכחי
            switch (direction) {
                case "UP":    sprite = walkUp[animationFrame]; break;
                case "DOWN":  sprite = walkDown[animationFrame]; break;
                case "LEFT":  sprite = walkLeft[animationFrame]; break;
                case "RIGHT": sprite = walkRight[animationFrame]; break;
            }
        } else {
            // אם הוא עומד, נחזיר לפריים הראשון (עמידה)
            animationTick = 0;
            animationFrame = 0;
        }
    }

    private void handleMovement(InputManager input) {
        this.dx = 0;
        this.dy = 0;
        if (input.W_key) dy = -1;
        if (input.S_Key) dy = 1;
        if (input.A_key) dx = -1;
        if (input.D_key) dx = 1;
    }

    // --- שאר הפעולות (Phone, Messages וכו') נשארות ללא שינוי ---
    public void togglePhone() {
        phoneOpen = !phoneOpen;
        if (phoneOpen) unreadCount = unreadCount - 1;
    }

    public void addMessage(String sender, String text) {
        phoneMessages.add(new PhoneMessage(sender, text));
        if (!phoneOpen) unreadCount++;
    }

    public boolean isPhoneOpen() { return phoneOpen; }

    public List<PhoneMessage> getPhoneMessages() { return phoneMessages; }

    public int getUnreadCount() { return unreadCount; }

    @Override
    public void Render(Graphics g) {
        if (sprite != null) {
            g.drawImage(sprite, (int)x, (int)y, width, height, null);
        }
    }
}