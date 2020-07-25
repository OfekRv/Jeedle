package jeedleC2.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "jdl_missions")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Mission {
    @Id
    @SequenceGenerator(name = "jdl_missions_seq", sequenceName = "jdl_missions_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jdl_missions_seq")
    @JoinColumn(name = "mission_id")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "agent_id")
    private Agent agent;
    @Enumerated(EnumType.STRING)
    private MissionType type;
    @ElementCollection
    @Column(nullable = true, unique = false)
    private Collection<String> args;
    @Column(nullable = true, unique = false)
    @CreationTimestamp
    private LocalDateTime creationTime;
    @Column(nullable = false, unique = false)
    private Boolean isSent = false;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "artifact_id")
    private Collection<Artifact> artifacts;
}
