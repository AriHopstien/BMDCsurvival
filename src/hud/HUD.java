package hud;

import entities.Player;
import engine.InputManager;

import java.awt.*;

public class HUD {
    private Player player;
    private PhoneUI phoneUI;
    private MassageBoxTop topMessageBox;
    private MassageBoxBottom bottomMessageBox;

    public HUD(Player player) {
        this.player = player;
        this.phoneUI = new PhoneUI(player);
        this.topMessageBox = new MassageBoxTop();
        this.bottomMessageBox = new MassageBoxBottom();
    }

    public void update(double deltaTime) {
        topMessageBox.update(deltaTime);
        bottomMessageBox.update(deltaTime);
        phoneUI.update(deltaTime);
    }

    public void render(Graphics2D g) {
        phoneUI.render(g);
        topMessageBox.render(g);
        bottomMessageBox.render(g);
    }

    // --- מעטפת (Wrappers) לניהול הודעות ---

    // הודעה בטלפון - אנחנו מעדכנים את השחקן, ה-UI כבר ימשוך את זה משם לבד
    public void addPhoneMessage(String sender, String text) {
        player.addMessage(sender, text);
    }

    // בדיקה אם הטלפון פתוח - שואלים את השחקן
    public boolean isPhoneOpen() {
        return player.isPhoneOpen();
    }

    // הודעות מערכת/עולם - נשארות ב-HUD כי הן לא "מידע של השחקן"
    public void showTopMessage(String text) {
        topMessageBox.show(text);
    }

    public void showBottomMessage(String text) {
        bottomMessageBox.show(text);
    }

    // חשוב: להעביר את הקלט ל-PhoneUI כדי שנוכל לדפדף בהודעות
    public void handleInput(InputManager input) {
        phoneUI.handleInput(input);
    }
}


