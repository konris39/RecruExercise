package exercise.solution;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class PolicyVerifier {

    public static boolean verifyPolicy(String resourcePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream resourceStream = PolicyVerifier.class.getResourceAsStream(resourcePath)) {
            if (resourceStream == null) {
                throw new IllegalStateException("File does not exist: " + resourcePath);
            }

            InputStreamReader reader = new InputStreamReader(resourceStream, StandardCharsets.UTF_8);
            JsonNode rootNode = objectMapper.readTree(reader);

            if (rootNode == null || rootNode.isEmpty()) {
                return true;
            }

            JsonNode policyDocumentNode = rootNode.path("PolicyDocument");
            JsonNode statementsNode = policyDocumentNode.path("Statement");

            if (statementsNode.isArray()) {
                for (JsonNode statement : statementsNode) {
                    JsonNode resourceNode = statement.path("Resource");
                    if (resourceNode.isTextual() && "*".equals(resourceNode.asText())) {
                        return false;
                    }
                }
            }
            return true;

        } catch (JsonProcessingException e) {
            System.out.println("ERROR: Malformed JSON in " + resourcePath);
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println("===============================================================================================");
        System.out.println("|| Policy should be valid in example2, example3, example4 and should NOT be valid in example1. ||");
        System.out.println("===============================================================================================");
        String[] resourcePaths = {"/example1.json", "/example2.json"};

        for (String resourcePath : resourcePaths) {
            try {
                boolean isValid = verifyPolicy(resourcePath);
                System.out.println("Is policy in '" + resourcePath + "' valid? " + isValid);
            } catch (IllegalStateException | IOException e) {
                System.out.println("Verification failed for '" + resourcePath + "': " + e.getMessage());
            }
        }
    }
}
