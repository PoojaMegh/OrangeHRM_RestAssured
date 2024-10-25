package services;

import base.CommonServices;
import constants.EducationAPIEndPoint;
import entities.request.educationPayload.DeleteEducationPayload;
import io.restassured.response.Response;

public class EducationServices extends CommonServices {
    public Response createEducation(Object payload) {
        setBody(payload);
        setContentTypeAsApplicationJSON();
        return executePostAPI(EducationAPIEndPoint.EDUCATION_API);
    }

    public Response getEducationList() {
        setContentTypeAsURLENC();
        return executeGetAPI(EducationAPIEndPoint.EDUCATION_API);
    }

    public Response updateSkill(String payload, String recordID) {
        setBody(payload);
        setContentTypeAsApplicationJSON();
        return executePatchAPI(EducationAPIEndPoint.EDUCATION_API, recordID);
    }

    public Response deleteEducation(Object payload) {
        setBody(payload);
        setContentTypeAsApplicationJSON();
        return executeDeleteAPI(EducationAPIEndPoint.EDUCATION_API);
    }
}
