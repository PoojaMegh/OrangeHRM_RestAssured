package entities.response.employeeList;

import java.util.ArrayList;

public class Datum{
    public String empNumber;
    public String firstName;
    public String lastName;
    public String middleName;
    public Object termination_id;
    public String employeeId;
    public String joinedDate;
    public boolean deletedEmployee;
    public ArrayList<Supervisor> supervisors;
    public ArrayList<Location> locations;
    public JobTitle jobTitle;
    public String job_title_code;
    public EmployeeStatus employeeStatus;
    public String emp_status;
    public SubDivision subDivision;
    public String work_station;
    public FullSubUnitHierarchy fullSubUnitHierarchy;
    public CostCentre costCentre;
    public String cost_centre_id;
    public boolean hasAccessiblePimTabs;
}