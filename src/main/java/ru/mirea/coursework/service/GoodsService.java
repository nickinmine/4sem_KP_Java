package ru.mirea.coursework.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.coursework.entity.Goods;
import ru.mirea.coursework.repository.GoodsRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class GoodsService {
    @Autowired
    private GoodsRepository goodsRepository;

    public GoodsService(GoodsRepository goodsRepository) {
        this.goodsRepository = goodsRepository;
    }

    public void create(Goods goods) {
        goodsRepository.save(goods);
    }

    public List<Goods> readAll() {
        return goodsRepository.findAll();
    }

    public Goods findByName(String name) {
        return goodsRepository.findByName(name);
    }

    public void delete(String name) {
        goodsRepository.deleteByName(name);
    }
}
