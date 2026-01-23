package missions;

public class Missions {

    private String title;
    private String description;
    private MissionStatus status;
    private double progress;

    public Missions(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = MissionStatus.NOT_STARTED;
        this.progress = 0.0;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public MissionStatus getStatus() { return status; }
    public void setStatus(MissionStatus status) { this.status = status; }

    public double getProgress() { return progress; }
    public void setProgress(double progress) {
        if (progress < 0) progress = 0;
        if (progress > 1) progress = 1;
        this.progress = progress;

        if (progress >= 1.0) {
            this.status = MissionStatus.COMPLETED;
        }
    }

    public void start() { this.status = MissionStatus.ACTIVE; }
    public boolean isCompleted() { return status == MissionStatus.COMPLETED; }
}
