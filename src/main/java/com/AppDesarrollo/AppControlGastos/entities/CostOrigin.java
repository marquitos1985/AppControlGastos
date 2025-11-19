package com.AppDesarrollo.AppControlGastos.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "cost_origins")
public class CostOrigin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="cost_origin_id")
    private List<Cost> costList;

    /*
    @ManyToMany(targetEntity = Income.class, cascade = CascadeType.ALL)
    @JoinTable(name = "category_per_cost_origin",
            joinColumns=
            @JoinColumn(name="cost_origin_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="category_id", referencedColumnName="id"))
    private List<Category> categoryList;


     */






    /*
    @ManyToMany(targetEntity = Income.class, cascade = CascadeType.ALL)
    @JoinTable(name = "income_per_cost_origin",
            joinColumns=
            @JoinColumn(name="cost_origin_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="income_id", referencedColumnName="id"))
    private List<Income> incomeList;


     */

}

