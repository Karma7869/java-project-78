package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        addValidation(value -> value != null);
        return this;
    }

    public NumberSchema positive() {
        addValidation(value -> value == null || value > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        addValidation(value -> value == null || (value >= min && value <= max));
        return this;
    }
}
