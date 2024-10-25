package testScripts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.request.vendorPayload.CreateVendorPayload;
import entities.request.vendorPayload.DeleteVendorPayload;
import entities.response.vendorResponse.VendorDataRoot;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.VendorServices;
import utility.TestData;

import java.util.ArrayList;
import java.util.List;

public class VendorTestScripts {

    VendorServices vendorServices = new VendorServices();

    //Vendor ID variables
    public static int vendorID;
    public static String currentVendorID;
    public static String nextVendorID;

    @Test
    public void vendorCURDOperation() throws JsonProcessingException {

        //Create or Post Operation
        getVendorID();
        CreateVendorPayload createVendorPayload = CreateVendorPayload.builder()
                .email(TestData.getEmail())
                .address(TestData.getAddress())
                .website(TestData.getWebsite())
                .contactNo(TestData.getContactNumber())
                .vendorCode(nextVendorID)
                .vendorName(TestData.getVendorName()).build();

        ObjectMapper objectMapper = new ObjectMapper();
        String payload = objectMapper.writeValueAsString(createVendorPayload);

        System.out.println(payload);
        Response createVendorResponse = vendorServices.createVendor(payload);
        System.out.println(createVendorResponse.asPrettyString());
        //Using Deserialization
//        VendorDataRoot vendorDataRoot = createVendorResponse.as(VendorDataRoot.class);
//        System.out.println(vendorDataRoot);

        currentVendorID = createVendorResponse.jsonPath().getString("data.vendorCode") ;
        String newVendor = createVendorResponse.jsonPath().getString("data.vendorName");

        //Assertions
        Assert.assertNotNull(currentVendorID,"New Vendor not generated");
        Assert.assertEquals(createVendorResponse.statusCode(), 201);
        System.out.println("New Vendor successfully created ! \n Vendor ID : " + currentVendorID + "\n Vendor Name : " + newVendor);
        System.out.println("===============================================");

        //Receive or Get Operation
        Response getVendorResponse = vendorServices.getVendorList();
        List<String> vendorIDList = getVendorResponse.jsonPath().getList("data.vendorCode");
        Assert.assertListContainsObject(vendorIDList, currentVendorID, "Vendor not present");
        Assert.assertTrue(vendorIDList.contains(currentVendorID), "Vendor not present");
        Assert.assertEquals(getVendorResponse.statusCode(), 200);
        System.out.println("New Vendor successfully retrieved !");
        System.out.println("===============================================");

        //Update or Put/Patch Operation
        getVendorID();
        CreateVendorPayload updateVendorPayload = CreateVendorPayload.builder()
                .email(TestData.getEmail())
                .address(TestData.getAddress())
                .website(TestData.getWebsite())
                .contactNo(TestData.getContactNumber())
                .vendorCode(currentVendorID)
                .vendorName(TestData.getVendorName()).build();

        String patchPayload = objectMapper.writeValueAsString(updateVendorPayload);
        Response updateVendorResponse = vendorServices.updateVendor(patchPayload, String.valueOf(vendorID));
        System.out.println(updateVendorResponse.asPrettyString());
        Assert.assertEquals(updateVendorResponse.statusCode(), 200);
        System.out.println("Vendor successfully updated ! Vendor ID : " + currentVendorID);
        System.out.println("===============================================");

        //Delete Operation
        ArrayList<Integer> vendorIDListToDelete = new ArrayList();
        vendorIDListToDelete.add(vendorID);
        DeleteVendorPayload deleteVendorPayload = DeleteVendorPayload.builder().ids(vendorIDListToDelete).build();

        Response deleteVendorResponse = vendorServices.deleteVendor(deleteVendorPayload);
        Response getDeletedVendorResponse = vendorServices.getVendorList();
        List<String> vendorIDListDeleted = getDeletedVendorResponse.jsonPath().getList("data.id");
        Assert.assertListNotContainsObject(vendorIDListDeleted, String.valueOf(vendorID), "Vendor not deleted, still present in the list");
        Assert.assertFalse(vendorIDListDeleted.contains(vendorID), "Vendor not deleted, still present in the list");
        Assert.assertEquals(deleteVendorResponse.statusCode(), 204);
        System.out.println("Vendor successfully deleted ! Vendor ID : " + currentVendorID);

    }

    public void getVendorID() throws JsonProcessingException {

        Response getVendorResponse = vendorServices.getVendorList();
        ObjectMapper objectMapper = new ObjectMapper();
        currentVendorID = objectMapper.writeValueAsString(getVendorResponse.jsonPath().get("meta.lastVendorId"));
        vendorID = Integer.parseInt(currentVendorID);

        //Adding prefix 0/00 to currentVendorID
        if(currentVendorID.length() == 1){
            currentVendorID = "00" + currentVendorID;
        } else if (currentVendorID.length() == 2){
            currentVendorID = "0" + currentVendorID;
        }

        nextVendorID = String.valueOf(vendorID + 1);

        //Adding prefix 0/00 to currentVendorID
        if(nextVendorID.length() == 1){
            nextVendorID = "00" + nextVendorID;
        } else if (nextVendorID.length() == 2){
            nextVendorID = "0" + nextVendorID;
        }

        System.out.println("Current Vendor ID : " + currentVendorID + "\nNext Available Vendor ID : " + nextVendorID);
    }
}
