package ai;

import entities.NPC;
import world.GameWorld;
import util.Vactor2;
import java.util.List;

public class PatrolAI extends AIcontroller {

    private List<Vactor2> waypoints;
    private int currentWaypoint = 0;

    public PatrolAI(List<Vactor2> waypoints) {
        super("Patrol");
        this.waypoints = waypoints;
    }

    @Override
    public void update(NPC npc, GameWorld world) {
        if (waypoints.isEmpty()) return;

        Vactor2 target = waypoints.get(currentWaypoint);

        // לחשב כיוון פשוט
        float dx = target.x - npc.getX();
        float dy = target.y - npc.getY();

        float distanceSquared = dx * dx + dy * dy;

        if (distanceSquared < 4) { // הגיע ל-waypoint
            currentWaypoint = (currentWaypoint + 1) % waypoints.size();
            target = waypoints.get(currentWaypoint);
            dx = target.x - npc.getX();
            dy = target.y - npc.getY();
        }

        // normalized direction
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
