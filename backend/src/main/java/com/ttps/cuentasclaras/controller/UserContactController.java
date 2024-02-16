package com.ttps.cuentasclaras.controller;

import com.ttps.cuentasclaras.dto.UserAltDTO;
import com.ttps.cuentasclaras.model.UserContact;
import com.ttps.cuentasclaras.service.UserContactService;
import com.ttps.cuentasclaras.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin
@RequestMapping("/userContacts")
public class UserContactController {

    @Autowired
    UserContactService userContactService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserAltDTO>> getUserContacts(@PathVariable Integer userId) {
        List<UserAltDTO> listResponse = userContactService.getUserContacts(userId);
        return ResponseEntity.ok(listResponse);
    }
}
