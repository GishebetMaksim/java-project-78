package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StringSchemaTest {

    StringSchema schema;

    @BeforeEach
    public void beforeEach() {
        schema = new Validator().string();
    }

    @Test
    public void noValidationTest() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid("Hi"));
    }

    @Test
    public void requiredTest() {
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid("Hello"));
    }

    @Test
    public void minLengthTest() {
        schema.minLength(5);
        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid("Hi"));
        assertTrue(schema.isValid("Hello"));
    }

    @Test
    public void containsTest() {
        schema.contains("Hi");
        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid("Hello, World!"));
        assertTrue(schema.isValid("Hi, World!"));

    }

    @Test
    public void allValidationsTest() {
        schema.required().minLength(5).contains("Hello");
        assertTrue(schema.isValid("Hello, World!"));
    }
}
