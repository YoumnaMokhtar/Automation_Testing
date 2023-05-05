import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test(dataProvider ="booksData" )
  public  void Addbook( String isbn,String aisle){
        RestAssured.baseURI="http://216.10.245.166";
       String Response= given().header("Content-Type","application/json").body(data.Addbook(isbn,aisle)).when().post("Library/Addbook.php")
                .then().assertThat().statusCode(200).extract().response().asString();
    JsonPath js= Reusable_methods.rawtojson(Response);
     String id=js.get("ID");
     System.out.println(id);

    }
    @DataProvider(name = "booksData")
    public Object[][] GetData() {

        return new Object [][] {{"40rules","5674"} , {"kafka","4567"} , {"1Q84","4422"}};

    }
}
