package testScripts;

import entities.response.employeeList.EmployeeDataRoot;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import services.DashboardServices;
import services.EmployeeListServices;

public class DashboardTestScripts {

    DashboardServices dashboardServices = new DashboardServices();
    EmployeeListServices employeeListServices = new EmployeeListServices();

    @Test
    public void widgetAPI(){
        Response response = dashboardServices.getWidget();

        System.out.println(response.asPrettyString());
    }

    @Test
    public void employeeListTest(){
        Response response = employeeListServices.getAllEmployees();
        System.out.println("Total Employees in th System : "+response.jsonPath().get("data.size()").toString());
        //System.out.println(response.asPrettyString());

        //If empNumber is in numberic
 //       List<String > listOfName = response.jsonPath().get("data.findAll { it.empNumber > 150 }.firstName");

        //If empNumber is not in numberic
//        System.out.println("All Employees having employeeId greater than 150 : ");
//        for (int i=0; i<Integer.parseInt(response.jsonPath().get("data.size()").toString()); i++){
//            int empNumber = Integer.parseInt(response.jsonPath().getString("data["+i+"].empNumber"));
//            if(empNumber>150){
//                System.out.println((response.jsonPath().getString("data["+i+"].firstName")));
//            }
//        }

        //Using Deserialization
        EmployeeDataRoot employeeDataRoot = response.as(EmployeeDataRoot.class);
        //System.out.println(employeeDataRoot);
        System.out.println("All Employees having employeeId greater than 150 : ");
        for (int i =0; i< employeeDataRoot.getData().size(); i++){
            int empNumber = Integer.parseInt(employeeDataRoot.getData().get(i).empNumber);
            if (empNumber > 150){
                System.out.println(employeeDataRoot.getData().get(i).firstName);
            }
        }

    }
}
