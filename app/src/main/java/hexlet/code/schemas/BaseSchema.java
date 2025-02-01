package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    // Список проверок
    private final List<Predicate<T>> validations = new ArrayList<>();

    public void addValidation(Predicate<T> validation) {
        validations.add(validation);
    }

    public boolean isValid(Object value) {
        for (Predicate<T> validation : validations) {
            if (!validation.test((T) value)) {
                return false;
            }
        }
        return true;
    }

    public BaseSchema<T> required() {
        addValidation(value -> value != null); // Базовая проверка на null
        return this;
    }
}

