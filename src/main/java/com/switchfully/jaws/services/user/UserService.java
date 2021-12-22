package com.switchfully.jaws.services.user;

import com.switchfully.jaws.Exceptions.ObjectAlreadyExist;
import com.switchfully.jaws.domain.User;
import com.switchfully.jaws.repositories.UserRepository;
import com.switchfully.jaws.services.user.dto.CreateUserDto;
import com.switchfully.jaws.services.user.dto.UserDto;
import com.switchfully.jaws.services.user.dto.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper = new UserMapper();


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public UserDto addUser(CreateUserDto createUserDto) {
        User user = userMapper.toUser(createUserDto);
        if (getAllUser().contains(user)) {
            throw new ObjectAlreadyExist("User" + createUserDto.toString());
        }

        User userOut = userRepository.save(user);
        UserDto userDto = userMapper.toUserDto(userOut);

        return userDto;
    }
}
