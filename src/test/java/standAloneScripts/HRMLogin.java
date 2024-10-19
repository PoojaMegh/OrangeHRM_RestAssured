package standAloneScripts;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HRMLogin {

    RequestSpecBuilder requestSpecBuilder;
    String csrfToken;
    Map<String,String> cookies;
    String accessToken;

    @BeforeMethod
    public void setup(){
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://apipoojamegh-trials714.orangehrmlive.com");
    }

    @Test(priority = 0)
    public void getCSRFToken(){
        Response response = RestAssured.given()
                .spec(requestSpecBuilder.build())
                .when()
                .get("/auth/login")
                .then().extract().response();

        cookies = response.getCookies();
        //Option 1
        Document document = Jsoup.parse(response.asString());
        csrfToken = document.getElementById("login__csrf_token").attr("value");
        System.out.println("CSRF Token  : " + csrfToken);
        Assert.assertNotNull(csrfToken,"CSRF token not generated");
        Assert.assertEquals(response.statusCode(), 200);

        //Option 2
//        String csrfToken1 = "";
//        String regularExpression = "([A-z0-9]{32})";
//        Pattern pattern = Pattern.compile(regularExpression);
//        Matcher matcher = pattern.matcher(response.asString());
//
//        while (matcher.find()){
//            csrfToken1 = matcher.group();
//        }
//        System.out.println("CSRF Token1 : " + csrfToken1);
    }

    @Test (priority = 1)
    public void validateCredential(){
        Response response = RestAssured.given()
                .spec(requestSpecBuilder.build())
                    .cookies(cookies)
                    .formParam("login[_csrf_token]", csrfToken)
                    .formParam("hdnUserTimeZoneOffset", 5.5)
                    .formParam("txtUsername", "Admin")
                    .formParam("txtPassword", "xfXJQWu03@")
                .when()
                    .post("/auth/validateCredentials")
                .then().extract().response();
        //System.out.println(response.asPrettyString());
        cookies = response.getCookies();
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test (priority = 2)
    public void getAccessToken(){
        Response response = RestAssured.given()
                .spec(requestSpecBuilder.build())
                .cookies(cookies)
                .when()
                .get("/core/getLoggedInAccountToken")
                .then().extract().response();

        System.out.println(response.asPrettyString());
        accessToken = response.jsonPath().getString("token.access_token");

        Assert.assertNotNull(accessToken,"Access token not generated");
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test (priority = 3)
    public void widgetAPI(){
        Map<String,String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + accessToken);
        headers.put("Content-type", "application/x-www-form-urlencoded");
        Response response = RestAssured.given()
                .spec(requestSpecBuilder.build())
                .headers(headers)
                .cookies(cookies)
                .when()
                .get("/api/dashboard/widgets")
                .then().extract().response();

        System.out.println(response.asPrettyString());

        Assert.assertEquals(response.statusCode(), 200);
    }
}
