package services;

import base.CommonServices;
import constants.SkillAPI;
import io.restassured.response.Response;

public class SkillServices extends CommonServices {

    public Response createSkill(String payload){
        setBody(payload);
        setContentTypeAsApplicationJSON();
        return executePostAPI(SkillAPI.SKILL_API);
    }

    public void updateSkill(String payload){

    }

    public Response getSkillList(){
        setContentTypeAsURLENC();
        return executeGetAPI(SkillAPI.SKILL_API);
    }

    public void deleteSkill(String skillID){

    }
}
