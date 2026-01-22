package world;
import java.awt.image.BufferedImage;

public class Tile {

    private BufferedImage image;
    private boolean walkable;

    public Tile(BufferedImage image, boolean walkable) {
        this.image = image;
        this.walkable = walkable;
    }

    public BufferedImage getImage() {
        return image;
    }

    public boolean isWalkable() {
        return walkable;
    }
}
