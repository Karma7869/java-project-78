import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorTest {
    @Test
    void testStringValidation() {
        Validator v = new Validator();
        StringSchema schema = v.string();

        assertTrue(schema.isValid("")); // true
        assertTrue(schema.isValid(null)); // true

        schema.required();
        assertFalse(schema.isValid(null)); // false
        assertFalse(schema.isValid("")); // false
        assertTrue(schema.isValid("hexlet")); // true

        schema.minLength(5).contains("hex");
        assertTrue(schema.isValid("hexlet")); // true
        assertFalse(schema.isValid("hex")); // false
    }
}
