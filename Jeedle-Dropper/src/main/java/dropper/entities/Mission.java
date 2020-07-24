package dropper.entities;

public class Mission {
    private Long id;
    private int type;
    private String[] agrs;

    public Mission(Long id, int type, String[] agrs) {
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String[] getAgrs() {
        return agrs;
    }

    public void setAgrs(String[] agrs) {
        this.agrs = agrs;
    }
}
