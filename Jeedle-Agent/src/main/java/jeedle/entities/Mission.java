package jeedle.entities;

public class Mission {
    private Long id;
    private MissionType type;
    private String[] args;

    public Mission(Long id, MissionType type, String[] agrs) {
        this.id = id;
        this.type = type;
        this.args = agrs;
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

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
}
