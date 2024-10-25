package testScripts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.request.skillPayload.CreateSkillPayload;
import entities.request.skillPayload.DeleteSkillPayload;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.SkillServices;
import utility.TestData;

import java.util.ArrayList;
import java.util.List;

public class SkillTestScripts {

    SkillServices skillServices = new SkillServices();
    @Test
    public void skillCURDOperations() throws JsonProcessingException {
        //Create or Post Operation
        CreateSkillPayload createSkillPayload = CreateSkillPayload.builder().name(TestData.getSkillName()).description(TestData.getSkillDescription()).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String payload = objectMapper.writeValueAsString(createSkillPayload);

        Response createSkillResponse = skillServices.createSkill(payload);
        System.out.println(createSkillResponse.asPrettyString());
        String skillID = createSkillResponse.jsonPath().getString("data.id") ;
        Assert.assertNotNull(skillID,"New Skill not generated");
        Assert.assertEquals(createSkillResponse.statusCode(), 201);
        System.out.println("New Skill successfully created ! Skill ID : " + skillID);
        System.out.println("===============================================");

        //Receive or Get Operation
        Response getSkillResponse = skillServices.getSkillList();
        List<String> skillIDList = getSkillResponse.jsonPath().getList("data.id");
        Assert.assertListContainsObject(skillIDList, skillID, "Skill not present");
        Assert.assertTrue(skillIDList.contains(skillID), "Skill not present");
        Assert.assertEquals(getSkillResponse.statusCode(), 200);
        System.out.println("New Skill successfully retrieved !");
        System.out.println("===============================================");

        //Update or Put/Patch Operation
        Response updateSkillResponse = skillServices.updateSkill(payload, skillID);
        System.out.println(updateSkillResponse.asPrettyString());
        Assert.assertEquals(updateSkillResponse.statusCode(), 200);
        System.out.println("Skill successfully updated ! Skill ID : " + skillID);
        System.out.println("===============================================");

        //Delete Operation
        ArrayList<String> skillIDListToDelete = new ArrayList();
        skillIDListToDelete.add(skillID);
        DeleteSkillPayload deleteSkillPayload = DeleteSkillPayload.builder().data(skillIDListToDelete).build();

        Response deleteSkillResponse = skillServices.deleteSkill(deleteSkillPayload);
        Response getDeletedSkillResponse = skillServices.getSkillList();
        List<String> skillIDListDeleted = getDeletedSkillResponse.jsonPath().getList("data.id");
        Assert.assertListNotContainsObject(skillIDListDeleted, skillID, "Skill not deleted, still present in the list");
        Assert.assertFalse(skillIDListDeleted.contains(skillID), "Skill not deleted, still present in the list");
        Assert.assertEquals(deleteSkillResponse.statusCode(), 204);
        System.out.println("Skill successfully deleted ! Skill ID : " + skillID);

    }
}
