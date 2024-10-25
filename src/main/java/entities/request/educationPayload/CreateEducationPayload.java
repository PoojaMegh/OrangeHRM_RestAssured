package entities.request.educationPayload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateEducationPayload {
    public String name;
}
