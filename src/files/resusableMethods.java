package files;

import io.restassured.path.json.JsonPath;

public class resusableMethods {
public static JsonPath rawToJson(String jspath) {
	 JsonPath js = new JsonPath(jspath);
	 return js;
}
}
