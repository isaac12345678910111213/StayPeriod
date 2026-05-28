package ucr.ac.cr.StayPeriod.model.DTO;

public class UserDTO {
    private Integer user_id;
    private String user_name;
    private String user_rol;

    public UserDTO() {
    }

    public UserDTO(Integer id, String name, String rol) {
        this.user_id = id;
        this.user_name = name;
        this.user_rol = rol;
    }

    public Integer getId() {
        return user_id;
    }

    public void setId(Integer id) {
        this.user_id = id;
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

    @Override
    public String toString() {
        return "Usuario: " +
                "\n user_id: " + user_id +
                "\n user_name: " + user_name +
                "\n user_rol: " + user_rol
                ;
    }
}
