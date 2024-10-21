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

    public Response updateSkill(String payload, String recordID){
        setBody(payload);
        setContentTypeAsApplicationJSON();
        return executePatchAPI(SkillAPI.SKILL_API, recordID);
    }

    public Response getSkillList(){
        setContentTypeAsURLENC();
        return executeGetAPI(SkillAPI.SKILL_API);
    }

    public Response deleteSkill(String payload){
        setBody(payload);
        setContentTypeAsApplicationJSON();
        return executeDeleteAPI(SkillAPI.SKILL_API);
    }
}
