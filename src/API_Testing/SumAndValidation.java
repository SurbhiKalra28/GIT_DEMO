package API_Testing;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import files.dataLoad;
import io.restassured.path.json.JsonPath;

public class SumAndValidation {
	@Test
	public void sumOfCourses() {
		JsonPath js = new JsonPath(dataLoad.coursePrice());
		int countCourses = js.getInt("courses.size()");
		int sum = 0;
		for(int i =0;i<countCourses;i++) {
		int prices = js.getInt("courses["+i+"].price");
		int copies =js.getInt("courses["+i+"].copies");
		int purchaseAmount = prices*copies;
		
		
		System.out.println(purchaseAmount);
		sum=sum+ prices*copies;
		
		}
		System.out.println("Total is :"+sum);
		Assert.assertEquals(js.getInt("dashboard.purchaseAmount"), sum, "total amount ");
		
}}
