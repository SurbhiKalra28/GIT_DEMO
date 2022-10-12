package API_Testing;

import files.dataLoad;
import files.resusableMethods;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {

		JsonPath js = new JsonPath(dataLoad.coursePrice());
		//1. Print No of courses returned by API
		int countCourses = js.getInt("courses.size()");
		System.out.println("Total courses "+countCourses);



		//2.Print Purchase Amount
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount "+purchaseAmount);
		//3. Print Title of the first course

		String firstCourse = js.get("courses[0].title");
		System.out.println("First Course is "+firstCourse);
		//4. Print All course titles and their respective Prices
		for(int i =0;i<countCourses;i++) {
			String title = js.get("courses["+i+"].title");
			int price = js.getInt("courses["+i+"].price");
			System.out.println("title is "+title+" and it's price is "+price);
			
			//as we dont know what would be the output and sysout takes 
			 //onlt string so convert with tostring
			System.out.println(	 js.get("courses["+i+"].price").toString());
		}
		//5. Print no of copies sold by RPA Course
		    for (int i = 0; i<countCourses;i++) {
		    	String courseTitle = js.getString("courses["+i+"].title");
		    	if(courseTitle.equalsIgnoreCase("RPA")) {
		    		int copies = js.getInt("courses["+i+"].copies");
		    		System.out.println("Number od copies sold for "+courseTitle+ "is" +copies);
		    	}
		    }

		//6. Verify if Sum of all Course prices matches with Purchase Amount
		    
		    
	}

}
