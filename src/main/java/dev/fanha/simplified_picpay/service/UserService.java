package dev.fanha.simplified_picpay.service;

import dev.fanha.simplified_picpay.controller.dto.UserRequestDTO;
import dev.fanha.simplified_picpay.entity.User;
import dev.fanha.simplified_picpay.exception.CredentialsAlreadyRegisteredException;
import dev.fanha.simplified_picpay.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserRequestDTO userDTO) {

        if (userRepository.existsByDocumentOrEmail(userDTO.document(), userDTO.email())) {
            throw new CredentialsAlreadyRegisteredException();
        }

        return userRepository.save(new User(userDTO));
    }
}
