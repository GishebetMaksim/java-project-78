package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    NumberSchema schema;

    @BeforeEach
    public void beforeEach() {
        schema = new Validator().number();
    }

    @Test
    public void noValidationTest() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(-10));
        assertTrue(schema.isValid(10));
    }

    @Test
    public void requiredTest() {
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(-10));
        assertTrue(schema.isValid(10));
    }

    @Test
    public void positiveTest() {
        schema.positive();
        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(10));
    }

    @Test
    public void containsTest() {
        schema.range(-10, 10);
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(-10));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-11));
        assertFalse(schema.isValid(11));
    }

    @Test
    public void allValidationsTest() {
        schema.required().positive().range(-10, 10);
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(null));
    }
}
