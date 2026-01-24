package util;

public class Rect {
    public Vactor2 position; // המיקום (x, y)
    public Vactor2 size;     // הגודל (רוחב, גובה)

    public Rect(Vactor2 position, Vactor2 size) {
        this.position = position;
        this.size = size;
    }

    // בנאי נוחות למקרה שיש לנו מספרים ישירים
    public Rect(float x, float y, float width, float height) {
        this.position = new Vactor2(x, y);
        this.size = new Vactor2(width, height);
    }

    public boolean intersects(Rect other) {
        return position.x < other.position.x + other.size.x &&
                position.x + size.x > other.position.x &&
                position.y < other.position.y + other.size.y &&
                position.y + size.y > other.position.y;
    }

    // פונקציות עזר לקבלת פינות ה-Hitbox (מאוד שימושי לבדיקת Tiles)
    public float getLeft()   { return position.x; }
    public float getRight()  { return position.x + size.x; }
    public float getTop()    { return position.y; }
    public float getBottom() { return position.y + size.y; }

    public Vactor2 getCenter() {
        return new Vactor2(position.x + size.x / 2, position.y + size.y / 2);
    }
}