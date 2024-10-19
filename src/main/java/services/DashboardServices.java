package services;

import base.CommonServices;
import constants.DashboardAPI;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class DashboardServices extends CommonServices {

    public Response getWidget(){
        setContentTypeAsURLENC();
        return executeGetAPI(DashboardAPI.WIDGET_API);
    }

}
