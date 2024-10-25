package services;

import base.CommonServices;
import constants.SkillAPIEndPoint;
import io.restassured.response.Response;

public class SkillServices extends CommonServices {

    public Response createSkill(Object payload){
        setBody(payload);
        setContentTypeAsApplicationJSON();
        return executePostAPI(SkillAPIEndPoint.SKILL_API);
    }

    public Response updateSkill(String payload, String recordID){
        setBody(payload);
        setContentTypeAsApplicationJSON();
        return executePatchAPI(SkillAPIEndPoint.SKILL_API, recordID);
    }

    public Response getSkillList(){
        setContentTypeAsURLENC();
        return executeGetAPI(SkillAPIEndPoint.SKILL_API);
    }

    public Response deleteSkill(Object payload){
        setBody(payload);
        setContentTypeAsApplicationJSON();
        return executeDeleteAPI(SkillAPIEndPoint.SKILL_API);
    }
}
