package ru.mirea.coursework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.coursework.entity.Goods;

import java.util.List;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, String> {
    Long deleteByName(String productName);
    Goods findByName(String productName);
    List<Goods> findAllById(int i);
}
