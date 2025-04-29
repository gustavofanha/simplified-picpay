package dev.fanha.simplified_picpay.repository;

import dev.fanha.simplified_picpay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByDocumentOrEmail(String document, String email);
}
