package missions;

public class MissionStatus {

    public static final MissionStatus NOT_STARTED = new MissionStatus("NOT_STARTED");
    public static final MissionStatus AVAILABLE = new MissionStatus("AVAILABLE");
    public static final MissionStatus ACTIVE = new MissionStatus("ACTIVE");
    public static final MissionStatus COMPLETED = new MissionStatus("COMPLETED");
    public static final MissionStatus FAILED = new MissionStatus("FAILED");

    private final String name;

    private MissionStatus(String name) {
        this.name = name;
    }

    public boolean isFinal() {
        return this == COMPLETED || this == FAILED;
    }

    @Override
    public String toString() {
        return name;
    }
}
