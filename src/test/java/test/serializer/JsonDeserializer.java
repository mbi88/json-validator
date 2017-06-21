package test.serializer;


import org.apache.maven.surefire.shade.org.apache.maven.shared.utils.io.IOUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;


public class JsonDeserializer {

    /**
     * Method to read a data from file from passed path and return Json object.
     *
     * @param path Path to the file with data. No need to add "src/main/resources" every time when you pass the path -
     *             it is already implemented in the method.
     * @return Json object
     */
    public static JSONObject getJsonFromFile(String path) {
        JSONObject json = new JSONObject();

        try {
            File file = new File(JsonDeserializer.class.getResource(path).toURI());
            FileInputStream fis = new FileInputStream(file);
            String string = IOUtil.toString(fis, "UTF-8");
            json = new JSONObject(string);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    public static JSONArray getJsonArrayFromFile(String path) {
        JSONArray json = new JSONArray();

        try {
            File file = new File(JsonDeserializer.class.getResource(path).toURI());
            FileInputStream fis = new FileInputStream(file);
            String string = IOUtil.toString(fis, "UTF-8");
            json = new JSONArray(string);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        return json;
    }
}
