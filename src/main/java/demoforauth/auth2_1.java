package demoforauth;

import static io.restassured.RestAssured.given;

import Pojo.Api;
import io.restassured.parsing.Parser;

import io.restassured.path.json.JsonPath;
import Pojo.getCourses;
import org.testng.Assert;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class auth2_1 {



    public static void main(String[] args) throws InterruptedException {

// TODO Auto-generated method stub
String [] courseTitles={"Selenium Webdriver Java","Cypress","Protractor"};
        String url ="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F0AbUR2VMbAW0IEs3cKQGR-FsACtRyevLe4fN0Rl7YNU42-YixQjkW_094XUs7JaS6lG4OjA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&session_state=0c32992f0d47e93d273922018ade42d1072b9d1f..a35c&prompt=none#";

        String partialcode=url.split("code=")[1];

        String code=partialcode.split("&scope")[0];

        System.out.println(code);

        String response =

                given()

                        .urlEncodingEnabled(false)

                        .queryParams("code",code)
                        .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")

                        .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")

                        .queryParams("grant_type", "authorization_code")

                        .queryParams("state", "verifyfjdss")

                        .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")

                        // .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")
                        .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")

                        .when().log().all()

                        .post("https://www.googleapis.com/oauth2/v4/token").asString();

// System.out.println(response);

        JsonPath jsonPath = new JsonPath(response);

        String accessToken = jsonPath.getString("access_token");

        System.out.println(accessToken);

        getCourses r2=    given().contentType("application/json").

                queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)

                .when()

                .get("https://rahulshettyacademy.com/getCourse.php")

                .as(getCourses.class);

        System.out.println(r2);
       System.out.println(r2.getLinkedIn());
        System.out.println(r2.getExpertise());
       System.out.println(r2.getCourses().getApi().get(1).getCourseTitle());
        List<Api> apiCourses=r2.getCourses().getApi();
        ArrayList<String> a = new ArrayList<String>();
        for(int i=0;i<apiCourses.size();i++) {
            if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")){

                System.out.println(apiCourses.get(i).getPrice());
            }


        }
        List<Pojo.webAutomation> w=r2.getCourses().getWebAutomation();
        for(int j =0;j<w.size();j++){

            a.add(w.get(j).getCourseTitle());
        }
       List<String> expecedResult=Arrays.asList(courseTitles);
        Assert.assertTrue(a.equals(expecedResult));

    }}


