package hud;

import entities.Player;

import java.awt.*;

public class HUD {
    private Player player;
    private PhoneUI phoneUI;
        private MessageBoxTop topMessageBox;
        private MessageBoxBottom bottomMessageBox;

        public HUD(Player player) {
            this.player = player;

            this.phoneUI = new PhoneUI(player);
            this.topMessageBox = new MessageBoxTop();
            this.bottomMessageBox = new MessageBoxBottom();
        }

        // עדכון לוגי של כל רכיבי ה-HUD
        public void update(double deltaTime) {
            phoneUI.update(deltaTime);
            topMessageBox.update(deltaTime);
            bottomMessageBox.update(deltaTime);
        }

        // ציור ה-HUD מעל המשחק
        public void render(Graphics2D g) {
            phoneUI.render(g);
            topMessageBox.render(g);
            bottomMessageBox.render(g);
        }

        // קלט מקלדת
        public void keyPressed(int keyCode) {
            phoneUI.keyPressed(keyCode);
            bottomMessageBox.keyPressed(keyCode);
        }

        public void keyReleased(int keyCode) {
            phoneUI.keyReleased(keyCode);
        }

        /* ===== API נוח לשאר המשחק ===== */

        // הודעה מדמות (מופיעה למעלה)
        public void showTopMessage(String text) {
            topMessageBox.show(text);
        }

        // הודעה לטלפון (מופיעה בטלפון)
        public void addPhoneMessage(String sender, String text) {
            phoneUI.addMessage(sender, text);
        }

        // טקסט כללי למטה (לדוגמה: אינטראקציה)
        public void showBottomMessage(String text) {
            bottomMessageBox.show(text);
        }

        public boolean isPhoneOpen() {
            return phoneUI.isOpen();
        }
 }


