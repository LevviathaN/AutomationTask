import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.util.*;

public class FileReader {

    public static Map<String, String> toMapFromFile(String file) {
        Gson g = new Gson();
        try {
            return g.fromJson(new JsonReader(new java.io.FileReader(file)), Map.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
