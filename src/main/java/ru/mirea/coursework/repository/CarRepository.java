package ru.mirea.coursework.repository;

import ru.mirea.coursework.entity.Car;
import ru.mirea.coursework.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findById(long id);
    Car findAllByClient(Client client);

    Car findByClientId(int id);
    Long deleteByClient(Client client);
    Long deleteById(long id);
}
