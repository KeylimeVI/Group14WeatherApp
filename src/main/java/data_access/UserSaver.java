package data_access;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserSaver {
	
	public static void saveUsers(Map<String, String> map) {
		saveJSONArray(MapToJSONArray(map));
	}
	
	private static void saveJSONArray(JSONArray array) {
		File file = new File("users.json");
		byte[] data = array.toString().getBytes();
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file, false);
			try {
				fileOutputStream.write(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static JSONArray MapToJSONArray(Map<String, String> map) {
		
		JSONArray result = new JSONArray();

		for (String item : map.keySet()) {
			JSONObject user = new JSONObject();
			user.put("username", item);
			user.put("password", map.get(item));

			result.put(user);
		}

		return result;
	}
}
