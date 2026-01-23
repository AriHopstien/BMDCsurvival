package missions;

import java.util.ArrayList;
import java.util.List;

public class MissionManager {

    private List<Missions> missions;

    public MissionManager() {
        this.missions = new ArrayList<>();
    }

    public void addMission(Missions mission) {
        missions.add(mission);
    }

    public Missions getMissionByTitle(String title) {
        for (Missions m : missions) {
            if (m.getTitle().equals(title)) return m;
        }
        return null;
    }

    public void updateMission(String title, double progress) {
        Missions m = getMissionByTitle(title);
        if (m != null) m.setProgress(progress);
    }

    public int getCompletedCount() {
        int count = 0;
        for (Missions m : missions) {
            if (m.isCompleted()) count++;
        }
        return count;
    }

    public List<Missions> getAllMissions() {
        return missions;
    }
}
