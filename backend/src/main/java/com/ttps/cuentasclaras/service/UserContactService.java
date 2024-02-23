package com.ttps.cuentasclaras.service;

import com.ttps.cuentasclaras.dto.*;
import com.ttps.cuentasclaras.model.User;
import com.ttps.cuentasclaras.model.UserContact;
import com.ttps.cuentasclaras.repository.UserContactRepository;
import com.ttps.cuentasclaras.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class UserContactService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserContactRepository userContactRepository;

    @Autowired
    UserService userService;

    public List<UserContactResponse> getUserContacts(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            Set<UserContact> contacts = user.getReceivedContactRequests();
            Set<UserContact> sentContacts = user.getSentContactRequests();

            List<UserContactResponse> response = new ArrayList<>();
            for (UserContact contact : contacts) {
                if(contact.getContactSince() != null){
                    response.add(new UserContactResponse(contact.getId(), userService.mapUserAlt(contact.getUser()),
                            contact.getContactSince()));
                }
            }

            for (UserContact contact : sentContacts) {
                if(contact.getContactSince() != null) {
                    response.add(new UserContactResponse(contact.getId(), userService.mapUserAlt(contact.getContact()),
                            contact.getContactSince()));
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

    public InvitationsUserContactDTO sendInvitations(InvitationContactDTO invitationReq) {
        try {
            Set<UserContact> contacts = new HashSet<>();
            for (Integer receiverId : invitationReq.getReceiverListId()) {
                UserContact contact = new UserContact();
                contact.setUser(userService.findUserById(invitationReq.getSenderId()));
                contact.setTimestamp(LocalDate.now());
                contact.setContactSince(null);
                contact.setContact(userService.findUserById(receiverId));
                contacts.add(contact);
            }
            userContactRepository.saveAll(contacts);
            User userUpdated = userService.findUserById(invitationReq.getSenderId());

            List<UserContact> listaEnviadas = userContactRepository.findByUserIdAndContactSinceIsNull(userUpdated.getId());
            List<UserContact> listaRecibidas = userContactRepository.findByContactIdAndContactSinceIsNull(userUpdated.getId());

            return this.mapInvitationsDTO(listaEnviadas, listaRecibidas);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public InvitationsUserContactDTO mapInvitationsDTO(List<UserContact> listaEnviadas,
                                                       List<UserContact> listaRecibidas) {
        List<InvitationUserContactDTO> solicitudesEnviadas = new ArrayList<>();
        List<InvitationUserContactDTO> solicitudesRecibidas = new ArrayList<>();

        for (UserContact contact : listaEnviadas) {
            solicitudesEnviadas.add(
                    new InvitationUserContactDTO(contact.getId(), contact.getTimestamp(), contact.getContactSince(),
                            userService.mapUserAlt(contact.getUser()), userService.mapUserAlt(contact.getContact())));
        }

        for (UserContact contact : listaRecibidas) {
            solicitudesRecibidas.add(
                    new InvitationUserContactDTO(contact.getId(), contact.getTimestamp(), contact.getContactSince(),
                            userService.mapUserAlt(contact.getUser()), userService.mapUserAlt(contact.getContact())));
        }

        return new InvitationsUserContactDTO(solicitudesEnviadas, solicitudesRecibidas);
    }

    public InvitationsUserContactDTO getInvitations(Integer userId) {
        List<UserContact> listaEnviadas = userContactRepository.findByUserIdAndContactSinceIsNull(userId);
        List<UserContact> listaRecibidas = userContactRepository.findByContactIdAndContactSinceIsNull(userId);

        return this.mapInvitationsDTO(listaEnviadas, listaRecibidas);
    }

    public InvitationsUserContactDTO deleteSolicitud(Integer solicitudId, Integer userId) {
        userContactRepository.deleteById(solicitudId);
        return this.getInvitations(userId);
    }

    public InvitationsUserContactDTO acceptSolicitud(Integer solicitudId, Integer userId) {
        UserContact solicitud = userContactRepository.findById(solicitudId).orElse(null);
        if (solicitud != null) {
            solicitud.setContactSince(LocalDate.now());
            userContactRepository.save(solicitud);
            return this.getInvitations(userId);
        }
        return null;
    }

    public InvitationsUserContactDTO deleteContact(Integer userId, Integer userContactId) {
        UserContact contact = userContactRepository.findById(userContactId).orElse(null);
        if (contact != null && (contact.getUser().getId().equals(userId) ||
                contact.getContact().getId().equals(userId))) {
            userContactRepository.delete(contact);
            return this.getInvitations(userId);
        }
        return null;
    }
}
