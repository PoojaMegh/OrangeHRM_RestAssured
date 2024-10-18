package standAloneScripts;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HRMLogin {

    RequestSpecBuilder requestSpecBuilder;

    @BeforeMethod
    public void setup(){
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://apipoojamegh-trials714.orangehrmlive.com");
        requestSpecBuilder.setBasePath("/auth");
    }

    @Test
    public void getCSRFToken(){
        Response response = RestAssured.given()
                .spec(requestSpecBuilder.build())
                .when()
                .get("/loginnn")
                .then().extract().response();

        //Option 1
        Document document = Jsoup.parse(response.asString());
        String csrfToken = document.getElementById("login__csrf_token").attr("value");
        System.out.println("CSRF Token  : " + csrfToken);

        //Option 2
        String csrfToken1 = "";
        String regularExpression = "([A-z0-9]{32})";
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(response.asString());

        while (matcher.find()){
            csrfToken1 = matcher.group();
        }
        System.out.println("CSRF Token1 : " + csrfToken1);
    }
}
