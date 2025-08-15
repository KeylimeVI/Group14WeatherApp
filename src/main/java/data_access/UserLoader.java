package data_access;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserLoader {
	
	private static final String usersFilepath = "users.json";
	private static JSONArray usersJSON;

	public static Map<String, String> loadUserMap() {
		try {
			File usersFile = new File(usersFilepath);
			usersJSON = new JSONArray(usersFile.toString());

			usersFile.createNewFile();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return JSONToUserMap(usersJSON);
	}

	private static Map<String, String> JSONToUserMap(JSONArray jArr) {
		Map<String, String> result = new HashMap<>();

		for (int i = 0; i < jArr.length(); i++) {
			JSONObject user = jArr.getJSONObject(i);
			String username = user.getString("username");
			String password = user.getString("password");
			
			result.put(username, password);
		}

		return result;
	}
}
