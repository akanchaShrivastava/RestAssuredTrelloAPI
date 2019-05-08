import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import utilities.Constants;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseTest {

    String key="0697ace29da135af1009cc535346c753";
    String token="2dc2cf3eae74c9eb992d3adc18809edc9f84fb5f1445711741b746f5fee69d9d";
    String BaordId;


    @BeforeTest
    public void setup() {


        RestAssured.baseURI = "https://api.trello.com/1/";
        createBoard();


    }
   // @BeforeSuite
    public void createBoard(){
        RestAssured.baseURI = "https://api.trello.com/1/";


        JSONObject requestJson=new JSONObject();


            requestJson.put("key",key);
            requestJson.put("token",token);
            requestJson.put("name","NewBoard1");



            RequestSpecification request = given()
                .body(requestJson.toJSONString())
                .log().all()
                .contentType(ContentType.JSON);



        Response response = request.when().post(Constants.createBoard);
        System.out.println(response.getBody());
        Map<String, String> mapValues = response.then().contentType(ContentType.JSON).extract().jsonPath().get();

        System.out.println(mapValues.get("name"));
        String nameOfTheBoard=mapValues.get("name");
        // mapValues.containsKey("id");
        BaordId=mapValues.get("id");
        System.out.println(BaordId);
        Assert.assertEquals(nameOfTheBoard,"NewBoard1");
        mapValues.containsKey("id");





    }
    @AfterTest
    public void teardown(){
        RequestSpecification request = given()
                .queryParam( "key",key)
                . queryParam("token", token).pathParam("id","BaordId").log().all().contentType(ContentType.JSON);


        Response response = request.when().delete("boards/{id}");
        response.getStatusCode();





    }
}
