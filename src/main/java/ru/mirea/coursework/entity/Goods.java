package ru.mirea.coursework.entity;

import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Data
@JsonIgnoreProperties
@Table(name = "goods")
public class Goods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "price", nullable = false)
    private float price;

    @OneToMany(mappedBy = "goods", fetch = FetchType.EAGER)
    private List<Car> car;

}
