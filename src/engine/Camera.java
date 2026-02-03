package engine;

import entities.Entity;

public class Camera {
    private float x, y;
    private int screenWidth, screenHeight;

    public Camera(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.x = 0;
        this.y = 0;
    }

    /**
     * @param target הישות שהמצלמה עוקבת אחריה (השחקן)
     * @param worldWidth רוחב המפה הכולל בפיקסלים (למשל: מספר עמודות * 64)
     * @param worldHeight גובה המפה הכולל בפיקסלים (למשל: מספר שורות * 64)
     */
    public void update(Entity target, int worldWidth, int worldHeight) {
        // 1. חישוב המיקום האידיאלי (השחקן במרכז)
        this.x = target.getX() - (screenWidth / 2f) + (target.getWidth() / 2f);
        this.y = target.getY() - (screenHeight / 2f) + (target.getHeight() / 2f);

        // 2. חסימת גבולות (Clamping)

        // מניעת יציאה משמאל (0)
        if (this.x < 0) {
            this.x = 0;
        }
        // מניעת יציאה מימין (רוחב המפה פחות רוחב המסך)
        if (this.x > worldWidth - screenWidth) {
            this.x = worldWidth - screenWidth;
        }

        // מניעת יציאה מלמעלה (0)
        if (this.y < 0) {
            this.y = 0;
        }
        // מניעת יציאה מלמטה (גובה המפה פחות גובה המסך)
        if (this.y > worldHeight - screenHeight) {
            this.y = worldHeight - screenHeight;
        }

        // מקרה קצה: אם המפה קטנה מהמסך, נמרכז אותה
        if (worldWidth < screenWidth) this.x = (worldWidth - screenWidth) / 2f;
        if (worldHeight < screenHeight) this.y = (worldHeight - screenHeight) / 2f;
    }

    public float getX() { return x; }
    public float getY() { return y; }
}