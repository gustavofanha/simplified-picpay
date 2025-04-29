package dev.fanha.simplified_picpay.controller;

import dev.fanha.simplified_picpay.controller.dto.UserRequestDTO;
import dev.fanha.simplified_picpay.entity.User;
import dev.fanha.simplified_picpay.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid UserRequestDTO userDTO) {
        var user = userService.createUser(userDTO);
        return ResponseEntity.created(URI.create("/user/" + user.getId())).body(user);
    }
}
