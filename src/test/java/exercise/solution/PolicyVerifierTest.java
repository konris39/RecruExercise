package exercise.solution;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PolicyVerifierTest {

    @Test
    public void testExample1JsonPolicyValidity() throws IOException {
        Assertions.assertFalse(PolicyVerifier.verifyPolicy("/example1.json"),
                "The policy in '/example1.json' should be invalid because it contains a Resource with '*'.");
    }

    @Test
    public void testExample2JsonPolicyValidity() throws IOException {
        Assertions.assertTrue(PolicyVerifier.verifyPolicy("/example2.json"),
                "The policy in '/example2.json' should be valid as it does NOT contain a Resource with '*'.");
    }

    // Will return true if the JSON is empty or malformed since it doesn't contain a Resource with "*"
    @Test
    public void testExample3JsonPolicyValidity() throws IOException {
        Assertions.assertNotNull(getClass().getResource("/example3.json"),
                "The '/example3.json' resource should exist.");
        Assertions.assertTrue(PolicyVerifier.verifyPolicy("/example3.json"),
                "The policy in '/example3.json' should be valid as empty or malformed JSON is NOT considered invalid.");
    }

    // Example doesn't contain the Resource, so it can't contain "*"
    @Test
    public void testExample4JsonPolicyValidity() throws IOException {
        Assertions.assertNotNull(getClass().getResource("/example4.json"),
                "The '/example4.json' resource should exist.");
        Assertions.assertTrue(PolicyVerifier.verifyPolicy("/example4.json"),
                "The policy in '/example4.json' should be valid as it does NOT contain a Resource with '*'.");
    }

    @Test
    public void testNonExistentJsonFile() {
        Assertions.assertThrows(IllegalStateException.class, () -> {
            PolicyVerifier.verifyPolicy("/notexisting.json");
        }, "Attempting to verify a not existing JSON file should throw an IllegalStateException.");
    }
}
