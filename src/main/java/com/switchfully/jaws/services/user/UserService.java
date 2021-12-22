package com.switchfully.jaws.services.user;

import com.switchfully.jaws.domain.User;
import com.switchfully.jaws.repositories.UserRepository;
import com.switchfully.jaws.services.user.dto.UserDto;
import com.switchfully.jaws.services.user.dto.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;

        this.userMapper = userMapper;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public String getAllMembersOverview() {
        //check if user role = manager
        StringBuilder membersoverview = new StringBuilder();
        String overview = "Overview of all members: \n";

        for (User user : userRepository.findAll()) {
            membersoverview.append("___________\n");
            membersoverview.append(user.getId())
                    .append(" ").append(user.getFirstName()).append(" ").append(user.getLastName()).append("\n")
                    .append(user.getLicensePlate().replaceAll("\\D+", "")).append("\n")
                    .append(user.getContactInformation().getHomePhoneNumber()).append("\n")
                    .append(user.getContactInformation().getEmailAddress()).append("\n")
                    .append(user.getRegistrationDate())
                    .append("\n___________\n");
        }

        return overview + membersoverview;
    }
}
