package testScripts;

import constants.DashboardAPI;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import services.DashboardServices;

public class DashboardTestScripts {

    DashboardServices dashboardServices = new DashboardServices();

    @Test
    public void widgetAPI(){
        Response response = dashboardServices.getWidget();
        System.out.println(response.asPrettyString());
    }
}
