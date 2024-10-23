package services;

import base.CommonServices;
import constants.DashboardAPIEndPoint;
import io.restassured.response.Response;

public class DashboardServices extends CommonServices {

    public Response getWidget(){
        setContentTypeAsURLENC();
        return executeGetAPI(DashboardAPIEndPoint.WIDGET_API);
    }

}
