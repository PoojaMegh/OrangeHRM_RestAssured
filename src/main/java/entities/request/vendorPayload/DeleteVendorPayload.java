package entities.request.vendorPayload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@Builder
public class DeleteVendorPayload {
    public ArrayList<Integer> ids;
}
