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

    public List<UserDTO> convertListToDTO(List<User> userList) {
        List<UserDTO> dtoList = new ArrayList<>();
        if (userList == null || userList.isEmpty()) {
            return dtoList;
        }
        for (User user : userList) {
            dtoList.add(convertUserToDTO(user));
        }
        return dtoList;
    }

    public List<UserDTO> findAll() {
        return this.convertListToDTO(this.repository.findAll());
    }

    public List<UserDTO> findUserByRol(String rol) {
        List<User> users = this.repository.findUserByRol(rol);
        return this.convertListToDTO(users);
    }

    public UserDTO findById(Integer id) {
        Optional<User> opt = this.repository.findById(id);
        if (opt.isPresent()) {
            return this.convertUserToDTO(opt.get());
        }
        return null;
    }

    public UserDTO findUserByEmail(String email) {
        User user = this.repository.findUserByEmail(email);
        if (user != null) {
            return this.convertUserToDTO(user);
        }
        return null;
    }

    public UserDTO saveUser(User user) {
        User existingUser = this.repository.findByEmail(user.getEmail());
        if (existingUser != null) {
            return null;
        }
        user.setId(null);
        return this.convertUserToDTO(this.repository.save(user));
    }

    public UserDTO updateUserById(Integer id, User updatedUser) {
        Optional<User> opt = this.repository.findById(id);
        if (opt.isPresent()) {
            User foundedUser = opt.get();
            foundedUser.setName(updatedUser.getName());
            foundedUser.setEmail(updatedUser.getEmail());
            foundedUser.setRol(updatedUser.getRol());

            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                foundedUser.setPassword(updatedUser.getPassword());
            }
            this.repository.save(foundedUser);
            return this.convertUserToDTO(foundedUser);
        }
        return null;
    }

    public void deleteUserById(Integer id) {
        this.repository.deleteById(id);
    }

    public User login(String email, String password) {
        return this.repository.verificarCredenciales(email, password);
    }
}