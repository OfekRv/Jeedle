package jeedleC2.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "jdl_agents")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Agent {
    @Id
    @JoinColumn(name = "agent_id")
    private String id;
    @Column(nullable = false, unique = false)
    private String ip;
    @Enumerated(EnumType.STRING)
    private AgentType type;
    @Column(nullable = true, unique = false)
    private LocalDateTime lastActive;
}
