package ru.feryafox.hacktemplate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.feryafox.hacktemplate.entities.Seller;

import java.util.Optional;
import java.util.UUID;

public interface SellerRepository extends JpaRepository<Seller, UUID> {
    @Override
    Optional<Seller> findById(UUID uuid);

    Optional<Seller> findByUser_PhoneNumber(String phoneNumber);
}