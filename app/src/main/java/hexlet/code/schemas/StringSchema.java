package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addValidation("required", string -> string != null && !string.isEmpty());
        return this;
    }

    public StringSchema minLength(int minLength) {
        addValidation("minLength", string -> string.length() >= minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        addValidation("contains", string -> string.contains(substring));
        return this;
    }
}
