package ucr.ac.cr.StayPeriod.validation;

public class ValidationBuilder {
    // Validación para email
    public static ValidationRules validateEmail(String email) {
        return ValidationRules.of(email)
                .notBlank("El email es obligatorio")
                .pattern(
                        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                        "Formato de email inválido. Ejemplo: usuario@dominio.com"
                );
    }

    // Validación para contraseña
    public static ValidationRules validatePassword(String password) {
        return ValidationRules.of(password)
                .notBlank("La contraseña es obligatoria")
                .minLength(8, "La contraseña debe tener al menos 8 caracteres")
                .pattern(
                        ".*[A-Z].*",
                        "La contraseña debe contener al menos una mayúscula"
                )
                .pattern(
                        ".*[a-z].*",
                        "La contraseña debe contener al menos una minúscula"
                )
                .pattern(
                        ".*\\d.*",
                        "La contraseña debe contener al menos un número"
                )
                .pattern(
                        ".*[@#$%^&+=!¡?¿].*",
                        "La contraseña debe contener al menos un carácter especial (@#$%^&+=!¡?¿)"
                );
    }
}
