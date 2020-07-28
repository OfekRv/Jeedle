package jeedleC2.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "jdl_artifacts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Artifact {
    @Id
    @SequenceGenerator(name = "jdl_artifacts_seq", sequenceName = "jdl_artifacts_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jdl_artifacts_seq")
    @JoinColumn(name = "artifact_id")
    private Long id;
    @Column(columnDefinition = "TEXT", nullable = true, unique = false)
    private String content;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "mission_id")
    private Mission creatingMission;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "agent_id")
    private Agent creatingAgent;

    public Artifact(String content, Agent creatingAgent) {
        this.content = content;
        this.creatingAgent = creatingAgent;
    }
}
