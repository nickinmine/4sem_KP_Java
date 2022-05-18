package ru.mirea.coursework.entity;

import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@Data
@JsonIgnoreProperties
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "count", length = 5, nullable = false)
    int count;

    @JoinColumn(name = "goods_id")
    @ManyToOne
    private Goods goods;

    @JoinColumn(name = "client_id")
    @ManyToOne
    private Client client;
}
