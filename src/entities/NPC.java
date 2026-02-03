package entities;

import ai.MovementAI;
import world.GameWorld;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import static engine.Time.deltaTime;

public class NPC extends MovableEntity {

    protected float visionRange = 200f;
    protected boolean alert = false;
    protected MovementAI movementAI;

    // שינוי בבנאי: מקבל מידות ומעביר ל-super בלי sprite ראשוני
    public NPC(float x, float y, int width, int height,int colorIndex) {
        super(x, y, width, height);
        this.speed =150.0f;
        loadAnimationsByIndex(colorIndex);
    }

    private void loadAnimationsByIndex(int index) {
        String npcType1 = "" ;
        String npcType2 = "";
        String npcType3 = "";
        try {
            switch (index){
                case 0:
                    npcType1 ="/entities/בלונדיני קדימה.png";
                    npcType2 ="/entities/בלונדיני אחורה.png";
                    npcType3 ="/entities/בלונדיני צד.png";
                    break;
                case 1:
                    npcType1 ="/entities/טראמפ קדימה.png";
                    npcType2 ="/entities/טראמפ אחורה.png";
                    npcType3 ="/entities/טראמפ צד.png";
                    break;
                case 2:
                    npcType1 ="/entities/ג'ינג'י קדימה.png";
                    npcType2 ="/entities/ג'ינג'י אחורה.png";
                    npcType3 ="/entities/ג'ינג'י צד.png";
                    break;
                case 3:
                    npcType1 ="/entities/שחור קדימה.png";
                    npcType2 ="/entities/שחור אחורה.png";
                    npcType3 ="/entities/שחור צד.png";
                    break;
                case 4:
                    npcType1 ="/entities/player-front.png";
                    npcType2 ="/entities/player-back.png";
                    npcType3 ="/entities/player-side.png";
                    break;
            }

            BufferedImage frontSheet = ImageIO.read(getClass().getResourceAsStream(npcType1));
            BufferedImage backSheet  = ImageIO.read(getClass().getResourceAsStream(npcType2));
            BufferedImage sideSheet  = ImageIO.read(getClass().getResourceAsStream(npcType3));

            // חיתוך האנימציות (זהה ללוגיקה של השחקן)
            walkDown = new BufferedImage[] {
                    frontSheet.getSubimage(0, 64, 64, 64),
                    frontSheet.getSubimage(0, 128, 64, 64)
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
                    sideSheet.getSubimage(64, 64, 64, 64)
            };

            this.sprite = walkDown[0];

        } catch (Exception e) {
            System.out.println("Error loading NPC color index " + index + ": " + e.getMessage());
        }
    }

    public void setMovementAI(MovementAI ai) {
        this.movementAI = ai;
    }

    public void update(GameWorld world) {
        if (movementAI != null) {
            movementAI.update(this, world);
        }
        // move(deltaTime) כבר מעדכן גם את האנימציה בתוך MovableEntity
        move(deltaTime,world);
    }


    public void moveTowards(float dx, float dy) {
        float len = (float) Math.sqrt(dx * dx + dy * dy);
        if (len == 0) {
            stop();
            return;
        }
        // נרמול וקביעת ה-dx/dy שיפעילו את האנימציה ב-move
        setDx(dx / len);
        setDy(dy / len);
    }

    // בדיקה אם NPC "רואה" יעד כללי
    public boolean canSee(Entity target) {
        float dx = target.getX() - x;
        float dy = target.getY() - y;
        return (dx * dx + dy * dy) <= visionRange * visionRange;
    }

    public void setAlert(boolean value) {
        alert = value;
    }

    @Override
    public void Render(Graphics g) {
        // מצייר את ה-sprite הנוכחי (האנימציה)
        super.Render(g);

        // מוסיף את סימן האזהרה מעל הראש
        if (alert) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("!", (int) x + width / 2 - 5, (int) y - 10);
        }
    }
}