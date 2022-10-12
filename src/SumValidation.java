
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import files.dataLoad;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	@Test
	public void sumOfCourses() {
		JsonPath js = new JsonPath(dataLoad.coursePrice());
		int countCourses = js.getInt("courses.size()");
		for(int i =0;i<countCourses;i++) {
		int prices = js.getInt("courses.price");
		int copies =js.getInt("courses.copies");
		int purchaseAmount = prices*copies;
		System.out.println(purchaseAmount);
		
		}
	}
}
