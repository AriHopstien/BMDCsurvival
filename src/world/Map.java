package world;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

public class Map {
    public Tile[] tiles;

    public int[][] layout;

    public Map() {
        tiles = new Tile[10];

        layout = new int[][] {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}
        };


    }

    private void setup(int index, String fileName, boolean walkable) {
        try {
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream(fileName));
            tiles[index] = new Tile(img, walkable);
        } catch (Exception e) {
            System.out.println("Error: Could not find file: " + fileName);
        }
    }
    private void loadTileImages(){
        setup(0, "floor.png", true);
        setup(1, "wall.png", false);
    }

    public void draw(Graphics2D g2) {
        int tileSize = 64;

        for (int row = 0; row < layout.length; row++) {
            for (int col = 0; col < layout[row].length; col++) {

                int tileIndex = layout[row][col];


                if (tiles[tileIndex] != null) {
                    g2.drawImage(tiles[tileIndex].getImage(), col * tileSize, row * tileSize, tileSize, tileSize, null);
                }
            }
        }
    }
}
