package ru.mirea.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.coursework.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String> {
    Long deleteByName(String name);
    Client findByName(String name);
}
