package entities.request.educationPayload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@Builder
public class DeleteEducationPayload {
    public ArrayList<String> data;
}
