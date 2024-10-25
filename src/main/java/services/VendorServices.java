package services;

import base.CommonServices;
import constants.SkillAPIEndPoint;
import constants.VendorAPIEndPoint;
import io.restassured.response.Response;

public class VendorServices extends CommonServices {

   

    public Response createVendor(Object payload){
        setBody(payload);
        setContentTypeAsApplicationJSON();
        return executePostAPI(VendorAPIEndPoint.VENDOR_API);
    }

    public Response updateVendor(String payload, String recordID){
        setBody(payload);
        setContentTypeAsApplicationJSON();
        return executePatchAPI(VendorAPIEndPoint.VENDOR_API, recordID);
    }

    public Response getVendorList() {
        setContentTypeAsURLENC();
        return executeGetAPI(VendorAPIEndPoint.VENDOR_API);
    }

    public Response deleteVendor(Object payload){
        setBody(payload);
        setContentTypeAsApplicationJSON();
        return executeDeleteAPI(VendorAPIEndPoint.VENDOR_API);
    }
}
