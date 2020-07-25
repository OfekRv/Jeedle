package jeedle.entities;

public class Agent {
    private String id;
    private String ip;
    private int type;

    public Agent(String id, String ip, int type) {
        this.id = id;
        this.ip = ip;
        this.type = type;
    }

    public Agent() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
