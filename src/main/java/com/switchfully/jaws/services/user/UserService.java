package com.switchfully.jaws.services.user;

import com.switchfully.jaws.Exceptions.EmailAddressIsInvalidException;
import com.switchfully.jaws.Exceptions.ObjectAlreadyExist;
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
            throw new ObjectAlreadyExist("User" + createUserDto.toString());
        }

        User userOut = userRepository.save(user);
        UserDto userDto = userMapper.toUserDto(userOut);

        return userDto;
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

//    public String selectMemberShipLevel(Long userId, MemberShipLevel memberShipLevel) {
//        Optional<User> optionalUserWithSpecifiedId = getAllUser().stream()
//                .filter(user -> user.getId().equals(userId))
//                .findFirst();
//
//        if(optionalUserWithSpecifiedId.isPresent()){
//            User userWithSpecifiedId = optionalUserWithSpecifiedId.get();
//            userWithSpecifiedId.setMemberShipLevel(memberShipLevel);
//            return "Membership level for user with [ "+userWithSpecifiedId.getId()+" ] ID, has been changed to "+memberShipLevel;
//        }
//        else return "Specified user ID has not been found, please try again.";
//
//    }

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
