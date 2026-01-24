package ai;

import entities.NPC;
import entities.Player;
import world.GameWorld;

public class ChaseAI implements MovementAI {

    private final Player target;

    public ChaseAI(Player target) {
        this.target = target;
    }

    @Override
    public void update(NPC npc, GameWorld world) {
        if (target == null) {
            npc.stop();
            return;
        }

        float dx = target.getX() - npc.getX();
        float dy = target.getY() - npc.getY();

        npc.moveTowards(dx, dy);
    }
}
