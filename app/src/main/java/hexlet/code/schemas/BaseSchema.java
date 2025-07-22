package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private Map<String, Predicate<T>> validations = new HashMap<>();
    protected void addValidation(String key, Predicate<T> validation) {
        this.validations.put(key, validation);
    }

    public boolean isValid(T data) {
        if (data == null) {
            return !validations.containsKey("required");
        }

        for (var validation: validations.entrySet()) {
            if (!validation.getValue().test(data)) {
                return false;
            }
        }
        return true;
    }
}
