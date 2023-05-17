import Pojo.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class EcommerceApi {

    public static void main(String args[]){
        RequestSpecification req= new RequestSpecBuilder().setBaseUri( "https://rahulshettyacademy.com" )
                .setContentType( ContentType.JSON ).build();
      LoginRequest loginrequest = new LoginRequest();
      loginrequest.setUserEmail( "youmna.m.moktar@gmail.com" );
      loginrequest.setUserPassword( "Y_m15627100" );
         RequestSpecification loginreq=given().relaxedHTTPSValidation().log().all().spec( req ).body( loginrequest);

       LoginResponse loginResponse=  loginreq.when().post("api/ecom/auth/login").then().extract().response().as(LoginResponse.class);
       System.out.println(loginResponse.getToken());
        String token = loginResponse.getToken();
       System.out.println(loginResponse.getUserId());
        String userId = loginResponse.getUserId();

       // Add product
        RequestSpecification addProducrbaseRequest= new RequestSpecBuilder().setBaseUri( "https://rahulshettyacademy.com")
                .addHeader( "authorization" ,token).build();
             RequestSpecification addProd= given().log().all().spec( addProducrbaseRequest ).param( "productName","bird" )
         .param( "productAddedBy",userId ).param( "productCategory","fashion" )
                      .param( "productSubCategory" ,"birds").param( "productPrice","1000" ).param("productDescription","birdsforsale" )
                      .param( "productFor" ,"kids").multiPart( "productImage",new File( "C://Users//V22YMokhtar1//Pictures//OIPCFVZL10Q.png" ) );
ProductDetails pro= new ProductDetails();

            String  AddProdResponse=  addProd.when().post("api/ecom/product/add-product")
                    .then().log().all().extract().response().asString();
            System.out.println(pro.getProductId());

            //Create order
        RequestSpecification createOrderBaseRequest=new RequestSpecBuilder().setBaseUri( "https://rahulshettyacademy.com" )
                .addHeader( "authorization" ,token).setContentType( ContentType.JSON ).build();
        Orders orders= new Orders();
        orderDetails orderDet= new orderDetails();
        orderDet.setCountry( "India" );
        orderDet.setProductOrderedId(pro.setProductId("64622f0d568c3e9fb16c8ca7"));
        List<orderDetails> orderDetailsList = new ArrayList<orderDetails>();
        orderDetailsList.add( orderDet );
        orders.setOrders( orderDetailsList );

      RequestSpecification createOrderReq= given().log().all().spec( createOrderBaseRequest).body( orders );

             String Addorder=createOrderReq.when().post("/api/ecom/order/create-order")
                     .then().log().all().extract().response().asString();

        RequestSpecification deleteProductBaseURi=new RequestSpecBuilder().setBaseUri( "https://rahulshettyacademy.com" )
                .addHeader( "authorization" ,token).build();
        RequestSpecification deleteProduct=given().log().all().spec( deleteProductBaseURi ).pathParam("productId",pro.setProductId( "64622f0d568c3e9fb16c8ca7"));
            String deleteproductdet=deleteProduct.when().delete("https://rahulshettyacademy.com/api/ecom/product/delete-product/{productId}").then().log().all()
                .extract().response().asString();
    }
}
