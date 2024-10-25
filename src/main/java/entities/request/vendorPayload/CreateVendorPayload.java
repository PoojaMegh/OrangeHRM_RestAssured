package entities.request.vendorPayload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateVendorPayload {
    public String email;
    public String address;
    public String website;
    public String contactNo;
    public String vendorCode;
    public String vendorName;
}
