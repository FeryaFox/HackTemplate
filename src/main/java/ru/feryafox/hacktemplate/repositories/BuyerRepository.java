package ru.feryafox.hacktemplate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.feryafox.hacktemplate.entities.Buyer;

import java.util.Optional;
import java.util.UUID;

public interface BuyerRepository extends JpaRepository<Buyer, UUID> {
    @Override
    Optional<Buyer> findById(UUID uuid);

    Optional<Buyer> findByUser_PhoneNumber(String phoneNumber);
}