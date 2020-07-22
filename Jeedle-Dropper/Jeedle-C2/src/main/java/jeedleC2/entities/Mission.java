package jeedleC2.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "artifact_id")
    private Collection<Artifact> artifacts;
}
