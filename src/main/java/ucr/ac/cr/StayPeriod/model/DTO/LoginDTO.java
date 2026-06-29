package ucr.ac.cr.StayPeriod.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import ucr.ac.cr.StayPeriod.validation.ValidationBuilder;
import ucr.ac.cr.StayPeriod.validation.ValidationRules;

public class LoginDTO {

    @NotBlank(message = "El email es obligatorio")
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Formato de email inválido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;  // @Pattern opcional, validamos manualmente

    // Constructores
    public LoginDTO() {}

    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public ValidationRules validate() {
        return ValidationBuilder.validatePassword(password);
    }

    // Getters y Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
