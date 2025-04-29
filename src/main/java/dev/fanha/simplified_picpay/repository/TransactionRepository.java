package dev.fanha.simplified_picpay.repository;

import dev.fanha.simplified_picpay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<User, Long> {
}
