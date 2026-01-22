package ai;

import entities.NPC;
import entities.Player;
import world.GameWorld;
import util.Vactor2;

/**
 * ChaseAI – מזיז NPC ישירות לכיוון השחקן
 */
public class ChaseAI extends AIcontroller {

    private Player target; // השחקן הוא יעד הרדיפה
    private float chaseTime = 0;

    public ChaseAI(Player target) {
        this.target = target;
    }

    /**
     * update – מזיז את ה-NPC לכיוון השחקן
     * @param npc - NPC ספציפי מ-npcs/
     * @param world - העולם, לבדיקה של גבולות
     * ⚠️ כאן אני מניח של-NPC יש:
     *      - npc.position או npc.getPosition()
     *      - npc.move(Vector2 direction)
     * ⚠️ וגם מניח של-Player יש:
     *      - target.position או target.getPosition()
     */
    @Override
    public void update(NPC npc, GameWorld world) {
        Vactor2 direction = target.getPosition().subtract(npc.getPosition()).normalize();
        npc.move(direction); // מזיז את ה-NPC לכיוון השחקן

        chaseTime += world.getDeltaTime(); // מניח שיש מתודה שמחזירה דלתא טיים
        if (stopWhenConditionMet()) {
            // כאן ניתן לעצור את הרדיפה
        }
    }

    @Override
    public String getType() {
        return "Chase";
    }

    /**
     * בדיקה אם יש לעצור את הרדיפה
     * ⚠️ מניח שה-Player יש מתודה isInSafeZone()
     */
    public boolean stopWhenConditionMet() {
        return target.isInSafeZone();
    }

    /**
     * מאפשר שינוי יעד הרדיפה
     */
    public void setTarget(Player player) {
        this.target = player;
    }
}