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

    public UserDTO convertUserToDTO (User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUser_name(user.getName());
        userDTO.setUser_rol(user.getRol());
        return userDTO;
    }

    public List<UserDTO> convertListToDTO (List<User> userList){
        List<UserDTO> dtoList = new ArrayList<>();
        if(userList.isEmpty()){
           return null;
        }
        for(User user: userList){
            dtoList.add(convertUserToDTO(user));
            return dtoList;
        }
        return null;
    }

    //metodo para encontrar la lista entera
    public List<UserDTO> findAll (){
        return this.convertListToDTO(this.repository.findAll());
    }

    //metodo para encontrar un usuario por rol
    public UserDTO findUserByRol (String rol){
        return this.convertUserToDTO(this.repository.findUserByRol(rol));
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
        Optional<User> opt = this.repository.findById(user.getId());
        if(opt.isPresent()){
            return null;
        }
        return this.convertUserToDTO(this.repository.save(user));
    }

    //metodo para actualizar el usuario por id
    public UserDTO updateUserById(Integer id, User updatedUser){
        Optional<User> opt = this.repository.findById(id);
        if(opt.isPresent()){
            User foundedUser = opt.get();
            foundedUser.setId(updatedUser.getId());
            foundedUser.setName(updatedUser.getName());
            foundedUser.setEmail(updatedUser.getEmail());
            foundedUser.setRol(updatedUser.getRol());
            this.repository.save(foundedUser);
            return this.convertUserToDTO(foundedUser);
        }
        return null;
    }

    //metodo para eliminar usuarios
    public void deleteUserById(Integer id){
        this.repository.deleteById(id);
    }
}
