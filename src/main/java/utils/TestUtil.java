package utils;

import org.json.JSONObject;

public class TestUtil {
		public static boolean jsonHasKey(String json,String key) {
				JSONObject jsonObject = new JSONObject(json);
				return jsonObject.has(key);
		}
		
		public static String jsonHasValue(String json,String key) {
			JSONObject jsonObject = new JSONObject(json);
			return jsonObject.get(key).toString();
		}
}
