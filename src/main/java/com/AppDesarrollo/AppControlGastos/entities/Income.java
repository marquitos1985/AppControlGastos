package com.AppDesarrollo.AppControlGastos.entities;

import com.AppDesarrollo.AppControlGastos.entities.enums.Currency;
import com.AppDesarrollo.AppControlGastos.entities.enums.IncomeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "incomes")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(value = EnumType.STRING)
    private IncomeType incomeType;
    private Double amount;

    @Enumerated(value = EnumType.STRING)
    private Currency currency;
    private LocalDate entryDate;
    private String description;


    /*
    @ManyToMany(targetEntity = CostOrigin.class)
    @JoinTable(name = "income_per_cost_origin",
            joinColumns= @JoinColumn(name="income_id", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="cost_origin_id", referencedColumnName="id"))
    private List<CostOrigin> costOriginList;

     */
    /*
    @ManyToMany(targetEntity = CostOrigin.class, cascade = CascadeType.ALL)
    @JoinTable(name = "income_per_cost_origin",
            joinColumns=
            @JoinColumn(name="cost_origin_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="income_id", referencedColumnName="id"))
    private List<Income> incomeList;


     */

}
