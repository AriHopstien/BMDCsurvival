package util;

public class Vactor2 {
    public float x;
    public float y;

    // בנאי – קובע ערכים ראשוניים
    public Vactor2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    // חיבור וקטורים
    public Vactor2 add(Vactor2 other) {
        return new Vactor2(this.x + other.x, this.y + other.y);
    }

    // חיסור וקטורים
    public Vactor2 subtract(Vactor2 other) {
        return new Vactor2(this.x - other.x, this.y - other.y);
    }

    // הכפלה בסקלר
    public Vactor2 scale(float scalar) {
        return new Vactor2(this.x * scalar, this.y * scalar);
    }

    // אורך הווקטור
    public float length() {
        return (float)Math.sqrt(x * x + y * y);
    }

    // אורך בריבוע – שימושי לחישובי מרחק בלי שורש ריבועי
    public float lengthSquared() {
        return x * x + y * y;
    }

    // נרמול – הפיכת הווקטור ליחידה (אורך = 1)
    public Vactor2 normalize() {
        float len = length();
        if (len != 0) {
            return new Vactor2(x / len, y / len);
        } else {
            return new Vactor2(0, 0);
        }
    }

    @Override
    public String toString() {
        return "Vactor2(" + x + ", " + y + ")";
    }
}
