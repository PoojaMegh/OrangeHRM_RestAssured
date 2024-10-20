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
        String payload = "{\n" +
                "    \"name\": \""+ TestData.getSkillName() +"\",\n" +
                "    \"description\": \""+TestData.getSkillDescription()+"\"\n" +
                "}";
        Response createSkillResponse = skillServices.createSkill(payload);
        System.out.println(createSkillResponse.asPrettyString());
        String skillID = createSkillResponse.jsonPath().getString("data.id") ;
        System.out.println("Skill ID :" + skillID);

        Response getSkillResponse = skillServices.getSkillList();
        List<String> skillIDList = getSkillResponse.jsonPath().getList("data.id");
        Assert.assertListContainsObject(skillIDList, skillID, "Skill not present");
        Assert.assertTrue(skillIDList.contains(skillID), "Skill not present");
    }
}
