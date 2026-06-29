package ucr.ac.cr.StayPeriod.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucr.ac.cr.StayPeriod.model.DTO.UserDTO;
import ucr.ac.cr.StayPeriod.model.User;
import ucr.ac.cr.StayPeriod.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repository;

    public UserDTO convertUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_id(user.getId());
        userDTO.setUser_name(user.getName());
        userDTO.setUser_email(user.getEmail());
        userDTO.setUser_rol(user.getRol());
        return userDTO;
    }

    public List<UserDTO> convertListToDTO (List<User> userList){
        List<UserDTO> dtoList = new ArrayList<>();
        if(userList == null || userList.isEmpty()){
            return dtoList;
        }
        for(User user: userList){
            dtoList.add(convertUserToDTO(user));
        }
        return dtoList;
    }

    //metodo para encontrar la lista entera
    public List<UserDTO> findAll (){
        return this.convertListToDTO(this.repository.findAll());
    }

    //metodo para encontrar un usuario por rol
    public List<UserDTO> findUserByRol (String rol){
        List<User> users = this.repository.findUserByRol(rol);
        return this.convertListToDTO(users);
    }

    //metodo para encontrar un usuario por id
    public UserDTO findById (Integer id){
        Optional<User> opt = this.repository.findById(id);
        if(opt.isPresent()){
            return  this.convertUserToDTO(opt.get());
        }
        return null;
    }

    //metodo para crear un usuario
    public UserDTO saveUser (User user){
        // Verificar si ya existe el email
        User existingUser = this.repository.findByEmail(user.getEmail());
        if(existingUser != null){
            return null; // Email ya registrado
        }
        // No establece un ID
        user.setId(null);
        return this.convertUserToDTO(this.repository.save(user));
    }

    //metodo para actualizar el usuario por id
    public UserDTO updateUserById(Integer id, User updatedUser){
        Optional<User> opt = this.repository.findById(id);
        if(opt.isPresent()){
            User foundedUser = opt.get();
            foundedUser.setName(updatedUser.getName());
            foundedUser.setEmail(updatedUser.getEmail());
            foundedUser.setRol(updatedUser.getRol());

            // Si viene contraseña, actualizarla
            if(updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()){
                foundedUser.setPassword(updatedUser.getPassword());
            }
            this.repository.save(foundedUser);
            return this.convertUserToDTO(foundedUser);
        }
        return null;
    }

    //metodo para eliminar usuarios
    public boolean deleteUserById(Integer id){
        if(this.repository.existsById(id)){
            this.repository.deleteById(id);
            return true;
        }
        return false;
    }

    // metodo para el login
    public User login (String email, String password){
        return this.repository.verificarCredenciales(email, password);
    }

    public UserDTO findUserByEmail(String email) {
        User user = this.repository.findUserByEmail(email);
        if (user != null) {
            return this.convertUserToDTO(user);
        }
        return null;
    }



}
