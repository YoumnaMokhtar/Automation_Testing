import io.restassured.path.json.JsonPath;

public class Reusable_methods {

    public  static  JsonPath rawtojson(String Response){
        JsonPath js=new JsonPath(Response);
        return js;

    }
}
