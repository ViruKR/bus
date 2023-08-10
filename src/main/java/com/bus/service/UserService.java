package com.bus.service;


import com.bus.entity.User;
import com.bus.payload.UserDTO;

public interface UserService {
    UserDTO registerUser(UserDTO userDTO);
    UserDTO findByUsername(String username);
    UserDTO loadUserByUsername(String username);
    public void deleteUser(Long id);

}