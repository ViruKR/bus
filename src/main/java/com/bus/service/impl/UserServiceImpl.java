package com.bus.service.impl;


import com.bus.entity.User;
import com.bus.payload.UserDTO;
import com.bus.repository.UserRepository;
import com.bus.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
@Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }





    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        User savedUser = userRepository.save(user);
        UserDTO savedUserDTO = new UserDTO();
        BeanUtils.copyProperties(savedUser, savedUserDTO);
         return userDTO;
    }
    @Override
    public UserDTO findByUsername(String username) {
        return userRepository.findByUsername(username);

    }
    @Override
    public UserDTO loadUserByUsername(String username) {
        UserDTO user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }

    public void deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            // Handle the case where the user with the specified ID does not exist
            throw new UsernameNotFoundException("User with ID " + id + " not found");
        }
    }

}
