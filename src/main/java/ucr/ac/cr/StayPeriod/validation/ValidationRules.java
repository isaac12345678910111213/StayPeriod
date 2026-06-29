package ucr.ac.cr.StayPeriod.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ValidationRules {
    private final List<String> errors = new ArrayList<>();
    private final String value;

    private ValidationRules(String value) {
        this.value = value;
    }

    public static ValidationRules of(String value) {
        return new ValidationRules(value);
    }

    public ValidationRules notBlank(String message) {
        if (value == null || value.trim().isEmpty()) {
            errors.add(message);
        }
        return this;
    }

    public ValidationRules pattern(String regex, String message) {
        if (errors.isEmpty() && value != null) {
            if (!Pattern.matches(regex, value)) {
                errors.add(message);
            }
        }
        return this;
    }

    public ValidationRules minLength(int min, String message) {
        if (errors.isEmpty() && value != null) {
            if (value.length() < min) {
                errors.add(message);
            }
        }
        return this;
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public List<String> getErrors() {
        return errors;
    }
}
