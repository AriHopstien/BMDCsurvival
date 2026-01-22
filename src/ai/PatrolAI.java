//package ai;
//
//import entities.NPC;
//import world.GameWorld;
//import util.Vactor2;
//import java.util.List;
//
/// **
// * PatrolAI – מזיז את ה-NPC בין נקודות waypoints
// */
//public class PatrolAI extends AIcontroller {
//
//    private List<Vactor2> waypoints; // רשימת נקודות לחיזור
//    private int currentIndex = 0;     // הנקודה הנוכחית
//    public PatrolAI(List<Vactor2> waypoints) {
//        this.waypoints = waypoints;
//    }
//
//    /**
//     * update – מזיז את ה-NPC לעבר ה-waypoint הנוכחי
//     * @param npc - NPC ספציפי מ-npcs/
//     * @param world - העולם, לבדוק גבולות
//     * ⚠️ כאן אני מניח של-NPC יש:
//     *      - npc.position או npc.getPosition()
//     *      - npc.move(Vector2 direction)
//     */
//    @Override
//    public void update(NPC npc, GameWorld world) {
//        if (waypoints.isEmpty()) return;
//
//        Vactor2 target = waypoints.get(currentIndex);
//        Vactor2 direction = target.subtract(npc.getPosition()).normalize(); // חישוב כיוון
//        npc.move(direction); // מזיז את ה-NPC
//
//        // בדיקה אם הגיע ל-waypoint
//        if (npc.getPosition().distance(target) < 0.1f) {
//            currentIndex = (currentIndex + 1) % waypoints.size();
//        }
//    }
//
//    @Override
//    public String getType() {
//        return "Patrol";
//    }
//
//    /**
//     * loop – מחזיר את ה-waypoint הבא, שימושי למעקב
//     */
//    public Vactor2 loop() {
//        return waypoints.get(currentIndex);
//    }
//}