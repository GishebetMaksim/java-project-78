package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        addValidation("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(int size) {
        addValidation("sizeof", map -> map.size() == size);
        return this;
    }

    public <T> MapSchema shape(Map<String, BaseSchema<T>> schemas) {
        addValidation(
                "shape",
                map -> {
                    for (var schema: schemas.entrySet()) {
                        if (map.containsKey(schema.getKey())) {
                            if (!schema.getValue().isValid((T) map.get(schema.getKey()))) {
                                return false;
                            }
                        }
                    }
                    return true;
                }
        );
        return this;
    }
}
