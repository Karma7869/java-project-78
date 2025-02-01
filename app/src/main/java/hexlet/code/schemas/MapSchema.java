package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<String, Object>> {
    private Map<String, BaseSchema<?>> shapeSchemas;

    // Метод required: значение должно быть не null и быть типом Map
    @Override
    public MapSchema required() {
        addValidation(value -> value != null && value instanceof Map);
        return this;
    }

    // Метод sizeof: проверяет количество элементов в Map
    public MapSchema sizeof(int size) {
        addValidation(value -> value instanceof Map && ((Map<?, ?>) value).size() == size);
        return this;
    }

    // Метод shape: задает схемы для ключей Map
    public MapSchema shape(Map<String, BaseSchema<?>> schemas) {
        this.shapeSchemas = schemas;
        addValidation(value -> validateShape((Map<String, Object>) value));
        return this;
    }

    // Логика проверки значений Map на основе схем
    private boolean validateShape(Map<String, Object> map) {
        if (shapeSchemas == null) {
            return true; // Если схемы не заданы, пропускаем проверку
        }

        for (Map.Entry<String, BaseSchema<?>> entry : shapeSchemas.entrySet()) {
            String key = entry.getKey();
            BaseSchema<?> schema = entry.getValue();

            if (map.containsKey(key)) {
                Object value = map.get(key);

                // Попробуем выполнить явное приведение
                if (!schema.isValid(value)) {
                    return false;
                }
            } else {
                return false; // Если ключ отсутствует в Map
            }
        }
        return true; // Все проверки пройдены
    }
}
