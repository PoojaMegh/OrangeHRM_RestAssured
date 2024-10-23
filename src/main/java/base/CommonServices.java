package base;

import constants.CommonAPIEndPoint;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.Assert;

import java.util.Map;

public class CommonServices extends BaseServices{

    private static String csrfToken;
    public static Map<String,String> cookies;
    private static String accessToken;

    public static String getAccessToken(){
        getCSRFToken();
        validateCredential();

        Response response = RestAssured.given()
                .baseUri(CommonAPIEndPoint.BASE_URI)
                .cookies(cookies)
                .when()
                .get("/core/getLoggedInAccountToken")
                .then().extract().response();

        accessToken = response.jsonPath().getString("token.access_token");
        Assert.assertNotNull(accessToken,"Access token not generated");
        Assert.assertEquals(response.statusCode(), 200);
        return accessToken;
    }

    public static void getCSRFToken(){
        Response response = RestAssured.given()
                .baseUri(CommonAPIEndPoint.BASE_URI)
                .when()
                .get("/auth/login")
                .then().extract().response();

        cookies = response.getCookies();
        //Option 1
        Document document = Jsoup.parse(response.asString());
        csrfToken = document.getElementById("login__csrf_token").attr("value");
       // System.out.println("CSRF Token  : " + csrfToken);
        Assert.assertNotNull(csrfToken);
        Assert.assertEquals(response.statusCode(), 200);
    }

    public static void validateCredential(){
        Response response = RestAssured.given()
                .baseUri(CommonAPIEndPoint.BASE_URI)
                .cookies(cookies)
                .formParam("login[_csrf_token]", csrfToken)
                .formParam("hdnUserTimeZoneOffset", 5.5)
                .formParam("txtUsername", "Admin")
                .formParam("txtPassword", "xfXJQWu03@")
                .when()
                .post("/auth/validateCredentials")
                .then().extract().response();
        cookies = response.getCookies();
        Assert.assertEquals(response.statusCode(), 200);
    }
}
