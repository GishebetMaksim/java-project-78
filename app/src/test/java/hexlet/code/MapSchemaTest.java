package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class MapSchemaTest {
    private Validator v;
    private MapSchema schema;
    private HashMap<String, String> map = new HashMap<>();

    @BeforeEach
    public void beforeEach() {
        v = new Validator();
        schema = v.map();
        map.put("firstName", "John");
        map.put("lastName", "Smith");
    }

    @Test
    public void noValidationTest() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
        assertTrue(schema.isValid(map));
    }

    @Test
    public void requiredTest() {
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
        assertTrue(schema.isValid(map));
    }

    @Test
    public void sizeofTest() {
        schema.sizeof(2);
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(map));
        assertFalse(schema.isValid(new HashMap<>()));
    }

    @Test
    public void nestedValidationTest() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));
        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3));
    }
}
