import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BoardTest extends BaseTest {




    @Test
    public void getAllTheListFromTheBoard() {

        RequestSpecification request = given().
                queryParam("key", key).
                queryParam("token", token).
                pathParam("id", "5ccfe39bea332852e5553d3b").log().all();

        List<Map<String, String>> mapList = request.when().get(Constants.getAllTheList).jsonPath().get();

        ArrayList actualList = new ArrayList();
        for (int i = 0; i < mapList.size(); i++) {
            System.out.println(mapList.get(i).get("name"));
            actualList.add(mapList.get(i).get("name"));
            ArrayList expectedList=new ArrayList();
            expectedList.add("To Do");
            expectedList.add("Doing");
            expectedList.add("Done");
            actualList.containsAll(expectedList);


        }
    }

        @Test
        public void CreateAList(){

            RequestSpecification request = given().
                    queryParam("key", key).
                    queryParam("token", token).
                    pathParam("id",BaordId)
                    .queryParam("name","List for creating card").contentType(ContentType.JSON).log().all();

           Response response= request.when().post("boards/{id}/lists");
       //     List<Map<String, String>> mapList = response.jsonPath().



        }













    }















      //  ValidatableResponse validate=response.then().contentType(ContentType.JSON).log().all();







