package com.example.leadsgen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "sales_figures")
@AllArgsConstructor
@NoArgsConstructor
public class SalesFigures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDate;

    @Column
    private int date;

    @Column
    private int quantity;
}
