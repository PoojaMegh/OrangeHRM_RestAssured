package entities.request.skillPayload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@Builder
public class DeleteSkillPayload {
    public ArrayList<String> data;
}
