package com.ttps.cuentasclaras.service;

import com.ttps.cuentasclaras.dto.UserAltDTO;
import com.ttps.cuentasclaras.dto.UserContactDTO;
import com.ttps.cuentasclaras.model.User;
import com.ttps.cuentasclaras.model.UserContact;
import com.ttps.cuentasclaras.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserContactService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public List<UserAltDTO> getUserContacts(Integer userId) {
        Optional<User> searchedUser = userRepository.findById(userId);
        User user = searchedUser.orElse(null);
        if (user != null) {
            Set<UserContact> contacts = user.getContacts();
            List<UserAltDTO> response = new ArrayList<>();
            for (UserContact contact : contacts) {
                if (contact.getContactSince() != null) {
                    response.add(userService.mapUserAlt(contact.getUser()));
                }
            }
            return response;
        }
        return null;
    }

    public List<UserAltDTO> searchUsers(Integer userId, String searchString) {
        User searchedUser = userRepository.findById(userId).orElse(null);
        if (searchedUser != null) {
            List<User> searchResults = userRepository.searchUsers(searchString, userId);
            List<UserAltDTO> response = new ArrayList<>();
            for (User user : searchResults) {
                response.add(userService.mapUserAlt(user));
            }
            return response;
        }
        return null;
    }
}
