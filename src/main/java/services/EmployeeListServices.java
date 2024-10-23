package services;

import base.CommonServices;
import constants.EmployeeAPIEndPoint;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class EmployeeListServices extends CommonServices {

    public Response getAllEmployees(){
        setContentTypeAsURLENC();
        Map<String, String> queryParam = new HashMap<>();
        queryParam.put("include","supervisors,jobTitle,locations");
        queryParam.put("page[limit]","200");

        setQueryParameters(queryParam);
        return executeGetAPI(EmployeeAPIEndPoint.GET_EMPLOYEE_LIST);
    }
}
