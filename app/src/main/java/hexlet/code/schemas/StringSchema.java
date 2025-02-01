package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addValidation(value -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addValidation(value -> value == null || value.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        addValidation(value -> value == null || value.contains(substring));
        return this;
    }
}
