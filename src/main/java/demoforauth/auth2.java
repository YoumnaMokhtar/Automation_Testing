package demoforauth;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
public class auth2 {

    public static void main (String args[]){

        String url="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AbUR2VOz9muLP9KmCIMYQBTPuAoaCiCRcw98bTXs6yXUma1KKSmUVuF1y9LteeILDpw9eg&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=1&prompt=none";

        String code="4%2F0AbUR2VOz9muLP9KmCIMYQBTPuAoaCiCRcw98bTXs6yXUma1KKSmUVuF1y9LteeILDpw9eg";
       String accessTokenResponse= given().urlEncodingEnabled(false).queryParam("code",code)
                .queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParam("grant_type","authorization_code").when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
        System.out.println(accessTokenResponse);
        JsonPath jp = new JsonPath(accessTokenResponse);
        String access_token = jp.getString("access_token");

        System.out.println(access_token);




        String response=  given().queryParam("access_token",access_token).when().log().all().get("https://rahulshettyacademy.com/getCourse.php")
                .asString();
        System.out.println(response);


    }

}
