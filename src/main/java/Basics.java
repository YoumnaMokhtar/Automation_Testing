import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import javax.print.DocFlavor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import  static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class Basics {
    public static void main (String args[]) throws IOException {

        RestAssured.baseURI="https://rahulshettyacademy.com";
     String Response=   given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body(data.addplace()).when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
        JsonPath js=new JsonPath(Response);
       String placeID= js.getString("place_id");
        String Newadress="30 Summer walk,UkA";
        System.out.println(placeID);

//        //Update a place for the data
//        String body="{\n" +
//                "\"place_id\":\""+placeID+"\",\n" +
//                "\"address\":\""+Newadress+"\",\n" +
//                "\"key\":\"qaclick123\"\n" +
//                "}";
//        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body(Files.readAllBytes(Paths.get("C:\\Users\\V22YMokhtar1\\Documents\\addplace"))).when().put("maps/api/place/update/json")
//                .then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
//
//
//       String getplaceresponse = given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeID).when()
//                .get("maps/api/place/get/json").then().statusCode(200).extract().response().asString();
//       JsonPath js1=Reusable_methods.rawtojson(getplaceresponse);
//       String Actualaddress= js1.getString("address");
//       System.out.println(Actualaddress);
//        Assert.assertEquals(Actualaddress,Newadress);

    }
}
