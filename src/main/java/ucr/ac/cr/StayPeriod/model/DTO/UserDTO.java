package ucr.ac.cr.StayPeriod.model.DTO;

public class UserDTO {
    private Integer user_id;
    private String user_name;
    private String user_rol;
    private String user_email;

    public UserDTO() {
    }

    public UserDTO(Integer user_id, String user_name, String user_rol, String user_email) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_rol = user_rol;
        this.user_email = user_email;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_rol() {
        return user_rol;
    }

    public void setUser_rol(String user_rol) {
        this.user_rol = user_rol;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    @Override
    public String toString() {
        return "Usuario: " +
                "\n user_id: " + user_id +
                "\n user_name: " + user_name +
                "\n user_email: " + user_email +
                "\n user_rol: " + user_rol;
    }
}