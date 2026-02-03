package world;

import util.Rect;

public class Zone {
    private String name;
    private Rect area;

    public Zone(String name, float x, float y, float width, float height) {
        this.name = name;
        this.area = new Rect(x, y, width, height);
    }

    // בדיקה האם נקודה (כמו מיקום השחקן) נמצאת בתוך האזור
    public boolean contains(float worldX, float worldY) {
        return worldX >= area.getLeft() && worldX <= area.getRight() &&
                worldY >= area.getTop() && worldY <= area.getBottom();
    }

    public Rect getArea() { return area; }
    public String getName() { return name; }
}