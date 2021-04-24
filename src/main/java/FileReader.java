import com.google.common.base.Joiner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileReader {
    public static final <T> List<T> toObjectListFromFile(final Class<T[]> clazz, final File file) throws IOException {
        T[] jsonToObject = null;
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .serializeNulls()
                .create();
        try {
            jsonToObject = gson.fromJson(new JsonReader(new java.io.FileReader(file)), clazz);
        } catch (JsonSyntaxException e){
            List<String> objects = Files.readAllLines(Paths.get(file.getAbsolutePath()));
            objects.removeAll(Collections.singleton(""));
            String jsonAsString = "["+ Joiner.on(",").join(objects)+"]";
            jsonToObject = gson.fromJson(jsonAsString, clazz);
        }
        List<T> result = new ArrayList<>(Arrays.asList(jsonToObject));
        result.removeAll(Collections.singleton(null));
        return result;
    }

    public static Map<String,String> getLocatorsMap(String locatorsFile) {
        Map<String,String> locatorsMap = new HashMap<>();
//        try {
//            List<Locator> locators = toObjectListFromFile(Locator[].class,
//                    new File(locatorsFile));
//            for (Locator locator : locators) {
//                locatorsMap.put(locator.getName(),locator.getValue());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return locatorsMap;
    }
}
