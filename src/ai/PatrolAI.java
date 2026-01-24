package ai;

import entities.NPC;
import world.GameWorld;
import util.Vactor2;

import java.util.List;

public class PatrolAI implements MovementAI {

    private final List<Vactor2> waypoints;
    private int currentWaypoint = 0;
    private static final float ARRIVAL_RADIUS_SQ = 4f;

    public PatrolAI(List<Vactor2> waypoints) {
        this.waypoints = waypoints;
    }

    @Override
    public void update(NPC npc, GameWorld world) {
        if (waypoints == null || waypoints.isEmpty()) {
            npc.stop();
            return;
        }

        Vactor2 target = waypoints.get(currentWaypoint);

        float dx = target.x - npc.getX();
        float dy = target.y - npc.getY();

        float distSq = dx * dx + dy * dy;

        if (distSq <= ARRIVAL_RADIUS_SQ) {
            currentWaypoint = (currentWaypoint + 1) % waypoints.size();
            return;
        }

        npc.moveTowards(dx, dy);
    }
}
