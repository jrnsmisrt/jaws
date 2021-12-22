package com.switchfully.jaws.services.user;

import com.switchfully.jaws.Exceptions.ObjectAlreadyExist;
import com.switchfully.jaws.domain.User;
import com.switchfully.jaws.repositories.UserRepository;
import com.switchfully.jaws.services.user.dto.CreateUserDto;
import com.switchfully.jaws.services.user.dto.UserDto;
import com.switchfully.jaws.services.user.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public UserDto addUser(CreateUserDto createUserDto) {
        User user = userMapper.toUser(createUserDto);
        if (getAllUser().contains(user)) {
            throw new ObjectAlreadyExist("User" + createUserDto);
        }

        User userOut = userRepository.save(user);

        return userMapper.toUserDto(userOut);
    }
}
