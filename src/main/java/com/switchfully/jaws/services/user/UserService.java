package com.switchfully.jaws.services.user;

import com.switchfully.jaws.exceptions.EmailAddressIsInvalidException;
import com.switchfully.jaws.exceptions.ObjectAlreadyExist;
import com.switchfully.jaws.domain.user.User;
import com.switchfully.jaws.repositories.UserRepository;
import com.switchfully.jaws.services.user.dto.CreateUserDto;
import com.switchfully.jaws.services.user.dto.UserDto;
import com.switchfully.jaws.services.user.dto.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.List;
import java.util.Optional;
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

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public UserDto addUser(CreateUserDto createUserDto) {
        checkIfValidEmail(createUserDto.contactInformationDto().emailAddress());
        User user = userMapper.toUser(createUserDto);
        if (getAllUser().contains(user)) {
            throw new ObjectAlreadyExist("User" + createUserDto);
        }

        User userOut = userRepository.save(user);

        return userMapper.toUserDto(userOut);
    }

    public String getAllMembersOverview() {
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


    private void checkIfValidEmail(String email) {
        if (!isValidEmailAddress(email)) {
            throw new EmailAddressIsInvalidException(email);
        }
    }

    private boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}
