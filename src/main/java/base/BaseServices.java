package base;

import constants.CommonAPI;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BaseServices {
    RequestSpecBuilder requestSpecBuilder;

    public BaseServices(){
        requestSpecBuilder = new RequestSpecBuilder();
    }
    public void buildServices(){
        requestSpecBuilder.setBaseUri(CommonAPI.BASE_URI);
    }

    public void setContentTypeAsURLENC(){
        requestSpecBuilder.setContentType(ContentType.URLENC);
    }

    public void setContentTypeAsApplicationJSON(){
        requestSpecBuilder.setContentType(ContentType.JSON);
    }
    public void setToken(){
        requestSpecBuilder.addHeader("Authorization", "Bearer " + CommonServices.getAccessToken());
    }

    public void setCookies(){
        requestSpecBuilder.addCookies(CommonServices.cookies);
    }

    public Response executeGetAPI(String endPoint){
        buildServices();
        setToken();
        setCookies();

        Response response = RestAssured.given()
                .spec(requestSpecBuilder.build())
                .when()
                .get(endPoint)
                .then().extract().response();
        return response;
    }

    public Response executePostAPI(String endPoint){
        buildServices();
        return RestAssured.given()
                .spec(requestSpecBuilder.build())
                .when()
                .get(endPoint)
                .then().extract().response();
    }

    public Response executePutAPI(String endPoint){
        buildServices();
        return RestAssured.given()
                .spec(requestSpecBuilder.build())
                .when()
                .get(endPoint)
                .then().extract().response();
    }
}
