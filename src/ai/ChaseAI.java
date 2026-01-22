package ai;

import entities.NPC;
import entities.Player;
import world.GameWorld;

public class ChaseAI extends AIcontroller {

    private Player targetPlayer;

    public ChaseAI(Player player) {
        super("Chase");
        this.targetPlayer = player;
    }

    @Override
    public void update(NPC npc, GameWorld world) {
        if (targetPlayer == null) return;

        float dx = targetPlayer.getX() - npc.getX();
        float dy = targetPlayer.getY() - npc.getY();

        float length = (float) Math.sqrt(dx * dx + dy * dy);
        if (length != 0) {
            npc.setDx(dx / length);
            npc.setDy(dy / length);
        } else {
            npc.setDx(0);
            npc.setDy(0);
        }
    }
}
