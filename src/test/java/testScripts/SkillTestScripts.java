package testScripts;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import services.SkillServices;
import utility.TestData;

import java.util.List;

public class SkillTestScripts {

    SkillServices skillServices = new SkillServices();

    @Test
    public void skillCURDOperations(){
        //Create or Post Operation
        String payload = "{\n" +
                "    \"name\": \""+ TestData.getSkillName() +"\",\n" +
                "    \"description\": \""+TestData.getSkillDescription()+"\"\n" +
                "}";
        Response createSkillResponse = skillServices.createSkill(payload);
        System.out.println(createSkillResponse.asPrettyString());
        String skillID = createSkillResponse.jsonPath().getString("data.id") ;
        Assert.assertNotNull(skillID,"New Skill not generated");
        Assert.assertEquals(createSkillResponse.statusCode(), 201);
        System.out.println("New Skill successfully created ! Skill ID : " + skillID);


        //Receive or Get Operation
        Response getSkillResponse = skillServices.getSkillList();
        List<String> skillIDList = getSkillResponse.jsonPath().getList("data.id");
        Assert.assertListContainsObject(skillIDList, skillID, "Skill not present");
        Assert.assertTrue(skillIDList.contains(skillID), "Skill not present");
        Assert.assertEquals(getSkillResponse.statusCode(), 200);
        System.out.println("New Skill successfully retrieved !");

        //Update or Put Operation
        Response updateSkillResponse = skillServices.updateSkill(payload, skillID);
        System.out.println(updateSkillResponse.asPrettyString());
        Assert.assertEquals(updateSkillResponse.statusCode(), 200);
        System.out.println("Skill successfully updated ! Skill ID : " + skillID);

        //Delete Operation
        String deletePayload = "{\n" +
                "    \"data\": [\n" +
                "        \""+ skillID +"\"\n" +
                "    ]\n" +
                "}";
        Response deleteSkillResponse = skillServices.deleteSkill(deletePayload);
        Response getSkillResponseDeleted = skillServices.getSkillList();
        List<String> skillIDListDeleted = getSkillResponseDeleted.jsonPath().getList("data.id");
        Assert.assertListNotContainsObject(skillIDListDeleted, skillID, "Skill not deleted, still present in the list");
        Assert.assertFalse(skillIDListDeleted.contains(skillID), "Skill not deleted, still present in the list");
        Assert.assertEquals(deleteSkillResponse.statusCode(), 204);
        System.out.println("Skill successfully deleted ! Skill ID : " + skillID);
    }
}
