package dropper.entities;

public class Artifact {
    private String content;
    private Long creatingMissionId;
    private String creatingAgentId;

    public Artifact(String content, Long creatingMissionId, String creatingAgentId) {
        this.content = content;
        this.creatingMissionId = creatingMissionId;
        this.creatingAgentId = creatingAgentId;
    }

    public Artifact(String content, String creatingAgentId) {
        this.content = content;
        this.creatingAgentId = creatingAgentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreatingMissionId() {
        return creatingMissionId;
    }

    public void setCreatingMissionId(Long creatingMissionId) {
        this.creatingMissionId = creatingMissionId;
    }

    public String getCreatingAgentId() {
        return creatingAgentId;
    }

    public void setCreatingAgentId(String creatingAgentId) {
        this.creatingAgentId = creatingAgentId;
    }
}
