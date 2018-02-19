import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.json.JSONArray;

class Person {
    public int id;
    public String name;
    public int age;
    public boolean active;
}

class Parse {
    public static void main(String[] args) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get("../sample1.json"));
            String str = new String(bytes, StandardCharsets.UTF_8);

            parseWithOrgJson(str);
            parseWithJackson(str);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void parseWithOrgJson(String str) {
        System.out.println("org.json -----------------------------------");

        JSONArray arr = new JSONArray(str);

        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);

            System.out.println("id = " + obj.getInt("id"));

            if (obj.has("name")) {
                if (obj.isNull("name")) {
                    System.out.println("name is null");
                } else {
                    String name = obj.getString("name");
                    System.out.println("name = " + name);
                }
            } else {
                System.out.println("name not exist");
            }

            if (obj.has("age")) {
                if (obj.isNull("age")) {
                    System.out.println("age is null");
                } else {
                    int age = obj.getInt("age");
                    System.out.println("age = " + age);
                }
            } else {
                System.out.println("age not exist");
            }

            if (obj.has("active")) {
                if (obj.isNull("active")) {
                    System.out.println("active is null");
                } else {
                    boolean active = obj.getBoolean("active");
                    System.out.println("active = " + active);
                }
            } else {
                System.out.println("active not exist");
            }
        }
    }

    private static void parseWithJackson(String str) {
        try {
            System.out.println("Jackson -----------------------------------");

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(str);

            for (JsonNode n : root) {
                String name = n.get("name").asText();
                System.out.println("name = " + name);

                if (n.get("age").isNull()) {
                    System.out.println("age is null");
                } else {
                    int age = n.get("age").asInt();
                    System.out.println("age = " + age);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
