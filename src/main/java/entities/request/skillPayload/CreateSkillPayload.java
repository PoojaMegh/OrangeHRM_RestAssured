package entities.request.skillPayload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateSkillPayload {

        public String name;
        public String description;

}
