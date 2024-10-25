package testScripts;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.request.educationPayload.CreateEducationPayload;
import entities.request.educationPayload.DeleteEducationPayload;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.EducationServices;
import utility.TestData;

import java.util.ArrayList;
import java.util.List;

public class EducationTestScripts {

    EducationServices educationServices = new EducationServices();

    @Test
    public void educationCURDOperation() throws JsonProcessingException {

        //Create or Post Operation
        CreateEducationPayload createEducationPayload = CreateEducationPayload.builder().name(TestData.getEducationName()).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String payload = objectMapper.writeValueAsString(createEducationPayload);

        Response createEducationResponse = educationServices.createEducation(payload);
        System.out.println(createEducationResponse.asPrettyString());
        String educationID = createEducationResponse.jsonPath().getString("data.id");
        Assert.assertNotNull(educationID,"New Education not generated");
        Assert.assertEquals(createEducationResponse.statusCode(),201);
        System.out.println("New Education successfully created ! Education ID : " + educationID);
        System.out.println("===============================================");

        //Receive or Get Operation
        Response getEducationResponse = educationServices.getEducationList();
        List<String> educationIDList = getEducationResponse.jsonPath().getList("data.id");
        Assert.assertListContainsObject(educationIDList, educationID, "Education not present");
        Assert.assertTrue(educationIDList.contains(educationID));
        Assert.assertEquals(getEducationResponse.statusCode(), 200);
        System.out.println("New Education successfully retrieved !");
        System.out.println("===============================================");

        //Update or Put/Patch Operation
        Response updateEducationResponse = educationServices.updateSkill(payload, educationID);
        System.out.println(updateEducationResponse.asPrettyString());
        Assert.assertEquals(updateEducationResponse.statusCode(), 200);
        System.out.println("New Education successfully updated ! Education ID : " + educationID);
        System.out.println("===============================================");

        //Delete Operation
        ArrayList<String> educationIDListToDelete = new ArrayList();
        educationIDListToDelete.add(educationID);
        DeleteEducationPayload deleteEducationPayload = DeleteEducationPayload.builder().data(educationIDListToDelete).build();

        Response deleteEducationResponse = educationServices.deleteEducation(deleteEducationPayload);
        Response getDeletedEducationResponse = educationServices.getEducationList();
        List<String> educationIDListDeleted = getDeletedEducationResponse.jsonPath().getList("data.id");
        Assert.assertListNotContainsObject(educationIDListDeleted, educationID, "Education not deleted, still present in the list");
        Assert.assertFalse(educationIDListDeleted.contains(educationID));
        Assert.assertEquals(deleteEducationResponse.statusCode(), 204);
        System.out.println("Education successfully deleted ! Education ID : " + educationID);
    }
}
