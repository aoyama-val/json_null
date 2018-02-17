import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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

            parseWithJackson(str);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void parseWithJackson(String str) {
        try {
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
