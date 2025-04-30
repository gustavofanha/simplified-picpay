package dev.fanha.simplified_picpay.controller;

import dev.fanha.simplified_picpay.controller.dto.UserRequestDTO;
import dev.fanha.simplified_picpay.controller.dto.UserResponseDTO;
import dev.fanha.simplified_picpay.entity.User;
import dev.fanha.simplified_picpay.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listUsers() {
        var users = userService.listUsers();
        return ResponseEntity.ok(users);
    }
}
