package dropper.entities;

public class Mission {
    private Long id;
    private MissionType type;
    private String[] agrs;

    public Mission(Long id, MissionType type, String[] agrs) {
        this.id = id;
        this.type = type;
        this.agrs = agrs;
    }

    public Mission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MissionType getType() {
        return type;
    }

    public void setType(MissionType type) {
        this.type = type;
    }

    public String[] getAgrs() {
        return agrs;
    }

    public void setAgrs(String[] agrs) {
        this.agrs = agrs;
    }
}
