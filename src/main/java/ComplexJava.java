import io.restassured.path.json.JsonPath;

public class ComplexJava {

    public static void main (String args[]){
        JsonPath js=new JsonPath(data.CoursePrice());
      int count=  js.getInt("courses.size()");
      System.out.println(count);
    int purchaseAmount = js.getInt("dashboard.purchaseAmount");
      System.out.println(purchaseAmount);
       String titles=js.get("courses[1].title");
       System.out.println(titles);

       //print all courses titles and their prices
        for(int i =0;i<count;i++){
            String CourseTitles=js.get("courses["+i+"].title");
             System.out.println(CourseTitles);
            int CoursePrices=js.getInt("courses["+i+"].price");
            System.out.println(CoursePrices);
          if(CourseTitles.equalsIgnoreCase("RPA")){
              int Copies=js.getInt("courses["+i+"].copies");
              System.out.println(Copies);
              break;
          }

        }
    }
}
