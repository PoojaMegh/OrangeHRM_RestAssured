package entities.response.employeeList;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class EmployeeDataRoot{
    public ArrayList<Datum> data;
    public Meta meta;

    @Override
    public String toString() {
        return "EmployeeDataRoot{" +
                "data=" + data +
                ", meta=" + meta +
                '}';
    }
}