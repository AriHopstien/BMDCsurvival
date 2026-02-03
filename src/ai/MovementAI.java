package ai;

import entities.NPC;
import world.GameWorld;

public interface MovementAI {
    void update(NPC npc, GameWorld world);
}