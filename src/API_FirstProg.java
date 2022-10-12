
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.Assert.*;

import files.dataLoad;
import files.resusableMethods;

import static org.hamcrest.Matcher.*;
public class API_FirstProg {

	public static void main(String[] args) {
		
		// validate add place 
		// given  - all inputs given - log all so that results show parameter given and results 
		//when - summit  - it has http method and resource .
		// then - validate the response 
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String jResp = given().log().all().queryParams
				("key", "qaclick123").header("Content-Type","application/json")
				.body(dataLoad.addData()).when()
				.post("maps/api/place/add/json").then()
				.assertThat()
				.statusCode(200).body("scope",equalTo("APP")).header("Server", "Apache/2.4.41 (Ubuntu)")
				.extract().response().asString();

		//add plcae -> update plce -> validate updation of new plcae 
		// After add we must get place id , so we can use it to update the same place .
		// put  it in string so we dont need it in console or we can remove log().all after then()
		// parse to json so that we can extract the desired value
		// pathe for location = location.lat 

		JsonPath js = new JsonPath(jResp); // to parse string response to Json
		String placeId = js.getString("place_id");  // it has no parent lik lat in location 
		System.out.println(placeId);

		// update the addres in new pplace 
		String newAddress = "Georgia,USA";
		given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\""+newAddress+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "").when().put("maps/api/place/update/json").then().assertThat().log().all()
		.statusCode(200).body("msg",equalTo("Address successfully updated"));

		//get - dont write header as  we dont send body in get 

		String getResp = given().queryParams("key", "qaclick123").queryParams("place_id", placeId)
				.when().get("maps/api/place/get/json").then().extract().response().asString();


		JsonPath js1 =resusableMethods.rawToJson(getResp);
		String extractedAddd = js1.getString("address");
		System.out.println("new adddress "+extractedAddd);
		Assert.assertEquals(extractedAddd, newAddress, "Address is uodated ");

	}

}
