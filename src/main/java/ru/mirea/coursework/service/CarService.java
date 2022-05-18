package ru.mirea.coursework.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.coursework.entity.Car;
import ru.mirea.coursework.entity.Client;
import ru.mirea.coursework.repository.CarRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void create(Car car) {
        carRepository.save(car);
    }

    public List<Car> readAll() {
        return carRepository.findAll();
    }

    public Optional<Car> findByCarId(Long id) {
        return carRepository.findById(id);
    }

    public List<Car> findByClient(Client client) {
        return (List<Car>) carRepository.findAllByClient(client);
    }

    public Long deleteByClient(Client client) {
        return carRepository.deleteByClient(client);
    }

    public void delete(long id) {
        carRepository.deleteById(id);
    }

    public List<Car> findByClientId(int id) {
        return (List<Car>) carRepository.findByClientId(id);
    }
}
