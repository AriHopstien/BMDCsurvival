package ai;

import entities.NPC;
import world.GameWorld;

/**
 * מחלקת בסיס לכל AI.
 * כל סוג AI (Patrol/Chase) יורש ממנה.
 */
public abstract class AIcontroller {

    /**
     * update – מתודה שמעדכנת את ה-AI
     * @param npc - ה-NPC שעליו ה-AI פועל
     * @param world - העולם (לבדיקה של גבולות או מחסומים)
     *
     * ⚠️ כאן אני מניח ש-NPC מכיל שדה או getter למיקום שלו
     * (כמו npc.position או npc.getPosition())
     */
    public abstract void update(NPC npc, GameWorld world);

    /**
     * מחזיר סוג ה-AI כ-string
     */
    public abstract String getType();
}