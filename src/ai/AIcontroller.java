package ai;

import entities.NPC;
import world.GameWorld;

public abstract class AIcontroller {

    protected String type; // לצורך זיהוי סוג AI

    public AIcontroller(String type) {
        this.type = type;
    }

    // הפונקציה העיקרית: מחליטה מה ה-NPC יעשה בפריים הזה
    public abstract void update(NPC npc, GameWorld world);

    public String getType() {
        return type;
    }
}
