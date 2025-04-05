package ru.feryafox.hacktemplate.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleName name;

    public enum RoleName {
        ROLE_USER, ROLE_ADMIN;

        public static RoleName fromString(String role) {
            if (role == null) {
                throw new IllegalArgumentException("Role string is null");
            }
            String formattedRole = "ROLE_" + role.trim().toUpperCase();
            try {
                return RoleName.valueOf(formattedRole);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Unknown role: " + role);
            }
        }
    }

    public String getRoleName() {
        return name.name();
    }
}
