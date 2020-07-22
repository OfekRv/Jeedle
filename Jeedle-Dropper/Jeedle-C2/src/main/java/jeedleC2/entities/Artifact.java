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
    @Column(nullable = true, unique = false)
    private String content;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, optional = false)
    @JoinColumn(name = "mission_id")
    private Mission creatingMission;
}
