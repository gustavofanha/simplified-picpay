package dev.fanha.simplified_picpay.entity;

import dev.fanha.simplified_picpay.controller.dto.UserRequestDTO;
import dev.fanha.simplified_picpay.controller.dto.UserResponseDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String password;

    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @CreationTimestamp
    private LocalDateTime creationTimestamp;

    public User() {}

    public User(UserRequestDTO userDTO) {
        this.fullName = userDTO.fullName();
        this.document = userDTO.document();
        this.email = userDTO.email();
        this.password = userDTO.password();
        this.balance = userDTO.balance();
        this.userType = userDTO.userType();
    }

    public UserResponseDTO toDto() {
        return new UserResponseDTO(
                this.id,
                this.fullName,
                this.email,
                this.balance,
                this.userType,
                this.creationTimestamp);
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDocument() {
        return document;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public UserType getUserType() {
        return userType;
    }

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
