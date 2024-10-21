package base;

import constants.CommonAPI;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

public class BaseServices {
    RequestSpecBuilder requestSpecBuilder;

    public BaseServices(){
        requestSpecBuilder = new RequestSpecBuilder();
    }

    public void buildServices(){
        requestSpecBuilder.setBaseUri(CommonAPI.BASE_URI);
    }

    public void setContentTypeAsURLENC(){
        setUp();
        requestSpecBuilder.setContentType(ContentType.URLENC);
    }

    public void setContentTypeAsApplicationJSON(){
        setUp();
        requestSpecBuilder.setContentType(ContentType.JSON);
    }
    public void setToken(){
        setUp();
        requestSpecBuilder.addHeader("Authorization", "Bearer " + CommonServices.getAccessToken());
    }

    public void setCookies(){
        setUp();
        requestSpecBuilder.addCookies(CommonServices.cookies);
    }

    public void setQueryParameters(Map<String , String > queryParameters){
        setUp();
        requestSpecBuilder.addQueryParams(queryParameters);
    }

    public void setParameter(String parameter){
        setUp();
        requestSpecBuilder.addParam(parameter);
    }

    public void setBody(String payload){
        setUp();
        requestSpecBuilder.setBody(payload);
    }

    public Response executeGetAPI(String endPoint){
        setToken();
        setCookies();

        Response response = RestAssured.given()
                .spec(requestSpecBuilder.build())
                .when()
                .get(endPoint)
                .then().extract().response();
        tearDown();
        return response;
    }

    public Response executePostAPI(String endPoint){
        setToken();
        setCookies();

        Response response = RestAssured.given()
                .spec(requestSpecBuilder.build())
                .when()
                .post(endPoint)
                .then().extract().response();
        tearDown();
        return response;
    }


    public Response executePutAPI(String endPoint){
        setToken();
        setCookies();

        Response response = RestAssured.given()
                .spec(requestSpecBuilder.build())
                .when()
                .put(endPoint)
                .then().extract().response();
        tearDown();
        return response;
    }

    public Response executePatchAPI(String endPoint, String recordID){
        setToken();
        setCookies();

        Response response = RestAssured.given()
                .spec(requestSpecBuilder.build())
                .when()
                .patch(endPoint +"/"+ recordID)
                .then().extract().response();
        tearDown();
        return response;
    }

    public Response executeDeleteAPI(String endPoint){
        setToken();
        setCookies();

        Response response = RestAssured.given()
                .spec(requestSpecBuilder.build())
                .when()
                .delete(endPoint)
                .then().extract().response();
        tearDown();
        return response;
    }


    private void setUp(){
        if (requestSpecBuilder == null){
            requestSpecBuilder = new RequestSpecBuilder();
        }
        requestSpecBuilder.setBaseUri(CommonAPI.BASE_URI);
    }

    private void tearDown(){
        requestSpecBuilder = null;
    }
}
