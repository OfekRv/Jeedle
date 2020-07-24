package jeedleC2.contracts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArtifactContract {
    private String content;
    private Long creatingMissionId;
    private String creatingAgentId;
}
